<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/appHeader.jsp" %>


<div class="m-4 p-3 width-medium text-color-darker">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <div class="mt-4 ml-4 mr-4">
            <div class="row border-bottom border-3">
                <div class="col"><h3 class="color-header text-uppercase">MEAL DETAILS</h3></div>
                <div class="col d-flex justify-content-end mb-2"><a
                        href="${pageContext.request.contextPath}/app/meal/list"
                        class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Back</a></div>
            </div>
            <br>
            <table class="table borderless">
                <tbody>
                <tr class="d-flex">
                    <th scope="row" class="col-2">Name</th>
                    <td class="col-7">
                        ${mealDto.name}
                    </td>
                </tr>
                <tr class="d-flex">
                    <th scope="row" class="col-2">Meal description</th>
                    <td class="col-7">${mealDto.description}</td>
                </tr>
                <tr class="d-flex">
                    <th scope="row" class="col-2">Calories</th>
                    <td class="col-7">
                        ${mealDto.mealCalories}
                    </td>
                </tr>
                <tr class="d-flex">
                    <th scope="row" class="col-2">Carbs</th>
                    <td class="col-7">
                        ${mealDto.mealCalories}
                    </td>
                </tr>
                <tr class="d-flex">
                    <th scope="row" class="col-2">Fats</th>
                    <td class="col-7">
                        ${mealDto.mealFats}
                    </td>
                </tr>
                <tr class="d-flex">
                    <th scope="row" class="col-2">Protein</th>
                    <td class="col-7">
                        ${mealDto.mealProtein}
                    </td>
                </tr>
                <tr class="d-flex">
                    <th scope="row" class="col-2">Author</th>
                    <td class="col-7">
                        <c:choose>
                            <c:when test="${empty mealDto.userDto}">
                                ---
                            </c:when>
                            <c:otherwise>
                                ${mealDto.userDto.firstName} ${mealDto.userDto.lastName}
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                </tbody>
            </table>

            <br><br>
            <div class="row d-flex">
                <div class="col-3 border-bottom border-3"><h5 class="text-uppercase">Comments</h5></div>
            </div>
            <div class="row d-flex">
                <ul class="col-5 p-4 list-unstyled">
                    <c:forEach items="${commentsOfMeal}" var="comment" varStatus="loop">
                        <li>${comment.content}</li>
                        <li class="font-italic">~~ ${comment.userDto.firstName} ${comment.userDto.lastName}</li>
                        <br>
                    </c:forEach>
                </ul>
            </div>

        </div>
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