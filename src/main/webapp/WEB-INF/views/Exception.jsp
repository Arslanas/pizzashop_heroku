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
    <div class="row">
        <h1 class="mx-auto mt-5 mb-5 text-info">Извините, произошла ошибка</h1>
    </div>
    <div class="row">
        <a href="${contextPath}/" class="btn btn-warning mx-auto mt-5 mb-5">Продолжить</a>
    </div>
</div>


<!-- Footer -->
<%@ include file="templates/Footer_template.jsp"%>

</body>
</html>
