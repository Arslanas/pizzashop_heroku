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
<%@ include file="templates/NavBar_template.jsp" %>


<div class="container">
    <div class="row">


        <!-- sidebar -->
        <%@ include file="templates/Sidebar_template.jsp" %>


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
                                    <tr id="productRow_${product.item.id}">
                                        <td>
                                            <button onclick="removeFromCart(${product.item.id})"
                                                    class="btn btn-danger btn-sm "><img
                                                    src="${contextPath}/resources/vendor/images/circle-x.svg" width="8">
                                            </button>
                                            <span
                                                    class="ml-2">${product.item.name}</span>
                                        </td>
                                        <td>${product.item.price}</td>
                                        <td>
                                            <button onclick="decreaseCart(${product.item.id})"
                                                    class="btn btn-warning btn-sm"><img
                                                    src="${contextPath}/resources/vendor/images/minus.svg" width="8">
                                            </button>
                                            <span id="productQuantity_${product.item.id}"
                                                  class="mx-3">${product.quantity}</span>
                                            <button onclick="increaseCart(${product.item.id})"
                                                    class="btn btn-warning btn-sm"><img
                                                    src="${contextPath}/resources/vendor/images/plus.svg"
                                                    width="8"></button>
                                        </td>
                                        <td><span id="productPrice_${product.item.id}">${product.totalPrice}</span>
                                        </td>
                                    </tr>

                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <div class="col-12 mt-3 ml-1 align-self-end">
                            <a href="${contextPath}/products/shoppingCart/clear" class="btn btn-danger ">Очистить
                                корзину</a>
                        </div>
                    </div>
                </div>
                <!-- Itogo -->
                <div class="col-6 mx-auto">
                    <div class="row justify-content-center">
                        <div class="col-12 mt-4 mb-3">
                            <h3 class="text-center text-primary">Сумма заказа: <span id="cartTotalPrice"
                                                                                     class="font-weight-bold"> ${cart.totalPrice} </span>
                            </h3>
                        </div>
                        <div class="col-12 mb-4">
                            <a href="${contextPath}/products/customerDetails" class="btn btn-warning btn-block"
                               href="#">Оформить заказ</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Footer -->
<%@ include file="templates/Footer_template.jsp" %>
<script>$(document).ready(initProductsPage('${contextPath}'))</script>
</body>
</html>
