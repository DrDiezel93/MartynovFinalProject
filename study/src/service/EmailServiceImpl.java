package service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import entity.User;

public class EmailServiceImpl implements EmailService {

    private final static Logger logger = Logger.getLogger(EmailService.class.getName());

    private static final long serialVersionUID = 1L;

    @Autowired
    private MailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendRecoveryPassword(final String emailto, String message) {

        
          SimpleMailMessage email = new SimpleMailMessage();
          email.setTo(emailto); 
          email.setSubject("support.study.ru");
          email.setText("Ваш новый пароль: " + message +
          ". Рекомендуем сменить в личном кабинете пользователя.");
          mailSender.send(email);
          
         

    }
   
    @Override
    public void AdminDelUser(User user) {
        
         SimpleMailMessage email = new SimpleMailMessage();
         email.setTo(user.getDefault_email());
          email.setSubject("support.study.ru"); email.setText(
          "Администрация сайта удалила ваш аккаунт на Study.ru. Желаем всего Вам наилучшего!"
          ); mailSender.send(email);
         

    }

}
