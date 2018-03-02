package restful.resources;

import java.util.List;

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

import com.google.gson.Gson;

import restful.blImpl.UserBlImpl;
import restful.persistence.User;

@Path("/user")
public class UserResource extends Resource
{
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(final String json)
	{
		try
		{
			UserBlImpl userBl = new UserBlImpl();
			User userObj = (User)new Gson().fromJson(json, User.class);
			userObj = userBl.createUser(userObj);
			Gson gson = getGsonBuilder();
			return buildResponse(gson.toJson(userObj));
		}
		catch(Exception ex)
		{
			return returnError(ex.getMessage());
		}
	}
	
	@PUT
	@Path("{uuid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("uuid")String uuid, final String json)
	{
		try
		{
			UserBlImpl userBl = new UserBlImpl();
			User userObj = (User)new Gson().fromJson(json, User.class);
			User user = userBl.getUserByUuid(uuid);
			if(user == null)
				throw new Exception("User you are trying to update doesn't exist");
			user.setUsername(userObj.getUsername());
			user.setPassword(userObj.getPassword());
			user.setEmail(userObj.getEmail());
		
			user = userBl.updateUser(user);
			Gson gson = getGsonBuilder();
			return buildResponse(gson.toJson(user));
		}
		catch(Exception ex)
		{
			return returnError(ex.getMessage());
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{uuid}")
	public Response deleteUser(@PathParam("uuid") String uuid)
	{
		try
		{
			UserBlImpl userBl = new UserBlImpl();
			User user = userBl.getUserByUuid(uuid);
			if(user == null)
				return returnError("User you are trying to delete does not exist");
			userBl.deleteUser(user);
			return returnSuccess("User Deleted Successfully");
		}
		catch(Exception ex)
		{
			return returnError(ex.getMessage());
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{uuid}")
	public Response getUserbyUuid(@PathParam("uuid") String uuid)
	{
		try
		{
			UserBlImpl userBl = new UserBlImpl();
			User user = userBl.getUserByUuid(uuid);
			if(user == null)
				return returnError("Couldn't find the requested user");
			Gson gson = getGsonBuilder();
			return buildResponse(gson.toJson(user));
		}
		catch(Exception ex)
		{
			return returnError(ex.getMessage());
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("")
	public Response getUsersByQuery(@QueryParam("q") String q)
	{
		try
		{
			UserBlImpl userBl = new UserBlImpl();
			List<User> users = userBl.getUsersByQuery(q);
			Gson gson = getGsonBuilder();
			return buildResponse(gson.toJson(users));
		}
		catch(Exception ex)
		{
			return returnError(ex.getMessage());
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers()
	{
		try
		{
			UserBlImpl userBl = new UserBlImpl();
			List<User> users = userBl.getAllUsers();
			Gson gson = getGsonBuilder();
			return buildResponse(gson.toJson(users));
		}
		catch(Exception ex)
		{
			return returnError(ex.getMessage());
		}
	}
}
