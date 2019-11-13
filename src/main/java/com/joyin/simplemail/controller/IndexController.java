package com.joyin.simplemail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <br/>
 *
 * @author yangchaozheng
 * @date 2019/11/11 11:29
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "/sendMail";
    }
}
