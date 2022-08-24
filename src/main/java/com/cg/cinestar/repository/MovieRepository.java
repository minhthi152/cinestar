package com.cg.cinestar.repository;

import com.cg.cinestar.model.Movie;
import com.cg.cinestar.model.dto.MovieDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    @Query("SELECT NEW com.cg.cinestar.model.dto.MovieDTO (" +
                "m.id, " +
                "m.categoryDetail, " +
                "m.title, " +
                "m.premiereDate, " +
                "m.showDuration, " +
                "m.director, " +
                "m.actor," +
                "m.language," +
                "m.description" +
            ") " +
            "FROM Movie m " +
            "WHERE m.deleted = false"
    )
    List<MovieDTO> findAllIMovieDTOByDeletedIsFalse();



}
