<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

</head>
<body>
	<h2 align="center" class="pageheading">Login</h2>

	<c:if test="${message!=null}">
		<h3>"${message}"</h3>
	</c:if>

	<div align="center">
		<form action="login" id="loginForm">
			<table>
				<tr>
					<td align="right">Email:</td>
					<td align="left"><input type="text" name="email" id="email"></td>
				</tr>

				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" name="password"
						id="password"></td>
				</tr>
				<tr></tr>
				<tr>
					<td align="center" colspan="2">
						<button type="submit">Login</button>
					</td>
				</tr>
			</table>
		</form>
	</div>


<script type="text/javascript">
	$(document).ready(function() {
		$("#loginForm").validate({
			rules : {

				email : {
					required : true,
					email : true
				},
				password:"required",
				
			},
			
			messages:{
				email:{
					required:"Please enter email",
					email:"Please enter a valid email"
				},
				password:"Please enter password"
			}
		});
	});
</script>
</body>
</html>