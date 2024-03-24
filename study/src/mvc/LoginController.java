package mvc;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.User;
import service.EmailService;
import service.UserService;

@Controller
public class LoginController {

    private final static Logger logger = Logger.getLogger(LoginController.class.getName());
    
    @Autowired
    private UserService srv;

    @Autowired
    private EmailService esrv;


    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }
    
    @RequestMapping("/security")
    public String security() {
        return "security";
    }

    @RequestMapping("/securitylogin")
    public String securitylogin() {
        return "securitylogin";
    }

    @RequestMapping("/securitypass")
    public String securitypass() {
        return "securitypass";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @RequestMapping("/mypage")
    public String mypage(Principal principal, Model model) {
        User user = srv.getUser(principal.getName());
        if (user==null)
            return "redirect:/logout";
        if (user.getSex() != null) {
            if (user.getSex().equals("Мужской"))
                model.addAttribute("selected", "1");
            else if (user.getSex().equals("Женский"))
                model.addAttribute("selected", "2");
            else
                model.addAttribute("selected", "");
        } else {
            model.addAttribute("selected", "");
        }
        if (user.getSpec() != null) {
            if (user.getSpec().equals("Cпециалист"))
                model.addAttribute("selected_sp", "3");
            else if (user.getSpec().equals("Магистрант"))
                model.addAttribute("selected_sp", "2");
            else if (user.getSpec().equals("Аспирант"))
                model.addAttribute("selected_sp", "1");
            else if (user.getSpec().equals("Бакалавр"))
                model.addAttribute("selected_sp", "0");
            else
                model.addAttribute("selected_sp", "");
        } else {
            model.addAttribute("selected_sp", "");
        }
        System.out.println(user.getBirthday());
        model.addAttribute("user", user);
        model.addAttribute("err", "");
        return "mypage";
    }

    @RequestMapping("/recovery")
    public String recovery(Model model) {
        model.addAttribute("err", "");
        return "recovery";
    }

    @RequestMapping("/recoverypass")
    public String recoverypass(Model model, HttpServletRequest request, HttpServletResponse response) {
        User user = srv.findByEmail(request.getParameter("email"));
        if (user == null) {
            model.addAttribute("err", "Не найдено пользователя с данным электронным ящиком");
            return "recovery";
        } else {
            user.setPassword(String.valueOf(new Date(System.currentTimeMillis()).hashCode()));
            esrv.sendRecoveryPassword(user.getDefault_email(), user.getPassword());
            srv.saveUser(user);
            return "index";
        }
    }

}
