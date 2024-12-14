package org.test.views.conlose;

import org.test.context.SimpleContext;
import org.test.views.conlose.pages.Page;

import java.util.Map;

public class Paginator {
    private final SimpleContext simpleContext;

    public Paginator(SimpleContext context) {
        this.simpleContext = context;
    }

    private Request navigateTo(Request request) {
        Page currentPage = (Page) simpleContext.get(request.pageType().getValue());
        currentPage.receiveRequest(request);
        currentPage.renderPage();
        return currentPage.handleUserInput();
    }

    public void start() {
        Request request = new Request(Map.of(
                "userId", "111"
        ), PageType.MAIN);

        while (request.pageType() != PageType.EXIT) {
            request = navigateTo(request);
        }
    }
}
