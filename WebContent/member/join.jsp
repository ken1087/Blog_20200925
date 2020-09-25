<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body bgcolor="#FFFFFF" oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
<TABLE align=center cellpadding=8 cellspacing=0 width='650' topmargin="0" leftmargin="0" rightmargin="0" marginheight="0" marginwidth="0" >
<TR>
<TD width="650">
<form name="join" method="post" action="/memberJoinProc" onsubmit="return validateJoin()">
<table width="650" height="536" border="1" cellspacing="0" cellpadding="0" bordercolor="#A3C2F6">
 <tr> 
  <td width="15%" align="right"><font color="#FF0000" size=1>★</font><font class="style1">아이디&nbsp;&nbsp;</font></td>
  <td width="75%">&nbsp;
   <input type="text" name="membername" class="membername" size="16" maxlength="16" class="input_style1">
   <input type="button" value="중복확인" onClick="validateDuplicateID()">
   <font class="style2">(영문+숫자 5~16자리) <font color="#0099FF">중복 확인 절차를 거쳐야 합니다.</font></font></td>
 </tr>
 <tr> 
  <td width="15%" align="right" ><font color="#FF0000" size=1>★</font><font class="style1">비밀번호&nbsp;&nbsp;</font></td>
  <td width="75%">&nbsp; 
   <input type="password" name="password" size="12" maxlength="12" class="input_style1 pw1">
   <font class="style1">다시한번&nbsp;&nbsp; </font>
   <input type="password" size="12" maxlength="12" class="input_style1 pw2">
   <font class="style2">(영문+숫자 4~12자리)</font></td>
 </tr>          
 <tr> 
  <td width="15%" align="right"><font color="#FF0000" size=1>★</font><font class="style1">이름&nbsp;&nbsp;</font></td>
  <td width="75%">&nbsp;<input type="text" name="name" size="10" maxlength="10" class="input_style1"> </td>
 </tr>
 <tr> 
  <td width="15%" align="right"><font color="#FF0000" size=1>★</font><font class="style1">성별&nbsp;&nbsp;</font></td>
  <td width="75%">&nbsp;<input type="radio" name="gender" value="남성" class="input_style1"><font class="style1">남성&nbsp;&nbsp;</font>  
  <input type="radio" name="gender" value="여성" class="input_style1"><font class="style1">여성&nbsp;&nbsp;</font></td>
 </tr>
 <tr > 
  <td width="15%" align="right"><font color="#FF0000" size=1>★</font><font class="style1">EMAIL&nbsp;&nbsp;</font></td>
  <td width="75%">&nbsp;<input type="text" name="email" size="20" maxlength="50" class="input_style1"></td>
 </tr>
 
 <tr> 
  <td width="15%" align="right"><font color="#FF0000" size=1>★</font><font class="style1">주소&nbsp;&nbsp;</font></td>
  <td width="75%">&nbsp;<input type="text" name="address1" size="50" maxlength="100" class="input_style1" ></td>
 </tr>
 <tr> 
  <td width="15%" align="right"><font color="#FF0000" size=1>★</font><font class="style1">나머지 주소&nbsp;&nbsp;</font></td>
  <td width="75%">&nbsp;
   <input type="text" name="address2" size="50" maxlength="100" class="input_style1">
   <font class="style2"><font color="#0099FF">나머지 주소를 적어 넣습니다.</font></font></td>
 </tr>
 <tr > 
  <td width="15%" align="right"><font class="style1">핸드폰&nbsp;&nbsp;</font></td>
  <td width="75%">&nbsp; 
   <select name="phone1">
    <option value="010">010</option>                
    <option value="011">011</option>
    <option value="016">016</option>
    <option value="017">017</option>
    <option value="018">018</option>
    <option value="019">019</option>
   </select> - 
   <input type="text" name="phone2" size="4" maxlength="4" class="input_style1">  - 
   <input type="text" name="phone3" size="4" maxlength="4" class="input_style1"></td>
 </tr>
</table>
<br>
<table width="614" border="0" cellspacing="0" cellpadding="0" align="center" >
 <tr>
  <td width=100% align="center"> 
   <input type="hidden" name="userid_check">
   <input type="submit" value="등   록">&nbsp;
   <input type="button" value="취   소"  onClick="javascript:history.go(-1);">
  </td>
 </tr>
</table>
</form>
</TD></TR></TABLE>
<div align="center">

<table width="638" border="0" cellpadding="0" cellspacing="0">
 <tr> 
  <td bgcolor="#FFFFFF" width="638" nowrap><p align="center"><br>과제</p></td>
 </tr>
</table>
</div>
<p>&nbsp;</p>
<script>
var check = 1;

function validateDuplicateID(){
	var membername = document.querySelector(".membername").value;
	membernameCheck(membername);
}

function validateJoin(){
	if(check == 1){
		alert("아이디 중복확인을 해주세요");
		return false;
	}
	var pw1 = document.querySelector(".pw1");
	var pw2 = document.querySelector(".pw2");
	
	if(pw1.value === pw2.value){
		alert("회원등록을 완료 하였습니다.")
		return true;
	}else{
		pw1.value = "";
		pw2.value = "";
		pw1.focus();
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	}
}

function membernameCheck(membername){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if(this.responseText == "ok"){
				alert("사용가능한 아이디 입니다.");
				check = 0;
			}else{
				alert("중복된 아이디가 있습니다.");
				check = 1;
			}
		}
	};
	xhttp.open("GET", "MemberNameCheck?membername="+membername, true);
	xhttp.send();
}
</script>
</body>
</html>