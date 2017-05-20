package com.graduation.mem.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.graduation.mem.dao.EquipmentDao;
import com.graduation.mem.entity.Equipment;

@Repository
public class EquipmentDaoImpl implements EquipmentDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	class EquipmentRowMapper implements RowMapper<Equipment>{

		@Override
		public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Equipment equipment=new Equipment();
			equipment.setAcquisitionTime(rs.getString("AcquisitionTime"));
			equipment.setEquipmentName(rs.getString("EquipmentName"));
			equipment.setEquipmentNo(rs.getString("EquipmentNo"));
			equipment.setEquipmentType(rs.getString("EquipmentType"));
			equipment.setManufacturer(rs.getString("Manufacturer"));
			equipment.setSpecification(rs.getString("Specification"));
			equipment.setUid(rs.getString("Uid"));
			equipment.setUsingDepartment(rs.getString("UsingDepartment"));
			equipment.setUsingState(rs.getString("UsingState"));
			return equipment;
		}
		
	}

	@Override
	public List<Equipment> getAllEquipment() {
		
		List<Equipment> equipmentList = new ArrayList<Equipment>();
		
		String sql ="select * from Equipment";
		
		equipmentList=jdbcTemplate.query(sql, new EquipmentRowMapper());
		
		return equipmentList;
	}

	@Override
	public int deleteEquipmentById(String uid) {
		
		String sql="delete from Equipment where Uid = ?";
		int state=jdbcTemplate.update(sql,new Object[]{uid});
		return state;
	}

	@Override
	public int insertEquipment(Equipment equipment) {
		String sql= "insert into Equipment (Uid,EquipmentName,EquipmentNo,EquipmentType,Manufacturer,Specification,UsingDepartment,UsingState,AcquisitionTime) values (?,?,?,?,?,?,?,?,?)";
		int state = jdbcTemplate.update(sql,new Object[]{equipment.getUid(),equipment.getEquipmentName(),equipment.getEquipmentNo(),equipment.getEquipmentType(),
				equipment.getManufacturer(),equipment.getSpecification(),equipment.getUsingDepartment(),
				equipment.getUsingState(),equipment.getAcquisitionTime()});
		return state;
	}

	@Override
	public int updateEquipment(Equipment equipment) {
		String sql ="update Equipment set EquipmentName=?,EquipmentNo=?,EquipmentType=?,Manufacturer=?,Specification=?,UsingDepartment=?,UsingState=?,AcquisitionTime=? where Uid=?";
		int state =jdbcTemplate.update(sql,new Object[]{equipment.getEquipmentName(),equipment.getEquipmentNo(),equipment.getEquipmentType(),
				equipment.getManufacturer(),equipment.getSpecification(),equipment.getUsingDepartment(),
				equipment.getUsingState(),equipment.getAcquisitionTime(),equipment.getUid()});
		return state;
	}

}
