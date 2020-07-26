<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Form</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/jquery-ui.min.css">
	<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<c:if test="${book==null}">
		<h2 class="pageheading">Create New Book</h2>
		<form action="create_book" method="post" id="bookform" enctype="multipart/form-data">
		</c:if>
		
		<c:if test="${book!=null}">
		<h2 class="pageheading">Edit Book</h2>
		<form action="update_book" method="post" id="bookform" enctype="multipart/form-data">
		<input type="hidden" name="bookid" value="${book.bookId}">
		</c:if>
		
			<table class="form">
				<tr>
					<td align="right">Category:</td>
					<td align="left">
						<select name="category">
							<c:forEach items="${listCategory}" var="category">
								<c:if test="${book.category.categoryId eq category.categoryId}">
									<option value="${category.categoryId}" selected>
								</c:if>
								
								<c:if test="${book.category.categoryId ne category.categoryId}">
									<option value="${category.categoryId}">
								</c:if>
									${category.name}
								</option>
								
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr>
					<td align="right">Title:</td>
					<td align="left"><input type="text" size="20" name="title" id="title" value="${book.title }"></td>
				</tr>
				
				<tr>
					<td align="right">Author:</td>
					<td align="left"><input type="text" size="20" name="author" id="author" value="${book.author }"></td>
				</tr>
				
				<tr>
					<td align="right">ISBN:</td>
					<td align="left"><input type="text" size="20" name="isbn" id="isbn" value=" ${book.isbn}"></td>
				</tr>
				
				<tr>
					<td align="right">Publish Date:</td>
					<td align="left"><input type="text" size="20" name="publishdate" id="publishdate"
					 value="<fmt:formatDate pattern='MM/dd/yyyy' value='${book.publishDate}'/>"/></td>
				</tr>
				
				<tr>
					<td align="right">Book Image:</td>
					<td align="left"><input type="file" size="20" name="bookimage" id="bookimage" ><br/>
						<img id="thumbnail" alt="Image Preview" style="width:20%; margin-top:10px"
							src="data:image/jpg;base64,${book.base64Image}"
						 />
					</td>
				</tr>
				
				<tr>
					<td align="right">Price:</td>
					<td align="left"><input type="text" size="20" name="price" id="price" value="${book.price }"></td>
					
				</tr>
				
				<tr>
					<td align="right">Description:</td>
					<td align="left"><textarea rows="5" cols="50" name="description" id="description" >${book.description}</textarea></td>
					
				</tr>
				<tr>
					<td align="center" colspan="2">
					<button type="submit">Save</button>
					<button  id="buttoncancel">Cancel</button>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	
	<jsp:directive.include file="footer.jsp" />

<script type="text/javascript">
	$(document).ready(function(){
		$("#publishdate").datepicker();
		
		$("#bookimage").change(function(){
			showImageThumbnail(this);
		});
		
		$("#bookform").validate({
			rules:{

				category:"required",
				title:"required",
				author:"required",
				isbn:"required",
				publishdate:"required",
				
				<c:if test="${book==null}">
				bookimage:"required",
				</c:if>
				
				price:"required",
				description:"required",
				
			},
			messages:{

				category:"select a category",
				title:"enter title",
				author:"enter author name",
				isbn:"enter isbn",
				publishdate:"enter publishdate",
				bookimage:"choose a book image",
				price:"enter price",
				description:"enter description"
			}
		});
		
		$("#buttoncancel").click(function() {
			history.go(-1);
		});
		
		function showImageThumbnail(fileInput){
			var file=fileInput.files[0];
			
			var reader=new FileReader();
			
			reader.onload=function(e){
				$("#thumbnail").attr('src',e.target.result);
			};
			reader.readAsDataURL(file);
		};
		
	});
</script>
</body>
</html>