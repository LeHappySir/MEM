package com.graduation.mem.service.impl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduation.mem.dao.RepairDao;
import com.graduation.mem.entity.Repair;
import com.graduation.mem.service.RepairService;

import net.sf.json.JSONArray;

@Service
public class RepairServiceImpl implements RepairService{

	@Autowired
	private RepairDao rpDao;
	
	@Override
	public String queryAllRepair() {
		List<Repair>rpList=rpDao.getAllRepair();
		String repairJsonArr = JSONArray.fromObject(rpList).toString();
		return repairJsonArr;
	}

	@Override
	public Object deleteRepairById(String uid) {
		int state = rpDao.deleteRepairById(uid);
		JSONObject msg = new JSONObject();
		if(state>0){
			msg.put("success", true);
		}else{
			msg.put("errorMsg", "删除失败");
		}
		return msg.toString();
	}

	@Override
	public void deleteRepairByHost(String host) {
		rpDao.deleteRepairByHost(host);
		
	}

	@Override
	public String addRepair(Repair repair) {
		int state = rpDao.insertRepair(repair);
		JSONObject msg = new JSONObject();
		if(state>0){
			msg.put("success", true);
		}else{
			msg.put("errorMsg", "添加失败");
		}
		return msg.toString();
	}

	@Override
	public String updateRepair(Repair repair) {
		int state =rpDao.updateRepair(repair);
		JSONObject msg = new JSONObject();
		if(state>0){
			msg.put("success", true);
		}else{
			msg.put("errorMsg", "添加失败");
		}
		return msg.toString();
	}

	@Override
	public String buildRepairTree() {
		String repairArr = queryAllRepair();
		JSONArray repairJsonArr = JSONArray.fromObject(repairArr);
		JSONArray treeArr = new JSONArray();
		int index=0;
		for (int i = 0; i < repairJsonArr.size(); i++) {
			net.sf.json.JSONObject  obj = (net.sf.json.JSONObject) repairJsonArr.get(i);
			if(i==0){
				net.sf.json.JSONObject tmp0=new net.sf.json.JSONObject();
				tmp0.put("id", index++);
				tmp0.put("text", obj.get("eqmDep"));
				JSONArray child = new JSONArray();
				obj.put("id", obj.get("uid"));
				obj.put("text", obj.get("repairName"));
				child.add(obj);
				tmp0.put("children", child);
				treeArr.add(tmp0);
			}else{
				boolean isChildren=false;
				for (int j = 0; j < treeArr.size(); j++) {
					net.sf.json.JSONObject  tmp = (net.sf.json.JSONObject) treeArr.get(j); 
					if(tmp.get("text").equals(obj.get("eqmDep"))){
						JSONArray chil = (JSONArray) tmp.get("children");
						obj.put("id", obj.get("uid"));
						obj.put("text", obj.get("repairName"));
						chil.add(obj);
						tmp.put("children", chil);
						isChildren=true;
					}
				}
				if(!isChildren){
					net.sf.json.JSONObject tmp0=new net.sf.json.JSONObject();
					tmp0.put("id", index++);
					tmp0.put("text", obj.get("eqmDep"));
					JSONArray child = new JSONArray();
					obj.put("id", obj.get("uid"));
					obj.put("text", obj.get("repairName"));
					child.add(obj);
					tmp0.put("children", child);
					treeArr.add(tmp0);
				}
			}
		}
		return treeArr.toString();
	}

}
