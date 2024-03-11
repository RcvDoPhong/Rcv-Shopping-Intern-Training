package com.shopping.intern.component;

import java.io.File;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.core.io.FileSystemResource;

import com.shopping.intern.repository.customer.ICustomerRepository;
import com.shopping.intern.repository.product.IProductRepository;
import com.shopping.intern.repository.user.IUserRepository;

public class CustomValidation {

    private final String STRING_DATATYPE = "String";

    private final String NUMBER_DATATYPE = "number";

    private IUserRepository userRepo;

    private IProductRepository productRepo;

    private ICustomerRepository customerRepo;

    private String imgStorePath = "/resources/static/user/images";

    public IUserRepository getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public IProductRepository getProductRepo() {
        return productRepo;
    }

    public void setProductRepo(IProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public ICustomerRepository getCustomerRepo() {
        return customerRepo;
    }

    public void setCustomerRepo(ICustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    private String emailValidateRegrex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    private String phoneNumberRegrex = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";

    private String imageExtensions = "png|jpg|jpeg";

    public String getImageExtension() {
        return imageExtensions;
    }

    public void setImageExtension(String imageExtensions) {
        this.imageExtensions = imageExtensions;
    }

    public boolean validateSingleField(
            Map<String, String> validateMap,
            String value,
            String field,
            JSONObject response,
            String id) {
        String[] conditions = validateMap.get(field).split("\\|");
        StringBuilder message = new StringBuilder();
        String fieldUppercase = field.substring(0, 1).toUpperCase() + field.substring(1);

        boolean validateFail = false;
        for (String rule : conditions) {
            String[] condition = rule.split(":"); // "min:String:6 => "min", "String", "6", "is too short"
            switch (condition[0]) {
                case "required":
                    if (String.valueOf(value).equals("")) {
                        validateFail = true;
                    }
                    if (validateFail) {
                        message.append(fieldUppercase + " is empty" + "<br>");
                    }
                    break;

                case "unique":
                    String table = condition[1];
                    String column = condition[2];
                    Object obj = null;
                    if (table.equals("users")) {
                        obj = this.userRepo.find(value, column, Integer.parseInt(id));
                    }

                    if (table.equals("products")) {
                        obj = this.productRepo.find(value, column, id);
                    }

                    if (table.equals("customers")) {
                        obj = this.customerRepo.find(value, column, Integer.parseInt(id));
                    }

                    if (obj != null) {
                        validateFail = true;
                        message.append(fieldUppercase + " is already exists" + "<br>"); // need to optimize
                    }
                    break;

                case "email":
                    String emailValue = String.valueOf(value);
                    Pattern emailValidation = Pattern.compile(emailValidateRegrex, Pattern.CASE_INSENSITIVE);
                    if (!emailValidation.matcher(emailValue).matches()) {
                        validateFail = true;
                        message.append(fieldUppercase + " is invalid" + "<br>"); // need to optimize
                    }
                    break;

                case "min": // need to optimize
                    boolean validateMinFail = validateNumber(condition, String.valueOf(value), "min");
                    if (validateMinFail) {
                        validateFail = true;
                        String limitMin = condition[2];
                        String dataType = condition[1];

                        if (dataType.equals(STRING_DATATYPE)) {
                            message.append(fieldUppercase + " is shorter than " + limitMin + " characters<br>");
                        } else if (dataType.equals(NUMBER_DATATYPE)) {
                            message.append(fieldUppercase + " smaller than " + limitMin + "<br>");
                        }
                    }
                    break;

                case "max": // need to optimize
                    boolean validateMaxFail = validateNumber(condition, String.valueOf(value), "max");
                    if (validateMaxFail) {
                        validateFail = true;
                        String limitMax = condition[2];
                        String dataType = condition[1];

                        if (dataType.equals(STRING_DATATYPE)) {
                            message.append(fieldUppercase + " is more than " + limitMax + " characters<br>");
                        } else if (dataType.equals(NUMBER_DATATYPE)) {
                            message.append(fieldUppercase + " is greater than " + "<br>");
                        }
                    }
                    break;

                case "phone":
                    Pattern phoneValidation = Pattern.compile(phoneNumberRegrex);
                    if (!phoneValidation.matcher(value).matches()) {
                        validateFail = true;
                        message.append("Phone number is invalid" + "<br>"); // need to optimize
                    }
                    break;

                default:
                    break;
            }
        }

        if (validateFail) {
            response.put(field, message.toString());
        }

        return validateFail;
    }

    public boolean validateNumber(String[] condition, String value, String compareType) {
        String dataType = condition[1];
        double dataLength = Double.parseDouble(condition[2]);

        boolean validateFail = false;
        switch (compareType) {
            case "min":
                if ((dataType.equals(STRING_DATATYPE) && value.length() < dataLength)
                        || (dataType.equals(NUMBER_DATATYPE) && Double.parseDouble(value) < dataLength))
                    validateFail = true;
                break;

            case "max":
                if ((dataType.equals(STRING_DATATYPE) && value.length() > dataLength)
                        || (dataType.equals(NUMBER_DATATYPE) && Double.parseDouble(value) > dataLength))
                    validateFail = true;
                break;

            default:
                break;
        }

        return validateFail;
    }

    public boolean validateImage(File image, String imageFileName, JSONObject response) {
        StringBuilder message = new StringBuilder();
        boolean validateFail = false;

        if (image != null) {
            if (image.exists()) {
                String[] imageFileNameTemp = imageFileName.split("\\.");
                int imgExtensionIndex = imageFileNameTemp.length;
                String imageExtensionGet = imageFileNameTemp[imgExtensionIndex - 1];

                if (!imageExtensions.contains(imageExtensionGet)) {
                    message.append("Unsupported image's extension detected");

                    response.put("uploadImage", message);
                    validateFail = true;
                }
            } else {
                message.append("Image was lost during validation");

                response.put("uploadImage", message);
                validateFail = true;
            }
        }

        return validateFail;
    }

    public void handleStoreImage(File image, String imageFileName, String destination) {
        // destination: for example: "/resources/images/"
        String path = new FileSystemResource("src/main").getFile().getAbsolutePath();

        String imagePath = path + imgStorePath + destination + imageFileName;
        File localFile = new File(imagePath);

        try {
            FileUtils.copyFile(image, localFile);
        } catch (Exception e) {
            System.out.println("There is something wrong happened, " + e);
        }
    }
}
