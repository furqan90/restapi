package restful.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import restful.dao.IUserDao;
import restful.persistence.User;

public class UserDaoImpl extends Dao implements IUserDao 
{

	public void add(User user) throws Exception {
		// TODO Auto-generated method stub
		Session session = getSession();
		session.save(user);
	}

	public User get(long id) throws Exception {
		// TODO Auto-generated method stub
		Session session = getSession();
		User user = (User)session.get(User.class, id);
		return user;
	}

	public User getByUuid(String uuid) throws Exception {
		Session session = getSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("uuid", uuid));
		List<User> list = criteria.list();	
		if(list == null || list.size() == 0)
			return null;
		return list.get(0);
	}
	
	public User getByUsername(String username) throws Exception {
		Session session = getSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		List<User> list = criteria.list();	
		if(list == null || list.size() == 0)
			return null;
		return list.get(0);
	}
	
	public List<User> getAll() throws Exception {
		Session session = getSession();
		Criteria criteria = session.createCriteria(User.class);
		List<User> list = criteria.list();	
		if(list == null || list.size() == 0)
			return null;
		return list;
	}


	public List<User> get(String queryStr) throws Exception {
		Session session = getSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add
		(
				Restrictions.disjunction()
				.add(Restrictions.ilike("username", queryStr, MatchMode.ANYWHERE))
				.add(Restrictions.ilike("email", queryStr, MatchMode.ANYWHERE))
				
		);
		criteria.addOrder(Order.desc("createdOn"));
		List<User> users = criteria.list();
		return users;
	}

	public void delete(User user) throws Exception {
		// TODO Auto-generated method stub
		Session session = getSession();
		session.delete(user);
	}

	public void update(User user) throws Exception {
		// TODO Auto-generated method stub
		Session session = getSession();
		session.merge(user);
	}
}
