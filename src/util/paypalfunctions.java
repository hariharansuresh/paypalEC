package util;

import java.util.*;
import java.io.*;
import java.net.*;
import javax.servlet.http.HttpServletResponse;

public class paypalfunctions {

	private String gv_APIUserName;
	private String gv_APIPassword;
	private String gv_APISignature;
	private String gv_APIEndpoint;
	private String gv_BNCode;
	private String gv_Version;
	private String PAYPAL_URL;

	public paypalfunctions() {
		Properties prop = new Properties();
		InputStream inputData = paypalfunctions.class.getResourceAsStream("config.properties");  
		try {
			prop.load(inputData);
		} catch (IOException e) {
			System.out.print(e.getCause());
		}

		gv_BNCode = prop.getProperty("BNCODE");
		gv_APIUserName = prop.getProperty("USER");
		gv_APIPassword = prop.getProperty("PWD");
		gv_APISignature = prop.getProperty("SIGN");
		
		String sandbox = "true";

		if (sandbox.equals(prop.getProperty("SANDBOX_FLAG"))) {
			gv_APIEndpoint = prop.getProperty("PP_NVP_ENDPOINT_SANDBOX");
			PAYPAL_URL = prop.getProperty("PP_CHECKOUT_URL_SANDBOX");
		} else {
			gv_APIEndpoint = prop.getProperty("PP_NVP_ENDPOINT");
			PAYPAL_URL = prop.getProperty("PP_CHECKOUT_URL");
		}
		gv_Version = prop.getProperty("VER");
		
		System.out.print(gv_APIUserName + gv_APIPassword+gv_APISignature);
		System.out.print(gv_APIEndpoint + PAYPAL_URL+gv_Version);

	}

	/*********************************************************************************
	 * CallShortcutExpressCheckout: Function to perform the SetExpressCheckout
	 * API call
	 * 
	 * Inputs: paymentAmount: Total value of the shopping cart currencyCodeType:
	 * Currency code value the PayPal API paymentType: paymentType has to be one
	 * of the following values: Sale or Order or Authorization returnURL: the
	 * page where buyers return to after they are done with the payment review
	 * on PayPal cancelURL: the page where buyers return to when they cancel the
	 * payment review on PayPal
	 * 
	 * Output: Returns a HashMap object containing the response from the server.
	 *********************************************************************************/
	public HashMap CallShortcutExpressCheckout(String paymentAmount,
			String returnURL, String cancelURL) {
		String currencyCodeType = "USD";
		String paymentType = "Sale";

		String nvpstr = "&PAYMENTREQUEST_0_AMT=" + paymentAmount
				+ "&PAYMENTREQUEST_0_PAYMENTACTION=" + paymentType
				+ "&ReturnUrl=" + URLEncoder.encode(returnURL) + "&CANCELURL="
				+ URLEncoder.encode(cancelURL)
				+ "&PAYMENTREQUEST_0_CURRENCYCODE=" + currencyCodeType;

		HashMap nvp = httpcall("SetExpressCheckout", nvpstr);
		String ack = nvp.get("ACK").toString();
		System.out.print(nvp);
		System.out.print(ack);

		if (ack != null && ack.equalsIgnoreCase("Success")) {
			return nvp;
		}
		return null;
	}

	/*********************************************************************************
	 * CallMarkExpressCheckout: Function to perform the SetExpressCheckout API
	 * call
	 * 
	 * Inputs: paymentAmount: Total value of the shopping cart currencyCodeType:
	 * Currency code value the PayPal API paymentType: paymentType has to be one
	 * of the following values: Sale or Order or Authorization returnURL: the
	 * page where buyers return to after they are done with the payment review
	 * on PayPal cancelURL: the page where buyers return to when they cancel the
	 * payment review on PayPal shipToName: the Ship to name entered on the
	 * merchant's site shipToStreet: the Ship to Street entered on the
	 * merchant's site shipToCity: the Ship to City entered on the merchant's
	 * site shipToState: the Ship to State entered on the merchant's site
	 * shipToCountryCode: the Code for Ship to Country entered on the merchant's
	 * site shipToZip: the Ship to ZipCode entered on the merchant's site
	 * shipToStreet2: the Ship to Street2 entered on the merchant's site
	 * phoneNum: the phoneNum entered on the merchant's site
	 * 
	 * Output: Returns a HashMap object containing the response from the server.
	 *********************************************************************************/
	public HashMap CallMarkExpressCheckout(String paymentAmount,
			String returnURL, String cancelURL, String shipToName,
			String shipToStreet, String shipToCity, String shipToState,
			String shipToCountryCode, String shipToZip, String shipToStreet2,
			String phoneNum) {

		String currencyCodeType = "USD";
		String paymentType = "Sale";

		String nvpstr = "ADDROVERRIDE=1&PAYMENTREQUEST_0_AMT=" + paymentAmount
				+ "&PAYMENTREQUEST_0_PAYMENTACTION=" + paymentType;
		nvpstr = nvpstr.concat("&PAYMENTREQUEST_0_CURRENCYCODE="
				+ currencyCodeType + "&ReturnUrl="
				+ URLEncoder.encode(returnURL) + "&CANCELURL="
				+ URLEncoder.encode(cancelURL));
		nvpstr = nvpstr.concat("&PAYMENTREQUEST_0_SHIPTONAME=" + shipToName
				+ "&PAYMENTREQUEST_0_SHIPTOSTREET=" + shipToStreet
				+ "&PAYMENTREQUEST_0_SHIPTOSTREET2=" + shipToStreet2);
		nvpstr = nvpstr.concat("&PAYMENTREQUEST_0_SHIPTOCITY=" + shipToCity
				+ "&PAYMENTREQUEST_0_SHIPTOSTATE=" + shipToState
				+ "&PAYMENTREQUEST_0_SHIPTOCOUNTRYCODE=" + shipToCountryCode);
		nvpstr = nvpstr.concat("&PAYMENTREQUEST_0_SHIPTOZIP=" + shipToZip
				+ "&PAYMENTREQUEST_0_SHIPTOPHONENUM" + phoneNum);
		HashMap nvp = httpcall("SetExpressCheckout", nvpstr);
		String strAck = nvp.get("ACK").toString();
		if (strAck != null
				&& !(strAck.equalsIgnoreCase("Success") || strAck
						.equalsIgnoreCase("SuccessWithWarning"))) {
			return nvp;
		}
		return null;
	}

