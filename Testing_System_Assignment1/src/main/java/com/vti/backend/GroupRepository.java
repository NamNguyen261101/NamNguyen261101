//
package com.vti.backend;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

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
public class GroupRepository {
	
	private SessionFactory sessionFactory;
	
	public GroupRepository() {
		sessionFactory = buildSessionFactory();
	}
	
	private SessionFactory buildSessionFactory() {
		// load configuration
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		// add entity
		configuration.addAnnotatedClass(Department.class);
		configuration.addAnnotatedClass(Group.class); // nếu có nhiều entity thì add thêm ở đây 
					// configuration.addAnnotatedClass(Department.class);
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		return configuration.buildSessionFactory(serviceRegistry);
	}
	
	// Get List (All) Group
	@SuppressWarnings("unchecked")
	public List<Group> getAllGroups() {

		Session session = null;

		try {

			// get session
			session = sessionFactory.openSession();

			// create hql query
			Query<Group> query = session.createQuery("FROM Group"); // Tự tạo ra question của mình (View List)
			// FROM ở đây như select dựa trên object và dựa trên đó khởi tạo nên

			return query.list(); // Lấy ra câu query
			// Transaction chỉ cần khi thực hiện với nhiều câu lệnh 

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	// Get Group by ID
	public Group getGroupByID(short id) {

		Session session = null;

		try {

			// get session
			session = sessionFactory.openSession();

			// get department by id
			Group group = session.get(Group.class, id); // Tìm group ID -- nếu có thì trả ra k thì trả ra null

			// Group group = session.load(Group.class, id) // cách làm việc khác với get 
					// với load nếu tìm tới k có kết quả thì nó sẽ trả ra not found exception
						// với load nếu tìm đc ra thì nó sẽ trả ra Reference (trả ra 1 con trỏ chỉ trỏ tới 1 con trỏ trong sql khi mà đóng session thì bị lỗi)
				// load chỉ xài khi xử dụng xử lý trong hàm nếu ngoài hàm thì sẽ k hoạt động
			
			// còn với get thì nếu k tìm thấy thì sẽ trả ra null
				// Get nếu thấy thì sẽ trả ra object như bình thường
			return group;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	// Get group by name
	@SuppressWarnings("unchecked")
	public Group getGroupByName(String name) {

		Session session = null;

		try {

			// get session
			session = sessionFactory.openSession();

			// create hql query
			Query<Group> query = session.createQuery("FROM Group WHERE name = :nameParameter");

			// set parameter
			query.setParameter("nameParameter", name);

			// get result
			Group group = query.uniqueResult();	// do name cấu hình ở entity là unique thì nó chỉ cần 1
							// nếu có nhiều thì query.list();
			return group;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	// Create Group
	public void createGroup(Group group) {

		Session session = null;

		try {

			// get session
			session = sessionFactory.openSession();
			session.beginTransaction();

			// create
			session.save(group);	// k có id thành save create xuống data

			session.getTransaction().commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	// Update Group (cách update 1 theo như jdbc có id và name) (nhanh hơn khi chỉ cần lấy r + setName)
	public void updateGroup(short id, String newName) {

		Session session = null;

		try {

			// get session
			session = sessionFactory.openSession();
			session.beginTransaction();

			// get department
			Group group = (Group) session.load(Group.class, id); // nếu sử dụng get thì chỉ clone trong java

			// update
			group.setName(newName); // bất kỳ thay đổi nào sẽ ăn thẳng vào trogn sql

			session.getTransaction().commit();

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	// Update theo cách 2 (k cần ghi trên nhưng phải set tham số
	public void updateGroup(Group group) {

		Session session = null;

		try {

			// get session
			session = sessionFactory.openSession();
			session.beginTransaction();

			// update
			session.update(group);

			session.getTransaction().commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	// Delete 
	public void deleteGroup(short id) {

		Session session = null;

		try {

			// get session
			session = sessionFactory.openSession();
			session.beginTransaction();

			// get department
			Group group = (Group) session.load(Group.class, id);

			// delete
			session.delete(group);

			session.getTransaction().commit();

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	// Check Group Is Exist( tồn tại hoặc k) theo iD
	public boolean isGroupExistsByID(short id) {

		// get department
		Group group = getGroupByID(id);

		// return result
		if (group == null) {
			return false;
		}

		return true;
	}
	
	// Check Group Is Exist( tồn tại hoặc k) theo name
	public boolean isGroupExistsByName(String name) {
		Group group = getGroupByName(name);

		if (group == null) {
			return false;
		}
		return true;
	}
	
	

	
}
