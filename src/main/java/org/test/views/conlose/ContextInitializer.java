package org.test.views.conlose;

import org.test.context.SimpleContext;
import org.test.controllers.MainPageControllerI;
import org.test.controllers.implementation.MainPageController;
import org.test.views.conlose.pages.MainPage;
import org.test.views.conlose.pages.Page;

public class ContextInitializer {
    private static SimpleContext context;

    private static void setupContext() {
        context = SimpleContext.getInstance();
    }

    private static void fillContext() {
        RequestContext requestContext = new RequestContext();

        MainPageControllerI mainPageController = new MainPageController();
        context.setBean("mainPageController", mainPageController);

        Page mainPage = new MainPage(mainPageController);
        mainPage.setContext(requestContext);

        context.setBean("mainPage", mainPage);
    }

    public static SimpleContext getContext() {
        setupContext();
        fillContext();

        return context;
    }
}
