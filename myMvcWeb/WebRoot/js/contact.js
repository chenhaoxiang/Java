function _add(){
	var url = path+"jsps/input.jsp";
	var result = window.showModalDialog(url,"","dialogWidth:400px;dialogHeight:300px;");
	if(result==null){
		return;
	}
	realadd(result);
}

function realadd(obj){
	var oTable = document.getElementById("tb");
	var oTr = oTable.insertRow();
	
	var oTd = oTr.insertCell();
	oTd.innerHTML='<input type="checkbox" name="childChk"  onclick="subchk(this)" /> ';
	oTr.insertCell().innerHTML=obj.name;
	oTr.insertCell().innerHTML=obj.age;
	oTr.insertCell().innerHTML=obj.tel;
	oTr.insertCell().innerHTML=obj.id; 
}

function chk(obj){
	//监听全部选择的那个复选框
	var allChildChks = document.getElementsByName("childChk");
	for(var i=0;i<allChildChks.length;i++){
		allChildChks[i].checked=obj.checked;
	}
}

function subchk(obj){
	//复选框监听
	if(obj.checked==false){
		document.getElementById("parentChk").checked=false;
	}else{
		var boo = true;
		var allChildChks = document.getElementsByName("childChk");
		for(var i=0;i<allChildChks.length;i++){
			if(allChildChks[i].checked == false ){
				boo=false;
				break;
			}
		}
		if(boo){
			document.getElementById("parentChk").checked=true;
		}
	}
}

function _del(){
	var ids ="";
	var oTable = document.getElementById("tb");
	var allChildChks = document.getElementsByName("childChk");
	for(var i=0;i<allChildChks.length;i++){
		if(allChildChks[i].checked==true){
			var id = oTable.rows[i+1].cells[4].innerHTML;
			if(ids==""){
				ids=id;
			}else{
				ids+=","+id;
			}
		}
	}
	//alert(ids);
	if(ids==""){
		alert("请选择要删除的行!");
	}else{
		document.getElementById("ids").value=ids;
		document.forms["form"].submit();
	}
}

//这里因为每个用户只能操坐自己的联系人。因此，当前用户操作时，没有其他用户对这些联系人进行增删改操作。
//如果要想多用户能够同时对同一集合联系人进行增删改查，那么每个增删改查的动作必须
//实时利用ajax向后台查询(在后台把结果放入list中，然后在前台利用<forEach>显示)

function realdel(boo){
	if(!boo){
		alert("很遗憾,删除失败了！");
		return;
	}
	
	var oTable = document.getElementById("tb");
	var allChildChks = document.getElementsByName("childChk");
	//因为allChiledChks的长度是随着删除而变短的，所以要用后面先删除、当然，可以从前面删除，不过要防范一下
	for(var i=allChildChks.length-1;i>=0;i--){
		if(allChildChks[i].checked == true){
			var oTr = oTable.rows[i+1];
			oTr.parentNode.removeChild(oTr);
		}
	}
	
	//对全选框设置为不打勾
	document.getElementById("parentChk").checked=false;
}

function _query(){
	var url = path+"jsps/query.jsp";
	var result = window.showModalDialog(url,"","dialogWidth:650px;dialogHeight:400px;");
	if(result==null){
		return;
	}
	realadd(result);
} 




