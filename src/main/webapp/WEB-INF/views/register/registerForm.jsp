<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="java.net.URLDecoder"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/css/registerForm.css'/>">
    <title>Register</title>
</head>
<body>
<!--<form action="<c:url value='/register/save'/>" method="post" onsubmit="return formCheck(this)">-->
    <form:form modelAttribute="userDto">
    <div class="title">회원가입</div>
    <div id="msg" class="msg">
        <form:errors path="id"/>
    </div>
    <label for="">아이디</label>
    <input  class="input-field" type="text" name="id" placeholder="아이디">
    <label for="">비밀번호</label>
    <input  class="input-field" type="password" name="password" placeholder="비밀번호">
    <label for="">이름</label>
    <input  class="input-field" type="text" name="name" placeholder="이름">
    <label for="">이메일</label>
    <input  class="input-field" type="text" name="email" placeholder="이메일">
    <button>회원 가입</button>
    </form:form>

<script>
    function formCheck(frm) {
        let msg ='';
        if(frm.id.value.length<3) {
            setMessage('id의 길이는 3이상이어야 합니다.', frm.id);
            return false;
        }
        return true;
    }

    function setMessage(msg, element){
        document.getElementById("msg").innerHTML = ${'${msg}'};
        if(element) {
            element.select();
        }
    }
</script>
</body>
</html>
