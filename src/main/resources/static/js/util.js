var printWhenRegisTrue = false;//正则通过不打印内容
// const HOST = 'http://127.0.0.1:8080';
// 定义枚举类型
const Rexs = {
    userNameRex: /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/,  //用户名正则
    passwordRex: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}$/,  //密码正则
    phoneRex: /^1[34578]\d{9}$/,  //手机号正则
    emailRex: /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/,  //邮箱正则
}
const RegErrMsgs = {
    userNameErrMsg: "用户名必须以字母开头，长度在5-16之间，只能包含字符、数字和下划线",
    passwordErrMsg: "密码必须包含大小写字母和数字的组合，长度在8-16之间",
    phoneErrMsg: "手机号码格式不正确",
    emailErrMsg: "邮箱格式不正确",
}

//当验证通过时是否打印，默认不打印,且返回值为bool值，默认为true,
function returnBool(errMsg, returnData = true) {
    if (errMsg && printWhenRegisTrue) {
        console.log("验证不通过：" + errMsg);
        alert(errMsg);
    }
    return returnData
}

//返回值为json对象，默认{errCode:1,errMsg:“”};
function returnResult(errCode = 1, errMsg = "") {
    return {errCode: errCode, errMsg: errMsg};
}

//判断callbacks（回调函数）的是否为空，返回不同的结果若为空则返回bool值（returnBool），若不为空则返回json对象（returnResult
function returnEnd(callback, errMsg, errCode = 1, returnData = true) {
    return callback == null ? returnBool(errMsg, returnData) : callback(returnResult(errCode, errMsg));
}

//根据正则表示式和参数(param)判断是否通过,传入errStr和callback,自动调用returnEnd，自动返回合适的类型
function checkByRex(Rex, param, errStr, callback) {
    if (!Rex.test(param)) {
        console.log("验证不通过：" + errStr);
        return returnEnd(callback, errStr, 0, false);
    }
    return returnEnd(callback, "");
}


//用户名校验 规则为 ^[a-zA-Z][a-zA-Z0-9_]{4,15}$
function checkUsername(param, callback) {
    // var rex = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
    // var errMsg="用户名必须以字母开头，长度在5-16之间，只能包含字符、数字和下划线";
    return checkByRex(Rexs.userNameRex, param, RegErrMsgs.userNameErrMsg, callback);
}

//密码校验 规则为：^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}$
function checkPassword(password, callback) {
    // var rex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}$/;
    // var errMsg="密码必须包含大小写字母和数字，长度为8-15位";
    return checkByRex(Rexs.passwordRex, password, RegErrMsgs.passwordErrMsg, callback);
}

//确认密码和密码对吧
function checkConfirmPassword(password, confirmPassword, callback) {
    if (password != confirmPassword) {
        return returnEnd(callback, "两次密码不一致", 0, false);
    }
    return returnEnd(callback);
}

//电话号码校验
function checkPhone(phone, callback) {
    return checkByRex(Rexs.phoneRex, phone, RegErrMsgs.phoneErrMsg, callback);
}

//邮箱校验
function checkEmail(email, callback) {
    // var rex = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
    // var errMsg="邮箱格式不正确";
    return checkByRex(Rexs.emailRex, email, RegErrMsgs.emailErrMsg, callback);
}

function loginCheck(username, password, callback) {
    if (callback) {
        checkUsername(username, function (data) {
            if (data.errCode == 0) {
                //不通过
                callback(data)
                return
            } else {
                //通过
                checkPassword(password, function (data) {
                    if (data.errCode == 0) {
                        //不通过
                        callback(data)
                        return
                    } else {
                        //通过
                        callback(data)
                    }
                })
            }

        })
    } else {
        if (checkUsername(username) && checkPassword(password)) {
            return true;
        }
        // alert("用户名或密码格式错误！");
        return false;
    }
}

//注册用户校验
function registerUserCheck(username, password, confirmPassword, email, phone, callback) {
    if (callback) {
        checkUsername(username, function (data) {
            if (data.errCode == 0) {
                //不通过
                callback(data)
                return false;
            } else {
                //通过
                checkPassword(password, function (data) {
                    if (data.errCode == 0) {
                        //不通过
                        callback(data)
                        return false;
                    } else {
                        //通过
                        checkConfirmPassword(password, confirmPassword, function (data) {
                            if (data.errCode == 0) {
                                //不通过
                                callback(data)
                                return false;
                            } else {
                                //通过
                                checkEmail(email, function (data) {
                                    console.log(data)
                                    if (data.errCode == 0) {
                                        //不通过
                                        callback(data)
                                        return false;
                                    } else {
                                        //通过
                                        checkPhone(phone, function (data) {
                                            console.log(data)
                                            if (data.errCode == 0) {
                                                //不通过
                                                callback(data)
                                                return false;
                                            } else {
                                                //通过
                                                callback(data)
                                                return true;
                                            }
                                        })
                                    }
                                })
                            }
                        })
                    }
                })
            }
        })
    }

    if (checkUsername(username) && checkPassword(password) && this.checkConfirmPassword(password, confirmPassword) && checkEmail(email) && checkPhone(phone)) {
        return true;
    }
    return false;
}

