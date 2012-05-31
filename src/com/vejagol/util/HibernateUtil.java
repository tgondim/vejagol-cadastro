package com.vejagol.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			String dialect = "org.hibernate.dialect.MySQLDialect";
			String driverClass = "com.mysql.jdbc.Driver";
			
			//vejagol.com
			String url = "jdbc:mysql://vejagol.com/tgondim_vejagoldb?autoCommit=true&autoReconnect=true";
			String username = "tgondim_vejagol";
			String password = "VeJ@Go1";

			//localhost
//			String url = "jdbc:mysql://localhost/vejagoldb";
//			String username = "vejagol";
//			String password = "VeJ@Go1";
			
			//dyndns
//			String url = "jdbc:mysql://tgondim.dyndns.info/vejagoldb";
//			String username = "vejagol";
//			String password = "VeJ@Go1";

			// Cria a SessionFactory do hibernate.cfg.xml
			sessionFactory = new Configuration().configure()
			.setProperty("hibernate.dialect", dialect)
			.setProperty("hibernate.connection.driver_class", driverClass)
			.setProperty("hibernate.connection.url", url)
			.setProperty("hibernate.connection.username", username)
			.setProperty("hibernate.connection.password", password)
			.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Criação da SessionFactory inicial falhou." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}