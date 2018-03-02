package restful.config;
import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig
{
	public Application()
	{
		packages("restful.resources");
       // register(LoggingFilter.class);
       
        //Register Auth Filter here
        register(AuthenticationFilter.class);
	}
}
