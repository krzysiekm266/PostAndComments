package com.km.projects.PostAndComments.post;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostSort {

    public static final Sort by( String property,String sortDirection) {

//        Sort sortById = Sort.sort(Post.class).by(Post::getId).descending();
//        Sort sortByTitle = Sort.sort(Post.class).by(Post::getTitle).descending();
//        Sort sortByAuthor = Sort.sort(Post.class).by(Post::getAuthor).descending();
//        Sort sortByDate = Sort.sort(Post.class).by(Post::getTimestampMilisec).descending();

        Sort defaultSort = Sort.sort(Post.class).by(Post::getId).descending();
        Sort typedSort = switch (property) {
            case "id" -> Sort.sort(Post.class).by(Post::getId).descending();
            case "title" -> Sort.sort(Post.class).by(Post::getTitle).descending();
            case "author" ->  Sort.sort(Post.class).by(Post::getAuthor).descending();
            case "date" ->  Sort.sort(Post.class).by(Post::getTimestampMilisec).descending();
            default -> defaultSort;
        };
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Sort sort = switch (direction) {
            case DESC -> typedSort.descending();
            case ASC ->  typedSort.ascending();
        };
        return sort;
    }

}
