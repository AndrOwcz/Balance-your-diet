<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/headerHomepage.jsp" %>

<section>
    <div class="row padding-small">
        <i class="fas fa-users icon-users"></i>
        <h1>About app:</h1>
        <hr>
        <div class="orange-line w-100"></div>
    </div>
</section>

<section class="mr-4 ml-4">
    <div class="container">
        <div class="row">
            <div class="col">
                <h3 class="mb-4">Lose Weight with BalanceYourDiet — for FREE!</h3>
                <p>"With a FREE membership you get:
                    <br><i class="fas fa-check"></i>

                    The easiest to use food diary on the web -

                    Track what you eat with just a few clicks from anywhere with an internet connection - at home or at
                    work
                    <br><i class="fas fa-check"></i>
                    A searchable food database of over 10 items -

                    and it's growing every day!
                    <br><i class="fas fa-check"></i>
                    Your own personal food database -

                    add your own foods and recipes at any time and access them from anywhere with an internet connection
                    <br><i class="fas fa-check"></i>
                    Support and motivation

                    from people just like you - Our comment system let you learn from others, share your own tips,
                    receive and give encouragement, and make friends.
                    <br><i class="fas fa-check"></i>
                    A personalized diet profile

                    - customized to your unique weight loss goals.
                    "</p>
            </div>
        </div>
        <br><br>
        <div class="text-center">
            <p>Join today and get on the path to healthier living!</p>
            <p>
                <button id="registerNow" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Register NOW !</button>
            </p>
        </div>
    </div>
</section>

<script type="text/javascript">
    document.getElementById("registerNow").onclick = function () {
        window.location.href = "/register";
    };
</script>

<%@ include file="/WEB-INF/views/footer.jsp" %>

