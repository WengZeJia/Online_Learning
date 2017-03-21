package ol.dao;

import ol.entity.Admin;

public interface IAdminDao {

	Admin findAdmin(String username, String pwd);
	
}
