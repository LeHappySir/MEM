package com.graduation.mem.dao;

import java.util.List;

import com.graduation.mem.entity.Equipment;

public interface EquipmentDao {

	public List<Equipment> getAllEquipment();
	
	public int deleteEquipmentById(String uid);

	public int insertEquipment(Equipment equipment);

	public int updateEquipment(Equipment equipment);
}
