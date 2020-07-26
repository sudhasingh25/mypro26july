<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">

	<div>
		<img src="images/BookstoreLogo.png">
	</div>
	<form action="search" method="get">
		<div>
			<input type="text" size="50" name="keyword"> <input
				type="submit" name="Search"> 
				
				&nbsp;&nbsp;&nbsp; 
				<a href="login">Sign In</a> | 
				<a href="register">Register</a> | 
				<a href="view_cart">Cart</a>
				
				 &nbsp;&nbsp;&nbsp;
		</div>
		
		&nbsp;
		
		<div>
			<c:forEach var="category" items="${listcategory}" varStatus="status">
				<a href="view_category?id=${category.categoryId}">
					<b><font size="+1"><c:out value="${category.name}"></c:out></font></b>
				</a>
				
				<c:if test="${not status.last}">
					&nbsp;|&nbsp;
				</c:if>
			</c:forEach>
		</div>
	</form>



</div>
