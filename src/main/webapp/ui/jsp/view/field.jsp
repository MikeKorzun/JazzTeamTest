<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<META HTTP-EQUIV="Refresh" CONTENT=2>

<h1>Robot List</h1>

<table class="table table-bordered table-hover">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Doing</th>
		<th>Action1</th>
		<th>Action2</th>
		<th>Action3</th>
		<th>Action4</th>
	</tr>
	<c:forEach items="${robotList}" var="robot">
		<tr>
			<td>${robot.id}</td>
			<td>${robot.robotName}</td>
			<td>${robot.currentRobotAction.name}</td>

			<td><a href="<c:url value='/addDestroyYourselfToRobot/${robot.id}'/>">Destroy</a> </td>
			<td><a href="<c:url value='/addAttackActionToRobot/${robot.id}'/>">Attack</a> </td>
			<td><a href="<c:url value='/addBuildActionToRobot/${robot.id}'/>">Build</a> </td>
			<td><a href="<c:url value='/addMineActionToRobot/${robot.id}'/>">Mine</a> </td>
		</tr>
	</c:forEach>
</table>
