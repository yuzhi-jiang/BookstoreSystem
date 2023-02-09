package com.example.BookstoreSystem.util;

public class Constan2 {

    //////////////////////验证码长度////////////////////////////
    public static final int VerificationCode_Count = 4;
    public static final Integer MAX_PAGE_SIZE = 20;


    /////////////////////////以下是返回值相关/////////////////////////////


    /////////////// 以下是登录返回值相关/////////////////////
    public static Integer Code_UnknownUser=1;
    public static String Msg_UnknownUser="未知用户";

    public static Integer Code_Too_many_errors=2;
    public static String Msg_Too_many_errors="用户名或密码错误次数过多";

    public static Integer Code_PasswordError=3;
    public static String Msg_PasswordError="密码不正确";

    public static Integer Code_Account_locked=4;
    public static String Msg_Account_locked="账户已锁定";

    public static Integer Code_NameOrPasswordError=5;
    public static String Msg_NameOrPasswordError="用户名或密码不正确!";

    public static Integer Code_LoginSuccess=6;
    public static String Msg_LoginSuccess="登录成功";

    public static Integer Code_LoginFail=7;
    public static String Msg_LoginFail="登录失败";

    /////////////// 以下是注册返回值相关/////////////////////

    public static Integer Code_Register_userEmpty=1;
    public static String Msg_Register_userEmpty="参数为空,不可注册";

    public static Integer Code_Register_CanRegister=1;
    public static String Msg_Register_CanRegister="可以注册";

    public static Integer Code_Register_Success=2;
    public static String Msg_Register_Success="注册用户成功";
    public static Integer Code_Register_Fail=3;
    public static String Msg_Register_Fail="注册用户失败";
    public static Integer Code_Register_UserNameHad=4;
    public static String Msg_Register_UserNameHad="用户名已存在";
    public static Integer Code_Register_UserPhoneHad=5;
    public static String Msg_Register_UserPhoneHad="手机号已被绑定";
    public static Integer Code_Register_UserMailHad=6;
    public static String Msg_Register_UserMailHad="邮箱已被绑定";
    public static Integer Code_Register_BindRoleFail=7;
    public static String Msg_Register_BindRoleFail="添加用户角色失败";
    public static Integer Code_Register_BindPermissionFail=8;
    public static String Msg_Register_BindPermissionFail="添加用户权限失败";


    ///////////////////////以下是书类型相关//////////////////////////////
    public static Integer Code_FindBookTypeSuccess=1;
    public static String  Msg_FindBookTypeSuccess="查找书类型成功";


    /////////////////////////以下是数据库列相关////////////////////////////////////////////////////

    public static Long ROLE_USER=1L;
    public static Long Code_ROLE_SELLER=2L;
    public static Long Code_ROLE_ADMIN_SUPPER=3L;

    public static Long Permission_User_Create=1L;
    public static Long Permission_User_Update=2L;
    public static Long Permission_User_Delete=3L;
    public static Long Permission_User_Select=4L;
    public static Long Permission_User_ALl=5L;



    public static Integer Code_Delete_Success=1; //删除用户成功
    public static Integer Code_Delete_Fail=2; //删除用户失败
    public static Integer Code_UserLogout_Success=3;//用户退出成功
    public static Integer Code_UserLogout_Failr;//用户退出失败
}
