<%@page import="java.sql.SQLException"%>
<%@page import="jdbc.ControlDB"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<a href="">asdf</a><br>
	<table>
		<tr>
			<th>name</th>
			<th>address</th>
			<th>link</th>
			<th>custodian.name</th>
			<th>custodian.phonenumber</th>
		</tr>
		<%
    ResultSet rs = null;
    rs = ControlDB.executeQuery("select * from shop,custodian where shop.custodian=custodian.id");
    try {
    	while(rs.next()){
    %>
		<tr>
			<td><%=rs.getString("name") %></td>
			<td><%=rs.getString("address") %></td>
			<td><%=rs.getString("link") %></td>
			<td><%=rs.getString("peoplename") %></td>
			<td><%=rs.getString("phonenumber") %></td>
		</tr>

		<%
    	}
    }catch(SQLException e){
    	e.printStackTrace();
    }
    %>
	</table>
</body>
</html>
