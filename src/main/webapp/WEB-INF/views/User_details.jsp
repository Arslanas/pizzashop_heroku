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
        <%@ include file="templates/Sidebar_template.jsp"%>

        <!-- sidebar -->
        <div class="col ">
            <div class="row justify-content-between">
                <div class="col-4 mx-auto mt-5">
                    <h3 class="text-center font-weight-bold text-info">Ваши данные</h3>
                </div>
                <div class="col-4 mx-auto mt-5">
                    <a href = "${contextPath}/user/detailsEdit" class="btn btn-warning">Редактировать</a>
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
                    <div class="col-2 ml-auto ">
                        <sf:form action="${contextPath}/user/remove/${user.username}" method="POST">
                            <button type="submit" class="btn btn-outline-danger">Удалить</button>
                        </sf:form>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>


<!-- Footer -->
<%@ include file="templates/Footer_template.jsp"%>
</body>
</html>
