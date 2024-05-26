package com.cntrlflow.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HtmlController {

    @RequestMapping("/{path:[^\\.]*}")
    public String redirect() {
        log.info("[cntrlflow] forwarded to index.html");
        return "forward:/index.html";
    }
}
