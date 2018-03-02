package restful.dao;

import java.util.List;

import restful.persistence.User;

public interface IUserDao 
{
	public void add(User User) throws Exception;
	public User get(long id) throws Exception;
	public List<User> getAll() throws Exception;
	public User getByUuid(String uuid) throws Exception;
	public User getByUsername(String username) throws Exception;
	public List<User> get(String queryStr) throws Exception;
	public void delete(User User) throws Exception;
	public void update(User User) throws Exception;
}
