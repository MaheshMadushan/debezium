package io.debezium.connector.arangodb;

import com.arangodb.Protocol;
import com.arangodb.internal.ArangoDefaults;
import com.arangodb.internal.http.HttpConnection;
import com.arangodb.internal.http.HttpConnectionFactory;
import com.arangodb.internal.net.HostDescription;
import com.arangodb.mapping.ArangoJack;
import com.arangodb.util.ArangoSerialization;
import org.apache.http.client.HttpRequestRetryHandler;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ArangoDbHTTPConnection implements ArangoDbConnection{

    private final short ARANGODB_MAX_CONNECTION_IN_POOL = 8;
    /**
     * keep server address type as string for now
     */
    private final Map<String, ArangoDbConnection> directConnections = new ConcurrentHashMap<>(ARANGODB_MAX_CONNECTION_IN_POOL);
    private final Map<List<String>, ArangoDbConnection> connections = new ConcurrentHashMap<>();

    @Override
    public ArangoDbConnection getConnectionFor(String host) {
        return null;
    }

    private ArangoDbHTTPConnection(){
    }

    public static class HTTPConnectionSettings {
        private String user;
        private String password;
        private ArangoSerialization util;
        private Boolean useSsl;
        private String httpCookieSpec;
        private Protocol contentType;
        private HostDescription host;
        private Long ttl;
        private SSLContext sslContext;
        private HostnameVerifier hostnameVerifier;
        private Integer timeout;
        private HttpRequestRetryHandler httpRequestRetryHandler;

        public HTTPConnectionSettings setUser(String user) {
            this.user = user;
            return this;
        }

        public HTTPConnectionSettings setPassword(String password) {
            this.password = password;
            return this;
        }

        public HTTPConnectionSettings setUtil(ArangoSerialization util) {
            this.util = util;
            return this;
        }

        public HTTPConnectionSettings setUseSsl(Boolean useSsl) {
            this.useSsl = useSsl;
            return this;
        }

        public HTTPConnectionSettings setHttpCookieSpec(String httpCookieSpec) {
            this.httpCookieSpec = httpCookieSpec;
            return this;
        }

        public HTTPConnectionSettings setContentType(Protocol contentType) {
            this.contentType = contentType;
            return this;
        }

        public HTTPConnectionSettings setHost(HostDescription host) {
            this.host = host;
            return this;
        }

        public HTTPConnectionSettings setTtl(Long ttl) {
            this.ttl = ttl;
            return this;
        }

        public HTTPConnectionSettings setSslContext(SSLContext sslContext) {
            this.sslContext = sslContext;
            return this;
        }

        public HTTPConnectionSettings setHostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.hostnameVerifier = hostnameVerifier;
            return this;
        }

        public HTTPConnectionSettings setTimeout(Integer timeout) {
            this.timeout = timeout;
            return this;
        }

        public HTTPConnectionSettings setHttpRequestRetryHandler(HttpRequestRetryHandler httpRequestRetryHandler) {
            this.httpRequestRetryHandler = httpRequestRetryHandler;
            return this;
        }
    }

    public static class HTTPConnectionSettingsBuilder{
        HTTPConnectionSettings settings = new HTTPConnectionSettings();

        public HTTPConnectionSettings build(){
            return settings.setContentType(com.arangodb.Protocol.HTTP_JSON)
                    .setUseSsl(ArangoDefaults.DEFAULT_USE_SSL) // null for now
                    .setHostnameVerifier(null) // null for now TODO : implement HostnameVerifier
                    .setPassword(null) // null for now TODO : implement a method to set password
                    .setHttpRequestRetryHandler(null)
                    .setUser(ArangoDefaults.DEFAULT_USER)
                    .setTtl(null)
                    .setHttpCookieSpec(null)
                    .setTimeout(ArangoDefaults.DEFAULT_TIMEOUT)
                    .setUtil(new ArangoJack())
                    .setHost(new HostDescription(ArangoDefaults.DEFAULT_HOST, ArangoDefaults.DEFAULT_PORT));
        }
    }

    public static class Builder{
        private HTTPConnectionSettings settings;

        public Builder settings(HTTPConnectionSettings settings){
            this.settings = settings;
            return this;
        }

        public HttpConnection build(){

            if(settings == null){
                this.settings = new HTTPConnectionSettingsBuilder().build();
            }

            return (HttpConnection) new HttpConnectionFactory(
                    settings.timeout,
                    settings.user,
                    settings.password,
                    settings.useSsl,
                    settings.sslContext,
                    settings.hostnameVerifier,
                    settings.util,
                    settings.contentType,
                    settings.ttl,
                    settings.httpCookieSpec,
                    settings.httpRequestRetryHandler).create(settings.host);
        }
    }


}
