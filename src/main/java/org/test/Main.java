package org.test;

import org.test.views.conlose.ContextInitializer;
import org.test.views.conlose.Paginator;

public class Main {
    public static void main(String[] args) {
        Paginator p = new Paginator(ContextInitializer.getContext());
        p.start();
    }
}