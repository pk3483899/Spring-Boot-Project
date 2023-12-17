package com.blog.ComparableAndComparator;

import java.util.Comparator;

public class MovieRatings implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getRatings()- o2.getRatings();
    }
}
