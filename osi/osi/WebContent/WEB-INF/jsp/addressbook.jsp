<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Granny's Address Book</h1>

<c:url var="url" value="/addressbook" /> 
<form:form action="${url}" commandName="addressBook">
  <form:select path="changeToSelectedContactId" onchange="this.form.submit()">
  <form:option value="0" label="Add New" />
  <form:options items="${contactList}" itemValue="id" itemLabel="name" />
</form:select>
  <br>
</form:form>


<p>
<c:url var="urlSave" value="/save" /> 
<form:form action="${urlSave}" commandName="addressBook">
    Name : <form:input path="selectedContact.name"/> <br>
 Address : <form:textarea rows="5" cols="30" path="selectedContact.address"/><br>
    Email : <form:input path="selectedContact.email"/><br>
        Phone : <form:input path="selectedContact.phone"/><br>
    <form:hidden path="selectedContact.id"/>
  <input type="submit" value="save"/>
    
</form:form>

<c:url var="urlDelete" value="/delete" /> 
<form:form action="${urlDelete}" commandName="addressBook">
    <form:hidden path="selectedContact.id"/>
  <input type="submit" value="delete"/>
</form:form>