package restful.blImpl;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Transaction;

import restful.bl.UserBl;
import restful.daoImpl.UserDaoImpl;
import restful.persistence.User;
import restful.utils.HibernateUtils;
public class UserBlImpl implements UserBl{

	public User getUserById(long id) throws Exception {
		// TODO Auto-generated method stub
		UserDaoImpl userDao = new UserDaoImpl();
		userDao.setSession(HibernateUtils.currentSession());
		Transaction tx = userDao.getSession().beginTransaction();
		User user = userDao.get(id);
		userDao.flush();
		tx.commit();
		userDao.getSession().close();
		return user;
	}

	public User getUserByUuid(String uuid) throws Exception{
		// TODO Auto-generated method stub
		UserDaoImpl userDao = new UserDaoImpl();
		userDao.setSession(HibernateUtils.currentSession());
		Transaction tx = userDao.getSession().beginTransaction();
		User user = userDao.getByUuid(uuid);
		userDao.flush();
		tx.commit();
		userDao.getSession().close();
		return user;
	}

	public User getUserByUsername(String username) throws Exception{
		// TODO Auto-generated method stub
		UserDaoImpl userDao = new UserDaoImpl();
		userDao.setSession(HibernateUtils.currentSession());
		Transaction tx = userDao.getSession().beginTransaction();
		User user = userDao.getByUsername(username);
		userDao.flush();
		tx.commit();
		userDao.getSession().close();
		return user;
	}

	public List<User> getAllUsers() throws Exception{
		// TODO Auto-generated method stub
		UserDaoImpl userDao = new UserDaoImpl();
		userDao.setSession(HibernateUtils.currentSession());
		Transaction tx = userDao.getSession().beginTransaction();
		List<User> users = userDao.getAll();
		userDao.flush();
		tx.commit();
		userDao.getSession().close();
		return users;
	}

	public List<User> getUsersByQuery(String query) throws Exception{
		// TODO Auto-generated method stub
		UserDaoImpl userDao = new UserDaoImpl();
		userDao.setSession(HibernateUtils.currentSession());
		Transaction tx = userDao.getSession().beginTransaction();
		List<User> users = userDao.get(query);
		userDao.flush();
		tx.commit();
		userDao.getSession().close();
		return users;
		
	}

	public User createUser(User userObj) throws Exception{
		// TODO Auto-generated method stub
		UserDaoImpl userDao = new UserDaoImpl();
		userObj.setCreatedOn(new Date());
		UUID uuid = UUID.randomUUID();
		userObj.setUuid(uuid.toString());
		userDao.setSession(HibernateUtils.currentSession());
		Transaction tx = userDao.getSession().beginTransaction();
		userDao.add(userObj);
		userDao.flush();
		tx.commit();
		userDao.getSession().close();
		return userObj;
	}

	public User updateUser(User userObj) throws Exception{
		UserDaoImpl userDao = new UserDaoImpl();
		userObj.setUpdatedOn(new Date());
		userDao.setSession(HibernateUtils.currentSession());
		Transaction tx = userDao.getSession().beginTransaction();
		userDao.update(userObj);
		userDao.flush();
		tx.commit();
		userDao.getSession().close();
		return userObj;
	}

	public void deleteUser(User userObj) throws Exception{
		UserDaoImpl userDao = new UserDaoImpl();
		userDao.setSession(HibernateUtils.currentSession());
		Transaction tx = userDao.getSession().beginTransaction();
		userDao.delete(userObj);
		userDao.flush();
		tx.commit();
		userDao.getSession().close();
		
	}

}
