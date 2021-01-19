package com.simplescrud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.simplescrud.config.BDConfig;
import com.simplescrud.entidade.Phone;

public class PhoneDAO {

	public List<Phone> getFilterPhone(int id , int limit) throws Exception {
		
		List<Phone> phones = new ArrayList<>();
		
		Connection conn  = BDConfig.getConnection();
		
		String query = "SELECT * FROM tbphoneusers where userId = ? limit ?";
		
		PreparedStatement stat =  conn.prepareStatement(query);
		stat.setInt(1 , id);
		stat.setInt(2 , limit);
		ResultSet res = stat.executeQuery();
		
		while (res.next()) {
			Phone phone = new Phone();
			phone.setId(res.getInt("id"));
			phone.setAreacode(res.getString("areacode"));
			phone.setPhone(res.getString("phone"));			
			phone.setIsmain(res.getBoolean("ismain"));	
			
			phones.add(phone);
		}
		
		return phones;
	}
	
	public void addingPhone (Phone phone, int id) throws Exception {
		
		Connection conn  = BDConfig.getConnection();
		
	    String queryPhone = "INSERT INTO tbphoneusers (userId, areacode, phone,  ismain) VALUES (? , ? , ?, ?)";
		
		PreparedStatement stat =  conn.prepareStatement(queryPhone);
		
		stat.setInt(1, id);
		stat.setString(2, phone.getAreacode());
		stat.setString(3, phone.getPhone());
		stat.setBoolean(4, phone.getIsmain());
				
		System.out.println("Areacode -" + phone.getAreacode()); 
	    	 	
	   stat.execute();
	}

}
