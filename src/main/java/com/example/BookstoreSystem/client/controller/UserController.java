package com.example.BookstoreSystem.client.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.BookstoreSystem.bean.ResCode;
import com.example.BookstoreSystem.bean.Result;
import com.example.BookstoreSystem.client.bean.BRole;
import com.example.BookstoreSystem.client.bean.BUser;
import com.example.BookstoreSystem.client.bean.MyUserDetail;
import com.example.BookstoreSystem.client.service.IUserService;
import com.example.BookstoreSystem.util.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 用户控制器
 *
 * @author yefeng
 * @since 2022-04-06
 */
@RestController
public class UserController {

    private Result result;
    /*
        RequestParam处理 	application/x-www-form-urlencoded 请求格式
        @RequestBody 可处理application/json/xml 请求格式
    *
    */


    /**
     * @title userLogin
     * @description 用户登录
     * @author yefeng
     * @Param [username, password, code]
     * @updateTime 2022/4/21 19:06
     * @return: com.example.BookstoreSystem.bean.Result
     */

//    @PostMapping("/userLogin")
//    public Result userLogin(@RequestParam("username") String username, @RequestParam("password") String password,
//                            @PathVariable(value = "code",required = false)String code, HttpServletResponse response) {
    @CrossOrigin
    @PostMapping("/userLogin")
    public Result userLogin(@RequestBody String rjson, HttpServletResponse response) {


        JSONObject jsonObject = JSON.parseObject(rjson);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String loginRole = jsonObject.getString("loginRole");
//        String code = jsonObject.getString("code");

        if (loginRole==null){
            return Result.fail("请选择登录角色");
        }

//        if(code==null){
//            return Result.fail().setErrMsg("请输入验证码");
//        }

        //验证验证码流程
//        boolean pass = VerificationCodeUtil.checkVerificationCode(code);
//        if (!pass) {
//            return Result.fail().setResultCode(ResCode.VERIFICATION_CODE_ERROR);
//        }

        Boolean flag=false;

        //校验用户名和密码，如果校验失败，抛出异常，交由全局异常处理器处理
        CheckUtil.checkUser(username);
        CheckUtil.checkPassword(password);

        MyUserDetail userDetail = service.getUserDetail(username);
        //判断用户是否存在
        if (userDetail == null) {
            return Result.fail().setResultCode(ResCode.USER_NOT_EXIST);
        }
        //校验密码
        if (!userDetail.getPassword().equals(password)) {
            //密码错误逻辑，可以加上错误次数限制，比如超过三次，就必须使用验证码
            return Result.fail().setResultCode(ResCode.USER_PASSWORD_ERROR);
        }
        if (!userDetail.getIsEnable()) {//判断用户是否被启用 true表示启用
            return Result.fail().setResultCode(ResCode.USER_ACCOUNT_LOCKED);
        }
        //登录成功生成token
//       String SToken= JwtUtil.createToken(String.valueOf(userDetail.getId()), username);
        BRole[] bRoles = userDetail.getRoles().toArray(new BRole[userDetail.getRoles().size()]);


        String[] roles = new String[bRoles.length];
        for (int i = 0; i < bRoles.length; i++) {
            roles[i] = bRoles[i].getName();
            //判断用户是否有角色 ROLE_ADMIN_SUPER 包含 ROLE_ADMIN  ROLE_USER 包含 ROLE_USER
            if (roles[i].toUpperCase().contains(loginRole)) {
                flag=true;
            }
        }

        if (!flag){
            return Result.fail("没有权限登录该角色");
        }

        //将用户角色信息存入token中
        String SToken = JwtUtil.createToken(String.valueOf(userDetail.getId()), username, roles);

        //将token写入header
        TokenUtil.setToken(response, SToken);
//        response.setHeader("Authentication", SToken);
        JSONObject json = new JSONObject();
        json.put("token", SToken);
        LogUtil.info("登录成功");
        LogUtil.info("token:" + SToken);
        return Result.success().setResultCode(ResCode.USER_LOGIN_SUCCESS).setData(json);
    }

