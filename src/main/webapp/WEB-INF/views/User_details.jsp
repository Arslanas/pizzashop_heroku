<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"        prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags"      prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add product</title>

    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <s:url value="${contextPath}/products/addProduct" var = "path"></s:url>

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
        <!-- sidebar -->
        <%@ include file="templates/Sidebar_template.jsp"%>

        <!-- sidebar -->
        <div class="col ">
            <div class="row justify-content-between">
                <div class="col-4 mx-auto mt-5">
                    <h3 class="text-center font-weight-bold text-info">Ваши данные</h3>
                </div>
                <div class="col-4 mx-auto mt-5">
                    <a href = "${contextPath}/products/user/details/edit" class="btn btn-warning">Редактировать</a>
                </div>
            </div>
            <div class="row">
                <div class="col mx-auto mt-4 mb-5">
                    <table class="table table-bordered table-light">
                        <tbody>
                        <tr>
                            <th>Имя профиля</th>
                            <td>${user.username}</td>
                        </tr>
                        <tr>
                            <th>Почта</th>
                            <td>${user.contact.email}</td>
                        </tr>
                        <tr>
                            <th>Телефон</th>
                            <td>${user.contact.phoneNum}</td>
                        </tr>
                        <tr>
                            <th>Адрес</th>
                            <td>${user.address.streetHome}</td>
                        </tr>
                        <tr>
                            <th>Квартира</th>
                            <td>${user.address.appartment}</td>
                        </tr>
                        <tr>
                            <th>Этаж</th>
                            <td>${user.address.level}</td>
                        </tr>
                        <tr>
                            <th>Подьезд</th>
                            <td>${user.address.entrance}</td>
                        </tr>
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
