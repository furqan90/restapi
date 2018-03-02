package restful.resources;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import restful.utils.ErrorMessage;
import restful.utils.SuccessMessage;

public class Resource {
	Response buildResponse(Object responseObj)
	{
	return Response.ok(responseObj).header("Access-Control-Allow-Origin", "*")
			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
			.header("Access-Control-Allow-Headers: Origin", "X-Requested-With, Content-Type, Accept")
			.allow("OPTIONS").build();
	}
	
	Gson getGsonBuilder()
	{
		return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz").create();
	}
	
	Response returnSuccess(String msg)
	{
		return buildResponse(new Gson().toJson(new SuccessMessage(msg)));
	}
	
	Response returnError(String msg)
	{
		return buildResponse(new Gson().toJson(new ErrorMessage(msg)));
	}
}
