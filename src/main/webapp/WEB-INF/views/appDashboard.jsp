<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/appHeader.jsp"%>
<div class="m-4 p-4 width-medium">
    <div class="dashboard-header m-4">
        <div class="dashboard-menu">
            <div class="menu-item border-dashed">
                <a href="/app/meal/add">
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">Add meal</span>
                </a>
            </div>
            <div class="menu-item border-dashed">
                <a href="/app/plan/add">
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">Add daily plan</span>
                </a>
            </div>
            <div class="menu-item border-dashed">
                <a href="/app/meal/plan/add">
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">Add meal to your plan</span>
                </a>
            </div>
        </div>

        <div class="dashboard-alerts">
            <div class="alert-item alert-info">
                <i class="fas icon-circle fa-info-circle"></i>
                <span class="font-weight-bold">The amount of your meals: ${mealCount}</span>
            </div>
            <div class="alert-item alert-light">
                <i class="far icon-calendar fa-calendar-alt"></i>
                <span class="font-weight-bold">The amount of your daily plans: ${planCount}</span>
            </div>
        </div>
    </div>

    <c:choose>
    <c:when test="${not empty planName}">
    <div class="m-4 p-4 border-dashed">
        <h2 class="dashboard-content-title">
            <span>Ostatnio dodany plan:</span> ${planName}
        </h2>
        <table class="table">

            <c:forEach items="${days}" var="day">
            <thead>
            <tr class="d-flex">
                <th class="col-2">${day}</th>
                <th class="col-8"></th>
                <th class="col-2"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${plan}" var="plan">
                <c:choose>
                    <c:when test="${plan[0].equals(day)}">
                        <tr class="d-flex">
                            <td class="col-2">${plan[1]}</td>
                            <td class="col-8">${plan[2]}</td>
                            <td class="col-2"><a href="/app/recipe/details?recipeId=${plan[4]}"><button type="button" class="btn btn-primary rounded-0">Szczegóły</button></a></td>
                        </tr>
                    </c:when>
                </c:choose>
            </c:forEach>
            </c:forEach>
            </tbody>
        </table>
        </c:when>
        </c:choose>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp"%>