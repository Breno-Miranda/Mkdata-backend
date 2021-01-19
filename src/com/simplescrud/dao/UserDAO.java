package com.simplescrud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.simplescrud.config.BDConfig;
import com.simplescrud.entidade.Phone;
import com.simplescrud.entidade.User;

public class UserDAO {
	
	public List<User> getAllUSers() throws Exception {
		
		List<User> users = new ArrayList<>();
		
		Connection conn  = BDConfig.getConnection();
		
		String query = "SELECT * FROM tbusers order by id desc";
		
		PreparedStatement stat =  conn.prepareStatement(query);
		ResultSet res = stat.executeQuery();
		
		while (res.next()) {
			User user = new User();
			user.setId(res.getInt("id"));
			user.setName(res.getString("name"));
			user.setCpfcnpj(res.getString("cpfcnpj"));
			user.setType(res.getString("type"));
			user.setRgie(res.getString("rgie"));
			user.setCreatedAt(res.getDate("created_at"));
				
			users.add(user);
		}
		
		return users;
	}
	

	
	public List<User> getFilterId(int id ) throws Exception {
		
		List<User> users = new ArrayList<>();
		
		Connection conn  = BDConfig.getConnection();
		
		String query = "SELECT * FROM tbusers where id = ? ";
		
		PreparedStatement stat =  conn.prepareStatement(query);
		
		stat.setInt(1 , id);
		
		System.out.println(stat.toString());
		
		ResultSet res = stat.executeQuery();
		
		while (res.next()) {
			User user = new User();
			user.setId(res.getInt("id"));
			user.setName(res.getString("name"));
			user.setCpfcnpj(res.getString("cpfcnpj"));
			user.setType(res.getString("type"));
			user.setRgie(res.getString("rgie"));
			user.setIsactive(res.getBoolean("isactive"));
			user.setCreatedAt(res.getDate("created_at"));
			
			users.add(user);
		}
		
		return users;
	}
	
	
	public  List<User> getFilterPeriod(String start ,String end, int limit) throws Exception {
		
		List<User> users = new ArrayList<>();
		
		Connection conn  = BDConfig.getConnection();
		
		String query = "SELECT * FROM tbusers WHERE created_at BETWEEN ? AND ? limit ?";
		
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1 , start);
		stat.setString(2 , end);
		stat.setInt(3 , limit);
		
		
		System.out.println(stat.toString());
		
		ResultSet res = stat.executeQuery();
		
		while (res.next()) {
			User user = new User();
			user.setId(res.getInt("id"));
			user.setName(res.getString("name"));
			user.setCpfcnpj(res.getString("cpfcnpj"));
			user.setType(res.getString("type"));
			user.setRgie(res.getString("rgie"));
			user.setIsactive(res.getBoolean("isactive"));
			user.setCreatedAt(res.getDate("created_at"));
			
			users.add(user);
		}
		
