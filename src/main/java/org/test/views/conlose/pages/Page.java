package org.test.views.conlose.pages;

import org.test.views.conlose.Paginator;
import org.test.views.conlose.RequestContext;

// превратить в интерфейс. Если сделать объект для передачи сообщений между страницами, то использование
// абстрактного класса излишне.
public abstract class Page {
    private RequestContext context;
    private Paginator paginator;


    public abstract void renderPage();

    public abstract void handleUserInput();

    public void setContext(RequestContext context) {
        this.context = context;
    }

    public RequestContext getContext() {
        return context;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

    public Paginator getPaginator() {
        return paginator;
    }
}
