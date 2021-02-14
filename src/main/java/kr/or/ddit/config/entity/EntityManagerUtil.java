package kr.or.ddit.config.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	
	private static EntityManagerFactory entityManagerFactory;
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("hibernate");
	}
	
	public static EntityManager getEm() {
		return entityManagerFactory.createEntityManager();
	}
}
