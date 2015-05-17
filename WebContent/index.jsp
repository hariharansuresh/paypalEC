
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Buy Adidas shoes at cheaper price!!!</title>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
	<form method="POST" action="checkout">
	<div class="left">
	<table >
		<tr>
			<h2>Adidas Ogin M Running Shoes</h2>
		</tr>
		<tr>
			<img src="adidas.jpg" width="400" height="300" />
		</tr>
		<tr>
			<td><p class="lead">Buyer Credentials:</p></td>
		</tr>
		<tr>
			<td>Email-id:&nbsp;&nbsp;&nbsp;<input type="text"
				id="buyer_email" name="buyer_email" value="traveleadapp-buyer-1@gmail.com" readonly></input>
			</td>
		</tr>
		<tr>
			<td>Password:<input type="text" id="buyer_password"
				name="buyer_password" value="traveleadapp123" readonly></input></td>
		</tr>
		</table>
		</div>
		<div class="right">
		<table width="100%">
		
		<h2>Item Specifications:</h2>
			<tr>
				<td>Shoe Name:</td>
				<td><input type="text" name="L_PAYMENTREQUEST_0_NAME0"
					value="Adidas Ogin M Running Shoes" size="140px"></input></td>
			</tr>
			
			<tr>
				<td>Description:</td>
				<td><input type="text" name="L_PAYMENTREQUEST_0_DESC0"
					value="ADIDAS fast moving runners"></input></td>
			</tr>
			<tr>
				<td>Quantity:</td>
				<td><input type="text" name="L_PAYMENTREQUEST_0_QTY0" value="1"
					readonly></input></td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><input type="text" name="PAYMENTREQUEST_0_ITEMAMT"
					value="500.00" readonly></input></td>
			</tr>
			<tr>
				<td>Tax:</td>
				<td><input type="text" name="PAYMENTREQUEST_0_TAXAMT"
					value="0.00" readonly></input></td>
			</tr>
			<tr>
				<td>Shipping Amount:</td>
				<td><input type="text" name="PAYMENTREQUEST_0_SHIPPINGAMT"
					value="5.00" readonly></input></td>
			</tr>
			<tr>
				<td>Handling Amount:</td>
				<td><input type="text" name="PAYMENTREQUEST_0_HANDLINGAMT"
					value="0.00" readonly></input></td>
			</tr>
			<tr>
				<td>Shipping Discount:</td>
				<td><input type="text" name="PAYMENTREQUEST_0_SHIPDISCAMT"
					value="-5.00" readonly></input></td>
			</tr>
			<tr>
				<td>Insurance Amount:</td>
				<td><input type="text" name="PAYMENTREQUEST_0_INSURANCEAMT"
					value="0.00" readonly></input></td>
			</tr>
			<tr>
				<td>Total Amount:</td>
				<td><input type="text" name="PAYMENTREQUEST_0_AMT"
					value="500.00" readonly></input></td>
			</tr>
			<tr>
				<td>
					<%
						String path = request.getContextPath();
						String basePath = request.getScheme() + "://"
								+ request.getServerName() + ":" + request.getServerPort()
								+ path + "/";
					%> <input type="hidden" name="LOGOIMG"
					value="<%=basePath%>logo.png"></input>
				</td>
			</tr>
			<tr>
				<td>Currency Code:</td>
				<td><select id="currencyCodeType" name="currencyCodeType">
						<option selected value="USD">USD</option>
						<option value="AUD">AUD</option>
						<option value="BRL">BRL</option>
						<option value="AUD">AUD</option>
						<option value="CAD">CAD</option>
						<option value="CZK">CZK</option>
						<option value="DKK">DKK</option>
						<option value="EUR">EUR</option>
						<option value="HKD">HKD</option>
						<option value="HUF">HUF</option>
						<option value="ILS">ILS</option>
						<option value="JPY">JPY</option>
						<option value="NOK">NOK</option>
						<option value="MXN">MXN</option>
						<option value="NZD">NZD</option>
						<option value="PHP">PHP</option>
						<option value="PLN">PLN</option>
						<option value="GBP">GBP</option>
						<option value="SGD">SGD</option>
						<option value="SEK">SEK</option>
						<option value="CHF">CHF</option>
						<option value="TWD">TWD</option>
						<option value="THB">THB</option>
						<option value="TRY">TRY</option>
				</select> <br></td>
			</tr>
			<tr>
				<td>Payment Type:</td>
				<td><select name="paymentType">
						<option value="Sale">Sale</option>
 				</select><br></td>
			</tr>
		</table>
<!-- 
		<a href="checkout"> <img
			src="//www.paypalobjects.com/en_US/i/btn/btn_xpressCheckout.gif"
			alt="Check out with PayPal" />
		</a>-->
        <input type="image" name="submit" src="//www.paypalobjects.com/en_US/i/btn/btn_xpressCheckout.gif" border="0" alt="Submit" />
		</form>
</div>

		
</body>
</html>
