<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Form</title>
<link rel="stylesheet" href="../css/style.css">
	<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<c:if test="${user==null}">
		<h2 class="pageheading">Create New User</h2>
		<form action="create_user" method="post" id="userform">
		</c:if>
		
		<c:if test="${user!=null}">
		<h2 class="pageheading">Edit User</h2>
		<form action="update_user" method="post" id="userform">
		<input type="hidden" name="userid" value="${user.userId}">
		</c:if>
		
			<table class="form">
				<tr>
					<td align="right">Email:</td>
					<td align="left"><input type="text" size="20" name="email" id="email" value=${user.email } ></td>
				</tr>
				
				<tr>
					<td align="right">Fullname:</td>
					<td align="left"><input type="text" size="20" name="fullname" id="fullname" value=${user.fullName }></td>
				</tr>
				
				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" size="20" name="password" id="password" value=${user.password }></td>
				</tr>	
				
				
					<td align="center" >
					<button type="submit">Save</button>
					<button  id="buttoncancel">Cancel</button>
					</td>
				
				
			</table>
		</form>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#userform").validate({
			rules:{
				email:{
					required:true,
					email:true
				},
				fullname:"required",
				password:"required"
			},
			messages:{
				email:{
					required:"Please enter email",
					email:"Please enter a valid email"
				},
				fullname:"Please enter fullname",
				password:"Please enter password"
			}
		});
		
		$("#buttoncancel").click(function() {
			history.go(-1);
		});
		
	});
</script>

</html>