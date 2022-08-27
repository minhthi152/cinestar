package com.cg.cinestar.controller.api;

import com.cg.cinestar.exception.DataInputException;
import com.cg.cinestar.model.Category;
import com.cg.cinestar.model.FileMedia;
import com.cg.cinestar.model.dto.*;
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
    public ResponseEntity<?> create(@RequestParam ("client") String client,
                                    @RequestParam(value = "files", required = false) MultipartFile files) throws JsonProcessingException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            MovieCreateDTO movieCreateDTO = mapper.readValue(client, MovieCreateDTO.class);

            MovieDTO movieDTO = movieCreateDTO.toMovieDTO();
            movieDTO.setFile(files);

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

    @PostMapping(value = "/create-test", produces = "application/json")
    public ResponseEntity<?> createTest(@RequestParam ("client") String client,
                                    @RequestParam(value = "files", required = false) MultipartFile files) throws JsonProcessingException {

        List<Category> categories = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        MovieCreateDTO movieCreateDTO = mapper.readValue(client, MovieCreateDTO.class);

        MovieDTO movieDTO = movieCreateDTO.toMovieDTO();
        movieDTO.setFile(files);

        System.out.println(movieDTO.getCategories());

        return new ResponseEntity<>(movieCreateDTO, HttpStatus.CREATED);

    }


}
