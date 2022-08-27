package com.cg.cinestar.model.dto;


import com.cg.cinestar.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MovieCreateDTO {

    private String id;
    private String title;
    private String premiereDate;
    private int showDuration;
    private List<CategoryDTO> categories;
    private String director;
    private String actor;
    private String language;
    private String description;

    public MovieDTO toMovieDTO() {
        return new MovieDTO()
                .setId(id)
                .setTitle(title)
                .setCategories(categories)
                .setPremiereDate(premiereDate)
                .setShowDuration(showDuration)
                .setDirector(director)
                .setActor(actor)
                .setLanguage(language)
                .setDescription(description);
    }
}
