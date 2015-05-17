/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.paypalfunctions;

/**
 *
 * @author harish
 */
public class checkoutServlet extends HttpServlet {

    
    /**
	 * Checkout servlet called from index.jsp process the SetExpressCheckout nvp service
	 * for starting the PayPal payment flow
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            HttpSession session = request.getSession(true);
            RequestDispatcher rd = null;
            String paymentAmount = request.getParameter("PAYMENTREQUEST_0_AMT")!=null?request.getParameter("PAYMENTREQUEST_0_AMT"):"0";
            System.out.print(paymentAmount);
            String returnURL = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/result?type=return";
            String cancelURL = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/cancel.jsp";
            paypalfunctions ppf = new paypalfunctions();
            HashMap nvp = ppf.CallShortcutExpressCheckout (paymentAmount, returnURL, cancelURL);
            String ack = nvp.get("ACK").toString();
            if(ack !=null && ack.equalsIgnoreCase("Success"))
            {
            	System.out.print("Amount -->"+paymentAmount);
                session.setAttribute("token", nvp.get("TOKEN").toString());
                session.setAttribute("payment_amount", paymentAmount);
                ppf.RedirectURL( response ,nvp.get("TOKEN").toString());
            }
            else
            {
                String ErrorCode = nvp.get("L_ERRORCODE0").toString();
                String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
                String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
                String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();
                
                String message = ErrorCode+ "\n" +  ErrorShortMsg+"\n"+ErrorLongMsg +"\n"+ ErrorSeverityCode;
                session.setAttribute("errMsg", message);

                rd = request.getRequestDispatcher("/cancel.jsp");
                rd.forward(request, response);
            }     
    }
}
