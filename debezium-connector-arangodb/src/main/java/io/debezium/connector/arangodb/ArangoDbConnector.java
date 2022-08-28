package io.debezium.connector.arangodb;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;

import java.util.List;
import java.util.Map;

public class ArangoDbConnector extends SourceConnector {
    @Override
    public void start(Map<String, String> map) {
        throw new UnsupportedOperationException("Yet to implement");
    }

    @Override
    public Class<? extends Task> taskClass() {
        return null;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int i) {
        return null;
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Yet to implement");
    }

    @Override
    public ConfigDef config() {
        return null;
    }

    @Override
    public String version() {
        return null;
    }
}
