package service;

import entity.User;

public interface EmailService {

    void sendRecoveryPassword(String emailto, String message);

    void AdminDelUser(User user);

}
