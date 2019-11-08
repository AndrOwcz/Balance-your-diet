<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/appHeader.jsp" %>

<script>
    function confirmRemoveProduct(mealId, prodPortId, name) {
        if (confirm("Are you sure you want to remove \"" + name + "\" from your meal products?")) {
            window.location.href = "/app/meal/remove/prodPortion/" + mealId + "/" + prodPortId;
        }
    }
</script>

<div class="m-4 p-3 width-medium">
    <form:form method="post" modelAttribute="mealDto">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <form>
            <div class="row border-bottom border-3 p-1 m-1">
                <div class="col noPadding">
                    <h3 class="color-header text-uppercase">EDIT MEAL PRODUCTS</h3>
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
                    <div class="col-sm-5">
                        <form:input type="text" cssClass="form-control" placeholder="${mealDto.name}" path="name"/>
                        <form:errors path="name" cssClass="error"/><br>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">
                        Plan description
                    </label>
                    <div class="col-sm-5">
                        <form:textarea type="text" cssClass="form-control" placeholder="${mealDto.description}" rows="3"
                                       path="description"/>
                        <form:errors path="description" cssClass="error"/><br>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 label-size col-form-label">
                        Products in meal
                    </label>

                    <table class="table border-bottom schedules-content">
                        <c:forEach items="${productPortionsInMeal}" var="productPortion" varStatus="loop">
                            <tbody class="text-color-black">
                            <tr class="d-flex">
                                <th scope="row" class="col-1">${loop.count}</th>
                                <td class="col-3">
                                        ${productPortion.productDto.name}
                                </td>
                                <td class="col-3">${productPortion.portion*100}g</td>
                                <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                                    <a href="/app/meal/products/edit/${meal.id}"
                                       class="btn btn-warning rounded-0 text-light m-1">Edit portion</a></td>
                                <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                                    <a href="#"
                                       onclick="confirmRemoveProduct(${mealDto.id}, ${productPortion.id}, '${productPortion.productDto.name}')"
                                       class="btn btn-danger rounded-0 text-light m-1">Remove product</a>
                                </td>
                            </tr>
                            <br>
                            </tbody>
                        </c:forEach>
                    </table>
                    <br>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">
                        Meals:
                    </label>
                    <div class="col-sm-3">
                        <form:select path="newProductPortionDto.productDto.id" itemValue="id" itemLabel="name"
                                     items="${products}"/>
                        <br>
                        <form:errors path="newProductPortionDto.productDto.id" cssClass="error"/> <br>
                    </div>
                    <div class="col-sm-3">
                        <form:input path="newProductPortionDto.portion" type="number" min="0" step="any"
                                    placeholder="0"/> * 100g
                        <br>
                        <form:errors path="newProductPortionDto.portion" cssClass="error"/> <br>
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