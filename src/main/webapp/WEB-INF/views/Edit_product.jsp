<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- TagLibs -->
<%@ include file="templates/TagLibs_template.jsp" %>

<html>
<head>
    <title>Homepage</title>

    <s:url value="${contextPath}/products/editProduct" var="path"></s:url>

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

        <div class="col ">
            <div class="row">

                <div class="col-12 mx-auto mt-5">
                    <h3 class="text-center font-weight-bold text-info">Добавить товар</h3>
                </div>


                <!-- Form -->
                <div class="col-11 mx-auto mt-4 mb-5">

                    <sf:form action="${path}" method="POST" modelAttribute="${item}" commandName="item"
                             enctype="multipart/form-data">

                        <div class="form-group">
                            <sf:label path="name" for="Name" class="font-weight-bold">Название товара</sf:label>
                            <sf:input path="name" type="text" class="form-control" id="Name"/>
                        </div>
                        <div class="form-group">
                            <label for="Description" class="font-weight-bold">Описание товара</label>
                            <sf:textarea path="description" class="form-control" id="Description"
                                         aria-label="With textarea"/>
                        </div>
                        <div class="form-group">
                            <sf:label path="price.amount" for="Price" class="font-weight-bold">Цена</sf:label>
                            <sf:input path="price.amount" type="text" class="form-control" id="Price"/>
                        </div>
                        <div class="form-group">
                            <sf:input path="id" type="hidden" id="Price"/>
                        </div>
                        <div class="form-group">
                            <label name="picture" for="pictureID"><img width="100"
                                                                       src="${contextPath}/products/image/${item.id}">
                            </label>
                            <input class="form-control-file" type="file" name="picture" accept="image/jpeg"
                                   id="pictureID"></div>

                        <div class="form-inline justify-content-center mt-5">
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary ">Подтвердить</button>
                                <button type="reset" class="btn btn-light ml-3 ">Отмена</button>
                            </div>
                        </div>
                    </sf:form>
                    <div class="col-2 ml-auto ">
                        <a href="${contextPath}/products/remove/${item.id}" class="btn btn-outline-danger ">Удалить</a>
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
