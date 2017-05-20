package com.graduation.mem.dao;

import java.util.List;
import com.graduation.mem.entity.Repair;

public interface RepairDao {

	public List<Repair> getAllRepair();

	public int deleteRepairById(String uid);
	
	public void deleteRepairByHost(String Host);

	public int insertRepair(Repair repair);

	public int updateRepair(Repair repair);
}
