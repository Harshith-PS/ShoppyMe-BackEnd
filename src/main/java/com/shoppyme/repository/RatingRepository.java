package com.shoppyme.repository;

import org.springframework.data.repository.CrudRepository;

import com.shoppyme.entity.CompositeKeyRating;
import com.shoppyme.entity.Rating;

public interface RatingRepository extends CrudRepository<Rating, CompositeKeyRating> {

}
