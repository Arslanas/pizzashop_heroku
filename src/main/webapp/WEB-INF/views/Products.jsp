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
<nav class="navbar navbar-expand-lg navbar-dark bg-info fixed-top ">

    <div class="container">
        <a class="navbar-brand font-weight-bold display-4 " href="#">Pizza Shop</a>

        <button class="btn btn-warning  mx-auto  " type="submit"><img src="/resources/vendor/images/cart.svg"
                                                                      width="20"> Ваш заказ : 566 р
        </button>
        <ul class="navbar-nav ml-auto">
            <li>
                <form class="form-inline mt-5 mt-md-0">
                    <input class="form-control mr-sm-2" type="text" placeholder="Поиск" aria-label="Search">
                    <button class="btn  btn-warning my-2 my-sm-0" type="submit">Найти</button>
                </form>
            </li>
            <li class="nav-item active ml-3">
                <button class="btn  btn-warning  my-2 my-sm-0 " type="submit"><img
                        src="/resources/vendor/images/person.svg" width="20"> Войти
                </button>
            </li>
        </ul>
    </div>

</nav>


<div class="container">
    <div class="row">


        <!-- sidebar -->
        <div class="col-3 bg-info ">
            <div class="row sticky-top">
                <div class="col">
                    <div class="row ">
                        <div class="col-6 mx-auto mt-5">
                            <br>
                            <h1 class="my-4 text-white">Меню</h1>
                            <br>
                        </div>
                    </div>
                    <div class="row mx-auto list-group mb-3">
                        <a class="btn btn-outline-info bg-info btn-block text-white ">Популярное</a>
                        <a class="btn btn-outline-info bg-info btn-block text-white">Пицца</a>
                        <a class="btn btn-outline-info bg-info btn-block text-white">Блюда из курицы</a>
                        <a class="btn btn-outline-info bg-info btn-block text-white">Десерты</a>
                        <a class="btn btn-outline-info bg-info btn-block text-white">Салаты</a>
                        <a class="btn btn-outline-info bg-info btn-block text-white">Напитки</a>
                        <a class="btn btn-outline-info bg-info btn-block text-white">Баскеты</a>
                    </div>
                </div>
            </div>


        </div>


        <!-- slider and items -->
        <div class="col">


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
                                <img class="d-block img-fluid" src="/resources/vendor/images/1200x350.png" alt="First slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block img-fluid" src="/resources/vendor/images/1200x350.png" alt="Second slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block img-fluid" src="/resources/vendor/images/1200x350.png" alt="Third slide">
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

                        <div class="col-4 mb-4">
                            <div class="card">
                                <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        <a href="#">Итальянская</a>
                                    </h4>

                                    <small class="text-muted">Descriptions</small>

                                </div>
                                <div class="card-footer">
                                    <div class="row">
                                        <div class="col-6 mt-2">
                                            <h5>488 р</h5>
                                        </div>
                                        <div class="col-6 mt-2">
                                            <a href="${contextPath}/products/test?quantity=5" class="btn btn-outline-warning active btn-sm">Заказать</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-4 mb-4">
                            <div class="card">
                                <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        <a href="#">Итальянская</a>
                                    </h4>

                                    <small class="text-muted">Descriptions</small>

                                </div>
                                <div class="card-footer">
                                    <div class="row">
                                        <div class="col-6 mt-2">
                                            <h5>488 р</h5>
                                        </div>
                                        <div class="col-6 mt-2">
                                            <button class="btn btn-outline-warning active btn-sm">Заказать</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-4 mb-4">
                            <div class="card">
                                <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        <a href="#">Итальянская</a>
                                    </h4>

                                    <small class="text-muted">Descriptions</small>

                                </div>
                                <div class="card-footer">
                                    <div class="row">
                                        <div class="col-6 mt-2">
                                            <h5>488 р</h5>
                                        </div>
                                        <div class="col-6 mt-2">
                                            <button class="btn btn-outline-warning active btn-sm">Заказать</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-4 mb-4">
                            <div class="card">
                                <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        <a href="#">Итальянская</a>
                                    </h4>

                                    <small class="text-muted">Descriptions</small>

                                </div>
                                <div class="card-footer">
                                    <div class="row">
                                        <div class="col-6 mt-2">
                                            <h5>488 р</h5>
                                        </div>
                                        <div class="col-6 mt-2">
                                            <button class="btn btn-outline-warning active btn-sm">Заказать</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-4 mb-4">
                            <div class="card">
                                <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        <a href="#">Итальянская</a>
                                    </h4>

                                    <small class="text-muted">Descriptions</small>

                                </div>
                                <div class="card-footer">
                                    <div class="row">
                                        <div class="col-6 mt-2">
                                            <h5>488 р</h5>
                                        </div>
                                        <div class="col-6 mt-2">
                                            <button class="btn btn-outline-warning active btn-sm">Заказать</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

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
<footer class="py-5 bg-info ">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Pizza Shop 2018</p>
    </div>
    <!-- /.container -->
</footer>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${contextPath}/resources/vendor/js/jquery-3.2.1.slim.min.js"></script>
<script>window.jQuery || document.write('<script src="${contextPath}/resources/vendor/js/jquery-3.2.1.min"><\/script>')</script>
<script src="${contextPath}/resources/vendor/js/bootstrap.bundle.min.js"></script>
<script src="${contextPath}/resources/vendor/js/bootstrap.min.js"></script>

</body>
</html>
