package com.tendril.cleanweb.stupid;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.client.apache.ApacheHttpClient;
import com.sun.jersey.client.apache.config.DefaultApacheHttpClientConfig;
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
public class Shit {

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

        try {
            SSLContext sslContext = null;

            try {
                sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            } catch (Exception e) {
            }

            DefaultApacheHttpClientConfig config = new DefaultApacheHttpClientConfig();
            config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            }, sslContext));

            Client c = ApacheHttpClient.create(config);
            c.addFilter(new com.sun.jersey.api.client.filter.HTTPBasicAuthFilter("cleanweb.hackathon@tendrilinc.com", "hackathon"));
            WebResource webResource = c.resource("https://dev-program.tendrildemo.com/api/rest/user/current-user");
            TendrilUser user = webResource.accept(MediaType.APPLICATION_JSON_TYPE).get(TendrilUser.class);
            System.out.println(user.getFirstName());

        } catch (Exception e) {
            System.out.println("fuck - " + e);
        }
    }

    private static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
}
