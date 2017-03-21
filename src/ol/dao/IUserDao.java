package ol.dao;

import java.util.List;

import ol.entity.User;

public interface IUserDao {

	void saveUser(User user);
	
	void updateUser(User user);
	
	void delete(int uid);
	
	User findUserByUsernameAndPwd(String username, String pwd);
	
	List<User> findUserByUsername(String username);
	
	User findUser(int uid);
	
	List<User> findAllUsers();
}
