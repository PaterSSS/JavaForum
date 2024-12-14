package org.test.views.conlose;

import org.test.context.SimpleContext;
import org.test.views.conlose.pages.MainPage;
import org.test.views.conlose.pages.Page;
// вообще появляются мылси по поводу передачи сообщений. Поскольку метод навигейт должен быть приватным
// всё таки, но пока оставим так. А то буду с этим возиться годы.
public class Paginator {
    private final SimpleContext simpleContext;

    public Paginator(SimpleContext context) {
        this.simpleContext = context;
    }

    private void navigateTo(String url) {
        Page currentPage = (Page) simpleContext.get(url);
        currentPage.renderPage();
        currentPage.handleUserInput();
    }

    public void start() {
        navigateTo("mainPage");
    }
}
