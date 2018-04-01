<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                <c:forEach items="${categories}" var="category">
                    <a href="${contextPath}/products/${category.id}"
                       class="btn btn-outline-info bg-info btn-block text-white ">${category.name}</a>
                </c:forEach>
                <sec:authorize access="hasAuthority('ADMIN')">
                    <a href="${contextPath}/admin/addProduct" class="btn btn-outline-info bg-info btn-block text-white">Add
                        product</a>
                    <a href="${contextPath}/admin/userManagement"
                       class="btn btn-outline-info bg-info btn-block text-white ">All
                        users</a>
                </sec:authorize>
            </div>
        </div>
    </div>


</div>