<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<div>
			<h2>Administrative Dashboard</h2>
		</div>

		<div>
			<h2>Quick Actions:</h2>
			<a href="new_book">New Book</a>&nbsp; <a href="new_user">New User</a>&nbsp;
			<a href="new_category">New Category</a>&nbsp; <a href="new_customer">New
				Customer</a>

		</div>
		
		<div>
			<h2>Recent Sales:</h2>
		</div>
		
		<div>
			<h2>Recent Reviews:</h2>
		</div>
		
		<div>
			<h2>Statistics:</h2>
		</div>
	
	
		<h4>Bookstore Administration</h4>
		<jsp:directive.include file="footer.jsp" />
	</div>

</body>
</html>