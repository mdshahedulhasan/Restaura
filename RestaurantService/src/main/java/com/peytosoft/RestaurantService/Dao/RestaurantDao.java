package com.peytosoft.RestaurantService.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peytosoft.RestaurantService.Model.Restaurant;

@Repository
public interface RestaurantDao extends JpaRepository<Restaurant, Integer> {

}
