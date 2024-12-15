package org.test.views.conlose;

import org.test.context.SimpleContext;
import org.test.controllers.CategoryPageControllerI;
import org.test.controllers.MainPageControllerI;
import org.test.controllers.implementation.CategoryPageController;
import org.test.controllers.implementation.MainPageController;
import org.test.views.conlose.pages.CategoryPage;
import org.test.views.conlose.pages.MainPage;
import org.test.views.conlose.pages.Page;

public class ContextInitializer {
    private static SimpleContext context;

    private static void setupContext() {
        context = SimpleContext.getInstance();
    }

    private static void fillContext() {

        MainPageControllerI mainPageController = new MainPageController();
        CategoryPageControllerI categoryPageController = new CategoryPageController();
        context.setBean("categoryPageController", categoryPageController);
        context.setBean("mainPageController", mainPageController);

        Page mainPage = new MainPage(mainPageController);
        Page categoryPage = new CategoryPage(categoryPageController);

        context.setBean("mainPage", mainPage);
        context.setBean("categoryPage", categoryPage);
    }

    public static SimpleContext getContext() {
        setupContext();
        fillContext();

        return context;
    }
}
