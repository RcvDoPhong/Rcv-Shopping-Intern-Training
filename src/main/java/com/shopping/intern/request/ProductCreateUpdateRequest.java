package com.shopping.intern.request;

import java.util.HashMap;
import java.util.Map;

public class ProductCreateUpdateRequest {

    private Map<String, String> validateMap = new HashMap<>();

    public Map<String, String> getValidateMap() {
        return validateMap;
    }

    public void setValidateMap(Map<String, String> validateMap) {
        this.validateMap = validateMap;
    }

    public ProductCreateUpdateRequest() {
        this.validateMap.put("name", "required|min:String:5|max:String:100|unique:products:product_name");
        this.validateMap.put("price", "min:number:0"); // thêm validate type
        this.validateMap.put("description", "required");
        this.validateMap.put("status", "required");
    }
}
