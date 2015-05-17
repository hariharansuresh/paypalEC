# paypalEC <br/>
Paypal express checkout sample application<br/>
<br/>
Implemented simple express payment workflow<br/>
<br/>
Flow of Application:<br/>
<br/>
->index.jsp<br/>
-> Paypal sandbox integration redirection *<br/>
   ***************** START SetExpressCheckout ********************<br/>
   --> login to paypal account<br/>
   --> Verify address details<br/>
   ***************** END SetExpressCheckout **********************<br/>
-> Review and confirm payment details<br/>
   ***************** GetExpressCheckoutDetails *******************<br/>
-> If user confirms<br/>
   ***************** DoExpressCheckoutPayment ********************<br/>
-> Summary page<br/>
   <br/>
<br/>
Working URL : http://tomcatapp-paypalec.rhcloud.com/PayPalExpCheckout/<br/>
<br/>
Setup file : src/util/config.properties<br/>
<br/>
Sample configuration details:<br/>
<br/>
USER=traveleadapp-facilitator_api1.gmail.com<br/>
PWD=UFD6Z548F3U879G4<br/>
SIGN=AFcWxV21C7fd0v3bYYYRCpSSRl31Ax6Qj.Q5ue4Ln1eUlrNJKZZ6KK2h<br/>
BNCODE=PP-ECWizard<br/>
SANDBOX_FLAG=true<br/>
VER=117<br/>
PP_CHECKOUT_URL_SANDBOX=https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&token=<br/>
PP_NVP_ENDPOINT_SANDBOX=https://api-3t.sandbox.paypal.com/nvp<br/>
PP_CHECKOUT_URL=https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=<br/>
PP_NVP_ENDPOINT=https://api-3t.paypal.com/nvp<br/>
<br/>
Sandbox buyer details:<br/>
<br/>
Buyer Id : traveleadapp-buyer-1@gmail.com <br/>
password : traveleadapp123<br/>