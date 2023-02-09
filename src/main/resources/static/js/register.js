function registerCheck() {
	//定义变量
	var uname = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
	var psw = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}$/;
	var eml = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
	var userName = document.getElementById("userName").value;
	var password1 = document.getElementById("password").value;
	var password2 = document.getElementById("confirmPasswrod").value;
	var email = document.getElementById("email").value;

	//判断用户名是否为空
	if (userName == "" || userName == null) {
		alert("用户名不能为空！");
		return false;
	}

	//判断用户名格式
	if (!uname.test(userName)) {
		alert("用户名格式不对，应字母开头，允许5-16字节，允许字母数字下划线");
		return false;
	}

	//判断密码是否为空
	if (password1 == "" || password1 == null) {
		alert("密码不能为空！");
		return false;
	}

	//判断密码格式
	if (!psw.test(password1)) {
		alert("密码格式不对，必须包含大小写字母和数字的组合，可以使用特殊字符，长度在8-15之间");
		return false;
	}

	//判断密码是否一致
	if (password2 != password1) {
		alert("密码不一致！");
		return false;
	}

	//判断邮箱是否为空
	if (email == "" || email == null) {
		alert("邮箱不能为空！");
		return false;
	}

	//判断邮箱格式
	if (!eml.test(email)) {
		alert("邮箱格式不对，以数字或字母开头！");
		return false;
	}
	return true;
}
