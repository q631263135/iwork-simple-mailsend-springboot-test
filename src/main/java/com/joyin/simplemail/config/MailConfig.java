package com.joyin.simplemail.config;

import com.joyin.simplemail.pojo.EmailEntity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * <br/>
 *
 * @author yangchaozheng
 * @date 2019/11/11 10:13
 */
@Component
@PropertySource({"classpath:mail.properties"})
public class MailConfig {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.sendFrom}")
    private String sendFrom;

    @Value("${mail.sendName}")
    private String sendName;
    
    @Value("${isqqmail}")
    private boolean isqqmail;

    @Bean(name = "JavaMailSenderImpl")
    public JavaMailSenderImpl getMailSender() throws IOException {
        Properties properties = new Properties();
        ClassPathResource resource = new ClassPathResource("mail.properties");
        InputStream resourceInputStream = resource.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(resourceInputStream);
        properties.load(new BufferedReader(inputStreamReader));

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setJavaMailProperties(properties);

        if (isqqmail) {
            Session qqAuthSession = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, "yzzphxmztpukbbfd");
                }
            });
            javaMailSender.setSession(qqAuthSession);
        }

        return javaMailSender;
    }

    public void sendSimpleMail(EmailEntity email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(username);
        String receiver = email.getReceiver();
        simpleMailMessage.setTo(receiver);
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setText(email.getContent());
        try {
            this.getMailSender().send(simpleMailMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
