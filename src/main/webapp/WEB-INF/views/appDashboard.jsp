<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/appHeader.jsp" %>
<html>
<body>
<div class="m-4 p-4 width-medium" id="wrap">
    <div class="dashboard-header col-md-11" id="main">
        <div class="dashboard-menu">
            <div class="menu-item border-dashed">
                <a href="${pageContext.request.contextPath}/app/meal/add">
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">Add meal</span>
                </a>
            </div>
            <div class="menu-item border-dashed">
                <a href="${pageContext.request.contextPath}/app/plan/add">
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">Add daily plan</span>
                </a>
            </div>
        </div>

        <div class="dashboard-alerts">
            <div class="alert-item alert-primary">
                <i class="fas icon-circle fa-info-circle"></i>
                <span class="font-weight-bold">The amount of your meals: ${mealsCount}</span>
            </div>
            <div class="alert-item alert-secondary">
                <i class="far icon-calendar fa-calendar-alt"></i>
                <span class="font-weight-bold">The amount of your daily plans: ${plansCount}</span>
            </div>
            <div class="alert-item alert-success">
                <i class="fas icon-pizza-slice fa-pizza-slice"></i>

                <span class="font-weight-bold">The amount of all meals in Database: ${allMealsCount}</span>
            </div>
            <div class="alert-item alert-warning">
                <i class="fas icon-receipt fa-receipt"></i>
                <span class="font-weight-bold">The amount of all products in Database: ${numberOfProducts}</span>
            </div>
        </div>
    </div>

</div>
</body>

<%@ include file="/WEB-INF/views/footer.jsp" %>
</html>