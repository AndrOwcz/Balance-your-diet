<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/appHeader.jsp" %>

<script>
    function checkIfCopy(mealId, name, userId, mealOwnerId) {

        if (userId !== mealOwnerId) {
            window.location.href = "/app/meal/copy/" + mealId;
        } else {
            alert("This is already your meal.");
            window.location.href = "/app/meal/all";
        }
    }
</script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<div class="m-4 p-3 width-medium">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding"><h3 class="color-header text-uppercase">All meals</h3></div>
            <div class="col noPadding d-flex justify-content-end mb-2"><a
                    href="${pageContext.request.contextPath}/app/meal/add"
                    class="btn btn-primary rounded-0 pt-1 pb-0 pr-4 pl-4">Add new meal</a></div>
        </div>
        <table class="table border-bottom schedules-content">
            <thead>
            <tr class="d-flex text-color-darker">
                <th scope="col" class="col-1">MEAL NO.</th>
                <th scope="col" class="col-2">NAME</th>
                <th scope="col" class="col-3">DESCRIPTION</th>
                <th scope="col" class="col-2">MEAL AUTHOR</th>
                <th scope="col" class="col-3 center">ACTIONS</th>
                <th scope="col" class="col-1 center">SHARE</th>
            </tr>
            </thead>
            <c:forEach items="${allMeals}" var="meal" varStatus="loop">
                <tbody class="text-color-lighter" style="font-size:20px;">
                <tr class="d-flex">
                    <th scope="row" class="col-1">${loop.count}</th>
                    <td class="col-2">
                            ${meal.name}
                    </td>
                    <td class="col-3">${meal.description}</td>
                    <td class="col-2">${meal.userDto.firstName} ${meal.userDto.lastName}</td>
                    <td class="col-3 d-flex align-items-center justify-content-center flex-wrap">
                        <a href="/app/meal/details/${meal.id}" class="btn btn-info rounded-0 text-light m-1">Details</a>
                        <a href="#" onclick="checkIfCopy(${meal.id}, '${meal.name}',${userDto.id}, ${meal.userDto.id})"
                           class="btn btn-success rounded-0 text-light m-1">Copy to your meals</a>
                        <a href="#" onclick="addComment(${meal.id}, '${meal.name}')"
                           class="btn btn-warning rounded-0 text-light m-1">Comment</a>
                    </td>
                    <td class="col-1 d-flex align-items-center justify-content-center flex-wrap ">
                        <a href="https://www.facebook.com/sharer/sharer.php?u=" title="Share on Facebook"
                           target="_blank" class="btn btn-facebook"><i class="fab fa-facebook"></i></a>
                        <div class="divider"></div>
                        <a href="http://www.twitter.com/share?url=" title="Share on Twitter" target="_blank"
                           class="btn btn-twitter"><i class="fab fa-twitter"></i></a>

                    </td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
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

<script>
    function addComment(mealId, name) {
        var newComment = prompt("Enter new comment for " + name);
        fetch("/app/meal/comment/" + mealId, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            method: 'post',
            body: 'newComment='+ newComment
        }).then(function(response) {
            return console.log(response);
        });
    }
</script>