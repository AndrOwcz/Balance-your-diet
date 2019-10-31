<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/appHeader.jsp" %>


<div class="m-4 p-3 width-medium">
    <form:form method="post" modelAttribute="planDto">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <form>
            <div class="row border-bottom border-3 p-1 m-1">
                <div class="col noPadding">
                    <h3 class="color-header text-uppercase">EDIT PLAN</h3>
                </div>
                <div class="col d-flex justify-content-end mb-2">
                    <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Save</button>
                </div>
            </div>

            <div class="schedules-content">

                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">
                        Plan name
                    </label>
                    <div class="col-sm-10">
                        <form:input type="text" cssClass="form-control" placeholder="${planDto.name}" path="name"/>
                        <form:errors path="name" cssClass="error"/><br>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">
                        Plan description
                    </label>
                    <div class="col-sm-10">
                        <form:textarea type="text" cssClass="form-control" placeholder="${planDto.description}" rows="3"
                                       path="description"/>
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

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>