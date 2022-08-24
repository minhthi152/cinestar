package com.cg.cinestar.service.movie;

import com.cg.cinestar.model.Category;
import com.cg.cinestar.model.Movie;
import com.cg.cinestar.model.dto.MovieDTO;
import com.cg.cinestar.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class MovieServiceImpl implements IMovieService{

    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<Movie> findAll() {

        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Movie getById(Long id) {
        return null;
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void remove(Long id) {

    }


    @Override
    public List<MovieDTO> findAllIMovieDTOByDeletedIsFalse() {
        return movieRepository.findAllIMovieDTOByDeletedIsFalse();
    }
}
