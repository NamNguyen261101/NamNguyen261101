//
package com.vti.frontend;

import java.util.List;

import com.vti.backend.DepartmentRepository;
import com.vti.backend.GroupRepository;
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
public class Program {
	
	public static void main(String[] args) {
		DepartmentRepository repository = new DepartmentRepository();
		
		System.out.println("==========================GET ALL DEPARTMENTS==============================");

		List<Department> departments = repository.getAllDepartments();

		for (Department department : departments) {
			System.out.println(department);
		}
//		
//
//		System.out.println("==========================GET DEPARTMENT BY ID==========================");
//
//		Department departmentById = repository.getDepartmentByID((short) 2);
//		System.out.println(departmentById);
//
//		System.out.println("\n\n==========================GET DEPARTMENT BY NAME==========================");
//
//		Department departmentByName = repository.getDepartmentByName("Marketting");
//		System.out.println(departmentByName);
//
//		System.out.println("\n\n==========================CREATE DEPARTMENT==========================");
//
//		Department departmentCreate = new Department();
//		departmentCreate.setName("waiting");
//		repository.createDepartment(departmentCreate);
//
//		System.out.println("\n\n==========================UPDATE DEPARTMENT 1==========================");
//
//		repository.updateDepartment((short) 3, "Security");
//
//		System.out.println("\n\n==========================UPDATE DEPARTMENT 2*==========================");
//
//		Department departmentUpdate = new Department();
//		departmentUpdate.setId((short) 2);
//		departmentUpdate.setName("Security2");
//		repository.updateDepartment(departmentUpdate);
//
//		System.out.println("\n\n==========================DELETE DEPARTMENTS==========================");
//		repository.deleteDepartment((short) 2);
//
//		System.out.println("==========================CHECK DEPARTMENT EXISTS BY ID==========================");
//		System.out.println(repository.isDepartmentExistsByID((short) 1));
//
//		System.out.println("==========================CHECK DEPARTMENT EXISTS BY NAME==========================");
//		System.out.println(repository.isDepartmentExistsByName("Security"));
		
		
		//================================================Group======================================================//
		GroupRepository groupRepository = new GroupRepository();

		System.out.println("==========================GET ALL Groups ==============================");

		List<Group> groups = groupRepository.getAllGroups();

		for (Group listGroup : groups) {
			System.out.println(listGroup);
		}
		

		System.out.println("\n\n==========================GET Group BY ID==========================");

		Group groupById = groupRepository.getGroupByID((short) 2);
		System.out.println(groupById);

		System.out.println("\n\n==========================GET Group BY NAME==========================");

		Group groupByName = groupRepository.getGroupByName("Java Web");
		System.out.println(groupByName);
		
		System.out.println("\n\n==========================UPDATE Group 1==========================");
		
		groupRepository.updateGroup((short) 1, "Nhóm Java");
		
		System.out.println("\n\n==========================UPDATE Group 2*==========================");
		
				Group groupUpdate = new Group();
				groupUpdate.setId((short) 1);
				groupUpdate.setName("Nhóm Java 2");
				groupRepository.updateGroup(groupUpdate);
				
				
		
	}
}
