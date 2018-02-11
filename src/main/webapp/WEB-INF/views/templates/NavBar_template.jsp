<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-info fixed-top ">

    <div class="container">
        <a href="${contextPath}/products" class="navbar-brand font-weight-bold display-4 " href="#">Pizza Shop</a>

        <a href="${contextPath}/products/shoppingCart" class="btn btn-warning  mx-auto  "><img
                src="${contextPath}/resources/vendor/images/cart.svg"
                width="20"> Ваш заказ : ${cart.finalPrice} р
        </a>
        <ul class="navbar-nav ml-auto">
            <li>
                <sf:form method="get" action="${contextPath}/products/search" class="form-inline mt-5 mt-md-0">
                    <input class="form-control mr-sm-2" name="searchString" type="text" placeholder="Поиск"  aria-label="Search">
                    <button class="btn  btn-warning my-2 my-sm-0" type="submit">Найти</button>
                </sf:form>
            </li>
            <li class="nav-item active ml-3">
                <button class="btn  btn-warning  my-2 my-sm-0 " type="submit"><img
                        src="${contextPath}/resources/vendor/images/person.svg" width="20"> Войти
                </button>
            </li>
        </ul>
    </div>

</nav>