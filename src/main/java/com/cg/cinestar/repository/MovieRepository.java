package com.cg.cinestar.repository;

import com.cg.cinestar.model.Category;
import com.cg.cinestar.model.Movie;
import com.cg.cinestar.model.dto.IMovieDTO;
import com.cg.cinestar.model.dto.MovieDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

//    @Query("SELECT NEW com.cg.cinestar.model.dto.MovieDTO (" +
//            "m.id, " +
//            "m.title, " +
//            "m.premiereDate, " +
//            "m.showDuration, " +
//            "m.director, " +
//            "m.actor," +
//            "m.language," +
//            "m.description" +
//            ") " +
//            "FROM Movie m " +
//            "WHERE m.deleted = false"
//    )
//    List<MovieDTO> findAllIMovieDTOByDeletedIsFalse();

//    @Query("SELECT " +
//            "fm.id AS id, " +
//            "fm.movie.title AS title, " +
//            "fm.movie.premiereDate AS premiereDate, " +
//            "fm.movie.showDuration AS showDuration, " +
//            "fm.movie.director AS director, " +
//            "fm.movie.actor AS actor, " +
//            "fm.movie.language AS language, " +
//            "fm.movie.description AS description, " +
//            "fm.id AS fileId, " +
//            "fm.fileFolder AS fileFolder, " +
//            "fm.fileName AS fileName, " +
//            "fm.fileType AS fileType, " +
//            "fm.fileUrl AS fileUrl " +
//            "FROM FileMedia fm " +
//            "WHERE fm.movie.deleted = false"
//    )
//    List<IMovieDTO> findAllIMovieDTOByDeletedIsFalse();

    @Query("SELECT NEW com.cg.cinestar.model.dto.MovieDTO (" +
            "fm.movie.id, " +
            "fm.movie.title, " +
            "fm.movie.premiereDate, " +
            "fm.movie.showDuration, " +
            "fm.movie.director, " +
            "fm.movie.actor, " +
            "fm.movie.language, " +
            "fm.movie.description, " +
            "fm.fileFolder, " +
            "fm.fileName, " +
            "fm.fileType, " +
            "fm.fileUrl " +
            ") " +
            "FROM FileMedia fm " +
            "WHERE fm.movie.deleted = false"
    )
    List<MovieDTO> findAllIMovieDTOByDeletedIsFalse();



    @Query("SELECT m.categories FROM Movie m WHERE m.id = :id")
    Set<Category> findAllCategoriesByFilmId(String id);

}