    @PostMapping("/adminLogin")
    public Result adminLogin(@RequestBody String rjson, HttpServletResponse response) {


        JSONObject jsonObject = JSON.parseObject(rjson);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String loginRole = "ROLE_ADMIN";


//        //验证验证码流程
//        boolean pass = VerificationCodeUtil.checkVerificationCode(code);
//        if (!pass) {
//            return Result.fail().setResultCode(ResCode.VERIFICATION_CODE_ERROR);
//        }

        Boolean flag=false;

        //校验用户名和密码，如果校验失败，抛出异常，交由全局异常处理器处理
        CheckUtil.checkUser(username);
        CheckUtil.checkPassword(password);

        MyUserDetail userDetail = service.getUserDetail(username);
        //判断用户是否存在
        if (userDetail == null) {
            return Result.fail().setResultCode(ResCode.USER_NOT_EXIST);
        }
        //校验密码
        if (!userDetail.getPassword().equals(password)) {
            //密码错误逻辑，可以加上错误次数限制，比如超过三次，就必须使用验证码
            return Result.fail().setResultCode(ResCode.USER_PASSWORD_ERROR);
        }
        if (!userDetail.getIsEnable()) {//判断用户是否被启用 true表示启用
            return Result.fail().setResultCode(ResCode.USER_ACCOUNT_LOCKED);
        }
        //登录成功生成token
//       String SToken= JwtUtil.createToken(String.valueOf(userDetail.getId()), username);
        BRole[] bRoles = userDetail.getRoles().toArray(new BRole[userDetail.getRoles().size()]);


        String[] roles = new String[bRoles.length];
        for (int i = 0; i < bRoles.length; i++) {
            roles[i] = bRoles[i].getName();
            //判断用户是否有角色 ROLE_ADMIN_SUPER 包含 ROLE_ADMIN  ROLE_USER 包含 ROLE_USER
            if (roles[i].toUpperCase().contains(loginRole)) {
                flag=true;
            }
        }

        if (!flag){
            return Result.fail("没有权限登录该角色");
        }

        //将用户角色信息存入token中
        String SToken = JwtUtil.createToken(String.valueOf(userDetail.getId()), username, roles);

        //将token写入header
        TokenUtil.setToken(response, SToken);
//        response.setHeader("Authentication", SToken);
        JSONObject json = new JSONObject();
        json.put("token", SToken);
        LogUtil.info("登录成功");
        LogUtil.info("token:" + SToken);
        return Result.success().setResultCode(ResCode.USER_LOGIN_SUCCESS).setData(json);
    }




    @Resource
    IUserService service;


    /**
     * @title getUserInfo
     * @description 获取用户信息，需要先登录
     * @author yefeng
     * @Param
     * @updateTime 2022/4/21 19:11
     */
//    @RequiresAuthentication//拦截器 需要认证
//    @RequiresRoles(value = {"admin", "user"}, logical = Logical.OR)//拦截器 需要角色
    @GetMapping("/getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
        String authorization = TokenUtil.getToken(request);
        if (authorization == null) {
            return Result.fail("请先登录");
        }
        String username = JwtUtil.getUserName(authorization);

        MyUserDetail userDetail = service.getUserDetail(username);
        if (userDetail == null) {
            return Result.fail("没有获取到用户信息");
        }

        ArrayList<String> roles = new ArrayList<>();

        Set<BRole> roles1 = userDetail.getRoles();
        roles1.forEach(bRole -> roles.add(bRole.getName()));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userInfo", userDetail);
        jsonObject.put("roles", roles);

        LogUtil.info(jsonObject.toJSONString());
        userDetail.roles = null;
        userDetail.permissions = null;
        return Result.success(jsonObject);

    }


