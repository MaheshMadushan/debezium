package io.debezium.connector.arangodb;
import com.arangodb.entity.BaseDocument;
import com.arangodb.internal.ArangoDefaults;
import com.arangodb.internal.http.HttpConnection;
import com.arangodb.internal.net.HostDescription;
import com.arangodb.mapping.ArangoJack;
import com.arangodb.velocystream.Request;
import com.arangodb.velocystream.RequestType;
import com.arangodb.velocystream.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public interface ArangoDbConnection {

    public ArangoDbConnection getConnectionFor(String serverAddress);
}
