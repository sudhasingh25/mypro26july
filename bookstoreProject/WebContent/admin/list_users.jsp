<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Users</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<div>
		<h2>User Management</h2>
		</div>
		<div class="pageheading">
			<h3><a href="user_form.jsp">Create New User</a></h3>
		</div>
		
		
		<div>
			<c:if test="${message!=null}">
				<h5 align="center">${message}</h5>
			</c:if>
		</div>
		
		<div>
			<table border="1" cellpadding="5">
				<tr>
					<th>Index</th>
					<th>ID</th>
					<th>Email</th>
					<th>Full Name</th>
					<th>Actions</th>
				</tr>
				
				
			
			
					<c:forEach var="user" items="${listuser}" varStatus="status">
						<tr>
							<td>${status.index+1}</td>
							<td>${user.userId}</td>
							<td>${user.email }</td>
							<td>${user.fullName }</td>
							<td>
								<a href="edit_user?id=${user.userId}">Edit</a>&nbsp;
								<a href="javascript:void(0);" class="deleteLink" id="${user.userId}">Delete</a>
							</td>
						</tr>
					</c:forEach>
				
			</table>
		</div>
	</div>
	<jsp:directive.include file="footer.jsp" />
	
	<script>
		$(document).ready(function(){
			$(".deleteLink").each(function(){
				$(this).on("click",function(){
					userId=$(this).attr("id");	
				
					if(confirm('Are you sure you want to delete user with ID '+userId +' ?')){
						window.location='delete_user?id=' +userId;
					}
				});
			});
		});
	</script>
	
</body>
</html>