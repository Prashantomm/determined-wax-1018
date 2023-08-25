package com.masai.dao;

import java.util.List;

import com.masai.entity.Home;
import com.masai.exceptions.NoHomeRecordFoundException;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;

public interface HomeDAO {
	Home addHome(Home home) throws SomethingWentWrongException;

	Home updateHome(Home home) throws NoRecordFoundException, SomethingWentWrongException, NoHomeRecordFoundException;

	void deleteHome(Long homeId) throws NoRecordFoundException, SomethingWentWrongException;

	List<Home> getAllHomes() throws SomethingWentWrongException;

	Home getHomeById(Long homeId) throws NoRecordFoundException;

	List<Home> searchHomesByArea(String area) throws SomethingWentWrongException;

	List<Home> searchHomesByType(String type) throws NoRecordFoundException;

	List<Home> searchHomesByPrice(double start, double end) throws SomethingWentWrongException;

}
