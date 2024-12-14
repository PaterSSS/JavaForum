package org.test.views.conlose;

import java.util.Map;
import java.util.Optional;

public record Request(Map<String, String> parameters, PageType pageType) {

    // Метод для безопасного получения значения с использованием Optional
    public Optional<String> getParameter(String key) {
        return Optional.ofNullable(parameters.get(key));
    }

    // Проверка наличия ключа
    public boolean containsParameter(String key) {
        return parameters.containsKey(key);
    }
}
