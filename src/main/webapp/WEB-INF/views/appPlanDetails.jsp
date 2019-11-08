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
                        <td class="col-1 center">
                            <a href='/app/meal/details/${meal.id}'
                               class="btn btn-info rounded-0 text-light m-1">Details</a>
                        </td>
                        <td class="col-1 center">
                            <a href='/app/meal/products/edit/${meal.id}'
                               class="btn btn-warning rounded-0 text-light m-1">Edit meal</a>
                        </td>
                        <td class="col-1 center">
                            <a href="#"
                               onclick="confirmDelete(${meal.id},'${meal.name}', ${planDto.id},'${planDto.name}')"
                               class="btn btn-danger rounded-0 text-light m-1">Remove from plan</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <h3>Plan charts</h3>

            <table>

                <tr>
                    <td class="col-sm-2" id="column chart calories">

                    <td class="col-sm-2" id="column chart carbs"></td>
                </tr>

                <tr>
                    <td class="col-sm-2" id="column chart fats">

                    <td class="col-sm-2" id="column chart protein"></td>
                </tr>


            </table>


            <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

            <script type="text/javascript">

                google.charts.load('current', {'packages': ['corechart']});
                google.charts.setOnLoadCallback(drawChart);

                function drawChart() {
                    var data = google.visualization.arrayToDataTable([
                        ['Data', 'Calories', {role: 'style'}],
                        ['Required calories', ${userDto.requiredCalories}, '#3498db'],
                        ['Plan calories', ${planCalories}, '#3498db']
                    ]);

                    var options = {
                        'title': 'Calories', 'width': 550, 'height': 400,
                        vAxis: {
                            viewWindow: {
                                min: 0
                            }
                        },
                        legend:{position: 'bottom'},
                        colors: ['#3498db', 'blue']
                    };

                    var chart = new google.visualization.ColumnChart(document.getElementById('column chart calories'));
                    chart.draw(data, options);
                }
            </script>

            <script type="text/javascript">

                google.charts.load('current', {'packages': ['corechart']});
                google.charts.setOnLoadCallback(drawChart);

                function drawChart() {
                    var data = google.visualization.arrayToDataTable([
                        ['Data', 'Carbs', {role: 'style'}],
                        ['Req carbs', ${userDto.requiredCarbs}, '#f1948a'],
                        ['Plan carbs', ${planCarbs}, '#f1948a']
                    ]);

                    var options = {
                        'title': 'Carbs', 'width': 550, 'height': 400,
                        vAxis: {
                            viewWindow: {
                                min: 0
                            }
                        },
                        legend:{position: 'bottom'},
                        colors: ['#f1948a', 'blue']
                    };

                    var chart = new google.visualization.ColumnChart(document.getElementById('column chart carbs'));
                    chart.draw(data, options);
                }
            </script>

            <script type="text/javascript">

                google.charts.load('current', {'packages': ['corechart']});
                google.charts.setOnLoadCallback(drawChart);

                function drawChart() {
                    var data = google.visualization.arrayToDataTable([
                        ['Data', 'Fats', {role: 'style'}],
                        ['Req fats', ${userDto.requiredFats}, '#f7dc6f'],
                        ['Plan fats', ${planFats}, '#f7dc6f']
                    ]);

                    var options = {
                        'title': 'Fats', 'width': 550, 'height': 400,
                        vAxis: {
                            viewWindow: {
                                min: 0
                            }
                        },
                        legend:{position: 'bottom'},
                        colors: ['#f7dc6f', 'blue']
                    };

                    var chart = new google.visualization.ColumnChart(document.getElementById('column chart fats'));
                    chart.draw(data, options);
                }
            </script>

            <script type="text/javascript">

                google.charts.load('current', {'packages': ['corechart']});
                google.charts.setOnLoadCallback(drawChart);

                function drawChart() {
                    var data = google.visualization.arrayToDataTable([
                        ['Data', 'Protein', {role: 'style'}],
                        ['Req protein', ${userDto.requiredProtein}, '#58d68d'],
                        ['Plan protein', ${planProtein}, '#58d68d']
                    ]);

                    var options = {
                        'title': 'Protein', 'width': 550, 'height': 400,
                        vAxis: {
                            viewWindow: {
                                min: 0
                            }
                        },
                        legend:{position: 'bottom'},
                        colors: ['#58d68d', 'blue']
                    };

                    var chart = new google.visualization.ColumnChart(document.getElementById('column chart protein'));
                    chart.draw(data, options);
                }
            </script>
        </div>
    </div>
</div>