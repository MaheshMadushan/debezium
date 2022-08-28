package io.debezium.connector.arangodb;

import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;

import java.util.List;
import java.util.Map;

public class ArangoDbConnectorTask extends SourceTask {

    @Override
    public String version() {
        return null;
    }

    @Override
    public void start(Map<String, String> map) {
        throw new UnsupportedOperationException("Yet to implement");
    }

    @Override
    public List<SourceRecord> poll() throws InterruptedException {
        return null;
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Yet to implement");
    }
}
