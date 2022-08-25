package com.cg.cinestar.service.movie;

import com.cg.cinestar.model.Movie;
import com.cg.cinestar.model.dto.IMovieDTO;
import com.cg.cinestar.model.dto.MovieDTO;
import com.cg.cinestar.service.IGeneralService;

import java.util.List;


public interface IMovieService extends IGeneralService<Movie> {

//    List<IMovieDTO> findAllIMovieDTOByDeletedIsFalse();

    List<MovieDTO> findAllIMovieDTOByDeletedIsFalse();
}
