package com.graduation.mem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.graduation.mem.entity.Equipment;
import com.graduation.mem.service.EquipmentService;
import com.graduation.mem.service.RepairService;
import com.graduation.mem.tool.Uid;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {

	@Autowired
	private EquipmentService eqmSer; 
	
	@Autowired
	private RepairService repairSer;
	
	@RequestMapping(value="/getAllEquipment",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object queryEquipment(){
		
		Object equipmentJsonArr=eqmSer.queryAllEquipment();
		return equipmentJsonArr;
	}
	
	@RequestMapping(value="/deleteEquipment",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Object deleteEquipment(@RequestParam String uid){
		Object result=eqmSer.deleteEquipment(uid);
		repairSer.deleteRepairByHost(uid);
		return result;
	}
	
	@RequestMapping(value="/saveNewEquipment",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String addEquipment(@RequestBody Equipment equipment){
		equipment.setUid(Uid.GetId());
		String msg = eqmSer.addEquipment(equipment);
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateEquipment",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public String updateEquipment(@RequestBody Equipment equipment){
		String msg =eqmSer.updateEquipment(equipment);
		return msg;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/buildEquipmentTree",produces="application/json;charset=utf-8")
	public String buildEquipmentTree(){
		String msg = eqmSer.buildEqmTree();
		return msg;
	}
}
