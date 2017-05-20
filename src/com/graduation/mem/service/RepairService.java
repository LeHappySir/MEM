package com.graduation.mem.service;

import com.graduation.mem.entity.Repair;

public interface RepairService {

	public String queryAllRepair();

	public Object deleteRepairById(String uid);
	
	public void deleteRepairByHost(String host);

	public String addRepair(Repair repair);

	public String updateRepair(Repair repair);

	public String buildRepairTree();
}
