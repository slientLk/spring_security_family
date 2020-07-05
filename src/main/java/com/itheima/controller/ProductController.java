package com.itheima.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
//@RolesAllowed({"ROLE_ADMIN","ROLE_PRODUCT"})  //JSR-250注解
//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PRODUCT')")  //spring表达式注解
@Secured({"ROLE_ADMIN","ROLE_PRODUCT"})  //springsecurity自带注解
public class ProductController {
    @RequestMapping("/findAll")
    public String findAll(){
        return "product-list";
    }
}
