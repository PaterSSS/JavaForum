package org.test.controllers;

import org.test.models.DTOs.CategoryDTO;
import org.test.models.DTOs.PostDTO;
import org.test.models.Response;

import java.util.List;

public interface CategoryPageControllerI {
    Response<CategoryDTO> categoryAllInfo(String categoryName);
    Response<List<PostDTO>> categoryPosts(int categoryId);
}
