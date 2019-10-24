<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/appHeader.jsp" %>

<div class="m-4 p-3 width-medium">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">

        <form:form method="post" modelAttribute="plan">
            <div class="row border-bottom border-3 p-1 m-1">
                <div class="col noPadding">
                    <h3 class="color-header text-uppercase">NEW PLAN</h3>
                </div>
                <div class="col d-flex justify-content-end mb-2 noPadding">
                    <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Save</button>
                </div>
            </div>

            <div class="schedules-content">

                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">
                        Plan name
                    </label>
                    <div class="col-sm-10">
                        <form:input type="text" cssClass="form-control" placeholder="Plan name" path="name"/>
                        <form:errors path="name" cssClass="error"/><br>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">
                        Plan description
                    </label>
                    <div class="col-sm-10">
                        <form:textarea type="text" cssClass="form-control" placeholder="Plan description" rows="3" path="description"/>
                        <form:errors path="description" cssClass="error"/><br>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">
                        Meals:
                    </label>
                    <div class="col-sm-10">
                        <form:select path="mealDtos" itemValue="id" itemLabel="name" items="${meals}"/>
                        <br>
                        <form:errors path="mealDtos" cssClass="error"/> <br>
                    </div>
                </div>



            </div>
        </form:form>
    </div>
</div>
</div>
</section>


</body>
</html>