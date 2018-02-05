<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"        prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags"      prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add product</title>

    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <s:url value="/test/save" var = "path"></s:url>

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
    <div class="row ">
        <!-- sidebar -->
        <div class="col-3 bg-info ">
            <div class="row sticky-top">
                <div class="col">
                    <div class="row ">
                        <div class="col-6 mx-auto ">
                            <br>
                            <h1 class="my-4 text-white">Меню</h1>
                            <br>
                        </div>
                    </div>
                    <div class="row mx-auto list-group mb-3">
                        <c:forEach items="${categories}" var="cat">
                            <a class="btn btn-outline-info bg-info btn-block text-white ">${cat.name}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>


        </div>
        <!-- sidebar -->
        <div class="col ">
            <div class="row">

                <div class="col-12 mx-auto mt-5">
                    <h3 class="text-center font-weight-bold text-info">Добавить товар</h3>
                </div>


                <!-- Form -->
                <div class="col-11 mx-auto mt-4 mb-5">

                    <sf:form action="${path}" method="post" commandName="item">

                        <div class="form-group">
                            <sf:label path="name" for="Name" class="font-weight-bold">Название товара</sf:label>
                            <sf:input path="name" type="text" class="form-control" id="Name" ></sf:input>
                        </div>
                        <div class="form-group">
                            <sf:label path="setOfCategorizedItems" for="Category" class="font-weight-bold">Категория</sf:label>
                            <sf:select path="setOfCategorizedItems" id="Category" class="form-control">
                                    <sf:options items="${categoryName}"/>
                            </sf:select>
                        </div>
                        <div class="form-group">
                            <label for="Description" class="font-weight-bold">Описание товара</label>
                            <textarea class="form-control" id="Description" aria-label="With textarea"></textarea>
                        </div>
                        <div class="form-group">
                            <sf:label path="price" for="Price" class="font-weight-bold">Цена</sf:label>
                            <sf:input path="price" type="number" class="form-control" id="Price"></sf:input>
                        </div>

                        <div class="form-group">
                            <label for="exampleFormControlFile1" class="font-weight-bold">Загрузить изображение</label>
                            <input type="file" class="form-control-file" id="exampleFormControlFile1">
                        </div>

                        <div class="form-inline justify-content-center mt-5">
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary ">Подтвердить</button>
                                <button type="reset" class="btn btn-light ml-3 ">Отмена</button>
                            </div>
                        </div>

                    </sf:form>

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
