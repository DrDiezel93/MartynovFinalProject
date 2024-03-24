package mvc;

import java.security.Principal;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import entity.User;
import service.EmailService;
import service.UserService;

@Controller
public class PersonalController {

    private final static Logger logger = Logger.getLogger(LoginController.class.getName());

    @Autowired
    private UserService srv;

    @RequestMapping("/saveinfo")
    public String saveinfo(@RequestParam("src") MultipartFile file, Principal principal, Model model,
            HttpServletRequest request, HttpServletResponse response) throws ParseException {
        User user = srv.getUser(principal.getName());
        if (user==null)
            return "redirect:/logout";
        boolean flag;
        if (!request.getParameter("default_email").isEmpty()) {
            User us = srv.findByEmail(request.getParameter("default_email"));
             user = srv.getUser(principal.getName());

            if ((us != null) && (us.getId() != user.getId())) {
                model.addAttribute("err", "Данный email уже занят");
                User userOk = srv.getUser(principal.getName());
                model.addAttribute("user", userOk);
                return "mypage";
            }
        }
        if (principal.getName().equals(request.getParameter("display_name"))) {
             user = srv.getUser(principal.getName());
            if (user==null)
                return "redirect:/logout";
            flag = srv.updateInfo(request, response, user);
            if (flag) {
                if (!file.isEmpty()) {
                    user.setAvatar(srv.uploadFotoInfo(file, request, response, user));
                }
                if (!request.getParameter("password").isEmpty()) {
                    srv.saveUser(user);
                }
                if (request.getParameter("password").isEmpty()) {
                    srv.saveUserNoPass(user);
                }
            } else {
                model.addAttribute("err", "Введенный email не валиден");
                User userOk = srv.getUser(principal.getName());
                if (userOk==null)
                    return "redirect:/logout";
                model.addAttribute("user", userOk);
                
                return "mypage";
            }
        } else {
             user = srv.getUser(request.getParameter("display_name"));
            if (user == null) {
                user = srv.getUser(principal.getName());
                flag = srv.updateInfo(request, response, user);
                if (flag) {
                    if (!file.isEmpty()) {
                        user.setAvatar(srv.uploadFotoInfo(file, request, response, user));
                    }
                    if (!request.getParameter("password").isEmpty()) {
                        user.setDisplay_name(request.getParameter("display_name"));
                        srv.saveUser(user);
                        model.addAttribute("j_username", user.getDisplay_name());
                        model.addAttribute("j_password", request.getParameter("password"));
                        return "securitylogin";
                    }
                    if (request.getParameter("password").isEmpty()) {
                        user.setDisplay_name(request.getParameter("display_name"));
                        srv.saveUserNoPass(user);
                        model.addAttribute("j_username", user.getDisplay_name());
                        return "securitypass";
                    }
                } else {
                    model.addAttribute("err", "Введенный email не валиден");
                    User userOk = srv.getUser(principal.getName());
                    if (userOk==null)
                        return "redirect:/logout";
                    model.addAttribute("user", userOk);
                    
                    return "mypage";
                }
            } else {
                model.addAttribute("err", "Данный псевдоним уже занят");
                User userOk = srv.getUser(principal.getName());
                if (userOk==null)
                    return "redirect:/logout";
                model.addAttribute("user", userOk);
                
                return "mypage";
            }
        }

        return "redirect:/mypage.html";
    }

}
