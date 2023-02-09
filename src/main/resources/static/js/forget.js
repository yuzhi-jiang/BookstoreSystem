	 var codeBox = document.getElementById('codeBox');
	  link = document.getElementById('link');
	  var res = '';
	  //=>编写一个获取四位的随机验证码方法
	  function queryCode() {
	  	//准备验证码获取的范围（索引“：0-61）
	  	var codeArea = 'qwertyuiopasdfghjklzxcvbnm' +
	  		'QWERTYUIOPASDFGHJKLZXCVBNM' +
	  		'1234567890';
	  	//需要准备四个索引，在code-area中通过char-at方法获取四个字符，把四个字符串拼接成一个字符串就是验证码
	  	 var result = '';
	  	for (var i = 0; i < 4; i++) { //循环四次
	  		var n = Math.round(Math.random() * 61); //=>(61-0)+0
	  		char = codeArea.charAt(n);
	  		//Math.round(Math.random()*(m-n)+n)：获取n-m之间的随机整数
	  		result += char;
			res=result;
	  	}
	  	return result;	
	  }
	  //=> 开始加载页面就需要生成一个验证码
	  codeBox.innerHTML = queryCode(); //执行方法，把return四位验证码插入在code-Box盒子
	  link.onclick = function() {
	  	codeBox.innerHTML = queryCode();
	  }

	  function forgetCheck() {
	  	var GraphicVerificationCode = document.getElementById("GraphicVerificationCode").value;
	  	var mobilePhone = document.getElementById("mobilePhone").value;
	  	var PhoneNum = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/; //11位手机号码正则
	  	var Password1 = document.getElementById("Password1").value;
	  	var Password2 = document.getElementById("Password2").value;
	  	var psw = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/;

	  	//判断手机号是否为空
	  	if (mobilePhone == "" || mobilePhone == null) {
	  		alert("手机号不能为空！");
	  		return false;
	  	}

	  	//判断手机号格式
	  	if (!PhoneNum.test(mobilePhone)) {
	  		alert("请输入正确的11位手机号！");
	  		return false;
	  	}
	  	//判断图验证码
	  	if (GraphicVerificationCode == "" || GraphicVerificationCode == null) {
	  		alert("图验证码不能为空！");
	  		return false;
	  	}
		
		//判断输入图验证码是否一致
		if(GraphicVerificationCode!=res){
			alert("请输入正确的图验证码！");
			return false;
		}
		
	  	//判断密码是否为空
	  	if (Password1 == "" || Password1 == null) {
	  		alert("请设置新密码！");
	  		return false;
	  	}

	  	//判断密码格式
	  	if (!psw.test(Password1)) {
	  		alert("密码格式不对，最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符！");
	  		return false;
	  	}

	  	//判断密码是否一致
	  	if (Password2 != Password1) {
	  		alert("密码不一致！");
	  		return false;
	  	}

	  	//修改成功跳转登录页面进行登录
	  	else {
	  		window.location.href = './login';
	  		return true;
	  	}
	  }
