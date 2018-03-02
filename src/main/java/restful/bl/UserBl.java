package restful.bl;

import java.util.List;

import restful.persistence.User;

public interface UserBl {
	User getUserById(long id) throws Exception;
	User getUserByUuid(String uuid) throws Exception;
	User getUserByUsername(String username) throws Exception;
	List<User> getAllUsers() throws Exception;
	List<User> getUsersByQuery(String query) throws Exception;
	User createUser(User userObj) throws Exception;
	User updateUser(User userObj) throws Exception;
	void deleteUser(User userObj) throws Exception;
}
