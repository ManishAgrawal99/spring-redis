package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Pincode;
import com.example.demo.service.PincodeService;

@RestController
@RequestMapping("/redis/pincode")
public class PincodeController {
	
	@Autowired
	private PincodeService pincodeService;
	
	@PostMapping
	public Pincode saveNewPincode(@RequestBody Pincode pincode) {
		pincodeService.save(pincode);
		return pincode;
	}
	
	
	@Cacheable(key = "#id", value = "pincodes", unless = "#result.id < 1200")
	@GetMapping(path = "/{id}")
	public Pincode fetchPincode(@PathVariable("id") long id) {
		System.out.println("Returning Pincode with ID: "+ id);
		return pincodeService.find(id);
	}
	
	
	@CacheEvict(key = "#id", value = "pincodes")
	@DeleteMapping("/{id}")
	public String deleteOldPincode(@PathVariable("id") long id) {
		pincodeService.delete(id);
		return "Successfully removed pincode with ID: " + id;
	}
	
	@GetMapping
	public Map<Long, Pincode> fetchAllPincodes(){
		return pincodeService.findAllPincodes();
	}
	
	
}
