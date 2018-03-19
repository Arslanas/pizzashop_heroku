<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

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
                                <img class="d-block img-fluid" src="/resources/vendor/images/1200x350.png"
                                     alt="First slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block img-fluid" src="/resources/vendor/images/1200x350.png"
                                     alt="Second slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block img-fluid" src="/resources/vendor/images/1200x350.png"
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
                        <div class="col-12 ">
                            <div class="col-3 mx-auto">
                                <a href="${requestType}?page=0&sort=name" class="text-info">По имени</a>
                            </div>
                            <div class="col-3 mx-auto">
                                <a href="${requestType}?page=0&sort=price" class="text-info">По цене</a>
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
                                                             src="/resources/vendor/images/1200x350.png"></a>
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
                                                <h5 class="text-info">${item.price}р</h5>
                                            </div>
                                            <div class="col-6 mt-2">
                                                <a href="${contextPath}/products/add/${item.id}"
                                                   class="btn btn-outline-warning active btn-sm">Заказать</a>
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
                                        <div class="row ">
                                            <div class="col-12 mt-3  ">
                                                <button id="itemID-${item.id}" onclick="addToCart(${item.id})"
                                                        class="btn btn-outline-info text-center btn-block">AJAX
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>

                    <div class="row">
                        <div class="col mt-4 mb-2">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-end">
                                    <li class="page-item ">
                                        <a href="${requestType}?page=${page.number - 1}&sort=${sort}&size=4&search=${search}"
                                           class="page-link" tabindex="-1">Previous</a>
                                    </li>
                                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                                    <li class="page-item">
                                        <a href="${requestType}?page=${page.number + 1}&sort=${sort}&size=4&search=${search}"
                                           class="page-link">Next</a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
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
