//
package com.vti.frontend;

import java.util.List;

import com.vti.backend.excercise2.DepartmentRepositoryByUltis;
import com.vti.entity.Department;

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
public class Program2 {
	public static void main(String[] args) {
		DepartmentRepositoryByUltis departmentRepositoryByUltis = new DepartmentRepositoryByUltis();
		
		System.out.println("==========================GET ALL DEPARTMENTS==============================");

		List<Department> listDepartment = departmentRepositoryByUltis.getAllDepartments();

		for (Department department : listDepartment) {
			System.out.println(department);
		}
	}
	
}
