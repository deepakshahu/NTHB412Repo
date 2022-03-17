<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show Report</title>
</head>
<body>
	<c:choose>
		<c:when test="${!empty pageData}">
			<h1 style="color: blue; text-align: center">The Report is :</h1>
			<table border="1" bgcolor="cyan" align="center">
				<tr>
					<th>PID</th>
					<th>PNAME</th>
					<th>PRICE</th>
					<th>QTY</th>
				</tr>
				<c:forEach var="prod" items="${pageData}">
					<tr>
						<td>${prod.pid}</td>
						<td>${prod.pname}</td>
						<td>${prod.price}</td>
						<td>${prod.qty}</td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<p align="center">
				<c:forEach var="i" begin="1" end="${pagesCount}" step="1">
					<a href="controller?pgNo=${i}">[${i}]</a>&nbsp;&nbsp;&nbsp;			
			</c:forEach>
			</p>
		</c:when>
		<c:otherwise>
			<h1 style="color: red; text-align: center">Records not found</h1>
		</c:otherwise>
	</c:choose>
	<br>
	<br>
	<h1 style="text-align: center">
		<a href="index.html">Home</a>
	</h1>
</body>
</html>