    /**
     * @title userRegister
     * @description 用户注册
     * @author yefeng
     * @Param [user]
     * @updateTime 2022/4/21 19:06
     * @return: com.example.BookstoreSystem.bean.Result
     */
    @CrossOrigin
    @PostMapping("/registerUser")
    public Result userRegister(@RequestBody @Validated BUser user) {
        result = new Result();
        CheckUtil.checkUser(user.getName());
        CheckUtil.checkPassword(user.getPassword());
        CheckUtil.checkEmail(user.getMail());
        CheckUtil.checkPhone(user.getPhone());
        ResCode canRegister = service.canRegisterByUserName(user.getName(), user.getPhone(), user.getMail());
        if (canRegister != ResCode.USER_NOT_EXIST) {
            return Result.fail().setResultCode(canRegister);
        }
        ResCode Code = service.register(user);
        return new Result(Code);
    }


    public Result UserAdd(@RequestBody BUser user) {
        CheckUtil.checkUser(user.getName());
        CheckUtil.checkPassword(user.getPassword());
        CheckUtil.checkEmail(user.getMail());
        CheckUtil.checkPhone(user.getPhone());
        ResCode canRegister = service.canRegisterByUserName(user.getName(), user.getPhone(), user.getMail());
        if (canRegister != ResCode.USER_NOT_EXIST) {
            return Result.fail().setResultCode(canRegister);
        }
        ResCode Code = service.register(user);
        return new Result(Code);
    }

    /**
     * hello 示例
     *
     * @param name 示例参数
     * @return 返回示例
     */
    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        return "hello " + name;
    }


    //登出

    /**
     * @title userLogout
     * @description 用户登出
     * @author yefeng
     * @Param
     * @updateTime 2022/4/21 19:12
     */

    @RequestMapping("/logout")
    public Result userLogout(HttpServletRequest request) {
        try {
            //

            LogUtil.info("正在退出");

            String token = TokenUtil.getToken(request);

            RedisUtils.addBlackList(token);//将token放入黑名单

//            SecurityUtils.getSubject().logout();
//            responseMsg.setErrCode(Code_UserLogout_Success);
//            responseMsg.setErrMsg("用户登出成功");
            return new Result(ResCode.USER_LOGOUT_SUCCESS);
        } catch (Exception e) {
//            responseMsg.setErrCode(Code_UserLogout_Failr);
////            responseMsg.setErrMsg("用户登出失败");
//            responseMsg.setErrMsg("用户登出失败 "+e.getMessage());
            return new Result(ResCode.USER_LOGOUT_FAIL).setErrMsg("用户登出失败 " + e.getMessage());
        }
//        return responseMsg.toJsonObject();
    }


    //删除用户
//    @RequiresAuthentication

    /**
     * @title deleteUser
     * @description 删除用户
     * @author yefeng
     * @Param
     * @updateTime 2022/4/21 19:12
     */
    @RequiresPermissions("user:delete")
    @RequestMapping("/deleteUser/{userId}")
    public Result deleteUser(@PathVariable Long userId) {
        try {
            Integer len = service.deleteUser(userId);
            LogUtil.info("len = " + len);
            return new Result(ResCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResCode.DELETE_USER_FAIL);
        }
    }

    //修改用户信息

    /**
     * @title updateUser
     * @description 修改用户信息 用户需要是本人或者是管理员才能修改
     * @author yefeng
     * @Param [user]
     * @updateTime 2022/4/21 19:14
     * @return: com.example.BookstoreSystem.bean.Result
     */
    @RequiresPermissions("user:update")
    @PutMapping("/updateUser")
