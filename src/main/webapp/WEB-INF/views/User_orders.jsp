<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add product</title>

    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <s:url value="${contextPath}/products/addProduct" var="path"></s:url>

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
        <%@ include file="templates/Sidebar_template.jsp" %>

        <!-- sidebar -->
        <div class="col mt-3">
            <table id="tableID" class="table table-light">
                <thead>
                <tr>
                    <th scope="col" class="bg-white"><h3 class="text-center font-weight-bold text-info">Ваши
                        заказы</h3>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sCarts}" var="sCart">
                    <tr>
                        <td>
                            <table class="table table-light">
                                <tbody>
                                <tr class="bg-info text-white">
                                    <th>Оформлено</th>
                                    <td>${sCart.formattedDate}</td>
                                    <td>${sCart.formattedTime}</td>
                                </tr>
                                <c:forEach items="${sCart.cart}" var="product">
                                    <tr>
                                        <td>${product.item.name}</td>
                                        <td>${product.quantity} шт</td>
                                        <td>${product.totalPrice} р</td>
                                    </tr>
                                </c:forEach>
                                <tr class="bg-info text-white">
                                    <td>
                                        <button onclick="addToCartFromCart(${sCart.id})" class="btn btn-warning">
                                            Повторить заказ
                                        </button>
                                    </td>
                                    <th>Итого</th>
                                    <th>${sCart.totalPrice} р</th>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>


<!-- Footer -->
<%@ include file="templates/Footer_template.jsp" %>
<script src="${contextPath}/resources/vendor/DataTables/DataTables-1.10.16/js/jquery.dataTables.min.js"></script>
<script src="${contextPath}/resources/vendor/DataTables/DataTables-1.10.16/js/dataTables.bootstrap4.min.js"></script>
<script src="${contextPath}/resources/custom_js/UserOrders.js"></script>
<script>$(document).ready(onloadUserOrders())</script>
</body>
</html>
