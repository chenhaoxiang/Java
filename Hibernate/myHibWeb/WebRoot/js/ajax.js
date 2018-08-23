String.prototype.trim=function(){
	var p = /^\s*/;
	//\s 匹配任何空白字符，包括空格、制表符、换页符等等
	var str = this.replace(p, "");
	p=/\s*$/;
	str = str.replace(p, "");
	return str;
};

function Ajax(){
	var xmlhttp;
	//1 创建一个ajax对象
	if(window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	}else{// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	//url为请求的链接或文件。
	//succ是status返回为200后运行的函数。
	//failure是status返回不为200后运行的函数。
	
	this.get=function(url,succ,failure){
		//2 设置通讯方式和地址
		xmlhttp.open("GET", url, true);//异步--多线程
		//3 设置访问成功后的 js对象(回调函数)
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState==4){//服务器的响应消息接收完毕
				if(xmlhttp.status==200){//服务器正常响应
					var txt = xmlhttp.responseText;//后台的响应信息
					succ(txt);
				}else{
	 				if(failure){
	  				    failure(xmlhttp.status);
	  				 }
	 			}
			}
		};
		//4发送---Get方式，没有参数(请求体) ---数据在请求地址中
		xmlhttp.send();
	};
	
	this.post= function(url,data,succ, failure){
		//2 设置通讯方式和地址
	 	xmlhttp.open("POST",url,true);//异步--多线程
	 	//3 设置访问成功后的 js对象(回调函数)
	 	xmlhttp.onreadystatechange=function(){
	 		if(xmlhttp.readyState==4){//服务器的响应消息接收完毕
	 			if(xmlhttp.status==200){//服务器正常响应
	 				var txt = xmlhttp.responseText;//后台的响应信息
	 				succ(txt);
	 			}else{
	 				if(failure){
	  				    failure(xmlhttp.status);
	  				 }
	 			}
	 		}
	 	};
	 	//4设置请求头
	 	xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	 	
	 	//5发送---Post方式，有参数(请求体) <---数据 ※
	 	xmlhttp.send(data);
	};
	
}