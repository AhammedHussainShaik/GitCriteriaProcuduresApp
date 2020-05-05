package com.cprocedure.controllers;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;

import com.cprocedure.models.Employee;
import com.cprocedure.utils.HibernateUtil;

public class ParamProcedureTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session=null;
		StoredProcedureQuery procedureQuery=null;
		try {
			session=HibernateUtil.getSessionFactory().openSession();
			// 1.Create StoredProcedure Object
			procedureQuery=session.createStoredProcedureQuery("getEmployeeByDept", Employee.class);
			//2 set IN/OUT params values
			procedureQuery.registerStoredProcedureParameter("empdept", String.class, ParameterMode.IN);
			procedureQuery.setParameter("empdept", "DEV");
			// 3 execute Query
			List<Employee> employees=procedureQuery.getResultList();
			
			//4 .print Result
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
