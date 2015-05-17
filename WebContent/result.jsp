<%-- 
    Document   : confirm.jsp
    Created on : May 16, 2015, 7:34:28 PM
    Author     : harish
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation Page</title>
        <jsp:include page="header.jsp"></jsp:include>
    </head>
 	
    <body>
    <div id="container">
        <h1>Order placed successfully!!!</h1>
        <table>
            <tr><td><h2>Shipping Address</h2></td></tr>
            <% HashMap resultData = (HashMap) session.getAttribute("resultMap");%>
            <tr><td><%=resultData.get("PAYMENTREQUEST_0_SHIPTONAME")%></td></tr>
            <tr><td><%=resultData.get("PAYMENTREQUEST_0_SHIPTOSTREET")%></td></tr>
            <tr><td><%=resultData.get("PAYMENTREQUEST_0_SHIPTOCITY")%></td><td></tr>
            <tr><td><%=resultData.get("PAYMENTREQUEST_0_SHIPTOSTATE")%></td><td></tr>
            <tr><td><%=resultData.get("PAYMENTREQUEST_0_SHIPTOCOUNTRYCODE")%></td></tr>
            <tr><td><%=resultData.get("PAYMENTREQUEST_0_SHIPTOZIP")%></td><td></tr>
            <tr><td></td></tr>
            <tr><td></td></tr>
            <tr><td></td></tr>
            <tr><td></td></tr>
            <tr><td>Total Amount:</td><td><span id="PAYMENTREQUEST_0_AMT"><%=resultData.get("PAYMENTREQUEST_0_AMT")%></span></td></tr>
            <tr><td>Currency Code:</td><td><span id="CURRENCYCODE"><%=resultData.get("CURRENCYCODE")%></span></td></tr>
            <tr><td>E-mail</td><td><span id="EMAIL"><%=resultData.get("EMAIL")%></span></td></tr>
            <tr><td>Status</td><td><span id="STATUS"><%=resultData.get("CHECKOUTSTATUS")%></span></td></tr>
            <tr><td></td></tr>
            <tr><td></td></tr>
            <tr><td></td></tr>
            <tr><td></td></tr>
            <tr><td colspan=2>
			<a href="index.jsp">
            <input type="button" alt="Shop again"  value="Shop again">        
        </a>            </td></tr>
            
        </table>
    </div>
    </body>

    <script>
    </script>
</html>
