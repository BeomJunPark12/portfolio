<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOut" value="${loginId == '' ? '로그인' : 'ID='+=loginId}"/>
<c:set var="loginOutLink" value="${loginId == '' ? '/login/login' : '/login/logout'}"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value='/css/board.css'/>">
    <title>board</title>
</head>
<body>
<script>
    let msg = "${msg}";
    if (msg == "write_error") alert("글 등록에 실패했습니다, 다시 시도해주세요.");

</script>
<div class="topnav">
    <a class="active" href="<c:url value='/'/>">Home</a>
    <a href="<c:url value='/board/list'/>">게시판</a>
    <a href="<c:url value='${loginOutLink}'/>">${loginOut}</a>
    <a href="<c:url value='/register/add'/>">회원가입</a>
</div>
<div class="container">
<h2>게시물 ${mode == "new" ? "글쓰기" : "읽기"}</h2>
<form action="" id="form" class="frm">
    <input type="hidden" name="bno" value="${boardDto.bno}">
    <input type="text" name="title" value="<c:out value='${boardDto.title}'/>" ${mode == "new" ? '' : 'readonly="readonly"'}>
    <textarea name="content" id="" cols="30" rows="10" ${mode == "new" ? '' : 'readonly="readonly"'}><c:out value="${boardDto.content}"/></textarea>
    <c:if test="${mode eq 'new'}">
        <button type="button" id="writeBtn" class="btn">등록</button>
    </c:if>
    <c:if test="${mode ne 'new'}">
        <button type="button" id="writeNewBtn" class="btn">글쓰기</button>
    </c:if>
    <c:if test="${boardDto.writer eq loginId}">
        <button type="button" id="modifyBtn" class="btn">수정</button>
        <button type="button" id="removeBtn" class="btn">삭제</button>
    </c:if>
    <button type="button" id="listBtn" class="btn">목록</button>
</form>
</div>
<script>
    $(document).ready(function (){  // main()




        $('#listBtn').on("click", function (){
            location.href = "<c:url value='/board/list${searchCondition.queryString}'/>";
        });

        $('#removeBtn').on("click", function (){
            if (!confirm('정말로 삭제하시겠습니까?')) return;
            let form = $('#form');
            form.attr("action", "<c:url value='/board/remove${searchCondition.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        });

        $('#writeBtn').on("click", function (){
            let form = $('#form');
            form.attr("action", "<c:url value='/board/write'/>");
            form.attr("method", "post");
            form.submit();
        });

        $("#writeNewBtn").on("click", function(){
            location.href = "<c:url value='/board/write'/> ";
        });

        $('#modifyBtn').on("click", function (){
            let form = $('#form');
            let readonly = $("input[name=title]").attr('readonly');

            // 1. 읽기 상태이면 수정 상태로 변경
            if (readonly == 'readonly') {
                $("input[name=title]").attr('readonly', false);
                $("textarea").attr('readonly', false);
                $("#modifyBtn").html("글 수정하기");
                $("h2").html("게시물 수정");
                return;
            }

            // 2. 수정 상태이면 수정 된 내용을 서버로 전송
            form.attr("action", "<c:url value='/board/modify${searchCondition.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        });
    });
</script>


</body>
</html>
