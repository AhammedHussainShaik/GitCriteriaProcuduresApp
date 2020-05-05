package com.cprocedure.controllers;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import com.cprocedure.utils.HibernateUtil;

public class ParamProcedureINOUT_Test {

	public static void main(String[] args) {
		Session session=null;
		StoredProcedureQuery procedureQuery=null;
		
		try {
			session=HibernateUtil.getSessionFactory().openSession();
			// 1.create stored Procedure Query
			procedureQuery=session.createStoredProcedureQuery("getEmployeeCountByDpt");
			//2. set IN/OUT params values
			procedureQuery.registerStoredProcedureParameter("empDept", String.class, ParameterMode.IN);
			procedureQuery.registerStoredProcedureParameter("deptCcount", Integer.class, ParameterMode.OUT);
			procedureQuery.setParameter("empDept", "DEV");
			
			procedureQuery.execute();
			
			// 3.execute Query
			Integer count=(Integer)procedureQuery.getOutputParameterValue("deptCcount");
			
			System.out.println("The Count Of The Department : "+count);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}//finally

	}//main

}//class
