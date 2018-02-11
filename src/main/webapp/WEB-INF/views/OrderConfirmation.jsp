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
        <div class="col-4 ">
            <div class="row  sticky-top">

                <div class="col-12 mx-auto mt-5">
                    <h1 class="text-center text-info">Ваши данные</h1>
                </div>

                <!-- User data -->
                <div class="col-12 mt-5">
                    <div class="row">
                        <div class="col-12">
                            <div class="row">
                                <div class="col-12">
                                    <p class="font-weight-bold">Имя</p>
                                </div>
                                <div class="col-12 ml-5">
                                    <p>${customer.name}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <p class="font-weight-bold">Мобильный тел.</p>
                                </div>
                                <div class="col-12 ml-5">
                                    <p>${customer.contact.phoneNum}</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="row">
                                <div class="col-12">
                                    <p class="font-weight-bold">Адрес</p>
                                </div>
                                <div class="col-12 ml-5">
                                    <p>${customer.address.streetHome}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <p class="font-weight-bold">Квартира</p>
                                </div>
                                <div class="col-12 ml-5">
                                    <p>${customer.address.appartment}</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="row">
                                <div class="col-12">
                                    <p class="font-weight-bold">Этаж</p>
                                </div>
                                <div class="col-12 ml-5">
                                    <p>${customer.address.level}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <p class="font-weight-bold">Подьезд</p>
                                </div>
                                <div class="col-12 ml-5">
                                    <p>${customer.address.entrance}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-8">
            <div class="col align align-self-center">
                <div class="row align-items-center">
                    <!-- Items -->
                    <div class="col-12">
                        <div class="row ">
                            <div class="col-12 mt-5">
                                <h1 class="text-center text-info">Список заказа</h1>
                                <table class="table mt-5  table-light">
                                    <thead>
                                    <tr>
                                        <th>Название товара</th>
                                        <th>Цена</th>
                                        <th>Количество</th>
                                        <th>Сумма</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>Итальянская пицца</td>
                                        <td>455 р</td>
                                        <td>1</td>
                                        <td>455 р</td>
                                    </tr>
                                    <tr>
                                        <td>Итальянская пицца</td>
                                        <td>455 р</td>
                                        <td>1</td>
                                        <td>455 р</td>
                                    </tr>
                                    <tr>
                                        <td>Итальянская пицца</td>
                                        <td>455 р</td>
                                        <td>1</td>
                                        <td>455 р</td>
                                    </tr>
                                    <tr>
                                        <td>Итальянская пицца</td>
                                        <td>455 р</td>
                                        <td>1</td>
                                        <td>455 р</td>
                                    </tr>
                                    <tr>
                                        <td>Итальянская пицца</td>
                                        <td>455 р</td>
                                        <td>1</td>
                                        <td>455 р</td>
                                    </tr>
                                    <tr>
                                        <td>Итальянская пицца</td>
                                        <td>455 р</td>
                                        <td>1</td>
                                        <td>455 р</td>
                                    </tr>
                                    <tr>
                                        <td>Итальянская пицца</td>
                                        <td>455 р</td>
                                        <td>1</td>
                                        <td>455 р</td>
                                    </tr>
                                    <tr>
                                        <td>Итальянская пицца</td>
                                        <td>455 р</td>
                                        <td>1</td>
                                        <td>455 р</td>
                                    </tr>
                                    <tr>
                                        <td>Итальянская пицца</td>
                                        <td>455 р</td>
                                        <td>1</td>
                                        <td>455 р</td>
                                    </tr>
                                    <tr>
                                        <td>Итальянская пицца</td>
                                        <td>455 р</td>
                                        <td>1</td>
                                        <td>455 р</td>
                                    </tr>
                                    <tr>
                                        <td>Итальянская пицца</td>
                                        <td>455 р</td>
                                        <td>1</td>
                                        <td>455 р</td>
                                    </tr>
                                    <tr>
                                        <td>Итальянская пицца</td>
                                        <td>455 р</td>
                                        <td>1</td>
                                        <td>455 р</td>
                                    </tr>
                                    <tr>
                                        <td>Итальянская пицца</td>
                                        <td>455 р</td>
                                        <td>1</td>
                                        <td>455 р</td>
                                    </tr>
                                    <tr>
                                        <td colspan="3" class="font-weight-bold table-bordered bg-warning text-right">Общая сумма заказа</td>
                                        <td class="font-weight-bold table-bordered bg-warning">455 р</td>
                                    </tr>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-12 justify-content-center">
            <div class="form-inline justify-content-center mt-5 mb-5">
                <div class="form-group">
                    <button type="submit" class="btn btn-light ">назад</button>
                    <button type="submit" class="btn btn-warning btn-lg ml-3 ">Отправить заказ</button>
                    <button type="submit" class="btn btn-light ml-3 ">отмена</button>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Footer -->
<%@ include file="templates/Footer_template.jsp"%>

</body>
</html>
