<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/appHeader.jsp" %>


<div class="m-4 p-3 width-medium">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding"><h3 class="color-header text-uppercase">Plan List</h3></div>
            <div class="col noPadding d-flex justify-content-end mb-2"><a href="/app/plan/add"
                                                                          class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Add new plan</a></div>
        </div>
        <table class="table border-bottom schedules-content">
            <thead>
            <tr class="d-flex text-color-darker">
                <th scope="col" class="col-1">PLAN NO.</th>
                <th scope="col" class="col-2">NAME</th>
                <th scope="col" class="col-6">DESCRIPTION</th>
                <th scope="col" class="col-3 center">ACTIONS</th>
            </tr>
            </thead>

            <c:forEach items="${allPlans}" var="plan" varStatus="loop">
                <tbody class="text-color-lighter">
                <tr class="d-flex">
                    <th scope="row" class="col-1">${loop.count}</th>
                    <td class="col-2">
                            ${plan.name}
                    </td>
                    <td class="col-6">${plan.description}</td>
                    <td class="col-3 d-flex align-items-center justify-content-center flex-wrap">
                        <a href="/app/plan/details?id=${plan.id}"
                           class="btn btn-info rounded-0 text-light m-1">Details</a>
                        <a href="/app/plan/edit?id=${plan.id}" class="btn btn-warning rounded-0 text-light m-1">Edit</a>
                        <a href="/app/plan/delete?id=${plan.id}"
                           class="btn btn-danger rounded-0 text-light m-1">Delete</a>
                    </td>
                </tr>
                </tbody>
            </c:forEach>

        </table>
    </div>
</div>
</div>
</section>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>