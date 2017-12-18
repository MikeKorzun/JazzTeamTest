<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<form action='${contextPath}/StartGame'>
    <button>Start Game</button>
</form>
<br/>
<br/>

<table class="table table-bordered table-hover">
    <tr>
        <td><a href="<c:url value='/addDestroyYourselfAction'/>">Destroy to all Robots</a> </td>
        <td><a href="<c:url value='/addAttackAction'/>">Attack to all Robots</a> </td>
        <td><a href="<c:url value='/addBuildAction'/>">Build to all Robots</a> </td>
        <td><a href="<c:url value='/addMineAction'/>">Mine to all Robots</a> </td>

    </tr>
</table>