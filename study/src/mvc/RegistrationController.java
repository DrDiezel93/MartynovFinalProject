package mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import service.UserService;
import validation.EmailExistsException;
import validation.EmailNotValidException;
import validation.LoginExistsException;

@Controller
public class RegistrationController {
    @Autowired
    private UserService srv;

    @Autowired
    private UserDetailsService uds;

    @Autowired
    RequestCache requestCache;

   /* @Autowired
    UserSecurityService uss;*/

    private final static Logger logger = Logger.getLogger(LoginController.class.getName());

    @RequestMapping("/signup")
    public String signup(Model model) {
        entity.User user = new entity.User();
        model.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping("/reg")
    public String registerUserAccount(@ModelAttribute("user") @Valid entity.User account, BindingResult result,
            Model model, HttpServletRequest request, HttpServletResponse response)
            throws EmailExistsException, LoginExistsException {
        logger.debug("Registering user account with information: {}" + account);
        entity.User registered = new entity.User();
        if (!result.hasErrors()) {
            if ((!account.getDisplay_name().isEmpty()) && (!account.getReal_name().isEmpty())
                    && (!account.getPassword().isEmpty())) {
                registered = detectEmail(account);
            } else {
                model.addAttribute("email", "Заполните все данные!");
                return "signup";
            }
        }
        if (registered == null) {
            model.addAttribute("email", "Существует аккаут с таким же эл.адресом : " + account.getDefault_email());
            return "signup";
        } else if (registered.getDisplay_name().equals("LoginExistsException") == true) {
            model.addAttribute("email", "Данный логин уже зянат: " + account.getDisplay_name());
            return "signup";
        } else if (registered.getDisplay_name().equals("EmailNotValidException") == true) {
            model.addAttribute("email", "Введенный email не валиден: " + account.getDefault_email());
            return "signup";
        }
        model.addAttribute("j_username", registered.getDisplay_name());
        model.addAttribute("j_password", registered.getPassword());
        return "security";
    }

    private entity.User detectEmail(final entity.User account) {
        entity.User registered = null;
        try {
        	logger.debug("Creating user account with information: {}" + account);
            registered = srv.createUserAccount(account);
        } catch (final EmailExistsException e) {
            return null;
        } catch (LoginExistsException e) {
            registered = new entity.User();
            registered.setDisplay_name("LoginExistsException");
            return registered;
        } catch (EmailNotValidException e) {
            registered = new entity.User();
            registered.setDisplay_name("EmailNotValidException");
            return registered;
        }
        return registered;
    }
}
