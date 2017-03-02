<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>

    <%@ include file="/WEB-INF/views/fragments/contactHeadSection.jsp" %>

</head>

<body>

<%@ include file="/WEB-INF/views/fragments/navbar.jspf" %>

<c:if test="${not empty requestScope.contacts}">
    <c:forEach var="contact" items="${requestScope.contacts}">
        <div class="container">
            <div class="row bs-callout bs-callout-primary">
                <div class="col col-md-11 col-sm-10">
                    <ul class="ul-accordion">
                        <li class="li-accordion">
                            <a href="#accordion${contact.id}" class="accordion-trigger" data-accord-group="group1"><h3 class="centered"><c:out value="${contact.name} ${contact.surname}" /></h3></a>
                            <div id="accordion${contact.id}" class="accordion-content collapsed">
                                <h6><small>Dnia: <c:out value="${contact.data}"/></small></h6>
                                <p>
                                    <strong>Nr tel: </strong><c:out value="${contact.phone}"/><br>
                                    <strong>Adres email: </strong><c:out value="${contact.email}"/><br>
                                    <strong>Data urodzenia: </strong><c:out value="${contact.birthday}"/>
                                </p>
                                <h6><small>Kategoria: <c:out value="${contact.category}"/>, Subkategoria: <c:out value="${contact.subcategory}" /></small></h6>
                            </div>
                        </li>
                    </ul>
                    <a href="${pageContext.request.contextPath}/edit?contact_id=${contact.id}"  class="btn btn-default btn-xs">Edytuj kontakt</a>
                    <a href="${pageContext.request.contextPath}/delete?contact_id=${contact.id}" class="btn btn-default btn-xs">Usuń kontakt</a>
                </div>
            </div>
        </div>
    </c:forEach>
</c:if>

<%@ include file="/WEB-INF/views/fragments/contactScriptSection.jsp" %>

</body>
</html>
