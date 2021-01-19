package com.simplescrud.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.simplescrud.dao.PhoneDAO;
import com.simplescrud.dao.UserDAO;
import com.simplescrud.entidade.Phone;
import com.simplescrud.entidade.User;


@Path("/users")
public class UserService {
	
	private UserDAO userDAO;
	private PhoneDAO phoneDAO;
	
	@PostConstruct
	private void init() {
		userDAO = new UserDAO();
		phoneDAO = new PhoneDAO();
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUSers() throws Exception{
		
		List<User> users = null;
		
		try {
			users = userDAO.getAllUSers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	@GET
	@Path("/filter/id")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getFilterId(@QueryParam("id") int id) throws Exception{
		
		List<User> users = null;
		
		try {
			users = userDAO.getFilterId( id );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}

	@GET
	@Path("/filter/name")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getFilterName(@QueryParam("name") String name, @QueryParam("limit") int limit) throws Exception{
		
		List<User> users = null;
		
		try {
			users = userDAO.getFilterName(name, limit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	
	@GET
	@Path("/filter/period")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getFilterPeriod(@QueryParam("start") String start, @QueryParam("end") String end , @QueryParam("limit") int limit) throws Exception{
		
		List<User> users = null;
		
		try {
			users = userDAO.getFilterPeriod(start, end , limit );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	
	@GET
	@Path("/filter/phone")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Phone> getFilterPhone(@QueryParam("id") int id, @QueryParam("limit") int limit) throws Exception{
		
		List<Phone> phones = null;
		
		try {
			phones = phoneDAO.getFilterPhone(id, limit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return phones;
	}
	
	@POST
	@Path("/phone/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUserPhone(Phone phone, @PathParam("id") int id) throws Exception {
		
		String message = "";
	
		try {
			message =  "{\"message\":\"Telefone do Usuário cadastrado com sucesso.\"}";
			phoneDAO.addingPhone(phone, id);
		} catch (Exception e) {
			message =  "{\"message\":\"Error ao cadastrar novo Telefone para usuário.\"}";
			e.printStackTrace();
		}
		
		return Response.status(200).entity(message).build();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User user) throws Exception {
		
		String message = "";
	
		try {
			message =  "{\"message\":\"Usuário cadastrado com sucesso.\"}";
			userDAO.addingUser(user);
		} catch (Exception e) {
			message =  "{\"message\":\"Error ao cadastrar novo usuário.\"}";
			e.printStackTrace();
		}
		
		return Response.status(200).entity(message).build();
	}
	
	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUsers(User user, @PathParam("id") int id) throws Exception {
		
		String message = "";
	
		try {
			message =  "{\"message\":\"Usuário atualizado com sucesso.\"}";
			userDAO.updateUser(user, id);
		} catch (Exception e) {
			message =  "{\"message\":\"Error ao atualizar usuário.\"}";
			e.printStackTrace();
		}

		return message;
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser(@PathParam("id")int id) throws Exception{
		
		String message = "";
		
		try {
			userDAO.deleteUser(id);
			
			message =  "{\"message\":\"Usuário deletado com sucesso.\"}";
			
		} catch (Exception e) {
			
			message =  "{\"message\":\"Error ao deletar usuário.\"}";
			e.printStackTrace();
		}
		return message;
	}
}
