package com.cg.cinestar.controller.api;

import com.cg.cinestar.exception.DataInputException;
import com.cg.cinestar.model.Category;
import com.cg.cinestar.model.FileMedia;
import com.cg.cinestar.model.dto.CategoryDTO;
import com.cg.cinestar.model.dto.IMovieDTO;
import com.cg.cinestar.model.dto.MovieDTO;
import com.cg.cinestar.model.dto.MovieTestDTO;
import com.cg.cinestar.repository.FileMediaRepository;
import com.cg.cinestar.repository.MovieRepository;
import com.cg.cinestar.service.category.ICategoryService;
import com.cg.cinestar.service.movie.IMovieService;
import com.cg.cinestar.model.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;


@RestController
@RequestMapping("/api/movies")
public class MovieAPI {

    @Autowired
    IMovieService movieService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    FileMediaRepository fileMediaRepository;


    @GetMapping
    public ResponseEntity<?> findAllMovies(){
        List<MovieDTO> movies = movieService.findAllIMovieDTOByDeletedIsFalse();
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (MovieDTO movieDTO: movies) {
            movieDTO.setCategories(categoryService.findAllCategoriesByFilmId(movieDTO.getId()).toString());
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(MovieDTO movieDTO){
        String categories = movieDTO.getCategories();

        movieDTO.setId("0");

        try {
//            Movie movieCreated = movieService.save(movieDTO.toMovie().setCategories(categoryList));

            Movie movieCreated = movieService.create(movieDTO);
            Optional<FileMedia> movieMedia = fileMediaRepository.findByMovie(movieCreated);

            movieDTO = movieCreated.toMovieDTO().setCategories(categories);
            movieDTO.setFileUrl(movieMedia.get().getFileUrl());
            return new ResponseEntity<>(movieDTO, HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new DataInputException("Invalid creation information, please check the information again!");
        }

    }

    @PostMapping("/create-test")
    public ResponseEntity<?> create(MovieTestDTO movieDTO){
        System.out.println(movieDTO.getTitle());
        return new ResponseEntity<>(movieDTO.getFile(), HttpStatus.CREATED);
    }
}
