<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/appHeader.jsp" %>


<div class="m-4 p-3 width-medium text-color-darker">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <div class="mt-4 ml-4 mr-4">
            <div class="row border-bottom border-3">
                <div class="col"><h3 class="color-header text-uppercase">MEAL DETAILS</h3></div>
                <div class="col d-flex justify-content-end mb-2">
                    <a href="${pageContext.request.contextPath}/app/meal/list"
                       class="btn btn-secondary rounded-0 pt-1 pb-0 pr-4 pl-4">Back to your meals</a></div>
                <div class="col d-flex justify-content-end mb-2">
                    <a href="${pageContext.request.contextPath}/app/meal/all"
                       class="btn btn-secondary rounded-0 pt-1 pb-0 pr-4 pl-4">Back to all meals</a></div>
            </div>
            <br>
            <table class="table borderless">
                <tbody>
                <tr class="justify-content-start">
                    <th scope="row" class="col-2">Name</th>
                    <td class="col-3">
                        ${mealDto.name}
                    </td>
                    <td class="col-3 p-2" rowspan="20" id="piechart">
                    </td>
                </tr>
                <tr>
                    <th scope="row" class="col-2">Meal description</th>
                    <td class="col-3">${mealDto.description}</td>
                </tr>
                <tr>
                    <th scope="row" class="col-2">Calories</th>
                    <td class="col-3">
                        ${mealDto.mealCalories} kcal
                    </td>
                </tr>
                <tr>
                    <th scope="row" class="col-2">Carbs</th>
                    <td class="col-3">
                        ${mealDto.mealCarbs} g
                    </td>
                </tr>
                <tr>
                    <th scope="row" class="col-2">Fats</th>
                    <td class="col-3">
                        ${mealDto.mealFats} g
                    </td>
                </tr>
                <tr>
                    <th scope="row" class="col-2">Protein</th>
                    <td class="col-3">
                        ${mealDto.mealProtein} g
                    </td>
                </tr>
                <tr>
                    <th scope="row" class="col-2">Author</th>
                    <td class="col-3">
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

                <tr>
                    <th scope="row" class="col-2">Products and portions</th>
                    <td class="col-3">
                        <c:forEach var="productPortion" items="${mealProductPortions}">

                            ${productPortion.productEntity.name} ${productPortion.portion*100} g
                            <p></p>
                        </c:forEach>
                    </td>

                </tr>
                </tbody>
            </table>
            <br><br>
            <div>
                <div class="col-3 border-bottom border-3"><h5 class="text-uppercase">Comments</h5></div>
            </div>
            <div>
                <ul class="col-3 p-4 list-unstyled">
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

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">

    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Nutrient', 'Amount', {role: 'style'}],
            ['Meal carbs', ${mealDto.mealCarbs}, '#3498db'],
            ['Meal fats', ${mealDto.mealFats}, '#3498db'],
            ['Meal protein', ${mealDto.mealProtein}, '#3498db']
        ]);

        var options = {'title':'Meal nutrients', 'width':500, 'height':350};

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
    }
</script>