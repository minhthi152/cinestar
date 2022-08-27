package com.cg.cinestar.service.movie;

import com.cg.cinestar.exception.DataInputException;
import com.cg.cinestar.model.Category;
import com.cg.cinestar.model.FileMedia;
import com.cg.cinestar.model.Movie;
import com.cg.cinestar.model.dto.CategoryDTO;
import com.cg.cinestar.model.dto.IMovieDTO;
import com.cg.cinestar.model.dto.MovieDTO;
import com.cg.cinestar.model.enums.FileType;
import com.cg.cinestar.repository.FileMediaRepository;
import com.cg.cinestar.repository.MovieRepository;
import com.cg.cinestar.service.upload.IUploadService;
import com.cg.cinestar.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class MovieServiceImpl implements IMovieService{

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    FileMediaRepository fileMediaRepository;

    @Autowired
    IUploadService uploadService;


    @Autowired
    UploadUtils uploadUtils;

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

    @Override
    public Movie create(MovieDTO movieDTO) {

        String fileType = movieDTO.getFile().getContentType();

        assert fileType != null;

        fileType = fileType.substring(0, 5);

        movieDTO.setFileType(fileType);

        List<Category> categories = new ArrayList<>();

        for (CategoryDTO categoryDTO : movieDTO.getCategories()) {
            categories.add(categoryDTO.toCategory());
        }

        Movie movie = movieDTO.toMovie();
        movie.setCategories(categories);

        Movie newMovie = movieRepository.save(movie);

        FileMedia movieMedia = fileMediaRepository.save(movieDTO.toFileMedia());

        if (fileType.equals(FileType.IMAGE.getValue())) {
            uploadAndSaveProductImage(movieDTO, movie, movieMedia);
        }

        if (fileType.equals(FileType.VIDEO.getValue())) {
            uploadAndSaveProductVideo(movieDTO, movie, movieMedia);
        }

        return newMovie;
    }

    private void uploadAndSaveProductImage(MovieDTO movieDTO, Movie movie, FileMedia movieMedia) {
        try {
            Map uploadResult = uploadService.uploadImage(movieDTO.getFile(), uploadUtils.buildImageUploadParams(movieMedia));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            movieMedia.setFileName(movieMedia.getId() + "." + fileFormat);
            movieMedia.setFileUrl(fileUrl);
            movieMedia.setFileFolder(UploadUtils.IMAGE_UPLOAD_FOLDER);
            movieMedia.setCloudId(movieMedia.getFileFolder() + "/" + movieMedia.getId());
            movieMedia.setMovie(movie);
            fileMediaRepository.save(movieMedia);

        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload hình ảnh thất bại");
        }
    }

    private void uploadAndSaveProductVideo(MovieDTO movieDTO, Movie movie, FileMedia movieMedia) {
        try {
            Map uploadResult = uploadService.uploadVideo(movieDTO.getFile(), uploadUtils.buildVideoUploadParams(movieMedia));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            movieMedia.setFileName(movieMedia.getId() + "." + fileFormat);
            movieMedia.setFileUrl(fileUrl);
            movieMedia.setFileFolder(UploadUtils.VIDEO_UPLOAD_FOLDER);
            movieMedia.setCloudId(movieMedia.getFileFolder() + "/" + movieMedia.getId());
            movieMedia.setMovie(movie);
            fileMediaRepository.save(movieMedia);

        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload video thất bại");
        }
    }

}