	/*********************************************************************************
	 * GetShippingDetails: Function to perform the GetExpressCheckoutDetails API
	 * call
	 * 
	 * Inputs: None
	 * 
	 * Output: Returns a HashMap object containing the response from the server.
	 *********************************************************************************/
	public HashMap GetShippingDetails(String token) {
		String nvpstr = "&TOKEN=" + token;
		HashMap nvp = httpcall("GetExpressCheckoutDetails", nvpstr);
		String strAck = nvp.get("ACK").toString();
		if (strAck != null
				&& (strAck.equalsIgnoreCase("Success") || strAck
						.equalsIgnoreCase("SuccessWithWarning"))) {
			return nvp;
		}
		return null;
	}

	/*********************************************************************************
	 * GetShippingDetails: Function to perform the DoExpressCheckoutPayment API
	 * call
	 * 
	 * Inputs: None
	 * 
	 * Output: Returns a HashMap object containing the response from the server.
	 *********************************************************************************/
	public HashMap ConfirmPayment(String token, String payerID,
			String finalPaymentAmount, String serverName) {

		String currencyCodeType = "USD";
		String paymentType = "Sale";
		String nvpstr = "&TOKEN=" + token + "&PAYERID=" + payerID
				+ "&PAYMENTREQUEST_0_PAYMENTACTION=" + paymentType
				+ "&PAYMENTREQUEST_0_AMT=" + finalPaymentAmount;
		nvpstr = nvpstr + "&PAYMENTREQUEST_0_CURRENCYCODE=" + currencyCodeType
				+ "&IPADDRESS=" + serverName;
		HashMap nvp = httpcall("DoExpressCheckoutPayment", nvpstr);
		String strAck = nvp.get("ACK").toString();
		if (strAck != null
				&& !(strAck.equalsIgnoreCase("Success") || strAck
						.equalsIgnoreCase("SuccessWithWarning"))) {
			return nvp;
		}
		return null;
	}
	/*********************************************************************************
	 * httpcall: Function to perform the API call to PayPal using API signature @
	 * methodName is name of API method. @ nvpStr is nvp string. returns a NVP
	 * string containing the response from the server.
	 *********************************************************************************/
	public HashMap httpcall(String methodName, String nvpStr) {

		String version = "2.3";
		String agent = "Mozilla/4.0";
		String respText = "";
		HashMap nvp = null;
		String encodedData = "METHOD=" + methodName + "&VERSION=" + gv_Version
				+ "&PWD=" + gv_APIPassword + "&USER=" + gv_APIUserName
				+ "&SIGNATURE=" + gv_APISignature + nvpStr + "&BUTTONSOURCE="
				+ gv_BNCode;

		try {
			URL postURL = new URL(gv_APIEndpoint);
			HttpURLConnection conn = (HttpURLConnection) postURL
					.openConnection();

			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("User-Agent", agent);
			conn.setRequestProperty("Content-Length",
					String.valueOf(encodedData.length()));
			conn.setRequestMethod("POST");
			// get the output stream to POST to.
			DataOutputStream output = new DataOutputStream(
					conn.getOutputStream());
			output.writeBytes(encodedData);
			output.flush();
			output.close();
			DataInputStream in = new DataInputStream(conn.getInputStream());
			int rc = conn.getResponseCode();
			if (rc != -1) {
				BufferedReader is = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				String _line = null;
				while (((_line = is.readLine()) != null)) {
					respText = respText + _line;
				}
				nvp = deformatNVP(respText);
			}
			return nvp;
		} catch (IOException e) {
			System.out.println(e.getCause());
			return null;
		}
	}
	/*********************************************************************************
	 * deformatNVP: Function to break the NVP string into a HashMap pPayLoad is
	 * the NVP string. returns a HashMap object containing all the name value
	 * pairs of the string.
	 *********************************************************************************/
	public HashMap deformatNVP(String pPayload) {
		HashMap nvp = new HashMap();
		StringTokenizer stTok = new StringTokenizer(pPayload, "&");
		while (stTok.hasMoreTokens()) {
			StringTokenizer stInternalTokenizer = new StringTokenizer(
					stTok.nextToken(), "=");
			if (stInternalTokenizer.countTokens() == 2) {
				String key = URLDecoder.decode(stInternalTokenizer.nextToken());
				String value = URLDecoder.decode(stInternalTokenizer
						.nextToken());
				nvp.put(key.toUpperCase(), value);
			}
		}
		return nvp;
	}
	/*********************************************************************************
	 * RedirectURL: Function to redirect the user to the PayPal site token is
	 * the parameter that was returned by PayPal returns a HashMap object
	 * containing all the name value pairs of the string.
	 * 
	 * @param response
	 * @param token
	 *********************************************************************************/
	public void RedirectURL(HttpServletResponse response, String token) {
		String payPalURL = PAYPAL_URL + token;
		response.setStatus(302);
		response.setHeader("Location", payPalURL);
		response.setHeader("Connection", "close");
	}
}
