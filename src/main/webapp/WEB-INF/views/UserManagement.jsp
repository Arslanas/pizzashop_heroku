<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"        prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags"      prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>
<head>
    <title>Homepage</title>

    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/resources/vendor/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${contextPath}/resources/custom_css/custom_style.css" rel="stylesheet">
</head>
<body>

<!-- Navigation -->
<%@ include file="templates/NavBar_template.jsp"%>


<div class="container">
    <div class="row ">
        <div class="col ">
            <div class="row">

                <div class="col-12 mx-auto mt-5">
                    <h3 class="text-center font-weight-bold text-info">Список клиентов</h3>
                </div>
                <!-- Form -->
                <div class="col-12 mx-auto mt-4 mb-5">
                    <table class="table tab table-hover table-striped table-light">
                        <thead>
                        <tr>
                            <th scope="col"><a  class="btn" href="${contextPath}/admin/userManagement?page=0&sort=username">Имя профиля</a></th>
                            <th scope="col"><a class="btn"href="${contextPath}/admin/userManagement?page=0&sort=contact.email">Почта</a></th>
                            <th scope="col"><a class="btn"href="${contextPath}/admin/userManagement?page=0&sort=contact.phoneNum">Телефон</a></th>
                            <th scope="col"><a class="btn"href="${contextPath}/admin/userManagement?page=0&sort=password">Пароль</a></th>
                            <th scope="col"><a class="btn"href="${contextPath}/admin/userManagement?page=0&sort=enabled">Статус</a></th>
                            <th scope="col"><a class="btn"href="${contextPath}/admin/userManagement?page=0&sort=date,desc">Дата создания</a></th>
                        </tr>
                        </thead>
                        <tbody>
                             <c:forEach items="${users.content}" var="user">
                                <tr>
                                    <td>${user.username}</td>
                                    <td>${user.contact.email}</td>
                                    <td>${user.contact.phoneNum}</td>
                                    <td>${user.password}</td>
                                    <td>${user.enabled}</td>
                                    <td>${user.date}</td>
                                </tr>
                             </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</div>


<!-- Footer -->
<%@ include file="templates/Footer_template.jsp"%>

</body>
</html>
