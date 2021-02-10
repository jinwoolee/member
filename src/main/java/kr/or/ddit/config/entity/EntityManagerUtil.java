package kr.or.ddit.config.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	
	private static EntityManager em;
	
	static {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hibernate");
		em = entityManagerFactory.createEntityManager();
	}
	
	public static EntityManager getEm() {
		return em;
	}
}
