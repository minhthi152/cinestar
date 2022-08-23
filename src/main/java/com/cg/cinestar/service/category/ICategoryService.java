package com.cg.cinestar.service.category;

import com.cg.cinestar.model.Category;
import com.cg.cinestar.service.IGeneralService;

import java.util.List;

public interface ICategoryService extends IGeneralService<Category> {
    List<Category> findAllCategoriesByFilmId(String id);
}
