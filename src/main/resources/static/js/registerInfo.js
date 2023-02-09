function check() {
    //定义变量
    var userName = document.getElementById("userName").value;
    var password1 = document.getElementById("password1").value;
    var password2 = document.getElementById("password2").value;
    var contact = document.getElementById("contact").value;
    var Email = document.getElementById("Email").value;


    //判断用户名是否为空
    if (userName == "" || userName == null) {
        alert("请输入用户名：");
        return false;
    }

    //用户名的正则表达式
    // var userName = /^[\u4E00-\u9FA5A-Za-z0-9]+$ 或 ^[\u4E00-\u9FA5A-Za-z0-9]{2,20}$/,
    //     str = '';
    //     console.log(pattern.test(str));

    //判断密码是否为空
    if (password1 == "" || password1 == null) {
        alert("请输入密码：");
        return false;
    }
    //判断密码长度不能低于6个
    if (password1.length < 6) {
        alert("密码长度不能小于6个！");
        return false;
    }

    //判断密码长度不能多于11个
    if (password1.length > 11) {
        alert("密码长度不能多于11个！");
        return false;
    }

    //密码的正则表达式
    //var pattern = /^\w+$ 或 ^\w{3,20}$/,
    // 	str = '';
    // console.log(pattern.test(str));

    //判断两次密码是否一致
    if (password2 != password1) {
        alert("两次密码不一致！");
        return false;
    }

    //判断联系方式是否为空
    if (contact == null || contact == "") {
        alert("联系方式不能为空！");
        return false;
    }

    //练习方的正则表达式
    // var pattern = /^\d{n}$/,
    //     str = '';
    // console.log(pattern.test(str));

    //判断邮箱是否为空
    if (Email == null || Email == "") {
        alert("请输入邮箱！");
        return false;
    }

    //判断邮箱的格式是否有.和@
    if (Email.indexOf(".") == -1 || Email.indexOf("@") == -1) {
        alert("邮箱格式不对！");
        return false;
    }

    //邮箱的正则表达式
    //var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
    // 	str = '';
    // console.log(pattern.test(str));

    //以上情况都不发生则返回true
    return true;
}
