<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="height: 200px; overflow: auto">
    <c:forEach items="${logList}" var="log">
        <br/>
        ${log}
    </c:forEach>
</div>

