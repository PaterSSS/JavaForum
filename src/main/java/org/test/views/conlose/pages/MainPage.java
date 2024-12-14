package org.test.views.conlose.pages;

import org.test.controllers.MainPageControllerI;
import org.test.controllers.implementation.MainPageController;
import org.test.models.DTOs.CategoryDTO;
import org.test.models.DTOs.UserDTO;
import org.test.models.Response;
import org.test.models.ResponseStatus;

import java.util.List;
import java.util.Scanner;

public class MainPage extends Page {
    private final MainPageControllerI controller;
    private Response<UserDTO> userData;
    private Response<List<CategoryDTO>> categories;

    public MainPage(MainPageControllerI controller) {
        this.controller = controller;
        receiveDate();
    }

    private void receiveDate() {
        userData = controller.username(111);
        categories = controller.categories();

    }

    private void renderUsernameBox() {

        if (userData.getStatusCode() == ResponseStatus.InternalServerError) {
            System.out.println(userData.getMessage() + " Please try to login again.");
            System.out.println("Enter q to exit.");
            return;
        }

        String username = (userData.getStatusCode() == ResponseStatus.NotFound) ? "Anon"
                : userData.getData().username();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < username.length() + 6; j++) {
                if (i == 0 || i == 2) {
                    System.out.print("_");
                } else {
                    if (j == 0 || j == username.length() + 5) {
                        System.out.print("|");
                    } else if (j < 3 || j > username.length() + 2) {
                        System.out.print(" ");
                    } else {
                        System.out.print(username.charAt(j - 3));
                    }
                }
            }
            System.out.println();
        }

        if (userData.getStatusCode() == ResponseStatus.NotFound) {
            System.out.println("Enter l: if you want to login");
        } else {
            System.out.println("Enter p: if you want to check your profile");

        }
    }

    private void renderCategoriesList() {

        if (categories.getStatusCode() == ResponseStatus.InternalServerError) {
            System.out.println(categories.getMessage() + " Please try again later.");
            System.out.println("Enter q to exit.");
            return;
        }
        List<CategoryDTO> categoriesList = categories.getData();

        for (int i = 0; i < categoriesList.size(); i++)
            System.out.println(i + 1 + ". " + categoriesList.get(i).categoryName());

        System.out.println("Enter number of category you want to view");
    }

    @Override
    public void renderPage() {
        renderUsernameBox();
        renderCategoriesList();
    }

    @Override
    public void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().toLowerCase().trim();

        if (userInput.matches("\\d")) {
            getContext().addData("CategoryId", userInput);
            getPaginator()
        }
    }

    public static void main(String[] args) {
        MainPage test = new MainPage(new MainPageController());
        test.receiveDate();
        test.renderUsernameBox();
        test.renderCategoriesList();
    }
}
