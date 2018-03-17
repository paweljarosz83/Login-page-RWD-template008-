package com.paweljarosz.security.repos;

import java.util.List;

import com.paweljarosz.main.dao.GenericDao;
import com.paweljarosz.security.model.Device;
import com.paweljarosz.security.model.User;



public interface UserDao extends GenericDao<User,Long>{
	
	User findUserByGameId(Long id);

	int getUserPoints(Long userId);

	void setUserPoints(Long userId, int updatedPoints);

	List<Device> getDevicesForUser(Long id);

	void addDevice(Device device,Long userId);

	void removeDevice(Long id);

}
