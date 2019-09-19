package test.com.testdemo.Util;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLHelper {

    public SSLHelper() {
    }

    public static SSLContext getSSLContext() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        TrustManager[] trustManagers = new TrustManager[]{new SSLHelper.TrustEveryoneManager()};
        sslContext.init((KeyManager[])null, trustManagers, new SecureRandom());
        return sslContext;
    }

    private static class TrustEveryoneManager implements X509TrustManager {
        private TrustEveryoneManager() {
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}
