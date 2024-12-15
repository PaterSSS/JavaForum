package org.test.views.conlose.pages;

import org.test.controllers.CategoryPageControllerI;
import org.test.models.DTOs.CategoryDTO;
import org.test.models.DTOs.PostDTO;
import org.test.models.Response;
import org.test.models.ResponseStatus;
import org.test.views.conlose.PageType;
import org.test.views.conlose.Request;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CategoryPage implements Page {
    private Request request;
    private Response<List<PostDTO>> posts;
    private Response<CategoryDTO> category;
    private final CategoryPageControllerI controller;

    public CategoryPage(CategoryPageControllerI controller) {
        this.controller = controller;
    }

    private void receiveData(String categoryName) {
        this.category = controller.categoryAllInfo(categoryName);
        this.posts = controller.categoryPosts(category.getData().categoryId());
    }

    private void prettyPrintForLongText(String text, int maxLineLength) {
        String[] words = text.split(" "); // Разбиваем текст на слова
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            if (currentLine.length() + word.length() + 1 > maxLineLength) {
                // Если добавление текущего слова превышает лимит, печатаем строку
                System.out.println(currentLine.toString());
                currentLine.setLength(0); // Очищаем строку
            }

            // Добавляем слово к текущей строке
            if (!currentLine.isEmpty()) {
                currentLine.append(" "); // Добавляем пробел между словами
            }
            currentLine.append(word);
        }

        // Печатаем оставшуюся часть строки
        if (!currentLine.isEmpty()) {
            System.out.println(currentLine);
        }
    }

    private void renderInfoAboutCategory() {
        if (category.getStatusCode() == ResponseStatus.NotFound) {
            System.out.println(category.getMessage() + "\n Enter b to return to previous page.");
            return;
        }
        if (posts.getStatusCode() == ResponseStatus.InternalServerError) {
            System.out.println(category.getMessage() + "\n Enter q to quit the application.");
            return;
        }

        System.out.println("Category name: " + category.getData().categoryName());
        System.out.println("Description:");
        prettyPrintForLongText(category.getData().description(), 60);
        System.out.println("-".repeat(60));
    }

    private void renderAllPostsInCategory() {
        if (posts.getStatusCode() == ResponseStatus.NotFound) {
            System.out.println(category.getMessage() + "\n Enter c to create the first one.");
            return;
        }
        if (posts.getStatusCode() == ResponseStatus.InternalServerError) {
            System.out.println(category.getMessage() + "\n Enter q to quit the application.");
        }

        int postIndex = 1;
        for (PostDTO post : posts.getData()) {
            System.out.println(postIndex + ") " + post.title());
            System.out.println("Author name: " + post.authorName());
            System.out.println("Content:");
            prettyPrintForLongText(post.content(), 60);
            System.out.println("-".repeat(60));
            postIndex++;
        }

        System.out.println("""
                Enter number of post to look it.
                Enter q to quit the application.
                Enter c to create your own post in the category.""");
    }

    @Override
    public void renderPage() {
        renderInfoAboutCategory();
        renderAllPostsInCategory();
    }

    @Override
    public Request handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().trim();

        if (userInput.matches("\\d")) {
            //TODO - проверка попадает ли введённое число в диапазон постов.
            int postId = Integer.parseInt(userInput);
            return new Request(Map.of(
                    "postId", String.valueOf(posts.getData().get(postId - 1).postId())
            ), PageType.POST);
        }
        switch (userInput) {
            case "q" -> {
                return new Request(Map.of(), PageType.EXIT);
            }
            case "c" -> {
                return new Request(Map.of(
                        "categoryId", String.valueOf(category.getData().categoryId()),
                        "categoryName", category.getData().categoryName()
                ), PageType.CREATE_POST);
            }
            case "b" -> {
                return new Request(Map.of(), PageType.MAIN);
            }
            default -> {
                System.out.println("Invalid user input, please try again after reloading the page in 3 seconds.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("Something went wrong while sleeping. Reloading page.");
                    return this.request;
                }
                return this.request;
            }
        }
    }

    @Override
    public void receiveRequest(Request request) {
        this.request = request;
        String categoryName = request.getParameter("categoryName").orElse("Cinema");
        receiveData(categoryName);
    }
}
