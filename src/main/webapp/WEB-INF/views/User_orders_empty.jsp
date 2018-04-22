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
    <div class="row ">
        <!-- sidebar -->
        <%@ include file="templates/Sidebar_template.jsp" %>
        <div class="col mt-5">
            <div class="row">
                <div class="col">
                    <h1 class="text-info text-center">Пусто</h1>
                </div>
            </div>
            <div class="row">
                <div class="col ml-5 ">
                    <img width="512" height="512" src="${contextPath}/resources/images/empty_cart.png">
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Footer -->
<%@ include file="templates/Footer_template.jsp" %>
<script src="${contextPath}/resources/vendor/DataTables/DataTables-1.10.16/js/jquery.dataTables.min.js"></script>
<script src="${contextPath}/resources/vendor/DataTables/DataTables-1.10.16/js/dataTables.bootstrap4.min.js"></script>
<script src="${contextPath}/resources/custom_js/UserOrders.js"></script>
<script>$(document).ready(onloadUserOrders('${contextPath}'))</script>
</body>
</html>
