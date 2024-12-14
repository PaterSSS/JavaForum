package org.test.views.conlose.pages;

import org.test.views.conlose.Request;

public interface Page {

    void renderPage();

    Request handleUserInput();

    void receiveRequest(Request request);
}
