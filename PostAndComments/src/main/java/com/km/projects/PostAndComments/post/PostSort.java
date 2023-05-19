package com.km.projects.PostAndComments.post;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostSort {

    public static final Sort by( Function<Post,Object> property,String sortDirection) {

        List<String> propertyNames = List.of("id","title");
        List<Sort.Order> orderList = Sort.sort(Post.class).toList().stream()
                .filter(order -> propertyNames.contains(order.getProperty()))
                .collect(Collectors.toList());
        //this code is not finished

        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Sort sort = switch (direction) {
            case DESC -> Sort.sort(Post.class).by(orderList).descending();
            case ASC ->  Sort.sort(Post.class).by(orderList).ascending();
        };
        return sort;
    }

}
