# paypalEC 
Paypal express checkout sample application

Implemented simple express payment workflow

Flow of Application:

->index.jsp

-> Paypal sandbox integration redirection *

   ***************** START SetExpressCheckout ********************
   
   --> login to paypal account
   
   --> Verify address details
   
   ***************** END SetExpressCheckout **********************
   
-> Review and confirm payment details

   ***************** GetExpressCheckoutDetails *******************
   
-> If user confirms

   ***************** DoExpressCheckoutPayment ********************
   
-> Summary page
   

Working URL : http://tomcatapp-paypalec.rhcloud.com/PayPalExpCheckout/

Setup file : src/util/config.properties

Sample configuration details:

USER=traveleadapp-facilitator_api1.gmail.com

PWD=UFD6Z548F3U879G4

SIGN=AFcWxV21C7fd0v3bYYYRCpSSRl31Ax6Qj.Q5ue4Ln1eUlrNJKZZ6KK2h

BNCODE=PP-ECWizard

SANDBOX_FLAG=true

VER=117

PP_CHECKOUT_URL_SANDBOX=https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&token=

PP_NVP_ENDPOINT_SANDBOX=https://api-3t.sandbox.paypal.com/nvp

PP_CHECKOUT_URL=https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=

PP_NVP_ENDPOINT=https://api-3t.paypal.com/nvp

Sandbox buyer details:

Buyer Id : traveleadapp-buyer-1@gmail.com 

password : traveleadapp123