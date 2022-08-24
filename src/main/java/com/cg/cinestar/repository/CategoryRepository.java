package com.cg.cinestar.repository;

import com.cg.cinestar.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

    @Query("SELECT m.categoryDetail FROM Movie m WHERE m.id = :id")
    List<Category> findAllCategoriesByFilmId(String id);

}
