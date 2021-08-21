<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>${sessionScope.message}</h3>
<h3>${sessionScope.user_details}</h3>
<h3> 
<a href="<spring:url value='/user/logout'/>">Log Me Out</a>
</h3>
</body>
</html>