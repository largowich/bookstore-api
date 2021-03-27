package com.devalgas.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author devalgas on 27/03/2021. 10:37
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
