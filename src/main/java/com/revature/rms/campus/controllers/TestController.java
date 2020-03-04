package com.revature.rms.campus.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/test")
public class TestController {

    @GetMapping(produces= MediaType.TEXT_PLAIN_VALUE)
    public String test() {return "Test Successful"; }

}