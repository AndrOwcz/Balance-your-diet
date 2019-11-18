<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/appHeader.jsp" %>

<div class="m-4 p-3 width-medium text-color-darker">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <form:form method="post" modelAttribute="mealDto">
        <div class="mt-4 ml-4 mr-4">
            <div class="row border-bottom border-3">
                <div class="col"><h3 class="color-header text-uppercase">New Meal</h3></div>
                <div class="col d-flex justify-content-end mb-2">
                    <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Save</button>
                </div>
            </div>
            <br>
            <br>

            <div class="form-group row">
                <label class="col-sm-2 label-size col-form-label">
                    Name
                </label>
                <div class="col-sm-4">
                    <form:input type="text" cssClass="form-control" placeholder="Meal name" path="name"/>
                    <form:errors path="name" cssClass="error"/><br>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 label-size col-form-label">
                    Description
                </label>
                <div class="col-sm-4">
                    <form:textarea type="text" cssClass="form-control" placeholder="Meal description" rows="3"
                                   path="description"/>
                    <form:errors path="description" cssClass="error"/><br>
                </div>
            </div>
            </form:form>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>