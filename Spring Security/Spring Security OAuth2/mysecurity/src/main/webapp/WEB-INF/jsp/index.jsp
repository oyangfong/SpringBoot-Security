<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>

<body>
<c:forEach items="${userList}" var="user">
    <p>${user.userName}</p>
</c:forEach>
=================================================<br/>
hahahahha
</body>
</html>