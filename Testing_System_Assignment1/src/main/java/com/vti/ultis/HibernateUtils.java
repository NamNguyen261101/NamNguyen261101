//
package com.vti.ultis;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.github.fluent.hibernate.cfg.scanner.EntityScanner;
import com.vti.entity.Department;
import com.vti.entity.Group;






/**
 * This class is . 
 * 
 * @Description: .
 * @author: NKNam
 * @create_date: Jun 14, 2022
 * @version: 1.0
 * @modifer: NKNam
 * @modifer_date: Jun 14, 2022
 */
public class HibernateUtils {
	// Design single pattern
	private static HibernateUtils hibernateUtils;

	private Configuration configuration;
	private SessionFactory sessionFactory;

	public static HibernateUtils getInstance() {
		if (null == hibernateUtils) {
			hibernateUtils = new HibernateUtils();
		}
		return hibernateUtils;
	}

	private HibernateUtils() {
		configure();
	}

	private void configure() {
		// load configuration
		configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		// add entity
		configuration.addAnnotatedClass(Department.class); // scan toàn bộ project entity nào thì sẽ add vào đây
		configuration.addAnnotatedClass(Group.class);
		
		 // EntityScanner.scanPackages("com.vti.entity").addTo(configuration);
	}

	private SessionFactory buildSessionFactory() {
		if (null == sessionFactory || sessionFactory.isClosed()) {
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}

		return sessionFactory;
	}

	public void closeFactory() {
		if (null != sessionFactory && sessionFactory.isOpen()) {
			sessionFactory.close();
		}
	}

	public Session openSession() {
		buildSessionFactory();
		return sessionFactory.openSession();
	}
	
	
	
	
	
}
