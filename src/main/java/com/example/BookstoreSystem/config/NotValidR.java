package com.example.BookstoreSystem.config;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSONException;
import com.example.BookstoreSystem.Excpetion.CheckFailException;
import com.example.BookstoreSystem.bean.Result;
import com.example.BookstoreSystem.util.LogUtil;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@Data
public class NotValidR {
    private String errCode;
    private String errMsg;
    private static final Log log = LogFactory.get();
    @ExceptionHandler(Exception.class)
    public NotValidR notValidR( Exception e) {
        log.error(e.getMessage());
        NotValidR notValid = new NotValidR();
        notValid.setErrCode("333");
        notValid.setErrMsg("未知错误");
        return notValid;
    }


    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public NotValidR notValidR(MethodArgumentNotValidException e)
    {
        log.error(e.getMessage());
        NotValidR notValid = new NotValidR();
        notValid.setErrCode("400");
        notValid.setErrMsg("参数错误"+e.getFieldError().getDefaultMessage());
        return notValid;
    }
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public NotValidR notValidR(BindException e)
    {
        log.error(e.getMessage());
        NotValidR notValid = new NotValidR();
        notValid.setErrCode("400");
        notValid.setErrMsg(e.getBindingResult().getFieldError().getDefaultMessage());
        return notValid;
    }

    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    public NotValidR NotValidR(AuthenticationException e){

        log.error(e.getMessage());
        NotValidR notValid = new NotValidR();
        notValid.setErrCode("402");
        notValid.setErrMsg("token失效");
        return notValid;
    }

    @ResponseBody
    @ExceptionHandler(org.apache.shiro.authz.AuthorizationException.class)
    public NotValidR NotValidR(AuthorizationException e){

        LogUtil.warn("没有权限 e:{}",e.getMessage());
        NotValidR notValid = new NotValidR();
        notValid.setErrCode("403");
        notValid.setErrMsg("未登录,没有权限");

        return notValid;
    }

    //拦截未授权页面
//    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnauthorizedException.class)
    public NotValidR NotValidR(UnauthorizedException e) {
        log.error(e.getMessage());
        NotValidR notValid = new NotValidR();
        notValid.setErrCode("403");
        notValid.setErrMsg("没有授权");
        return notValid;
    }

    @ExceptionHandler({FileSizeLimitExceededException.class, MaxUploadSizeExceededException.class})
    public Result FileSizeLimitExceededException(Exception e) {
        log.error(e.getMessage());
        Result result = new Result();
        result.setErrCode(500);
        result.setErrMsg("文件大小超出限制");
        return result;
    }


    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        log.error(e.getMessage());
        Result fail = Result.fail("未知错误");
        return fail;
    }


    @ExceptionHandler({CheckFailException.class, MissingServletRequestParameterException.class})
    public Result CheckFailException(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        LogUtil.warn("参数校验失败,{}",e.getMessage());
//        e.printStackTrace();
        return new Result().setErrCode(403).setErrMsg("参数校验失败");
    }


    @ExceptionHandler({UnknownAccountException.class})
    public Result UnknownAccountException(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        log.error(e.getMessage());
        return new Result().setErrCode(402).setErrMsg("未知用户");
    }

    @ExceptionHandler(JSONException.class)
    public Result JSONException(HttpServletRequest request, final Exception e, HttpServletResponse response) {
//        System.out.println("JSON转换失败-------------------------");
        log.error("JSON转换失败 {}",e.getMessage());
        return new Result().setErrCode(403).setErrMsg("请使用json格式传递参数");

    }

    @ResponseBody
    @ExceptionHandler(UnauthenticatedException.class)
    public Result UnauthenticatedException(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        e.printStackTrace();
        return new Result().setErrCode(403).setErrMsg("未登录");
    }


    public Result MissingRequestHeaderException(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        e.printStackTrace();
        return new Result().setErrCode(403).setErrMsg("权限校验失败,你没有权限执行该操作");
    }

}