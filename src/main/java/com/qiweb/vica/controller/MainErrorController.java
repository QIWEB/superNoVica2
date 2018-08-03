package com.qiweb.vica.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

/**
 * Created by office on 2018/8/3.
 */
@Controller
public class MainErrorController implements ErrorController{

    @Override
    public String getErrorPath() {
        return null;
    }
}
