package com.cg.cinestar.controller.api;

import com.cg.cinestar.exception.DataInputException;
import com.cg.cinestar.model.Category;
import com.cg.cinestar.model.FileMedia;
import com.cg.cinestar.model.dto.IMovieDTO;
import com.cg.cinestar.model.dto.MovieDTO;
import com.cg.cinestar.model.dto.MovieTestDTO;
import com.cg.cinestar.repository.FileMediaRepository;
import com.cg.cinestar.repository.MovieRepository;
import com.cg.cinestar.service.category.ICategoryService;
import com.cg.cinestar.service.movie.IMovieService;
import com.cg.cinestar.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


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
//            movieDTO.setCategories(categoryService.findAllCategoriesByFilmId(movieDTO.getId()));
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(MovieDTO movieDTO){
//        List<Category> categories = movieDTO.getCategories();
//        movieDTO.setId("0");

        List<Category> categories = new ArrayList<>();

        try {
            Movie movie = movieDTO.toMovie();
            movie.setCategories(categories);

            Movie movieCreated = movieService.create(movieDTO);
            Optional<FileMedia> movieMedia = fileMediaRepository.findByMovie(movieCreated);
            movieDTO = movieCreated.toMovieDTO();
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
