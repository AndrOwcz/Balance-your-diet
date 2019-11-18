<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/headerHomepage.jsp" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-100">
                <h1 class="text-color-darker padding-small text-center">Login</h1>

                <form:form method="post" modelAttribute="userDto">
                    <div style="text-align:center;font-size:30px;">
                        Email:
                        <br>
                        <div style="text-align:center;font-size:20px;">
                        <form:input type="email" path="username"/>
                        <form:errors path="username" cssClass="error"/><br>
                    </div>
                    </div>
                    <br>
                    <div style="text-align:center;font-size:30px;">
                        Password:
                        <br>
                        <div style="text-align:center;font-size:20px;">
                        <form:input type="password" path="password"/>
                        <form:errors path="password" cssClass="error"/><br>
                    </div>
                    </div>
                    <c:if test="${loginFailed == true}">
                        <div class="error" style="text-align:center;font-size:30px;">Login failed, try again</div>
                    </c:if>
                    <br>
                    <div style="text-align:center">
                        <button class="btn btn-color rounded-0 center" type="submit">Login</button>
                    </div>
                </form:form>

            </div>
        </div>
    </div>
</section>
<%@ include file="/WEB-INF/views/footer.jsp" %>

