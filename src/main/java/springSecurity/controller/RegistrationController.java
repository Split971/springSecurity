package springSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springSecurity.entity.User;
import springSecurity.service.UserService;
import springSecurity.user.CrmUser;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

@Validated
@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(getClass().getName());

    private Map<String, String> roles;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model model) {

        model.addAttribute("crmUser", new CrmUser());
        model.addAttribute("roles", roles);

        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("crmUser") CrmUser crmUser, BindingResult bindingResult, Model model) {

        String userName = crmUser.getUserName();
        logger.info("Processing registration form for: " + userName);

        if (bindingResult.hasErrors()) {
            model.addAttribute("crmUser", new CrmUser());
            model.addAttribute("roles", roles);
            model.addAttribute("registrationError", "User name/password can not be empty.");
            logger.warning("User name/password can not be empty.");
            return "registration-form";
        }

        User existing = userService.findByUserName(userName);
        if (existing != null) {
            model.addAttribute("crmUser", new CrmUser());
            model.addAttribute("roles", roles);
            model.addAttribute("registrationError", "User name already exists.");

            logger.warning("User name already exists.");
            return "registration-form";
        }

        userService.save(crmUser);

        logger.info("Successfully created user: " + userName);

        return "registration-confirmation";
    }

    @PostConstruct
    protected void loadRoles() {

        roles = new LinkedHashMap<>();

        roles.put("ROLE_EMPLOYEE", "Employee");
        roles.put("ROLE_MANAGER", "Manager");
        roles.put("ROLE_ADMIN", "Admin");
    }
}
