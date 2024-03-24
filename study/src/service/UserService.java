package service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import entity.Role;
import entity.User;
import validation.EmailExistsException;
import validation.EmailNotValidException;
import validation.LoginExistsException;

public interface UserService {

    User getUser(Long id);
 
    User getUser(String login);

    User findByEmail(String email);

    List<User> getUsers();

    void deleteUser(Long id);

    void saveUser(User user);

    void saveUserNoPass(User user);

    Role getRole(String name);

    List<Role> getRoles();

    User createUserAccount(User account) throws EmailExistsException, LoginExistsException, EmailNotValidException;

    boolean updateInfo(HttpServletRequest request, HttpServletResponse response, User user) throws ParseException;

    String uploadFotoInfo(MultipartFile file, HttpServletRequest request, HttpServletResponse response, User user);

    List<User> adminFindUsers(HttpServletRequest request);
    
    void add_role(HttpServletRequest request);

}
