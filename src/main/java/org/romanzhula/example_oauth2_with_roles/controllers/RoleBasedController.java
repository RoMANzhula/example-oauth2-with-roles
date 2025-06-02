package org.romanzhula.example_oauth2_with_roles.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class RoleBasedController {

    @GetMapping("/home")
    public String home() {
        return "Available to all!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Available for ADMIN";
    }

    @GetMapping("/client")
    public String client() {
        return "Available for CLIENT";
    }

    @GetMapping("/vip")
    public String vipClient() {
        return "Available for VIP_CLIENT";
    }

    @GetMapping("/manager")
    public String manager() {
        return "Available for MANAGER";
    }

}
