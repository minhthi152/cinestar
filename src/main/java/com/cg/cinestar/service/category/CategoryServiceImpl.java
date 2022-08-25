package com.cg.cinestar.service.category;

import com.cg.cinestar.model.Category;
import com.cg.cinestar.repository.CategoryRepository;
import com.cg.cinestar.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MovieRepository movieRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Category getById(Long id) {
        return null;
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Set<Category> findAllCategoriesByFilmId(String id) {
        return movieRepository.findAllCategoriesByFilmId(id);
    }
}
