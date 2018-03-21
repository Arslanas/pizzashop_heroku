<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>
<head>
    <title>Homepage</title>

    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <s:url var="findUsersURL" value="${contextPath}/admin/userManagementRest"/>
    <s:url var="disableUserURL" value="${contextPath}/admin/userManagementRest/disable/"/>

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
                            <th scope="col">
                                <button id="usernameButton" class="btn btn-light"
                                        onclick="findUsers('${findUsersURL}','username', 'asc')">Имя профиля
                                </button>
                            </th>
                            <th scope="col">
                                <button id="contact.emailButton" class="btn btn-light"
                                        onclick="findUsers('${findUsersURL}','contact.email', 'asc')">Почта
                                </button>
                            </th>
                            <th scope="col">
                                <button id="contact.phoneNumButton" class="btn btn-light"
                                        onclick="findUsers('${findUsersURL}','contact.phoneNum', 'asc')">Телефон
                                </button>
                            </th>
                            <th scope="col">
                                <button id="passwordButton" class="btn btn-light"
                                        onclick="findUsers('${findUsersURL}','password', 'asc')">Пароль
                                </button>
                            </th>
                            <th scope="col">
                                <button id="enabledButton" class="btn btn-light"
                                        onclick="findUsers('${findUsersURL}','enabled', 'asc')">Статус
                                </button>
                            </th>
                            <th scope="col">
                                <button id="dateButton" class="btn btn-light"
                                        onclick="findUsers('${findUsersURL}','date', 'asc')">Дата создания
                                </button>
                            </th>
                        </tr>
                        </thead>
                        <tbody id="usersBody">
                        <c:forEach items="${users.content}" var="user">
                            <tr>
                                <td>${user.username}</td>
                                <td>${user.contact.email}</td>
                                <td>${user.contact.phoneNum}</td>
                                <td>${user.password}</td>
                                <td>
                                    <button id="${user.username}_enabled"
                                            onclick="disableUser('${disableUserURL}','${user.username}')">${user.enabled}</button>
                                </td>
                                <td>${user.formattedDate}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-12">
                    <div class="col ml-auto mt-4 mb-2">
                        <ul class="pagination justify-content-end">
                            <li class="page-item disabled">
                                <a class="page-link" href="#" tabindex="-1">Previous</a>
                            </li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#">Next</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>


<!-- Footer -->
<%@ include file="templates/Footer_template.jsp" %>

</body>
</html>
