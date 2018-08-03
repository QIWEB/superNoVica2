package com.qiweb.vica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by office on 2018/8/2.
 */
@Controller
public class WellcomeController {
    @RequestMapping("/hi")
    public String hello(){
        return "/h/405.html";
    }
    @RequestMapping("/error/*")
    public String error(){
        System.out.print("访问的页面找不到了");
        return "error.html";
    }
    @RequestMapping(value = "/error/{code}")
    public String error(@PathVariable int code, Model model) {
        String pager = "/content/error-pager";
        switch (code) {
            case 404:
                model.addAttribute("code", 404);
                pager = "/content/error-pager";
                break;
            case 500:
                model.addAttribute("code", 500);
                pager = "/content/error-pager";
                break;
        }
        return pager;
    }
}
