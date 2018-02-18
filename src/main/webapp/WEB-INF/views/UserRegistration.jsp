<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"        prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags"      prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

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
        <!-- sidebar -->
        <div class="col-12 ">
            <div class="row">

                <div class="col-12 mx-auto mt-5">
                    <h1 class="text-center text-info">Регистрация</h1>
                </div>
                <div class="col-12 mx-auto mt-5">
                    <h5 class="text-center text-info">Пожалуйста, введите Ваши данные</h5>
                </div>

                <!-- Form -->
                <div class="col-12 mt-4 mb-5">
                    <sf:form action="${contextPath}/admin/userRegistration" commandName="user" method="post">
                        <div class="form-group">
                            <label for="Name">Имя профиля</label>
                            <sf:input path="username" type="text" class="form-control" id="Name" />
                        </div>
                        <div class="form-group">
                            <label for="Password">Пароль</label>
                            <sf:input path="password" type="password" class="form-control" id="Password"/>
                        </div>
                        <div class="form-group">
                            <label for="Email">Почта</label>
                            <sf:input path="contact.email" type="email" class="form-control" id="Email"/>
                        </div>
                        <div class="form-group">
                            <label for="Mobil">Мобильный тел.</label>
                            <sf:input path="contact.phoneNum" type="number" class="form-control" id="Mobil"/>
                        </div>
                        <div class="form-group">
                            <label for="Mobil">Адрес</label>
                            <sf:input path="address.streetHome" type="text" class="form-control" id="Address"/>
                        </div>
                        <div class="form-inline justify-content-center mt-5">
                            <div class="form-group">
                                <label for="Appartment">Квартира</label>
                                <sf:input path="address.appartment" type="number" id = "Appartment" class="form-control ml-2"/>
                            </div>
                            <div class="form-group ml-5">
                                <label for="Level">Этаж</label>
                                <sf:input path="address.level" type="number" id = "Level" class="form-control ml-2"/>
                            </div>
                            <div class="form-group ml-5">
                                <label for="Entrance">Подьезд</label>
                                <sf:input path="address.entrance" type="number" id = "Entrance" class="form-control ml-2"/>
                            </div>
                        </div>
                        <div class="form-inline justify-content-center mt-5">
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary ">Submit</button>
                                <button type="reset" class="btn btn-light ml-3 ">Cancel</button>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>
        </div>

    </div>
</div>


<!-- Footer -->
<%@ include file="templates/Footer_template.jsp"%>

</body>
</html>