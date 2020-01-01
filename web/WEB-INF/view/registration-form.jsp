<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register New User Form</title>

    <style>
        .error{
            color: red;
        }
    </style>
</head>

<body>

    <h3>Register New User</h3>

    <form:form action="${pageContext.request.contextPath}/register/processRegistrationForm"  modelAttribute="crmUser">

        <c:if test="${registrationError != null}">

            <i class="error">${registrationError}</i>

        </c:if>

        <!-- User name -->
        <form:errors path="userName" cssClass="error" />
        <form:input path="userName" placeholder="username (*)" />

        <br><br>

        <!-- Password -->
        <form:errors path="password" cssClass="error" />
        <form:password path="password" placeholder="password (*)" />

        <br><br>

        <!-- Confirm Password -->
        <form:errors path="matchingPassword" cssClass="error" />
        <form:password path="matchingPassword" placeholder="confirm password (*)" />

        <br><br>

        <!-- First name -->
        <form:errors path="firstName" cssClass="error" />
        <form:input path="firstName" placeholder="first name (*)" />

        <br><br>

        <!-- Last name -->
        <form:errors path="lastName" cssClass="error" />
        <form:input path="lastName" placeholder="last name (*)" />

        <br><br>

        <!-- Email -->
        <form:errors path="email" cssClass="error" />
        <form:input path="email" placeholder="email (*)" />

        <br><br>

        <form:select path="formRole" items="${roles}" />

        <br><br>

        <button type="submit">Register</button>

    </form:form>

</body>
</html>
