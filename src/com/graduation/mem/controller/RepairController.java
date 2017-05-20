package com.graduation.mem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.graduation.mem.entity.Repair;
import com.graduation.mem.service.RepairService;
import com.graduation.mem.tool.Uid;

@Controller
@RequestMapping("/repair")
public class RepairController {

	@Autowired
	private RepairService rpSer;
	
	@RequestMapping(value="/getAllRepair",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object queryAllRepair(){
		Object repairJsonArr = rpSer.queryAllRepair();
		//System.out.println(repairJsonArr);
		return repairJsonArr;
	}
	
	@RequestMapping(value="/deleteRepair",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Object deleteRepair(@RequestParam String uid){
		Object rs = rpSer.deleteRepairById(uid);
		return rs;
	}
	
	@ResponseBody
	@RequestMapping(value="/saveNewRepair",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public String addRepair(@RequestBody Repair repair){
		repair.setUid(Uid.GetId());
		repair.setHost(repair.getEqmName());
		String msg = rpSer.addRepair(repair);
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateRepair",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public String updateRepair(@RequestBody Repair repair){
		String msg = rpSer.updateRepair(repair);
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value="buildRepairTree",produces="application/json;charset=utf-8")
	public String buildRepairTree(){
		String tree = rpSer.buildRepairTree();
		return tree;
	}
}
