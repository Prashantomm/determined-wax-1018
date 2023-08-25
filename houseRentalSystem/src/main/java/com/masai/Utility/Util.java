package com.masai.Utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Util {
	static EntityManagerFactory emf = null;

	static {
		emf = Persistence.createEntityManagerFactory("houseRentingSystem");
	}

	public static EntityManager getEm() {
		return emf.createEntityManager();
	}

}
