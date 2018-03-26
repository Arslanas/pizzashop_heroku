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
                    <div class="dropdown mb-4 btn-sm">
                        <button class="btn btn-info dropdown-toggle" type="button" id="showByButtons"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Показать по ${page.size}
                        </button>
                        <div class="dropdown-menu" aria-labelledby="showByButtons">
                            <button id="showButtonID_01" class="dropdown-item" onclick="showBy(this.id)" type="button">
                                1
                            </button>
                            <button id="showButtonID_02" class="dropdown-item" onclick="showBy(this.id)" type="button">
                                2
                            </button>
                            <button id="showButtonID_03" class="dropdown-item" onclick="showBy(this.id)" type="button">
                                3
                            </button>
                            <button id="showButtonID_04" class="dropdown-item" onclick="showBy(this.id)" type="button">
                                5
                            </button>
                            <button id="showButtonID_05" class="dropdown-item" onclick="showBy(this.id)" type="button">
                                Все
                            </button>
                        </div>
                    </div>
                    <table class="table tab table-hover table-striped table-light">
                        <thead>
                        <tr>
                            <th scope="col">
                                <button id="usernameButton" class="btn btn-light"
                                        onclick="findUsers('username', 'asc')">Имя профиля
                                </button>
                            </th>
                            <th scope="col">
                                <button id="contact.emailButton" class="btn btn-light"
                                        onclick="findUsers('contact.email', 'asc')">Почта
                                </button>
                            </th>
                            <th scope="col">
                                <button id="contact.phoneNumButton" class="btn btn-light"
                                        onclick="findUsers('contact.phoneNum', 'asc')">Телефон
                                </button>
                            </th>
                            <th scope="col">
                                <button id="passwordButton" class="btn btn-light"
                                        onclick="findUsers('password', 'asc')">Пароль
                                </button>
                            </th>
                            <th scope="col">
                                <button id="enabledButton" class="btn btn-light"
                                        onclick="findUsers('enabled', 'asc')">Статус
                                </button>
                            </th>
                            <th scope="col">
                                <button id="dateButton" class="btn btn-light"
                                        onclick="findUsers('date', 'asc')">Дата создания
                                </button>
                            </th>
                        </tr>
                        </thead>
                        <tbody id="usersBody">
                        <c:forEach items="${page.content}" var="user">
                            <tr>
                                <td>${user.username}</td>
                                <td>${user.contact.email}</td>
                                <td>${user.contact.phoneNum}</td>
                                <td>${user.password}</td>
                                <td>
                                    <button id='${user.username}_enabled'
                                            onclick="disableUser('${user.username}')">${user.enabled}</button>
                                </td>
                                <td>${user.formattedDate}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="mt-3">
                        <h5 class="text-primary">Страница <span id="pageNumID">1</span> из
                            <span id="pageTotalID">${page.totalPages}</span></h5>
                    </div>

                </div>
                <div class="col-12">
                    <div class="col ml-auto mt-4 mb-2">
                        <ul class="pagination justify-content-end">
                            <li id="firstPageID" class="page-item ">
                                <button id="firstPageButtonID" class="page-link "
                                        onclick="getFirstUsersPage()">First
                                </button>
                            </li>
                            <li id="prevPageID" class="page-item ">
                                <button id="previousPageButtonID" class="page-link"
                                        onclick="getPrevUsersPage()">Previous
                                </button>
                            </li>
                            <li id="nextPageID" class="page-item">
                                <button id="nextPageButtonID" class="page-link"
                                        onclick="getNextUsersPage()">Next
                                </button>
                            </li>
                            <li id="lastPageID" class="page-item">
                                <button id="lastPageButtonID" class="page-link"
                                        onclick="getLastUsersPage()">Last
                                </button>
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
<script src="${contextPath}/resources/custom_js/paginator.js"></script>
<script>$(document).ready(onloadUsersManagement(${page.totalPages}, ${page.totalElements}, '${disableUserURL}', '${findUsersURL}'))</script>
</body>
</html>
