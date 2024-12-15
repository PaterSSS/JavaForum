package org.test.views.conlose.pages;

import org.test.controllers.MainPageControllerI;
import org.test.controllers.implementation.MainPageController;
import org.test.models.DTOs.CategoryDTO;
import org.test.models.DTOs.UserDTO;
import org.test.models.Response;
import org.test.models.ResponseStatus;
import org.test.views.conlose.PageType;
import org.test.views.conlose.Request;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
// проверить вывод, когда не находит юзера база данных. По хорошему нужен глобальный объект session, в котором
// будет храниться id пользователя и другая доп инфа имеющая ценность на всех страница. ПОка не знаю что кроме id туда
// впихнуть можно.
public class MainPage implements Page {
    private final MainPageControllerI controller;
    private Response<UserDTO> userData;
    private Response<List<CategoryDTO>> categories;
    private Request request;

    public MainPage(MainPageControllerI controller) {
        this.controller = controller;
    }

    private void receiveDate(String userId) {
        userData = controller.username(Integer.parseInt(userId));
        categories = controller.categories();
    }

    private void renderUsernameBox() {
        //это блок кода вынести бы по-хорошему в отдельную функцию проверки(утверждение к другим таким же относится)
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

        System.out.println("Enter number of category you want to view: ");
    }

    @Override
    public void renderPage() {
        renderUsernameBox();
        renderCategoriesList();
    }

    @Override
    public Request handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().toLowerCase().trim();

        if (userInput.matches("\\d")) {
            //TODO - добавить проверку что введённое число попадает в диапазон тем
            //Если нет то, можно снова вывести всю страницу(затратно) придумать как крутить ввод пока правильный не
            //получим
            String selectedCategoryName = categories.getData().get(Integer.parseInt(userInput) - 1).categoryName();

            return new Request(Map.of(
                    "categoryName", selectedCategoryName
            ), PageType.CATEGORY);
        } else {
            switch (userInput) {
                case "q" -> {
                    return new Request(null, PageType.EXIT);
                }
                case "p" -> {
                    return new Request(Map.of(
                            "userId", request.getParameter("userId").orElse("111")
                    ), PageType.PROFILE);
                }
                case "l" -> {
                    return new Request(Map.of(), PageType.LOGIN);
                }
                default -> {
                    System.out.println("Seems you entered an invalid commend.\n" +
                            "Please try again. Page will be reloaded in 3 seconds.");
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
    }

    @Override
    public void receiveRequest(Request request) {
        this.request = request;
        String userId = request.getParameter("userId").orElse("111"); // 111 - id того кто ещё на авторизовался
        receiveDate(userId);
    }

    public static void main(String[] args) {
        MainPage test = new MainPage(new MainPageController());
        test.receiveDate("111");
        test.renderUsernameBox();
        test.renderCategoriesList();
    }
}
