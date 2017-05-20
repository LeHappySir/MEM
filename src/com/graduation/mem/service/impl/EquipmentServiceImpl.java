package com.graduation.mem.service.impl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduation.mem.dao.EquipmentDao;
import com.graduation.mem.entity.Equipment;
import com.graduation.mem.service.EquipmentService;

import net.sf.json.JSONArray;

@Service
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentDao equipmentDao;
	
	@Override
	public String queryAllEquipment() {
		List<Equipment> equipmentList=equipmentDao.getAllEquipment();
		String equipmentJsonArr=JSONArray.fromObject(equipmentList).toString();
		return equipmentJsonArr;
	}

	@Override
	public Object deleteEquipment(String uid) {
		int state=equipmentDao.deleteEquipmentById(uid);
		JSONObject msg=new JSONObject();
		if(state>0){
			msg.put("success", true);
		}else{
			msg.put("errorMsg", "删除失败");
		}
		return msg.toString();
	}

	@Override
	public String addEquipment(Equipment equipment) {
		int state = equipmentDao.insertEquipment(equipment);
		JSONObject msg = new JSONObject();
		if(state>0){
			msg.put("success", true);
		}else{
			msg.put("errorMsg", "添加失败");
		}
		return msg.toString();
	}

	@Override
	public String updateEquipment(Equipment equipment) {
		int state = equipmentDao.updateEquipment(equipment);
		JSONObject msg = new JSONObject();
		if(state>0){
			msg.put("success", true);
		}else{
			msg.put("errorMsg", "添加失败");
		}
		return msg.toString();
	}

	@Override
	public String buildEqmTree() {
		String repairArr = queryAllEquipment();
		JSONArray repairJsonArr = JSONArray.fromObject(repairArr);
		JSONArray treeArr = new JSONArray();
		int index=0;
		for (int i = 0; i < repairJsonArr.size(); i++) {
			net.sf.json.JSONObject  obj = (net.sf.json.JSONObject) repairJsonArr.get(i);
			if(i==0){
				net.sf.json.JSONObject tmp0=new net.sf.json.JSONObject();
				tmp0.put("id", index++);
				tmp0.put("text", obj.get("usingDepartment"));
				JSONArray child = new JSONArray();
				obj.put("id", obj.get("uid"));
				obj.put("text", obj.get("equipmentName"));
				child.add(obj);
				tmp0.put("children", child);
				treeArr.add(tmp0);
			}else{
				boolean isChildren=false;
				for (int j = 0; j < treeArr.size(); j++) {
					net.sf.json.JSONObject  tmp = (net.sf.json.JSONObject) treeArr.get(j); 
					if(tmp.get("text").equals(obj.get("usingDepartment"))){
						JSONArray chil = (JSONArray) tmp.get("children");
						obj.put("id", obj.get("uid"));
						obj.put("text", obj.get("equipmentName"));
						chil.add(obj);
						tmp.put("children", chil);
						isChildren=true;
					}
				}
				if(!isChildren){
					net.sf.json.JSONObject tmp0=new net.sf.json.JSONObject();
					tmp0.put("id", index++);
					tmp0.put("text", obj.get("usingDepartment"));
					JSONArray child = new JSONArray();
					obj.put("id", obj.get("uid"));
					obj.put("text", obj.get("equipmentName"));
					child.add(obj);
					tmp0.put("children", child);
					treeArr.add(tmp0);
				}
			}
		}
		return treeArr.toString();
	}

}
