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
<%@ include file="templates/NavBar_template.jsp" %>


<div class="container">
    <div class="row ">
        <div class="col-12 ">
            <div class="row">

                <div class="col-12  mx-auto mt-5 jumbotron">
                    <h1 class="text-center text-info jumbotron">Вы успешно зарегистрированы!</h1>
                </div>
            </div>
        </div>
        <div class="col-12 mb-5">
            <div class="row justify-content-center">
                <div class="col-1">
                    <a href="${contextPath}/" class="btn btn-warning">OK</a>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Footer -->
<%@ include file="templates/Footer_template.jsp" %>

</body>
</html>
