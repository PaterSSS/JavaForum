package org.test.controllers;

import org.test.models.DTOs.CategoryDTO;
import org.test.models.DTOs.UserDTO;
import org.test.models.Response;

import java.util.List;

public interface MainPageControllerI {
    Response<UserDTO> username(int userId);
    Response<List<CategoryDTO>> categories();
}
