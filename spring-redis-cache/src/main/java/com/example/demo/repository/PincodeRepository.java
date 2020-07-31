package com.example.demo.repository;

import java.util.Map;

import com.example.demo.model.Pincode;

public interface PincodeRepository {
	
	void save(Pincode pincode);
	Pincode find(Long id);
	Map<Long, Pincode> findAllPincodes();
	void delete(Long id);
}
