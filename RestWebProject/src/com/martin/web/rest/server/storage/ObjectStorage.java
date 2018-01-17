package com.martin.web.rest.server.storage;

import java.util.HashMap;

public class ObjectStorage {
	private static HashMap<Long, Object> storage = new HashMap<>();
	private static Long lastID = new Long(0);
	
	public static Object getObject(Long id) {
		return storage.get(id);
	}
	
	public static Long storeObject(Object o) {
		Long id = new Long(++lastID);
		storage.put(id, o);
		return id;
	}
}
