package com.graduation.mem.service;

import com.graduation.mem.entity.Equipment;

public interface EquipmentService {

	public String queryAllEquipment();
	
	public Object deleteEquipment(String uid);

	public String addEquipment(Equipment equipment);

	public String updateEquipment(Equipment equipment);

	public String buildEqmTree();
	
}
