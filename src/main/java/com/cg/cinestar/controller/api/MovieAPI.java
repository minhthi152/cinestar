package com.cg.cinestar.controller.api;

import com.cg.cinestar.exception.DataInputException;
import com.cg.cinestar.model.Category;
import com.cg.cinestar.model.JsonToMapConverter;
import com.cg.cinestar.model.dto.MovieDTO;
import com.cg.cinestar.repository.MovieRepository;
import com.cg.cinestar.service.category.ICategoryService;
import com.cg.cinestar.service.movie.IMovieService;
import com.cg.cinestar.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/api/movies")
public class MovieAPI {

    @Autowired
    private IMovieService movieService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private JsonToMapConverter jsonToMapConverter;

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        List<Movie> movies = movieService.findAll();
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<?>> findAllMovies(){
        List<MovieDTO> movies = movieService.findAllIMovieDTOByDeletedIsFalse();
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody MovieDTO movieDTO){
//        Set<Category> categories = movieDTO.getCategories();
//        movieDTO.setId("0");

        try {
            String categoryDetail = jsonToMapConverter.convertToEntityAttribute(movieDTO.getCategoryDetail());
            Movie movie = movieDTO.toMovie();
            movie.setId(null);
            movie.setCategoryDetail(categoryDetail);
            Movie movieCreated = movieService.save(movie);
            Map<String, Object> categories = jsonToMapConverter.convertToDatabaseColumn(movieCreated.getCategoryDetail());
            return new ResponseEntity<>(movieCreated.toMovieDTO(categories), HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new DataInputException("Invalid apartment creation information, please check the information again!");
        }
    }


}
