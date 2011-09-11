package com.tendril.cleanweb.stupid;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;
import com.tendril.cleanweb.domain.tendril.TendrilUser;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.core.MediaType;

/**
 */
public class Fuck {

//    public static void main(String[] args) {
//        ClientConfig config = new DefaultClientConfig();
//        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(DO_NOT_VERIFY, createSSLContext()));
//    }
//
    private static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
//
//    private SSLContext createSSLContext() throws Exception {
//        SSLContext ctx = SSLContext.getInstance("SSL");
//        ctx.init(null, new TrustManager[]{new MyX509TrustManager("/Users/ghengeli/.keystore", "!t3ndr1l".toCharArray())}, null);
//
//        return ctx;
//    }



    public static void main(String[] args) {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

// Install the all-trusting trust manager
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        } catch (Exception e) {
        }

// Now you can access an https URL without having the certificate in the truststore
        try {
//            URL url = new URL("https://dev-program.tendrildemo.com/api/rest/user/current-user");
//            System.out.println(url.getContent());

//            DefaultApacheHttpClientConfig config = new DefaultApacheHttpClientConfig();
//            config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(null, sslContext));
//            config.getState().setCredentials(null, null, -1, "cleanweb.hackathon@tendrilinc.com", "hackathon");
//
//            Client c = ApacheHttpClient.create(config);

        ClientConfig config = new DefaultClientConfig();
        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(null, sslContext));
        Client c = Client.create(config);

            WebResource webResource = c.resource("https://dev-program.tendrildemo.com/api/rest/user/current-user");
//            webResource.header("Emsauthtoken", userName + ":" + password);
            TendrilUser user = webResource.type(MediaType.APPLICATION_JSON_TYPE).get(TendrilUser.class);

        } catch (Exception e) {
            System.out.println("fuck - " + e);
        }
    }
}
