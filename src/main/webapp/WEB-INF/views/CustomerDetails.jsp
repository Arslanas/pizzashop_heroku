<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- TagLibs -->
<%@ include file="templates/TagLibs_template.jsp" %>

<html>
<head>
    <title>Customer details</title>

    <!-- Styles -->
    <%@ include file="templates/Css_template.jsp" %>

</head>
<body>

<!-- Navigation -->
<%@ include file="templates/NavBar_template.jsp"%>


<div class="container">
    <div class="row ">
        <!-- sidebar -->
        <div class="col-12 ">
            <div class="row">

                <div class="col-6 mx-auto mt-5">
                    <h1 class="text-center text-info">Ваши данные</h1>
                </div>

                <!-- Form -->
                <div class="col-12 mt-4 mb-5">
                    <sf:form id="formID" action="${contextPath}/products/customerDetails" method="POST" commandName="customer">
                        <div class="form-group">
                            <sf:label path="username" for="Name">Имя</sf:label>
                            <sf:input path="username"  type="text" class="form-control" id="Name" />
                            <sf:errors path="username" cssClass="alert-danger"/>
                        </div>
                        <div class="form-group">
                            <sf:input path="password" type="hidden" class="form-control" id="Password" />
                        </div>
                        <div class="form-group">
                            <sf:input path="contact.email" type="hidden" class="form-control" id="emailID" />
                        </div>
                        <div class="form-group">
                            <sf:label path="contact.phoneNum" for="Mobil">Мобильный тел.</sf:label>
                            <sf:input path="contact.phoneNum"  type="text" class="form-control" id="phoneNumID"/>
                            <sf:errors path="contact.phoneNum" cssClass="alert-danger"/>
                        </div>
                        <div class="form-group">
                            <sf:label path="address.streetHome" for="Address">Адрес</sf:label>
                            <sf:input path="address.streetHome" type="text" class="form-control" id="Address"/>
                            <sf:errors path="address.streetHome" cssClass="alert-danger"/>
                        </div>
                        <div class="form-inline justify-content-center mt-5">
                            <div class="form-group">
                                <label for="Appartment">Квартира</label>
                                <sf:input path="address.appartment" type="number" id = "Appartment" class="form-control ml-2"/>
                            </div>
                            <div class="form-group ml-5">
                                <label for="Level">Этаж</label>
                                <sf:input path="address.level" type="number" id = "Level" class="form-control ml-2"/>
                            </div>
                            <div class="form-group ml-5">
                                <label for="Entrance">Подьезд</label>
                                <sf:input path="address.entrance" type="number" id = "Entrance" class="form-control ml-2"/>
                            </div>
                        </div>
                        <div class="form-inline justify-content-center mt-5">
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">Подтвердить</button>
                                <a href="${contextPath}/products/shoppingCart" class="btn btn-light ml-3 ">Назад</a>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>
        </div>

    </div>
</div>


<!-- Footer -->
<%@ include file="templates/Footer_template.jsp"%>
<script src="${contextPath}/resources/custom_js/customerDetails.js"></script>
<script>$(document).ready(validateForm())</script>
</body>
</html>
