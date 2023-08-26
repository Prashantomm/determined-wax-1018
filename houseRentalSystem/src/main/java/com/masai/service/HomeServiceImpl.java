package com.masai.service;

import java.util.List;

import com.masai.dao.HomeDAO;
import com.masai.dao.HomeDAOImpl;
import com.masai.entity.Home;
import com.masai.exceptions.NoHomeRecordFoundException;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;

public class HomeServiceImpl implements HomeService {

	@Override
	public Home addHome(Home home) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		HomeDAO hd = new HomeDAOImpl();
		return hd.addHome(home);
		
	}

	@Override
	public Home updateHome(Home home)
			throws NoRecordFoundException, SomethingWentWrongException, NoHomeRecordFoundException {
		HomeDAO hd = new HomeDAOImpl();
		return hd.updateHome(home);
	}

	@Override
	public void deleteHome(Long homeId) throws NoRecordFoundException, SomethingWentWrongException {
		// TODO Auto-generated method stub
		HomeDAO hd = new HomeDAOImpl();
		 hd.deleteHome(homeId);

	}

	@Override
	public List<Home> getAllHomes() throws SomethingWentWrongException {
		HomeDAO hd = new HomeDAOImpl();
		return hd.getAllHomes();
	}

	@Override
	public Home getHomeById(Long homeId) throws NoRecordFoundException {
		HomeDAO hd = new HomeDAOImpl();
		return hd.getHomeById(homeId);
	}

	@Override
	public List<Home> searchHomesByArea(String area) throws SomethingWentWrongException {
		HomeDAO hd = new HomeDAOImpl();
		return hd.searchHomesByArea(area);
	}

	@Override
	public List<Home> searchHomesByType(String type) throws NoRecordFoundException {
		HomeDAO hd = new HomeDAOImpl();
		return hd.searchHomesByType(type);
	}

	@Override
	public List<Home> searchHomesByPrice(double start, double end) throws SomethingWentWrongException {
		HomeDAO hd = new HomeDAOImpl();
		return hd.searchHomesByPrice(start, end);
	}

}
