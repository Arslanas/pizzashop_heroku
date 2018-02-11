<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                        <c:forEach items="${items}" var="item">
                            <div class="col-4 mb-4">
                                <div class="card">
                                    <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
                                    <div class="card-body">
                                        <h4 class="card-title">
                                            <a href="#">${item.name}</a>
                                        </h4>

                                        <small class="text-muted">${item.description}</small>

                                    </div>
                                    <div class="card-footer">
                                        <div class="row">
                                            <div class="col-6 mt-2">
                                                <h5>${item.price}р</h5>
                                            </div>
                                            <div class="col-6 mt-2">
                                                <a href="${contextPath}/products/add/${item.id}" class="btn btn-outline-warning active btn-sm">Заказать</a>
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
                            </nav>
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
