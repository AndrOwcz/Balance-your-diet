<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/appHeader.jsp" %>

<div class="m-4 p-3 width-medium">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">

        <form:form method="post" modelAttribute="productDto">
            <div class="row border-bottom border-3 p-1 m-1">
                <div class="col noPadding">
                    <h3 class="color-header text-uppercase">NEW PRODUCT</h3>
                </div>
                <div class="col d-flex justify-content-end mb-2 noPadding">
                    <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Save</button>
                </div>
            </div>

            <div class="schedules-content">

                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">
                        Product name
                    </label>
                    <div class="col-sm-2 col-form-label">
                        <form:input type="text" cssClass="form-control" placeholder="Product name" path="name"/>
                        <form:errors path="name" cssClass="error"/><br>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">
                        Product calories
                    </label>
                    <div class="col-sm-2 col-form-label">
                        <form:input type="number" min="0" placeholder="Product calories" path="calories"/>
                        <form:errors path="calories" cssClass="error"/><br>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">
                        Product calories
                    </label>
                    <div class="col-sm-2 col-form-label">
                        <form:input type="number" min="0" placeholder="Product carbs" path="carbs"/>
                        <form:errors path="carbs" cssClass="error"/><br>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">
                        Product calories
                    </label>
                    <div class="col-sm-2 col-form-label">
                        <form:input type="number" min="0" placeholder="Product fats" path="fats"/>
                        <form:errors path="fats" cssClass="error"/><br>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">
                        Product calories
                    </label>
                    <div class="col-sm-2 col-form-label">
                        <form:input type="number" min="0" placeholder="Product protein" path="protein"/>
                        <form:errors path="protein" cssClass="error"/><br>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">
                        Category:
                    </label>
                    <div class="col-sm-2 col-form-label">
                        <form:select path="categoryDto.id" itemValue="id" itemLabel="name" items="${categories}"/>
                        <br>
                        <form:errors path="categoryDto.id" cssClass="error"/> <br>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>