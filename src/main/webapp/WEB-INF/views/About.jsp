<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- TagLibs -->
<%@ include file="templates/TagLibs_template.jsp" %>

<html class="fill">
<head>
    <title>About</title>

    <!-- Styles -->
    <%@ include file="templates/Css_template.jsp" %>
</head>
<body class="fill bg-info">
<div class="container">
    <div class="row ">
        <div class="col-12 mt-5 ">
            <h1 class=" text-white text-center">PizzaShop</h1>
        </div>
        <div class="col-12 mt-5  ">
            <p class=" text-white text-center">Это приложение - некоммерческий проект, созданный с целью закрепления навыков работы с :</p>
            <div class="col-6 mx-auto">
                <ul class="text-white">
                    <li>
                        Spring Framework - MVC, Data JPA, Security, Rest
                    </li>
                    <li>
                        Hibernate
                    </li>
                    <li>
                        MySQL
                    </li>
                    <li>
                        JSP
                    </li>
                    <li>
                        Javascript, JQuery, Bootstrap, HTML
                    </li>
                </ul>
            </div>
            <p class=" text-white text-center"><a class="font-weight-bold text-warning" href="https://github.com/Arslanas/pizzashop_heroku">GitHub</a> </p>
            <p class=" text-white text-center">
                Для входа в приложение в качестве администратора используйте
                логин - <span class="text-warning font-weight-bold">admin</span> и пароль - <span class="text-warning font-weight-bold">password</span>.
                Регистрация в приложении не требует подтверждения введенных Вами данных (используйте произвольные)
            </p>
            <p class=" text-white text-center"><a class="btn btn-warning" href="${contextPath}/">Приложение</a> </p>
        </div>

    </div>
</div>

<!-- Footer -->
<%@ include file="templates/Footer_template.jsp" %>

</body>
</html>
