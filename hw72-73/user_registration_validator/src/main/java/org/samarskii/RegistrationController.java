package org.samarskii;

import lombok.extern.slf4j.Slf4j;
import org.samarskii.model.User;
import org.samarskii.validator.ConfirmPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/")
@Controller
@Slf4j
public class RegistrationController {

    Validator confirmPasswordValidator;

    @Autowired
    public RegistrationController(ConfirmPasswordValidator confirmPasswordValidator){
        this.confirmPasswordValidator = confirmPasswordValidator;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/new")
    public String createStudent(@Validated @ModelAttribute(name = "user") User user, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        log.debug(user.toString());
        String message = "";
        confirmPasswordValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            log.debug(bindingResult.toString());
            redirectAttributes.addAttribute("message", "Validation error");
            return "index";
        }

        message = "successfully saved";

        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/";
    }

    @ModelAttribute(name = "user")
    public User getUser() {
        log.debug("getUser()");
        return new User();
    }
}
