<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<sec:authorize access="authenticated" var="authenticated"/>


<nav class="navbar navbar-expand-lg navbar-dark bg-info fixed-top ">

    <div class="container">
        <a href="${contextPath}/products" class="navbar-brand font-weight-bold display-4 " href="#">Pizza Shop</a>

        <a href="${contextPath}/products/shoppingCart" id="shoppingCart" class="btn btn-warning  mx-auto"><img
                src="${contextPath}/resources/vendor/images/cart.svg"
                width="20"> Ваш заказ : <span id="cartPrice"> ${cart.totalPrice} </span>
        </a>
        <ul class="navbar-nav ml-auto">
            <li>
                <sf:form method="get" action="${contextPath}/products/search" class="form-inline mt-5 mt-md-0">
                    <input class="form-control mr-sm-2" name="search" type="text" placeholder="Поиск"
                           aria-label="Search">
                    <button class="btn  btn-warning my-2 my-sm-0" type="submit">Найти</button>
                </sf:form>
            </li>

            <c:choose>
                <c:when test="${authenticated}">
                    <sec:authentication property="name" var="userName"/>
                    <li class="nav-item active ml-3">
                        <div class="dropdown">
                            <button class="btn  btn-warning dropdown-toggle my-2 my-sm-0 " type="button" id="userMenu"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <img src="${contextPath}/resources/vendor/images/person.svg" width="20">${userName}
                            </button>
                            <div class="dropdown-menu" aria-labelledby="userMenu">
                                <a class="dropdown-item" href="${contextPath}/products/user/orders">Ваши заказы</a>
                                <a class="dropdown-item" href="${contextPath}/products/user/details">Ваш профиль</a>
                                <div class="dropdown-divider"></div>
                                <sf:form action="${contextPath}/logout" method="post">
                                    <button type="submit" class="dropdown-item">Выход</button>
                                </sf:form>
                            </div>
                        </div>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item ml-3">
                        <div class="dropdown">
                            <button class="btn  btn-warning dropdown-toggle my-2 my-sm-0 " type="button"
                                    id="userMenuAnonym"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <img src="${contextPath}/resources/vendor/images/person.svg" width="20"> Войти
                            </button>
                            <div class="dropdown-menu" aria-labelledby="userMenuAnonym">
                                <a class="dropdown-item" href="${contextPath}/login">Вход</a>
                                <a class="dropdown-item" href="${contextPath}/admin/userRegistration">Регистрация</a>
                            </div>
                        </div>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>

</nav>