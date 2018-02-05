<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach items="${categories}" var="i">
        ${i}
        <br>
    </c:forEach>
    <br>
    <br>
    <c:forEach items="${items}" var="i">
        ${i}
        <br>
    </c:forEach>
    <br>
    <br>
    <br>
    <br>
    <c:forEach items="${category_pizza}" var="i">
        ${i}
        <br>
    </c:forEach>
    <br>
    <br>
    <br>
    <br>
    <c:forEach items="${category_dessert}" var="i">
        ${i}
        <br>
    </c:forEach>
    <br>
    <br>
    <br>
    getCategoryByName()
    <br>
    ${categoriesByName}

</body>
</html>
