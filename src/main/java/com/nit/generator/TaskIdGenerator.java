package com.nit.generator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class TaskIdGenerator implements IdentifierGenerator {

	private static final long serialVersionUID = 1L;

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		
		String prefix = "todo", suffix = "";
		
		try {
			Connection con = session.getJdbcConnectionAccess().obtainConnection();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT TASK_ID_SEQ.NEXTVAL FROM DUAL");
			
			if(rs.next()) {
				int id = rs.getInt(1);
				suffix = String.valueOf(id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prefix + suffix;
	}
}
