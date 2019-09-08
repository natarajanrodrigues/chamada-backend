package br.gov.bnb.cultura.siscultural3.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @RequestMapping("/home")
    @PreAuthorize("hasRole('ADMIN')")
    public String hello() {
        return "Hello buddy!";
    }
}
