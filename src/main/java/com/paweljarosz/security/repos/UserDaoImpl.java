package com.paweljarosz.security.repos;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import com.paweljarosz.main.dao.GenericDaoImpl;
import com.paweljarosz.security.model.Device;
import com.paweljarosz.security.model.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User,Long> implements UserDao{

	@Override
	public User findUserByGameId(Long id) {
		TypedQuery<User>query = em.createQuery("SELECT u FROM User u join u.games g where g.id=?1",User.class);
		query.setParameter(1, id);
		return query.getSingleResult();
	}

	@Override
	public int getUserPoints(Long userId) {
		TypedQuery<Integer>query = em.createQuery("SELECT u.points FROM User u WHERE u.id = ?1",Integer.class);
		query.setParameter(1, userId);
		return query.getSingleResult();
	}

	@Override
	public void setUserPoints(Long userId, int updatedPoints) {
		em.createQuery("UPDATE User u SET u.points = ?1 WHERE u.id = ?2").setParameter(1, updatedPoints).setParameter(2, userId).executeUpdate();		
	}

	@Override
	public List<Device> getDevicesForUser(Long id) {
		TypedQuery<Device>query = em.createQuery("SELECT d FROM Device d JOIN d.user u WHERE u.id = ?1 ",Device.class);
		query.setParameter(1, id);
		return query.getResultList();
	}

	@Override
	public void addDevice(Device device,Long id) {
		try {
			Query query = em.createNativeQuery("INSERT INTO Device (NAME,SYSTEM_VERSION,USER_ID)  VALUES(?,?,?)");
			query.setParameter(1, device.getName());
			query.setParameter(2, device.getSystemVersion());
			query.setParameter(3, id);
			query.executeUpdate();
		} catch (Exception e) {
			System.out.println("ee "+e);
		}
	}

	@Override
	public void removeDevice(Long id) {
		Query query = em.createNativeQuery("DELETE FROM DEVICE WHERE ID = ?1");
		query.setParameter(1, id);
		query.executeUpdate();
	}
}
