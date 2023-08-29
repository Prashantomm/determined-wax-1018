package com.masai.dao;

import java.util.List;

import com.masai.Utility.Util;
import com.masai.entity.Home;
import com.masai.exceptions.NoHomeRecordFoundException;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class HomeDAOImpl implements HomeDAO{
	@Override
	public Home updateHome(Home hm) throws NoRecordFoundException, SomethingWentWrongException, NoHomeRecordFoundException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			Home home = em.find(Home.class, hm.getHomeId());
			et.begin();
			if (home != null) {
				em.merge(hm);
				et.commit();
				return home;
			} else {
				throw new NoRecordFoundException("No home found with the given ID : " + hm.getHomeId());
			}

		} catch (Exception e) {
			et.rollback();
			throw new NoHomeRecordFoundException("Home is not available at the moment. Try again later.");
		} finally {
			em.close();
		}
	}

	@Override
	public void deleteHome(Long homeId) throws NoRecordFoundException, SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			Home home = em.find(Home.class, homeId);
			et.begin();
			if (home != null) {
				em.remove(home);
				et.commit();
			} else {
				throw new NoRecordFoundException("No record found with the given ID : " + homeId);
			}

		} catch (Exception e) {
			et.rollback();
			// throw new NoRecordFoundException("Something went wrong while adding Details.
			// Try again later.");
			e.printStackTrace();
		} finally {
			em.close();
		}


	}

	@Override
	public Home addHome(Home home) throws SomethingWentWrongException {
				EntityTransaction et = null;

		try(EntityManager em=Util.getEm()) {
			

			
			et = em.getTransaction();

			et.begin();
			em.persist(home);
			et.commit();
			return home;
		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong while adding Details. Try again later.");
		} 
	}
	

	public List<Home> getAllHomes() throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			Query query = em.createQuery("SELECT h FROM Home h");
			et.begin();
			List<Home> homelist = (List<Home>) query.getResultList();
			if (homelist != null) {
				et.commit();
				return homelist;
			} else {
				throw new SomethingWentWrongException("Home List is empty.");
			}

		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while fetching home Details. Try again later.");
		} finally {
			em.close();
		}
	}

	@Override
	public Home getHomeById(Long homeId) throws NoRecordFoundException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			Home home = em.find(Home.class, homeId);
			et.begin();
			if (home != null) {
				et.commit();
				return home;
			} else {
				throw new NoRecordFoundException("No record found with the given ID : " + home.getHomeId());
			}

		} catch (Exception e) {
			et.rollback();
			throw new NoRecordFoundException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
	}

	@Override
	public List<Home> searchHomesByArea(String area) throws SomethingWentWrongException {
		EntityManager em = null;
		try {
			em = Util.getEm();
			Query query = em.createQuery("SELECT h FROM Home h WHERE h.area = :area");
			query.setParameter("area", area);
			return query.getResultList();
		} catch (Exception e) {
			throw new SomethingWentWrongException("Something went wrong while fetching home details. Try again later.");
		} finally {
			em.close();

		}
	}

	@Override
	public List<Home> searchHomesByType(String type) throws NoRecordFoundException {
		EntityManager em = null;
		try {
			em = Util.getEm();
			Query query = em.createQuery("SELECT h FROM Home h WHERE h.type = :type");
			query.setParameter("type", type);
			List<Home> homes = query.getResultList();
			if (homes.isEmpty()) {
				throw new NoRecordFoundException("No homes found with the given model: " + type);
			}
			return homes;
		} catch (NoRecordFoundException e) {
			throw new NoRecordFoundException("No homes found with the given model: " + type);
		} finally {
			em.close();

		}
	}

	@Override
	public List<Home> searchHomesByPrice(double start, double end) throws SomethingWentWrongException {
		EntityManager em = null;
		try {
			em = Util.getEm();
			Query query = em.createQuery("SELECT h FROM Home h WHERE h.price BETWEEN :start AND :end");
			query.setParameter("start", start);
			query.setParameter("end", end);
			return query.getResultList();
		} catch (Exception e) {
			throw new SomethingWentWrongException("Something went wrong while fetching home details. Try again later.");
		} finally {
			em.close();
		}
	}

}
