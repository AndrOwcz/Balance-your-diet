<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/headerHomepage.jsp" %>

<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-25">
                    <h1 class="text-color-darker padding-small text-center">Login</h1>

                    <form:form method="post" modelAttribute="userDto">
                <div style="text-align:center;">
                        Email: <form:input type="email" path="email"/>
                        <form:errors path="email" cssClass="error"/><br>
                </div>
                <div style="text-align:center;">
                        Password: <form:input type="text" path="password"/>
                        <form:errors path="password" cssClass="error"/><br>
            </div>
                        <c:if test="${loginFailed == true}">
                            <div class="error">Login failed, try again</div>
                        </c:if>
                        <br>
                <div style="text-align:center;">
                        <button class="btn btn-color rounded-0 center"  type="submit">Login</button>

                    </form:form>

            </div>
        </div>
    </div>
</section>
<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>

