package com.cg.cinestar.model;

import com.cg.cinestar.model.dto.MovieDTO;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "movies")
@TypeDef(name = "json", typeClass = JsonStringType.class)
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

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "movies_categories",
//            joinColumns = @JoinColumn(name = "movie_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id")
//    )
//    private Set<Category> categories;

    @Column(name = "category_detail", columnDefinition = "json")
    private String categoryDetail;

    private String director;

    private String actor;

    private String language;

    private String description;

    public MovieDTO toMovieDTO(Map<String, Object> categoryDetail) {
        return new MovieDTO()
                .setId(id)
                .setTitle(title)
                .setPremiereDate(premiereDate)
                .setShowDuration(showDuration)
                .setCategoryDetail(categoryDetail)
                .setDirector(director)
                .setActor(actor)
                .setLanguage(language)
                .setDescription(description);
    }

}
