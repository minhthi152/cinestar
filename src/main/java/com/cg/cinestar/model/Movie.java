package com.cg.cinestar.model;

import com.cg.cinestar.model.dto.MovieDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "movies")
public class Movie extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String title;

    @Column(name="premiere_date")
    private String premiereDate;

    @Column(name="show_duration")
    private int showDuration;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movies_categories",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    private String director;

    private String actor;

    private String language;

    private String description;


    @OneToMany(mappedBy = "movie")
    private List<FileMedia> movieMedia;

    public MovieDTO toMovieDTO() {
        return new MovieDTO()
                .setId(id)
                .setTitle(title)
                .setPremiereDate(premiereDate)
                .setShowDuration(showDuration)
//                .setCategories(categories)
                .setDirector(director)
                .setActor(actor)
                .setLanguage(language)
                .setDescription(description);
    }

}
