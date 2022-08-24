package com.cg.cinestar.model.dto;

import com.cg.cinestar.model.JsonToMapConverter;
import com.cg.cinestar.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MovieDTO {

    @Autowired
    private JsonToMapConverter jsonToMap;

    private String id;
    private String title;

    private String premiereDate;
    private int showDuration;

    private Map<String, Object> categoryDetail;
    private String director;
    private String actor;
    private String language;
    private String description;
    private String fileName;
    private String fileFolder;
    private String fileUrl;
    private String cloudId;
    private String fileProductId;

    private MultipartFile file;
    private String fileType;

    public Movie toMovie() {
        return new Movie()
                .setId(id)
                .setTitle(title)
                .setPremiereDate(premiereDate)
                .setShowDuration(showDuration)
                .setDirector(director)
                .setActor(actor)
                .setLanguage(language)
                .setDescription(description);
    }

    public MovieDTO(String id, String title, String premiereDate, int showDuration, String director, String actor, String language, String description) {
        this.id = id;
        this.title = title;
        this.premiereDate = premiereDate;
        this.showDuration = showDuration;
        this.director = director;
        this.actor = actor;
        this.language = language;
        this.description = description;
    }

//    String, Object, String, String, int, String, String, String, String

    public MovieDTO(String id, String categoryDetail, String title, String premiereDate, int showDuration, String director, String actor, String language, String description) {
        this.id = id;
        this.categoryDetail = jsonToMap.convertToDatabaseColumn(categoryDetail);
        this.title = title;
        this.premiereDate = premiereDate;
        this.showDuration = showDuration;
        this.director = director;
        this.actor = actor;
        this.language = language;
        this.description = description;
    }
}
