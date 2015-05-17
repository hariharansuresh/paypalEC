package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.paypalfunctions;

public class resultServlet extends HttpServlet {

    /**
     * Used for showing the review and result screens
     * 
     * Review :
     * Pagetype = return
     * method call = GetExpressCheckoutDetails
     * 
     * Confirm payment:
     * Pagetype = confirm
     * method call = DoExpressCheckoutPayment
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sess = request.getSession();
        String token = null;
        RequestDispatcher rd = null;
        String page = request.getParameter("type") != null ? request.getParameter("type") : "Error";
        token = sess.getAttribute("token").toString() != null ? sess.getAttribute("token").toString() : "Error";
        HashMap results = null;
        paypalfunctions ppf = new paypalfunctions();
        if (!(token.equals("Error"))) {
            results = ppf.GetShippingDetails(token);
        } else {
            results = null;
        }
        
        if (page.equals("return")) {
            sess.setAttribute("resultMap", results);
            sess.setAttribute("payerID", request.getParameter("payerID"));
            rd = request.getRequestDispatcher("/confirm.jsp");
            rd.forward(request, response);
        } 
        
        if (page.equals("confirm")) {
            System.out.println("AMT-->" + results.get("PAYMENTREQUEST_0_AMT").toString());
            System.out.println("request.getServerName()-->" + request.getServerName());

            HashMap completePaymentDetails = ppf.ConfirmPayment(token, results.get("PAYERID").toString(), results.get("PAYMENTREQUEST_0_AMT").toString(), request.getServerName());
            results = ppf.GetShippingDetails(token);

            sess.setAttribute("resultMap", results);
            Iterator it = results.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                System.out.println(pair.getKey() + " = " + pair.getValue());
            }
            rd = request.getRequestDispatcher("/result.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
