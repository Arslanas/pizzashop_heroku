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
    <div class="row">


        <!-- sidebar -->
        <%@ include file="templates/Sidebar_template.jsp"%>


        <!-- Cart -->
        <div class="col align align-self-center">
            <div class="row align-items-center">
                <!-- Items -->
                <div class="col-12">
                    <div class="row ">
                        <div class="col-12 mt-5">
                            <table class="table tab table-hover table-striped table-light">
                                <thead>
                                <tr>
                                    <th>Название товара</th>
                                    <th>Цена</th>
                                    <th>Количество</th>
                                    <th>Сумма</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${cartSet}" var="product">
                                    <tr>
                                        <td><a href="${contextPath}/products/shoppingCart/remove/${product.item.id}" class="btn btn-danger btn-sm "><img
                                                src="${contextPath}/resources/vendor/images/circle-x.svg" width="8"></a>
                                            <span
                                                    class="ml-2">${product.item.name}</span>
                                        </td>
                                        <td>${product.item.price} р</td>
                                        <td><a href="${contextPath}/products/shoppingCart/decrease/${product.item.id}" class="btn btn-warning btn-sm"><img
                                                src="${contextPath}/resources/vendor/images/minus.svg" width="8"></a>
                                            <span class="mx-3">${product.quantity}</span>
                                            <a href="${contextPath}/products/shoppingCart/increase/${product.item.id}" class="btn btn-warning btn-sm"><img
                                                    src="${contextPath}/resources/vendor/images/plus.svg"
                                                    width="8"></a></td>
                                        <td>${product.totalPrice} р</td>
                                    </tr>

                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <div class="col-12 mt-3 ml-1 align-self-end">
                            <a href="${contextPath}/products/shoppingCart/clear" class="btn btn-danger ">Очистить корзину</a>
                        </div>
                    </div>
                </div>
                <!-- Itogo -->
                <div class="col-6 mx-auto">
                    <div class="row justify-content-center">
                        <div class="col-12 mt-4 mb-3">
                            <h3 class="text-center text-primary">Сумма заказа: <span
                                    class="font-weight-bold"> ${cart.finalPrice} </span>р</h3>
                        </div>
                        <div class="col-12 mb-4">
                            <a href="${contextPath}/products/customerDetails" class="btn btn-warning btn-block" href="#">Оформить заказ</a>
                        </div>
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
