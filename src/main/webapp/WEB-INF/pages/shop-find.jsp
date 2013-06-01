<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Find Shop page</title>
</head>
<body>
<h1>Find Shop page</h1>
<table>
<tbody>
<tr>
<td>Shop id:</td>
<td>${shop.id}</td>
</tr>
<tr>
<td>Shop name:</td>
<td>${shop.name}</td>
</tr>
<tr>
<td>Employees number:</td>
<td>${shop.emplNumber}</td>
</tr>
</tbody>
</table>
<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>