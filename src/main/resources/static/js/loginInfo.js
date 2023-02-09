var show_num = [];
draw(show_num);
function dj() {
    draw(show_num);
}
function sublim() {
    var val = document.getElementById("text").value;
    var num = show_num.join("");
    if (val == '') {
        alert('请输入验证码！');
    } else if (val == num) {
        alert('提交成功！');
        document.getElementById(".input-val").val('');
        draw(show_num);

    } else {
        alert('验证码错误！\n你输入的是:  ' + val + "\n正确的是:  " + num + '\n请重新输入！');
        document.getElementById("text").value = '';
        draw(show_num);
    }
}
function draw(show_num) {
    var canvas_width = document.getElementById('canvas').clientWidth;
    var canvas_height = document.getElementById('canvas').clientHeight;
    var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
    var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
    canvas.width = canvas_width;
    canvas.height = canvas_height;
    var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0,q,w,e,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m";
    var aCode = sCode.split(",");
    var aLength = aCode.length;//获取到数组的长度

    for (var i = 0; i <= 3; i++) {
        var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
        var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
        var txt = aCode[j];//得到随机的一个内容
        show_num[i] = txt;
        var x = 10 + i * 20;//文字在canvas上的x坐标
        var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
        context.font = "bold 23px 微软雅黑";

        context.translate(x, y);
        context.rotate(deg);

        context.fillStyle = randomColor();
        context.fillText(txt, 0, 0);

        context.rotate(-deg);
        context.translate(-x, -y);
    }
    for (var i = 0; i <= 5; i++) { //验证码上显示线条
        context.strokeStyle = randomColor();
        context.beginPath();
        context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.stroke();
    }
    for (var i = 0; i <= 30; i++) { //验证码上显示小点
        context.strokeStyle = randomColor();
        context.beginPath();
        var x = Math.random() * canvas_width;
        var y = Math.random() * canvas_height;
        context.moveTo(x, y);
        context.lineTo(x + 1, y + 1);
        context.stroke();
    }
}
function randomColor() {//得到随机的颜色值
    var r = Math.floor(Math.random() * 256);
    var g = Math.floor(Math.random() * 256);
    var b = Math.floor(Math.random() * 256);
    return "rgb(" + r + "," + g + "," + b + ")";
}
function check() {
    //定义变量
    var userName = document.getElementById("userName").value;
    var password = document.getElementById("password").value;
    // var password2 = document.getElementById("password2").value;
    //var contact = document.getElementById("contact").value;
    //var Email = document.getElementById("Email").value;
    var a = document.getElementById("a").value;


    //判断用户名是否为空
    if (userName == "" || userName == null) {
        alert("请输入用户名：");
        document.getElementById("yhm").innerHTML = "格式不对";
        return false;
    }

    //用户名的正则表达式
    // var userName = /^[\u4E00-\u9FA5A-Za-z0-9]+$ 或 ^[\u4E00-\u9FA5A-Za-z0-9]{2,20}$/,
    //     str = '';
    //     console.log(pattern.test(str));

    //判断密码是否为空
    if (password == "" || password == null) {
        alert("请输入密码：");
        return false;
    }
    //判断密码长度不能低于6个
    if (password.length < 6) {
        alert("密码长度不能小于6个！");
        return false;
    }

    //判断密码长度不能多于11个
    if (password.length > 11) {
        alert("密码长度不能多于11个！");
        return false;
    }

    //密码的正则表达式
    //var pattern = /^\w+$ 或 ^\w{3,20}$/,
    // 	str = '';
    // console.log(pattern.test(str));

    //判断两次密码是否一致
    // if (password2 != password1) {
    //     alert("两次密码不一致！");
    //     return false;
    // }

    //判断联系方式是否为空
    // if (contact == null || contact == "") {
    //     alert("联系方式不能为空！");
    //     return false;
    // }

    //练习方的正则表达式
    // var pattern = /^\d{n}$/,
    //     str = '';
    // console.log(pattern.test(str));

    //判断邮箱是否为空
    // if (Email == null || Email == "") {
    //     alert("请输入邮箱！");
    //     return false;
    // }

    //判断邮箱的格式是否有.和@
    // if (Email.indexOf(".") == -1 || Email.indexOf("@") == -1) {
    //     alert("邮箱格式不对！");
    //     return false;
    // }

    //邮箱的正则表达式
    //var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
    // 	str = '';
    // console.log(pattern.test(str));

    if (a == null || a == "") {
        alert("请勾选选项！");
        return false;
    }

    //以上情况都不发生则返回true
    return true;
}



