<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/appHeader.jsp" %>

<script>
    function confirmDelete(mealId, mealName, planId, planName) {
        if (confirm("Are you sure you want to remove meal \"" + mealName + "\" from plan \"" + planName + "\"")) {
            window.location.href = "/app/plan/remove/meal/" + planId + "/" + mealId;
        }
    }
</script>

<div class="m-4 p-3 width-medium ">
    <div class="dashboard-content border-dashed p-3 m-4">
        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
                <h3 class="color-header text-uppercase">DAILY PLAN DETAILS</h3>
            </div>
            <div class="col d-flex justify-content-end mb-2 noPadding">
                <a href="${pageContext.request.contextPath}/app/plan/list"
                   class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Back</a>
            </div>
        </div>
        <div class="schedules-content">
            <div class="schedules-content-header">
                <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Plan Name
                                </span>
                    <div class="col-sm-10">
                        <p class="schedules-text">
                            ${planDto.name}
                        </p>
                    </div>
                </div>
                <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Plan Description
                                </span>
                    <div class="col-sm-10">
                        <p class="schedules-text">
                            ${planDto.description}
                        </p>
                    </div>
                </div>
            </div>
            <table class="table">
                <thead>
                <tbody>
                <c:forEach items="${mealsInPlan}" var="meal">
                    <tr class="d-flex">
                        <td class="col-2">${meal.name}</td>
                        <td class="col-5">${meal.description}</td>
                        <td class="col-2 center">
                            <a href='/app/meal/details/${meal.id}'
                               class="btn btn-info rounded-0 text-light m-1">Details</a>
                        </td>
                        <td class="col-2 center">
                            <a href="#" onclick="confirmDelete(${meal.id},'${meal.name}', ${planDto.id},'${planDto.name}')"
                               class="btn btn-danger rounded-0 text-light m-1">Remove from plan</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>