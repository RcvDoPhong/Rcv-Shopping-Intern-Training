package com.shopping.intern.controller;

import java.util.List;

import javax.validation.Valid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.shopping.intern.mapper.EmployeeMapper;
import com.shopping.intern.mapper.UserMapper;
import com.shopping.intern.model.User;
import com.shopping.intern.repository.User.IUserRepository;
import com.shopping.intern.request.UserLoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
// @MapperScan("com.shopping.intern.mapper")
public class UserController {

    private final IUserRepository userRepo;
    // private final UserMapper userMapper;

    public UserController(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/dashboard")
    public String index(Model model) {
        model.addAttribute("message", "test");
        return "index";
    }

    @GetMapping("/login")
    public String loginView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("title", "Login");
            // model.addAttribute("adminLoginRequest", new UserLoginRequest());
            return "pages/auth/index";
        }

        return "redirect:/admin/dashboard";
    }

    @PostMapping("/loginProcess")
    public String loginProcess(@ModelAttribute("userLoginRequest") @Valid UserLoginRequest loginRequest, BindingResult result, RedirectAttributes attr, Model model) {
        System.out.println("Value: " + loginRequest);
        if (result.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.loginRequest", result);
            attr.addFlashAttribute("userLoginRequest", loginRequest);
            return "redirect:/admin/login";
        }

        if (this.userRepo.loginUser(loginRequest.getEmail())) {

            User user = this.userRepo.findByEmail(loginRequest.getEmail());
            model.addAttribute("message", user.getEmail());
            return "test";
        }

        model.addAttribute("message", "NO");
        return "test";
    }
}
