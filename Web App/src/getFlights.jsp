<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.tcs.*" %>
 <html>
 <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <title>Flightcheap results</title>
 </head>
 <body>
 <h2> Flight Result from Kayak </h2> <br/><br/>
 <%
	String deptFrom = request.getParameter("departFrom");
	String deptTo = request.getParameter("departTo");
	String deptMonth = request.getParameter("departMonth");
	String deptYear = request.getParameter("departYear");
	String departMonth=deptMonth+deptYear;
	out.println((new FetchXml()).parseXML(deptFrom, deptTo, departMonth));	
 %>
 </body>
 </html>