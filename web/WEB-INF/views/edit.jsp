<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contactr - edytuj kontakt</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>

<body>

<%@ include file="/WEB-INF/views/fragments/navbar.jspf" %>

<div class="container">
    <div class="col-md-8 col-md-offset-2">
        <form class="form-signin" method="post" action="edit">
            <h2 class="form-signin-heading">Edytuj kontakt</h2>
            <input name="inputID" type="text" class="form-control" value="${contact.id}" style="display: none"/>
            <input name="inputName" type="text" class="form-control" placeholder="Podaj imię" value="${contact.name}" required autofocus />
            <input name="inputSurname" type="text" class="form-control" placeholder="Podaj nazwisko" value="${contact.surname}" required autofocus />
            <input name="inputEmail" type="email" class="form-control" placeholder="Podaj email" value="${contact.email}" required autofocus />
            <select name="inputCategory" id="first" class="form-control" role="listbox" required autofocus>
                <option value="0" selected="selected">Wybierz kategorie</option>
                <c:forEach var="categories" items="${categories}">
                    <option>${categories}</option>
                </c:forEach>
            </select>

            <select name="subcategory" id="second" class="form-control" role="listbox" style="display:none">
                <option value="0" selected="selected">Wybierz podkategorie</option>
                <c:forEach var="subcategories" items="${subcategories}">
                    <option>${subcategories}</option>
                </c:forEach>
            </select>
            <input  id="subcategoryInput" name="inputSubcategory" type="text" class="form-control" placeholder="Podaj podkategorie" style="display:none"/>
            <input name="inputPhone" type="text" class="form-control" placeholder="Podaj nr. telefonu" value="${contact.phone}" required autofocus />
            <input name="inputBirthday" type="date" class="form-control" placeholder="Podaj datę urodzenia" pattern="YYYY-mm-dd" value="${contact.birthday}" required autofocus />
            <input class="btn btn-lg btn-primary btn-block" type="submit" value="Dodaj!" />
        </form>
    </div>
</div>


<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/script.js"></script>
<script src="resources/js/plugins.js"></script>
</body>
</html>
