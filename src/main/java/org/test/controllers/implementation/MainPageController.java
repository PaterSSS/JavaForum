package org.test.controllers.implementation;

import org.test.controllers.MainPageControllerI;
import org.test.models.DTOs.CategoryDTO;
import org.test.models.DTOs.UserDTO;
import org.test.models.Response;
import org.test.models.SuccessResponse;

import java.util.List;

public class MainPageController implements MainPageControllerI {
    @Override
    public Response<UserDTO> username(int userIf) {
        return new SuccessResponse<>(new UserDTO("Jon Dow", 111));
    }

    @Override
    public Response<List<CategoryDTO>> categories() {
        return new SuccessResponse<>(List.of(new CategoryDTO("cinema", "common films about nature"),
                new CategoryDTO("Science", "about intimidating facts")));

    }
}
