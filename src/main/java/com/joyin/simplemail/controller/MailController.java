package com.joyin.simplemail.controller;

import com.joyin.simplemail.config.MailConfig;
import com.joyin.simplemail.pojo.EmailEntity;
import com.joyin.simplemail.pojo.ExceptionUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Properties;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <br/>
 *
 * @author yangchaozheng
 * @date 2019/11/11 10:20
 */
@Controller
@Log
@RequestMapping("/mailapi")
public class MailController {

    @Autowired
    MailConfig mc;

    @GetMapping("/sendMail")
    public String sendMail(String receiver, Model mv) throws IOException {

        EmailEntity email = new EmailEntity();
        email.setReceiver(receiver);
        email.setContent("This is a test for email-send.");
        email.setSubject("TEST SENDING EMAIL");
        try {
            mc.sendSimpleMail(email);
            mv.addAttribute("msg", "发送成功");
        } catch (Exception e) {
            mv.addAttribute("msg", "发送失败：" + ExceptionUtil.getStackTrace(e));
        }

        return "/sendMail";
    }

    @GetMapping("/showSysProp")
    public String showSysProp(Model mv) throws IOException {

        StringBuilder sb = new StringBuilder();
        Properties properties = System.getProperties();
        Enumeration<?> elements = properties.propertyNames();
        while (elements.hasMoreElements()) {
            String key = (String) elements.nextElement();

            sb.append(key).append(": ").append(properties.getProperty(key)).append(System.getProperty("line.separator")).append("<br/>");
        }

        mv.addAttribute("SystemProperties", sb.toString());

        return "/sendMail";
    }


}
