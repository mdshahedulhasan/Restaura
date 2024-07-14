package com.peytosoft.RestaurantService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peytosoft.RestaurantService.Model.Restaurant;
import com.peytosoft.RestaurantService.Service.RestaurantService;


@RestController
@RequestMapping("restaurant")
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;
	
	@GetMapping("allRestaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        return restaurantService.getAllRestaurants();
    }
	
	@PostMapping("addRestaurant")
	public ResponseEntity<String> addRestaurant (@RequestBody Restaurant restaurant ){
		return restaurantService.addRestaurant(restaurant);
	}

}
