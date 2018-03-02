package restful.daoImpl;

import org.hibernate.Session;

public class Dao 
{
	private Session session;

	public Session getSession() throws Exception{
		return this.session;
	}
	
	public void setSession(Session value) throws Exception{
		this.session = value;
	}
	
	public void flush() throws Exception{
		Session session = getSession();
		session.flush();
	}
}
