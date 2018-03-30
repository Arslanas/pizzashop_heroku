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
<%@ include file="templates/NavBar_template.jsp"%>


<div class="container">
    <div class="row ">
        <!-- sidebar -->
        <%@ include file="templates/Sidebar_template.jsp"%>

        <div class="col ">
            <div class="row">

                <div class="col-12 mx-auto mt-5">
                    <h3 class="text-center font-weight-bold text-info">Добавить товар</h3>
                </div>


                <!-- Form -->
                <div class="col-11 mx-auto mt-4 mb-5">

                    <sf:form action="${path}" method="post" modelAttribute="${item}" commandName="item" enctype="multipart/form-data">

                        <div class="form-group">
                            <sf:label path="name" for="Name" class="font-weight-bold">Название товара</sf:label>
                            <sf:input path="name" type="text" class="form-control" id="Name"></sf:input>
                        </div>
                        <div class="form-group">
                            <label for="Description" class="font-weight-bold">Описание товара</label>
                            <sf:textarea path="description" class="form-control" id="Description" aria-label="With textarea"></sf:textarea>
                        </div>
                        <div class="form-group">
                            <sf:label path="price" for="Price" class="font-weight-bold">Цена</sf:label>
                            <sf:input path="price" type="number"  class="form-control" id="Price"></sf:input>
                        </div>

                        <sf:input path="id" type="hidden" id="Price"></sf:input>


                        <div class="form-group">
                            <label for="exampleFormControlFile1" class="font-weight-bold">Загрузить изображение</label>
                            <input type="file" name="picture" accept="image/jpeg" class="form-control-file" id="exampleFormControlFile1">
                        </div>

                        <div class="form-inline justify-content-center mt-5">
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary ">Подтвердить</button>
                                <button type="reset" class="btn btn-light ml-3 ">Отмена</button>
                            </div>
                        </div>

                    </sf:form>
                    <div class="col-2 ml-auto ">
                        <a href="${contextPath}/products/remove/${item.id}"  class="btn btn-outline-danger ">Удалить</a>
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
