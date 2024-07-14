package com.peytosoft.RestaurantService.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.peytosoft.RestaurantService.Dao.RestaurantDao;
import com.peytosoft.RestaurantService.Model.Restaurant;

@Service
public class RestaurantService {
	
	@Autowired
	RestaurantDao restaurantDao;

	public ResponseEntity<String> addRestaurant(Restaurant restaurant) {
		restaurantDao.save(restaurant);
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<Restaurant>> getAllRestaurants() {
		try {
            return new ResponseEntity<>(restaurantDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}
	
	

}
