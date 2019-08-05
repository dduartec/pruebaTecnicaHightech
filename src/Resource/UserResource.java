package Resource;

import Service.AuthService;
import Service.CreditCardService;
import Service.UserService;
import Model.CreditCard;
import Model.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/user")
public class UserResource {
	private ResponseBuilder response;
	private Gson builder= new Gson();

	@Context
	private UriInfo uriInfo;

	@EJB
	private UserService userService = new UserService();

	@EJB
	private AuthService authService =  new AuthService();
	
	@EJB
	private CreditCardService creditCardService =  new CreditCardService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllStudents() {
		if(userService.getDbCon()==null) {
			return "ERROR BASE DE DATOS";
		}
	    List<User> users = new ArrayList<>();
		try {
			users = userService.getAllUsers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String json = builder.toJson(e.getMessage());
		    return json;
		}        
	    System.out.println(users.toString());
	    // Convert to GenericEntity and return in response    
	    GenericEntity<List<User>> entities = new GenericEntity<List<User>>(users){};
	    //return users.toString();
	    String json = builder.toJson(users);
	    return json;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {	
		if(userService.getDbCon()==null) {
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity("ERROR BASE DE DATOS");
			return  response.build();
		}
		String token = authService.createUserToken(user);
		user.setAuthToken(token);
		User createdUser;
		try {
			createdUser = userService.createUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity(e.getMessage());
			return  response.build();			
		}		
		response = Response.status(Response.Status.CREATED);
		response.entity(createdUser);
		return  response.build();
	}
	
	@GET
	@Path("{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByUsername(@PathParam("username") String username,@HeaderParam("authToken") String authToken) {	
		if(userService.getDbCon()==null) {
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity("ERROR BASE DE DATOS");
			return  response.build();
		}
		if(!authService.verifyToken(authToken)) {
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity("TOKEN INVALIDO");
			return  response.build(); 
		}
		User user = null;
		try {
			user = userService.getUserByUsername(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity(e.getMessage());
			return  response.build();			
		}		
		response = Response.status(Response.Status.CREATED);
		response.entity(user);
		return  response.build();
	}
	
	@PUT
	@Path("{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("username") String username,@HeaderParam("authToken") String authToken,User user) {	
		if(userService.getDbCon()==null) {
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity("ERROR BASE DE DATOS");
			return  response.build();
		}
		if(!authService.verifyUserToken(authToken,username)) {
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity("TOKEN INVALIDO");
			return  response.build(); 
		}
		User updatedUser;
		try {
			updatedUser = userService.updateUser(username, user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity(e.getMessage());
			return  response.build();			
		}		
		response = Response.status(Response.Status.OK);
		response.entity(updatedUser);
		return  response.build();
	}
	
	@DELETE
	@Path("{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("username") String username,@HeaderParam("authToken") String authToken) {	
		if(userService.getDbCon()==null) {
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity("ERROR BASE DE DATOS");
			return  response.build();
		}
		if(!authService.verifyUserToken(authToken,username)) {
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity("TOKEN INVALIDO");
			return  response.build(); 
		}
		String deletedUser;
		try {
			deletedUser = userService.deleteUser(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity(e.getMessage());
			return  response.build();			
		}		
		response = Response.status(Response.Status.OK);
		response.entity(deletedUser);
		return  response.build();
	}
	
	@GET
	@Path("{username}/creditCard")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserCreditCards(@PathParam("username") String username,@HeaderParam("authToken") String authToken) {
		if(userService.getDbCon()==null) {
			return "ERROR BASE DE DATOS";
		}
	    List<CreditCard> creditCards = new ArrayList<>();
		try {
			creditCards = creditCardService.getUserCreditCards(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String json = builder.toJson(e.getMessage());
		    return json;
		}        
	    String json = builder.toJson(creditCards);
	    return json;
	}

	@POST
	@Path("{username}/creditCard")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCreditCard(@PathParam("username") String username,@HeaderParam("authToken") String authToken,CreditCard creditCard) {	
		if(userService.getDbCon()==null) {
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity("ERROR BASE DE DATOS");
			return  response.build();
		}
		if(!authService.verifyUserToken(authToken,username)) {
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity("TOKEN INVALIDO");
			return  response.build(); 
		}
		CreditCard createdCreditCard;
		try {
			createdCreditCard = creditCardService.createCreditCard(username, creditCard);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity(e.getMessage());
			return  response.build();			
		}		
		response = Response.status(Response.Status.CREATED);
		response.entity(createdCreditCard);
		return  response.build();
	}
	
	@GET
	@Path("{username}/creditCard/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCreditCard(@PathParam("id") int id,@PathParam("username") String username,@HeaderParam("authToken") String authToken) {	
		if(userService.getDbCon()==null) {
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity("ERROR BASE DE DATOS");
			return  response.build();
		}
		if(!authService.verifyUserToken(authToken,username)) {
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity("TOKEN INVALIDO");
			return  response.build(); 
		}
		CreditCard creditCard = null;
		try {
			creditCard = creditCardService.getCreditCardById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity(e.getMessage());
			return  response.build();			
		}		
		response = Response.status(Response.Status.CREATED);
		response.entity(creditCard);
		return  response.build();
	}
	
	@PUT
	@Path("{username}/creditCard/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCreditCard(@PathParam("id") int id,@PathParam("username") String username,@HeaderParam("authToken") String authToken,CreditCard creditCard) {	
		if(userService.getDbCon()==null) {
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity("ERROR BASE DE DATOS");
			return  response.build();
		}
		if(!authService.verifyUserToken(authToken,username)) {
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity("TOKEN INVALIDO");
			return  response.build(); 
		}
		CreditCard updatedCreditCard;
		try {
			updatedCreditCard = creditCardService.updateCreditCard(id, creditCard);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity(e.getMessage());
			return  response.build();			
		}		
		response = Response.status(Response.Status.OK);
		response.entity(updatedCreditCard);
		return  response.build();
	}
	
	@DELETE
	@Path("{username}/creditCard/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCreditCard(@PathParam("id") int id,@PathParam("username") String username,@HeaderParam("authToken") String authToken) {	
		if(userService.getDbCon()==null) {
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity("ERROR BASE DE DATOS");
			return  response.build();
		}
		if(!authService.verifyUserToken(authToken,username)) {
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity("TOKEN INVALIDO");
			return  response.build(); 
		}
		int deletedCreditCard;
		try {
			deletedCreditCard = creditCardService.deleteCreditCard(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR);
			response.entity(e.getMessage());
			return  response.build();			
		}		
		response = Response.status(Response.Status.OK);
		response.entity(deletedCreditCard);
		return  response.build();
	}
}
