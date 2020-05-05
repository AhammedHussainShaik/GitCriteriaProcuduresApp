package com.cprocedure.controllers;

import java.util.List;

import javax.persistence.StoredProcedureQuery;
import org.hibernate.Session;
import com.cprocedure.models.Employee;
import com.cprocedure.utils.HibernateUtil;

public class CriteriaProcudureTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session=null;
		StoredProcedureQuery procedureQuery=null;
		
		try {
			session=HibernateUtil.getSessionFactory().openSession();
			//1.Create StoredProcedureQuery
			procedureQuery=session.createStoredProcedureQuery("getAllEmployees",Employee.class);
			//2. Specify IN/OUT Params --NOW NOTHING
	         //3. execute and get Result as List
			List<Employee> employees=procedureQuery.getResultList();
			
			for(Employee employee:employees) {
				System.out.println(employee);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}

	}

}
