package com.example.BookstoreSystem.publicController;

import cn.hutool.captcha.LineCaptcha;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.BookstoreSystem.Excpetion.MyTokenException;
import com.example.BookstoreSystem.bean.ResCode;
import com.example.BookstoreSystem.bean.Result;
import com.example.BookstoreSystem.client.entity.AdvertiseType;
import com.example.BookstoreSystem.client.entity.Booktype;
import com.example.BookstoreSystem.client.entity.Role;
import com.example.BookstoreSystem.client.service.IAdvertiseTypeService;
import com.example.BookstoreSystem.client.service.IBooktypeService;
import com.example.BookstoreSystem.client.service.IRoleService;
import com.example.BookstoreSystem.client.service.IUserService;
import com.example.BookstoreSystem.util.JwtUtil;
import com.example.BookstoreSystem.util.RedisUtils;
import com.example.BookstoreSystem.util.TokenUtil;
import com.example.BookstoreSystem.util.VerificationCodeUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static com.example.BookstoreSystem.util.Constan2.VerificationCode_Count;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName apiController.java
 * @Description TODO
 * @createTime 2022年04月20日 10:41:00
 */
@RestController
@RequestMapping("/api")
public class apiController {
    public static String VerificationCode = "VerificationCode";
    Result result;


    @Resource
    IRoleService roleService;

    @Resource
    IAdvertiseTypeService advertiseTypeService;

    @Resource
    IBooktypeService bookTypeService;


    //getAllRole
    @GetMapping("/getAllRoles")
    public Result getAllRole() {
        List<Role> roles = roleService.queryAllRoles();
        if (roles.size() == 0) {
            return new Result(ResCode.ROLE_NOT_EXIST);
        }
        return new Result(ResCode.SUCCESS, roles);
    }