//json转换为字符串 用于请求参数
function jsonToString(json) {
    return JSON.stringify(json);
}

//字符串转json  用于解析返回的json
function stringToJson(string) {
    return JSON.parse(string);
}


// 校验验证码是否正确
function checkVCode(code) {
    if (code.length == 4) {
        $.ajax({
            url: "/api/checkVCode",
            type: "get",
            data: {
                code: code
            },
            success: function (data) {
                if (data.errCode == 1) {
                    alert("验证码正确！");
                    return true;
                } else {
                    alert("验证码错误！");
                    return false;
                }
            }
        })
    }
    return false;
}

//获取验证码
function getBase64VCode() {
    var codeUrl = "";
    $.ajax({
        url: "/api/getVCodeBase64",
        type: "get",
        async: false,
        success: function (data) {
            if (data.errCode == 1) {
                codeUrl = data.data;
            }
        }
    })
    return codeUrl;
}


//检查用户名是否已经存在
function checkUserEXIT(userName, callback) {
    var result = {errCode: -1, errMsg: ''};

    $.ajax(
        {
            type: "get",
            url: '/api/checkUserEXIT',
            // contentType: "application/json",
            data: {
                username: userName
            },
            async: false,
            dataType: "json",
            success(data) {
                msg = data.errMsg;
                mdata = msg;
                if (data.errCode == 1) {
                    result['errCode'] = 1;
                    result['errMsg'] = '';
                    // that.userstatus = 1;
                    // that.userMsg = '';
                } else {
                    result['errCode'] = 0;
                    result['errMsg'] = '用户名已被占用';
                    // result.errCode = 0;
                    // result.errMsg = '用户名已被占用';
                    // return false;
                    // that.userstatus = 0;
                    // that.userMsg = '用户名已被占用';
                }
            },
            error(data) {
                alert("出现异常" + data.errMsg);
                console.log(data)
                // alert("注册失败,请重试" + data.errMsg);
            }
        }
    )
    callback(result);
}

//检查邮箱是否已经存在
function checkEmailEXIT(email, callback) {
    var email = email
    var result = {errCode: -1, errMsg: ''};
    $.ajax(
        {
            type: "get",
            url: '/api/checkEmailEXIT',
            // contentType: "application/json",
            data: {
                email: email
            },
            dataType: "json",
            async: false,
            success(data) {
                msg = data.errMsg;
                mdata = msg;
                if (data.errCode == 1) {
                    result['errCode'] = 1;
                    result['errMsg'] = '';
                    // that.mailtatus = 1;
                    // that.mailMsg = '';
                } else {
                    result['errCode'] = 0;
                    result['errMsg'] = '邮箱已经存在';
                    // return false;
                    // that.mailtatus = 0;
                    // that.mailMsg = '邮箱已经存在';
                }
            },
            error(data) {
                console.log("邮箱已经存在" + data.errMsg);
                alert("出现异常" + data.errMsg);
            }
        }
    )

    callback(result);
}

//检查手机号是否已经存在
function checkPhoneEXIT(phone, callback) {
    var result = {errCode: -1, errMsg: ''};
    $.ajax(
        {
            type: "get",
            url: '/api/checkPhoneEXIT',
            // contentType: "application/json",
            data: {
                phone: phone
            },
            dataType: "json",
            async: false,
            success(data) {
                msg = data.errMsg;
                mdata = msg;
                if (data.errCode == 1) {
                    result['errCode'] = 1;
                    result['errMsg'] = '';
                    // return true;
                    // that.phonestatus = 1;
                    // that.phoneMsg = '';
                } else {
                    result['errCode'] = 0;
                    result['errMsg'] = '手机号已经存在';
                    // return false;
                    // that.phonestatus = 0;
                    // that.phoneMsg = '手机号已经存在';
                }
            },
            error(data) {
                alert("出现异常" + data.errMsg);
            }
        }
    )
    callback(result);
}

//注册
function UserRegister(username, password, gender, phone, email, callback) {
    $.ajax(
        {
            type: "post",
            url: '/registerUser',
            // dataType: "jsonp",
            contentType: "application/json",
            data: JSON.stringify({
                name: username,
                password: password,
                gender: gender,
                phone: phone,
                mail: email
            }),
            dataType: "json",
            success(data) {
                callback(data);
                // msg = data.errMsg;
                // mdata = msg;
                // // alert(mdata);
                // if (data.errCode == 277) {
                //     alert("注册成功前往登录吧");
                //     window.location.href = "/client/login";
                // }else {
                //     alert(mdata);
                // }
            },
            error(data) {
                alert("注册失败,网络异常请检测网络或是否以json格式提交");
            }
        }
    )
}

//登录
function userlogin(username, password, loginrole, callback) {
    $.ajax(
        {
            type: "post",
            url: '/userLogin',
            contentType: "application/json",
            data: jsonToString(
                {
                    username: username,
                    password: password,
                    loginRole: loginrole,
                    // code:vCode
                }
            ),
            dataType: "json",
            async: false,
            success(data) {
                callback(data);
            },
            error(data) {
                alert("出现异常" + data.errMsg);
            }
        }
    )

}