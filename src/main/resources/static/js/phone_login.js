function phoneLoginCheck() {
		var phone = document.getElementById("phone").value;
		var phoneNum = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/; //11位手机号码正则
		var verificationcode = document.getElementById("verificationcode").value;

		//判断手机号是否为空
		if (phone == "" || phone == null) {
			alert("手机号不能为空！");
			return false;
		}

		//判断手机号格式
		if (!phoneNum.test(phone)) {
			alert("请输入正确的11位手机号！");
			return false;
		}

		//判断验证码
		if (verificationcode == "" || verificationcode == null) {
			alert("验证码不能为空！");
			return false;
		}

		//修改成功跳转登录页面进行登录
		else {
			window.location.href = './Reception_index';
			return true;
		}
	}
