<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/appHeader.jsp" %>

<div class="m-4 p-3 width-medium text-color-darker">
    <div class="m-4 border-dashed view-height">
        <form action="${pageContext.request.contextPath}/app/user/edit" method="POST">
            <input type="hidden" name="adminId" value="${userDto.id}">
            <div class="mt-4 ml-4 mr-4">
                <div class="row border-bottom border-3">
                    <div class="col"><h3 class="color-header text-uppercase">Edit password</h3></div>
                    <div class="col d-flex justify-content-end mb-2">
                        <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Save changes
                        </button>
                    </div>
                </div>

                <table class="table borderless">
                    <tbody>
                    <tr class="d-flex">
                        <br>
                        <br>

                        <form:form method="post" modelAttribute="userDto">
                        <th scope="row" class="col-2"><h4>Old password</h4></th>
                        <td class="col-7">
                            <form:input cssClass="w-100 p-1" type="text" path="password"/>
                            <form:errors path="password" cssClass="error"/><br>
                        </td>
                    </tr>
                    <tr class="d-flex">
                        <th scope="row" class="col-2"><h4>New password</h4></th>
                        <td class="col-7">
                            <form:input cssClass="w-100 p-1" type="text" path="newPassword"/>
                            <form:errors path="newPassword" cssClass="error"/><br>
                        </td>
                    </tr>

                    <c:if test="${editUserDataFailed == true}">
                        <div class="error">Something went wrong, try again</div>
                    </c:if>
                    </form:form>
                    </tbody>
                </table>
            </div>
        </form>
    </div>
</div>
</div>
</section>
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