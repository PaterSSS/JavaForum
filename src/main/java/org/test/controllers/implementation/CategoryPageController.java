package org.test.controllers.implementation;

import org.test.controllers.CategoryPageControllerI;
import org.test.models.DTOs.CategoryDTO;
import org.test.models.DTOs.PostDTO;
import org.test.models.Response;
import org.test.models.SuccessResponse;

import java.util.List;

public class CategoryPageController implements CategoryPageControllerI {
    @Override
    public Response<CategoryDTO> categoryAllInfo(String categoryName) {
        return new SuccessResponse<>(new CategoryDTO(1,"cinema", "common films about nature"));
    }

    @Override
    public Response<List<PostDTO>> categoryPosts(int categoryId) {
        return new SuccessResponse<>((List.of(new PostDTO(1, "The best movie ever", "recently i have watched " +
                "such an amazing movie called Avatar. It was fantastic fantacy about blue people. You should watch" +
                "it too", 111, "Jon Dow", "14.12.2024"))));
    }
}
