<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/headerHomepage.jsp"%>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>

<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-25">
                 <h1 class="text-color-darker padding-small text-center">Register</h1>

                    <form:form method="post" modelAttribute="userEntity">
                        First Name: <form:input type="text" path="firstName"/>
                        <form:errors path="firstName" cssClass="error"/><br>
                        Last Name: <form:input type="text" path="lastName"/>
                        <form:errors path="lastName" cssClass="error"/><br>
                        Email: <form:input type="email" path="email"/>
                        <form:errors path="email" cssClass="error"/><br>
                        Password: <form:input type="text" path="password"/>
                        <form:errors path="password" cssClass="error"/><br>

                        <button class="btn btn-color rounded-0" type="submit">Register</button>
                    </form:form>
            </div>

        </div>
    </div>
</section>
<%@ include file="/WEB-INF/views/footer.jsp" %>

</body>
</html>