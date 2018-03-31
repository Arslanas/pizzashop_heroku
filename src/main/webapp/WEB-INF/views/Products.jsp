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


        <!-- slider and items -->
        <div class="col-9">
            <div class="row">
                <!-- Slider -->
                <div class="col-12">
                    <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                        </ol>
                        <div class="carousel-inner" role="listbox">
                            <div class="carousel-item active">
                                <img class="d-block img-fluid" src="${contextPath}/resources/vendor/images/1200x350.png"
                                     alt="First slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block img-fluid" src="${contextPath}/resources/vendor/images/1200x350.png"
                                     alt="Second slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block img-fluid" src="${contextPath}/resources/vendor/images/1200x350.png"
                                     alt="Third slide">
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button"
                           data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button"
                           data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>

                </div>
                <!-- Items -->
                <div class="col-12">

                    <div class="row">
                        <div class="col-12 ">
                            <div class="col-4 mx-auto">
                                <h1 class="text-info">Популярное</h1>
                            </div>
                        </div>
                        <c:forEach items="${page.content}" var="item">
                            <div class="col-4 mb-4">
                                <div class="card">
                                    <c:choose>
                                        <c:when test="${not empty item.image.picture}">
                                            <a href="#"><img class="card-img-top"
                                                             src="${contextPath}/products/image/${item.id}"></a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="#"><img class="card-img-top"
                                                             src="${contextPath}/resources/images/PizzaShop.jpg"></a>
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="card-body">
                                        <h4 class="card-title text-info">
                                                ${item.name}
                                        </h4>

                                        <small class="text-muted">${item.description}</small>

                                    </div>
                                    <div class="card-footer">
                                        <div class="row">
                                            <div class="col-6 mt-2">
                                                <h5 class="text-info">${item.price}</h5>
                                            </div>
                                            <div class="col-6 mt-2">
                                                <button id="itemID-${item.id}" onclick="addToCart(${item.id})"
                                                   class="btn btn-outline-warning active btn-sm">Заказать</button>
                                            </div>
                                        </div>
                                        <sec:authorize url="${contextPath}/products/addProduct">
                                            <div class="row ">
                                                <div class="col-12 mt-3 ">
                                                    <a href="${contextPath}/products/editProduct/${item.id}"
                                                       class="btn btn-outline-info text-center                                                                                                 btn-block">Редактировать</a>
                                                </div>
                                            </div>
                                        </sec:authorize>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

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
