<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="true"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOut" value="${loginId == '' ? '로그인' : 'ID='+=loginId}"/>
<c:set var="loginOutLink" value="${loginId == '' ? '/login/login' : '/login/logout'}"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/css/boardList.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <title>home</title>
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
<br>
<div class="board_container">
    <div class="search_container">
<form action="<c:url value='/board/list'/>"  class="search_form" method="get">
    <select name="option">
        <option value="A" ${ph.sc.option=='A' || ph.sc.option=='' ? "selected" : ""}>제목+내용</option>
        <option value="T" ${ph.sc.option=='T' ? "selected" : ""}>제목만</option>
        <option value="W" ${ph.sc.option=='W' ? "selected" : ""}>작성자</option>
    </select>
    <input type="text" name="keyword" class="search-input" value="${ph.sc.keyword}" placeholder="검색어를 입력해주세요">
    <input type="submit" class="search-button" value="검색">
</form>
<button type="button" class="btnWrite" onclick="location.href='<c:url value='/board/write'/>'">글쓰기</button>
    </div>

<table>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>이름</th>
        <th>등록일</th>
        <th>조회수</th>
    </tr>
    <c:forEach var="boardDto" items="${list}">
    <tr>
        <td class="no">${boardDto.bno}</td>
        <td class="title"><a href="<c:url value='/board/read${ph.sc.queryString}&bno=${boardDto.bno}'/>"><c:out value="${boardDto.title}"/></a></td>
        <td class="writer">${boardDto.writer}</td>
        <c:choose>
            <c:when test="${boardDto.reg_date.time >= startOfToday}">
                <td class="regdate"><fmt:formatDate value="${boardDto.reg_date}" pattern="HH:mm" type="time"/></td>
            </c:when>
            <c:otherwise>
                <td class="regdate"><fmt:formatDate value="${boardDto.reg_date}" pattern="yyyy-MM-dd" type="date"/></td>
            </c:otherwise>
        </c:choose>
        <td class="viewcnt">${boardDto.view_cnt}</td>
    </tr>
    </c:forEach>
</table>
<br>
<div class="paging_container">
    <div class="paging">
    <c:if test="${totalCnt == null || totalCnt == 0}">
        <div> 게시물이 없습니다.</div>
    </c:if>
    </div>
    <c:if test="${totalCnt != null && totalCnt != 0}">
        <c:if test="${ph.showPrev}">
            <a class="page" href="<c:url value='/board/list${ph.sc.getQueryString(ph.beginPage-1)}'/>">&lt</a>
        </c:if>
        <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
            <a  class="page ${i == ph.sc.page ? "paging-active" : ""}"  href="<c:url value='/board/list${ph.sc.getQueryString(i)}'/>">${i}</a>
        </c:forEach>
        <c:if test="${ph.showNext}">
            <a class="page" href="<c:url value='/board/list${ph.sc.getQueryString(ph.endPage+1)}'/>">&gt</a>
        </c:if>
    </c:if>
</div>
</div>
<script>
    let msg = "${msg}";
    if (msg == "remove_ok") alert("성공적으로 삭제되었습니다.");
    if (msg == "write_ok") alert("성공적으로 등록되었습니다.");
    if (msg == "modify_ok") alert("성공적으로 수정되었습니다.");

    if (msg == "remove_error") alert("삭제되었거나 없는 게시물입니다.");
    if (msg == "read_error") alert("삭제되었거나 없는 게시물입니다.");
    if (msg == "list_error") alert("게시물 목록을 가져오는데 실패했습니다. 다시 시도해 주세요.");

</script>
</body>
</html>