    @CrossOrigin
    @GetMapping("/getOptionConfig")
    public Result getOptionConfig() {

        try {
            List<Role> roles = roleService.queryAllRoles();
            List<AdvertiseType> advertiseTypeList= advertiseTypeService.queryAllAdvertiseType();
            List<Booktype> allBookType = bookTypeService.getAllBookType();

            System.out.println(advertiseTypeList);
            for (AdvertiseType advertiseType : advertiseTypeList) {
                System.out.println(advertiseType);
            }
            if (roles.size() == 0||advertiseTypeList.size()==0) {
                return new Result().setErrCode(203).setErrMsg("数据库中没有角色和广告数据");
            }
            JSONObject json = new JSONObject();
            json.put("roles", roles);
            json.put("adTypes", advertiseTypeList);
            json.put("bookTypes", allBookType);
            System.out.println(json);
            return new Result(ResCode.SUCCESS,json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result().setErrCode(203).setErrMsg("获取数据失败");
    }

    @Resource
    private IUserService service;

    private void response401(ServletRequest request, ServletResponse response
            , String msg) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        try {
            // //请求转发401controller
            httpServletRequest.getRequestDispatcher("/rbacManager/401/" + msg).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    //更新token
    @CrossOrigin
    @PostMapping("/flushToken")
    public Result updateToken(HttpServletRequest request,HttpServletResponse response) {
        String token=TokenUtil.getFlushToken(request);
        try {
            JwtUtil.verifyToken(token);
        } catch (TokenExpiredException e) {
            System.out.println("token过期");
            System.out.println("即将刷新token");
        } catch (Exception e) {
            throw new MyTokenException("token错误");
        }


        //是否在黑名单中
        if (RedisUtils.isBlackList(token)) {
            return  Result.failure("token已失效");
        }

        boolean tokenExpired = JwtUtil.isTokenExpired(token);
        if (tokenExpired) {
//            //token过期未超过一天
//            if (JwtUtil.isTokenExpiredDays(token, 1)) {
//                System.out.println("token已经过了刷新时间");
//                return new Result().setErrCode(401).setErrMsg("token已经过了刷新时间,请重新登录");
//            }
////            创建新的token
//            String userName = JwtUtil.getUserName(token);
//            MyUserDetail userDetail = service.getUserDetail(userName);
//            String[] arrayRoles = userDetail.getArrayRoles();
//            String SToken = JwtUtil.createToken(String.valueOf(userDetail.getId()), userName, arrayRoles);

            String SToken = JwtUtil.refreshToken(token);
            if (SToken == null||SToken.equals("")||!StringUtils.hasText(SToken)) {
                return new Result().setErrCode(401).setErrMsg("token已经过了刷新时间,请重新登录");
            }

            TokenUtil.setToken(response, SToken);

            //旧的token移入黑名单
            RedisUtils.addBlackList(token);

            return new Result().setErrCode(200).setErrMsg("token刷新成功").setData(SToken);
        }
        //token未过期
        JSONObject token1 = new JSONObject().fluentPut("token", token);
        return new Result().setErrMsg("token未过期").setErrCode(200).setData(token1);
    }

    /**
     * @param email 邮箱
     * @return 返回示例
     * @title checkEmail
     * @deprecated 查看邮箱是否存在, 利用正则表达式判断邮箱格式是否正确错误则返回错误信息，正确则继续执行判断邮箱是否存在业务
     */
    @CrossOrigin
    @GetMapping("/checkEmailEXIT")
    @Deprecated
    public Result checkEmail(@RequestParam("email") String email) {
        //利用正则表达式判断邮箱格式是否正确
        if (!email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")) {
            return new Result(ResCode.USER_EMAIL_FORMAT_ERROR);
        }
        ResCode resCode = service.checkMail(email);
        return new Result(resCode);
    }


    //查看用户是否存在

    /**
     * @title checkUser
     * @description 查看用户是否存在
     * @author yefeng
     * @Param [userName]
     * @updateTime 2022/4/21 19:07
     */
    @CrossOrigin
    @GetMapping("/checkUserEXIT")
    public Result checkUser(@RequestParam("username") String userName) {
        ResCode resCode = service.checkUser(userName);
        return new Result(resCode);
    }

    //查看手机号是否存在

    /**
     * @title checkPhone
     * @description 查看查看手机号是否存在
     * @author yefeng
     * @Param
     * @updateTime 2022/4/21 19:08
     */
    @CrossOrigin
    @GetMapping("/checkPhoneEXIT")
    public Result checkPhone(@RequestParam("phone") String phone) {
        ResCode resCode = service.checkPhone(phone);
        return new Result(resCode);
    }

    //从VerificationCodeUtil中获取验证码
    @GetMapping("/getVCodeBase64")
    public Result getVerificationBase64Code(HttpServletRequest request) {
        result = new Result();
        String codeImage;
        try {
            LineCaptcha captcha = VerificationCodeUtil.getCaptcha(VerificationCode_Count);
            String code = captcha.getCode();
            codeImage = captcha.getImageBase64();
            HttpSession session = request.getSession();
            session.removeAttribute("VerificationCode");
            session.setAttribute("VerificationCode", code);
            System.out.println(code);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("获取验证码失败");
        }
        return Result.success("data:image/png;base64," + codeImage);
    }


    @GetMapping("/VCodeImg.jpg")
    @ResponseBody
    public void getVerificationCode(HttpServletRequest request, HttpServletResponse response) {
        result = new Result();
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        try {
            LineCaptcha captcha = VerificationCodeUtil.getCaptcha(VerificationCode_Count);
            String code = captcha.getCode();

            byte[] imageBytes = captcha.getImageBytes();
            HttpSession session = request.getSession();
            session.removeAttribute("VerificationCode");
            session.setAttribute("VerificationCode", code);

            System.out.println(code);
//            System.out.println(image);
            OutputStream out = response.getOutputStream();

            out.write(imageBytes);
            out.flush();
            out.close();
//            ImageIO.write(image, "JPEG", response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
//            return Result.fail("获取验证码失败");
        }
//        return Result.success();
    }

    //从VerificationCodeUtil中获取验证码
//    @RequestMapping("/getVCode")
//    public Result getVerificationCode(HttpServletRequest httpServletRequest){
//        result = new Result();
//        BufferedImage codeImage;
//        try {
//            codeImage = VerificationCodeUtil.getVerificationCodeImage(VerificationCode_Count);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.fail("获取验证码失败");
//        }
//        return Result.success(codeImage);
//    }

    //验证验证码是否正确
    @GetMapping("/checkVCode")
    public Result checkVerificationCode(@RequestParam("code") String code) {
        result = new Result();
        System.out.println("yourCode=" + code);
        String verificationCodeCode = VerificationCodeUtil.getVerificationCodeCode();//获取session中的验证码
        System.out.println(verificationCodeCode);

        boolean pass = VerificationCodeUtil.checkVerificationCode(code, verificationCodeCode);
        if (pass) {
            return Result.success("验证码正确");
        }
        return Result.fail("验证码错误");
    }
}
