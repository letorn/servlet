package service;

import java.util.HashMap;
import java.util.Map;

import dao.UserDAO;

public class UserService {

	private UserDAO userDAO = new UserDAO();

	public Map<String, Object> page(Integer start, Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totla", userDAO.getCount());
		map.put("list", userDAO.find(start, limit));
		return map;
	}
}
