package org.test.views.conlose;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
// по хорошему сделать бы singleton
public class RequestContext {
    private Map<String, String> dataMap;

    public RequestContext() {
        dataMap = new HashMap<String, String>();
    }

    public void addData(String key, String value) {
        dataMap.put(key, value);
    }

    public Optional<String> getData(String key) {
        return Optional.ofNullable(dataMap.get(key));
    }
}
