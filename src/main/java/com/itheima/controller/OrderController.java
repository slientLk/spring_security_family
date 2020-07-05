package com.itheima.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@Secured({"ROLE_ADMIN","ROLE_ORDER"})  //springsecurity自带注解
//@RolesAllowed({"ROLE_ADMIN","ROLE_ORDER"})  //springsecurity自带注解
//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ORDER')")  //springsecurity自带注解
public class OrderController {
    @RequestMapping("/findAll")
    public String findAll(){
        return "order-list";
    }
}