		return users;
	}
	
	
	
	public  List<User> getFilterName(String name , int limit) throws Exception {
		
		List<User> users = new ArrayList<>();
		
		Connection conn  = BDConfig.getConnection();
		
		String query = "SELECT * FROM tbusers WHERE  name like ? limit ?";
		
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1 , '%' + name + '%' );
		stat.setInt(2 , limit);
		
		
		System.out.println(stat.toString());
		
		ResultSet res = stat.executeQuery();
		
		while (res.next()) {
			User user = new User();
			user.setId(res.getInt("id"));
			user.setName(res.getString("name"));
			user.setCpfcnpj(res.getString("cpfcnpj"));
			user.setType(res.getString("type"));
			user.setRgie(res.getString("rgie"));
			user.setIsactive(res.getBoolean("isactive"));
			user.setCreatedAt(res.getDate("created_at"));
			
			users.add(user);
		}
		
		
		return users;
	}
	
	
	
	public boolean addingUser (User user) throws Exception {
		
		Connection conn  = BDConfig.getConnection();
		
		PreparedStatement statIsvarify =  conn.prepareStatement("SELECT * FROM tbusers where cpfcnpj = ? limit 1");    
		statIsvarify.setString(1, user.getCpfcnpj());
		
		ResultSet getrs = statIsvarify.executeQuery();
		
		if(getrs.next()) {
			System.out.println("usuario já existe");
			
			return false;
			
		} else {
			
			System.out.println("usuario não existe");
			
			String query = "INSERT INTO tbusers (name, cpfcnpj, type, rgie, isactive ) VALUES (? , ? , ? , ? , ?)";
			
			PreparedStatement stat =  conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stat.setString(1 , user.getName());
			stat.setString(2 , user.getCpfcnpj());
			stat.setString(3 , user.getType());
			stat.setString(4 , user.getRgie());
			stat.setBoolean(5, user.getIsactive());
			stat.execute();
			
			ResultSet res = stat.getGeneratedKeys();
			
			if (res.next()) {
				
			    int id = res.getInt(1);
			    
			    System.out.println("Inserted ID -" + id); 
			    
			    String queryPhone = "INSERT INTO tbphoneusers (userId, areacode, phone,  ismain) VALUES (? , ? , ?, ?)";
				
				PreparedStatement statPhone =  conn.prepareStatement(queryPhone);
				
				 Iterator<Phone> phonesAsIterator = user.getPhones().iterator();
		         while (phonesAsIterator.hasNext()){
		        	 	Phone it = phonesAsIterator.next();
						statPhone.setInt(1, id);
						statPhone.setString(2, it.getAreacode());
						statPhone.setString(3, it.getPhone());
						statPhone.setBoolean(4, it.getIsmain());
						statPhone.addBatch();
						
		        	 	System.out.println("Areacode -" + it.getAreacode()); 
		        	 	
		         }
		         
		         statPhone.executeBatch();
			}
			
			return true;
		}
		

	}
	
	public void updateUser (User user, int id) throws Exception {
		
		Connection conn  = BDConfig.getConnection();
		

		String query = "UPDATE tbusers set name = ?, cpfcnpj = ?, type = ?, rgie = ?, isactive = ? WHERE id = ?";
		
		PreparedStatement stat =  conn.prepareStatement(query);
		stat.setString(1 , user.getName());
		stat.setString(2 , user.getCpfcnpj());
		stat.setString(3 , user.getType());
		stat.setString(4 , user.getRgie());
		stat.setBoolean(5, user.getIsactive());
		stat.setInt(6, id);
		stat.execute();
		
		System.out.println(stat.toString());
		
	    String queryPhone = "UPDATE tbphoneusers set areacode = ?, phone = ?, ismain = ? WHERE id = ?";
		
		PreparedStatement statPhone =  conn.prepareStatement(queryPhone);
		
		 Iterator<Phone> phonesAsIterator = user.getPhones().iterator();
         while (phonesAsIterator.hasNext()){
        	 	Phone it = phonesAsIterator.next();
				statPhone.setString(1, it.getAreacode());
				statPhone.setString(2, it.getPhone());
				statPhone.setBoolean(3, it.getIsmain());
				statPhone.setInt(4, it.getId());
				statPhone.addBatch();
				
        	 	System.out.println("Areacode -" + it.getAreacode()); 
        	 	
         }
         
         statPhone.executeBatch();
			
	}
	
	public void deleteUser (int id) throws Exception {
		
		Connection conn  = BDConfig.getConnection();
		
		String queryPhone = "DELETE FROM tbphoneusers WHERE userId = ?";
		
		PreparedStatement statPhone =  conn.prepareStatement(queryPhone);
		statPhone.setInt(1,  id);
		statPhone.execute();
		
		String queryUser = "DELETE FROM tbusers WHERE id = ?";
		
		PreparedStatement statUser =  conn.prepareStatement(queryUser);
		statUser.setInt(1,  id);
		statUser.execute();
		
	}

}
