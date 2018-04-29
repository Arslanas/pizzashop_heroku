<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- TagLibs -->
<%@ include file="templates/TagLibs_template.jsp" %>

<html>
<head>
    <title>Homepage</title>

    <!-- Styles -->
    <%@ include file="templates/Css_template.jsp" %>

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
                    <sf:form id = "UserRegistrationFormID" action="${contextPath}/user/registration" commandName="user" method="POST">
                        <div class="form-group">
                            <label for="Name">Имя профиля</label>
                            <sf:input path="username" type="text" name="username" class="form-control" id="Name" />
                            <sf:errors path="username" cssClass="alert-danger"/>
                        </div>
                        <div class="form-group">
                            <label for="Password">Пароль</label>
                            <sf:input path="password" type="password"  name="password" class="form-control" id="Password"/>
                            <sf:errors path="password" cssClass="alert-danger"/>
                        </div>
                        <div class="form-group">
                            <label for="Email">Почта</label>
                            <sf:input path="contact.email" type="text" class="form-control" id="Email"/>
                            <sf:errors path="contact.email" cssClass="alert-danger"/>
                        </div>
                        <div class="form-group">
                            <label for="phoneNumID">Мобильный тел.</label>
                            <sf:input path="contact.phoneNum" type="text" class="form-control" id="phoneNumID"/>
                            <sf:errors path="contact.phoneNum" cssClass="alert-danger"/>
                        </div>
                        <div class="form-group">
                            <label for="Address">Адрес</label>
                            <sf:input path="address.streetHome" type="text" class="form-control" id="Address"/>
                            <sf:errors path="address.streetHome" cssClass="alert-danger"/>
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
                                <button type="submit" class="btn btn-primary">Подтвердить</button>
                                <a href="${contextPath}/products" class="btn btn-light ml-3">Назад</a>
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
<script src="${contextPath}/resources/custom_js/UserRegistrationScript.js"></script>
<script>$(document).ready(UserRegistrationInit())</script>
</body>
</html>
