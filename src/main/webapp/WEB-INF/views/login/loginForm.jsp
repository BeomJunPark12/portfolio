<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<c:set var="loginId" value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? '로그인' : '로그아웃'}"/>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="<c:url value='/css/loginForm.css'/>">
  <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">

    <title>login</title>
</head>
<body>
<div class="topnav">
  <ul>
    <li><a class="active" href="<c:url value='/'/>">Home</a></li>
    <li><a href="<c:url value='/board/list'/>">게시판</a></li>
    <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
    <li><a href="<c:url value='/register/add'/>">회원가입</a></li>
  </ul>
</div>


<form action="<c:url value='/login/login'/>" method="post" onsubmit="return formCheck(this);">
  <div class="title">로그인</div>
  <div id="msg" class="msg">
    <c:if test="${not empty param.msg}">
      ${URLDecoder.decode(param.msg, "utf-8")}
    </c:if>
  </div>
  <input  class="input-field" type="text" name="id"  value="${cookie.id.value}" placeholder="아이디" autofocus>
  <input  class="input-field" type="password" name="password" placeholder="비밀번호">
  <input  class="input-field" type="hidden" name="toURL" value="${param.toURL}">
  <button>로그인</button>
  <label><input type="checkbox" name="rememberId" ${empty cookie.id.value ? "" : "checked"}>아이디 기억</label>
  <a href="<c:url value='/register/add'/>">회원가입</a>
</form>

<script>
  function formCheck(frm) {
    let msg ='';
    if(frm.id.value.length==0) {
      setMessage('id를 입력해주세요.', frm.id);
      return false;
    }
    if(frm.password.value.length==0) {
      setMessage('password를 입력해주세요.', frm.password);
      return false;
    }
    return true;
  }
  function setMessage(msg, element){
    document.getElementById("msg").innerHTML = ` ${'${msg}'}`;
    if(element) {
      element.select();
    }
  }
</script>
</body>
</html>
