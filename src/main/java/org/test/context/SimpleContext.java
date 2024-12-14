package org.test.context;

import java.util.Map;
import java.util.Optional;

public final class SimpleContext {
    private Map<String, Object> context;
    private static SimpleContext instance;

    private SimpleContext() {}

    public static SimpleContext getInstance() {
        if (instance == null) {
            instance = new SimpleContext();
        }
        return instance;
    }

    public Object get(String key) {
        return Optional.ofNullable(context.get(key));
    }

    public void setBean(String key, Object bean) {
        context.put(key, bean);
    }
}
