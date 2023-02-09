function logincheck() {
	// alert('123123')
	var uname = /^[a-zA-Z0-9_-]{3,16}$/;
	var psw = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/;
	var userName = document.getElementById("userName").value;
	var password = document.getElementById("password").value;


	if (!document.getElementById("a").checked) {
		alert("请勾选注册条款！");
		return false
	} else {
		//判断用户名是否为空
		if (userName == "" || userName == null) {
			alert("用户名不能为空！");
			return false;
		}

		//判断用户名格式
		if (!uname.test(userName)) {
			alert("用户名格式不对，4到16位（字母，数字，下划线，减号）！");
			return false;
		}

		//判断密码是否为空
		if (password == "" || password == null) {
			alert("密码不能为空！");
			return false;
		}

		//判断密码格式
		if (!psw.test(password)) {
			alert("密码格式不对，最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符！");
			return false;
		}
		return true;
	}
}
