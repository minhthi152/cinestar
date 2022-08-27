package com.cg.cinestar.model.dto;

import com.cg.cinestar.model.Category;
import com.cg.cinestar.model.FileMedia;
import com.cg.cinestar.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MovieDTO implements Serializable {
    private String id;
    private String title;

    private String premiereDate;
    private int showDuration;

    private List<CategoryDTO> categories;
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
//                .setCategories(category)
                .setPremiereDate(premiereDate)
                .setShowDuration(showDuration)
                .setDirector(director)
                .setActor(actor)
                .setLanguage(language)
                .setDescription(description);
    }

    public FileMedia toFileMedia() {
        return new FileMedia()
                .setId(fileProductId)
                .setFileName(fileName)
                .setFileFolder(fileFolder)
                .setFileUrl(fileUrl)
                .setCloudId(cloudId)
                .setFileType(fileType);
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

    public MovieDTO(String id, String title, String premiereDate, int showDuration, String director, String actor, String language, String description, String fileFolder, String fileName, String fileType, String fileUrl) {
        this.id = id;
        this.title = title;
        this.premiereDate = premiereDate;
        this.showDuration = showDuration;
        this.director = director;
        this.actor = actor;
        this.language = language;
        this.description = description;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
    }

}
