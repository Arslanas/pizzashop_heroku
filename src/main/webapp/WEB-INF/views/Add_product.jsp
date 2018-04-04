<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- TagLibs -->
<%@ include file="templates/TagLibs_template.jsp" %>

<html>
<head>
    <title>Add product</title>
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

                    <sf:form id="addProductForm" action="${contextPath}/admin/addProduct" method="POST"
                             commandName="item" enctype="multipart/form-data">

                        <div class="form-group">
                            <sf:label path="name" for="Name" class="font-weight-bold">Название товара</sf:label>
                            <sf:input path="name" type="text" class="form-control" id="Name"></sf:input>
                            <sf:errors path="name" cssClass="alert-danger"/>
                        </div>
                        <div class="form-group">
                            <sf:label path="categorizedItems" for="Category"
                                      class="font-weight-bold">Категория</sf:label>
                            <sf:select path="categorizedItems" id="Category" class="form-control" multiple="true">
                                <sf:options items="${categoryName}"/>
                            </sf:select>
                            <sf:errors path="categorizedItems" cssClass="alert-danger"/>
                        </div>
                        <div class="form-group">
                            <label for="Description" class="font-weight-bold">Описание товара</label>
                            <sf:textarea path="description" class="form-control" id="Description"
                                         aria-label="With textarea"></sf:textarea>
                            <sf:errors path="description" cssClass="alert-danger"/>
                        </div>
                        <div class="form-group">
                            <sf:label path="price.amount" for="Price" class="font-weight-bold">Цена</sf:label>
                            <sf:input path="price.amount" type="text" class="form-control" id="Price"></sf:input>
                            <sf:errors path="price" cssClass="alert-danger"/>
                        </div>

                        <div class="form-group">
                            <input type="file" name="picture" accept="image/jpeg" class="form-control-file"
                                   id="fileInputID">
                        </div>

                        <div class="form-inline justify-content-center mt-5">
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary ">Подтвердить</button>
                                <a href="${contextPath}/products" class="btn btn-light ml-3 ">Отмена</a>
                            </div>
                        </div>

                    </sf:form>

                </div>
            </div>
        </div>

    </div>
</div>


<!-- Footer -->
<%@ include file="templates/Footer_template.jsp" %>
<script src="${contextPath}/resources/vendor/Fileselect/src/bootstrap-fileselect.js"></script>
<script src="${contextPath}/resources/custom_js/AddProductScript.js"></script>
<script>$(document).ready(AddProductInit())</script>
</body>
</html>
