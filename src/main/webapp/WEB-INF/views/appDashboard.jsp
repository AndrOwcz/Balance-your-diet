<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/appHeader.jsp" %>

<div class="m-4 p-4 width-medium">
    <div class="dashboard-header m-4">
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
            <div class="alert-item alert-info">
                <i class="fas icon-circle fa-info-circle"></i>
                <span class="font-weight-bold">The amount of your meals: ${mealsCount}</span>
            </div>
            <div class="alert-item alert-light">
                <i class="far icon-calendar fa-calendar-alt"></i>
                <span class="font-weight-bold">The amount of your daily plans: ${plansCount}</span>
            </div>
            <div class="alert-item alert-success">
                <i class="fas icon-database fa-database"></i>
                <span class="font-weight-bold">The amount of all meals in Database: ${allmealsCount}</span>
            </div>
        </div>
    </div>

</div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>