package com.imcode.common.controller;


import com.imcode.common.exception.BizException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;



@ControllerAdvice
public class CommonExceptionHandler {
    Logger logger = LogManager.getLogger(CommonExceptionHandler.class);

    /**
     * 自定义业务异常
     */

    @ExceptionHandler(BizException.class)
    public ModelAndView handleBizException(BizException e, HttpServletRequest request) {
        logger.error("操作失败,请联系系统管理员");
        HashMap<String, Object> reponseData = new HashMap<String, Object>();
        reponseData.put("code",1);
        reponseData.put("error","操作失败,请联系系统管理员");
        reponseData.put("msg",e.getMessage()); // 错误的详细信息
        return modelAndView(request,reponseData);
    }

    @ExceptionHandler
    public ModelAndView handlerException(Throwable e,HttpServletRequest request) {
        logger.error("服务器内部错误",e);
        HashMap<String, Object> reponseData = new HashMap<String, Object>();
        reponseData.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value()); // 500
        reponseData.put("error","服务器内部错误");
        reponseData.put("msg",e.getMessage()); // 错误的详细信息
         return modelAndView(request,reponseData);
    }

    private ModelAndView modelAndView (HttpServletRequest request, Map<String,Object> responseData) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(responseData); // 设置模型数据
        if (isAjaxRequest(request)) {
            modelAndView.setView(new MappingJackson2JsonView());
        } else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }
    private static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        if (accept!=null && accept.indexOf("application/json")!=-1) {
            return true;
        }
        String header = request.getHeader("X-Requested-with");
        if (header!=null && header.indexOf("XMLHttpRequest")!=-1) {
            return true;
        }
        return false;
    }
}
