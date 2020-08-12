<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<! DOCTYPE HTML>
<html>
<head>
	<title>CRM - Customers List</title>
	<link type="text/css" 
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/style.css" >
</head>
<body>
	
	<div id="wrapper">
		<div id="header">
		<h2>CRM - Customers Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		
		<!-- Add button for customers -->
		<input type="button" value="Add Customer" onclick="window.location.href='showFormAdd'; return false;"
			class="add-button"/>
			<!--  add a search box -->
        <form:form action="searchCustomer" method="POST">
                Search customer: <input type="text" name="theSearchName" />
             <input type="submit" value="Search Customer" class="add-button" />
        </form:form>		
		<!-- table of customers -->
		<table>
		<tr>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Email</th>
		<th>Actions</th>
		</tr>
		<!-- loop over and print the customers -->
		<c:forEach var="tempCustomer" items="${customers}">
		
		<!-- URL variable -->
		<c:url var="updateLink" value="/customer/showFormForUpdate">
			<c:param name="customerId" value="${tempCustomer.id}"></c:param>
		</c:url>
		
		<c:url var="deleteLink" value="/customer/deleteCustomer">
			<c:param name="customerId" value="${tempCustomer.id}"></c:param>
		</c:url>
		
		<tr>
			<td> ${tempCustomer.firstName} </td>		
			<td> ${tempCustomer.lastName} </td>
			<td> ${tempCustomer.email} </td>
			<td> <a href="${updateLink}">Update</a>
			|
			<a href="${deleteLink}" onclick="if(!(confirm('Are you sure you wan to delete the customer?'))) return false">Delete</a> </td>
		</tr>
		</c:forEach>
		</table>
		</div>
	</div>
</body>
</html>