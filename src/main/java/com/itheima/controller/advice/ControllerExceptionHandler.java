package com.itheima.controller.advice;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class ControllerExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv = new ModelAndView();
        //将异常信息放入request中，基本不用
        mv.addObject("errMsg",e.getMessage());
        if (e instanceof AccessDeniedException){
            mv.setViewName("redirect:/403.jsp");
        }else{
            mv.setViewName("redirect:/500.jsp");
        }
        return mv;
    }
}
