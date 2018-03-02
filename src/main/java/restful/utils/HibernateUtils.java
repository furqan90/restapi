package restful.utils;

import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
//	private static Log log = LogFactory.getLog(HibernateUtil.class);
	private static final SessionFactory sessionFactory;
	static {
		try {
		    //getLicense();
			// Create the SessionFactory
			Configuration configuration = new Configuration();
			sessionFactory = configuration.configure().buildSessionFactory();
			//Configuration configuration = new Configuration();
			//configuration.configure("hibernate.cfg.xml");
			//sessionFactory = configuration.buildSessionFactory();
		//	SaveOrUpdateEventListenerImpl listenerObj=new SaveOrUpdateEventListenerImpl();
		//	configuration.getSessionEventListenerConfig().setSaveOrUpdateEventListener(listenerObj);
			
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			//System.out.println("Initial SessionFactory creation failed.",
			// ex);
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	private static final ThreadLocal session = new ThreadLocal();
	private static final ThreadLocal localInterceptor = new ThreadLocal();

	public static Session currentSession() {
		Session s = (Session) session.get();		
		// Open a new Session, if this Thread has none yet
		/*if  ( (s==null) || (!s.isOpen()) ) {
			if(localInterceptor.get() == null){
				s = sessionFactory.openSession();
			}
			else{
				Interceptor interceptor = (Interceptor)localInterceptor.get();
				s = sessionFactory.openSession();
			}
			session.set(s);
		}*/
		s = sessionFactory.openSession();
		return s;
	}

	public static void closeSession() {
		/* ????
		Session s = (Session) session.get();
		if (s != null)
			s.close();
		session.set(null);
		*/
		
		//by capt faisal to prevent multiple sessions for single request
//		Object obj = session.get();
//		if( obj != null )
//		{
//			Session currentSession = ((Session)obj);
//			currentSession.close();
//			session.set( null );
//		}
	}	
	
	public static void terminateSession()
	{
		Object obj = session.get();
		if( obj != null )
		{
			Session currentSession = ((Session)obj);
			currentSession.close();
			session.set( null );
		}
	}
	
	//added by shahzad required for Unit Test for all hibernate mapping files.
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void registerInterceptor(Interceptor interceptor){
    	if(interceptor != null){
    		localInterceptor.set(interceptor);
    	}
	}
    
//	This method would be called at start-up of application so it would be placed somewhere else
	/*private static void getLicense()
	{
		FileInputStream fstream = null;
		 try
		 {
		   //Create a stream object containing the license file
		    String filePath = Environment.getProperty("asposeLicensePath");  
		 	fstream = new FileInputStream(filePath);


		   //Instantiate the License class
		     License license=new License();


		   //Set the license through the stream object
		   license.setLicense(fstream);
		    
		 }
		 catch(Exception ex)
		 {
		   //Printing the exception, if it occurs
		   ex.printStackTrace();
		 }
		 finally
		 {
		   try{
		   		fstream.close();
		   }
		   catch(Exception e)
		   {
		   	 e.printStackTrace();
		   }
		 }
	}*/
    
}
