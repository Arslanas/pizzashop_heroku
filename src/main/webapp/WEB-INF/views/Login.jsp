<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
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
<%@ include file="templates/NavBar_template.jsp" %>


<div class="container">
    <div class="row ">
        <!-- sidebar -->
        <div class="col-12 ">
            <div class="row">

                <div class="col-6 mx-auto mt-5">
                    <h4 class="text-center text-info">Введите имя Вашего профиля и пароль</h4>
                    <c:if test="${param.error != null}">
                            <h4 class="text-center text-danger">Вы ввели неправильные данные</h4>
                    </c:if>
                </div>

                <!-- Form -->
                <div class="col-8 mx-auto mt-4 mb-5">
                    <sf:form action="${contextPath}/login" method="post">
                        <div class="form-group">
                            <label for="Name">Имя профиля</label>
                            <input type=text" class="form-control" name="username" id="Name">
                        </div>
                        <div class="form-group">
                            <label for="Mobil">Пароль</label>
                            <input type="password" class="form-control"  name="password" id="Mobil">
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" name="remember-me" type="checkbox" value="true" id="RememberMe">
                            <label class="form-check-label" for="RememberMe">Запомнить меня</label>
                        </div>
                        <div class="form-inline justify-content-center mt-5">
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary ">Submit</button>
                                <button type="submit" class="btn btn-light ml-3 ">Cancel</button>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>
        </div>

    </div>
</div>


<!-- Footer -->
<%@ include file="templates/Footer_template.jsp" %>

</body>
</html>