//    @RequiresRoles(value = {"user:update", "ROLE_ADMIN_SUPPER"}, logical = Logical.OR)
    public Result updateUser(@RequestBody MyUserDetail user) {

        //已经验证过token有删除权限
        //需判断是否是本人或者是管理员
        //如果是管理员，则可以修改所有信息
//        ArrayList roles =JwtUtil.getValueByKey(Authorization, "roles",ArrayList.class);
//        LogUtil.info("roles = " + roles);
////        List<String> strings1 = roles.asList(String.class);
//
//        //如果是其他人，则不能修改
//        assert roles != null;
//        if (!roles.contains("ROLE_ADMIN_SUPPER")&&!Objects.equals(JwtUtil.getUserName(Authorization), user.getUsername())) {
//            //是管理员
//            return new Result().setErrCode(403).setErrMsg("没有权限操作");
//        }


        LogUtil.info("user = " + user);
        try {
            service.updateUser(user);
            return new Result(ResCode.UPDATE_USER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResCode.UPDATE_USER_FAIL);
        }
    }

//    //修改用户密码
//    @RequiresAuthentication
//    @PostMapping("/updateUserPwd")
//    public JSONObject updateUserPwd(String userName, String oldPwd, String newPwd) {
//        MyUserDetail userDetail =(MyUserDetail) SecurityUtils.getSubject().getPrincipal();
//
//        if (!userDetail.getUsername().equals(userName)) {
//            return new ResponseMsg2(400, "用户名不匹配").toJsonObject();
//        }
//        if (userDetail.getPassword().equals(oldPwd)) {
//                    return new ResponseMsg2(400, "旧密码不正确").toJsonObject();
//        }
//
//        ResponseMsg2 responseMsg = new ResponseMsg2();
//        try {
//            service.updateUserPwd(userName,newPwd);
//            responseMsg.setErrCode(200);
//            responseMsg.setErrMsg("修改用户密码成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseMsg.setErrCode(400);
//            responseMsg.setErrMsg("修改用户密码失败");
//        }
//        return responseMsg.toJsonObject();
//    }
//
//    @RequiresAuthentication
//    @PostMapping("/updateUserPwd")
//    public JSONObject updateUserPwd(Long uid, String oldPwd, String newPwd) {
//        MyUserDetail userDetail =(MyUserDetail) SecurityUtils.getSubject().getPrincipal();
//
//        if (!(userDetail.getId()==uid)) {
//            return new ResponseMsg2(400, "用户名不匹配").toJsonObject();
//        }
//        if (userDetail.getPassword().equals(oldPwd)) {
//            return new ResponseMsg2(400, "旧密码不正确").toJsonObject();
//        }
//
//        ResponseMsg2 responseMsg = new ResponseMsg2();
//        try {
//            service.updateUserPwd(uid,newPwd);
//            responseMsg.setErrCode(200);
//            responseMsg.setErrMsg("修改用户密码成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseMsg.setErrCode(400);
//            responseMsg.setErrMsg("修改用户密码失败");
//        }
//        return responseMsg.toJsonObject();
//    }


    //获取所有用户

    /**
     * @title getAllUser
     * @description 获取所有用户
     * @author yefeng
     * @Param []
     * @updateTime 2022/4/21 19:14
     * @return: com.alibaba.fastjson.JSONObject
     */
    @RequiresAuthentication
    @GetMapping("/getAllUser")
    public Result getAllUser() {
        try {
            List<MyUserDetail> list = service.getAllUser();
            return new Result().setErrCode(200).setErrMsg("获取所有用户成功").setData(list);
        } catch (Exception e) {
            e.printStackTrace();

            return new Result().setErrCode(400).setErrMsg("获取所有用户失败");
        }
    }

    //分页请求获取用户
    @RequiresAuthentication
    @GetMapping("/getUserByPage")
    public Result getUserByPage(@RequestParam(defaultValue = "1") @Min(1) Integer page, @RequestParam(defaultValue = "10") @Max(20) Integer limit) {


        List<MyUserDetail> userDetailList = service.getUserByPage(page, limit);


        if (userDetailList.size() > 0) {
            JSONObject json = new JSONObject();
            json.put("total", service.getUserCount());
            json.put("items", userDetailList);
            return new Result().setErrCode(200).setErrMsg("获取用户成功").setData(json);
        }


        return null;
    }
}
