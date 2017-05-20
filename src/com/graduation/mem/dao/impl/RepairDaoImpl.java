package com.graduation.mem.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.graduation.mem.dao.RepairDao;
import com.graduation.mem.entity.Repair;

@Repository
public class RepairDaoImpl implements RepairDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	class RepairMapper implements RowMapper<Repair>{

		@Override
		public Repair mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Repair rp = new Repair();
			rp.setEndTime(rs.getString("EndTime"));
			rp.setFault(rs.getString("Fault"));
			rp.setHost(rs.getString("Host"));
			rp.setRepairProposer(rs.getString("RepairProposer"));
			rp.setRepairCharge(rs.getString("RepairCharge"));
			rp.setRepairReason(rs.getString("RepairReason"));
			rp.setRepairState(rs.getString("RepairState"));
			rp.setRepairTime(rs.getString("RepairTime"));
			rp.setStartTime(rs.getString("StartTime"));
			rp.setUid(rs.getString("Uid"));
			rp.setRepairNo(rs.getString("RepairNo"));
			rp.setEqmName(rs.getString("EquipmentName"));
			rp.setEqmDep(rs.getString("UsingDepartment"));
			rp.setEqmNo(rs.getString("EquipmentNo"));
			rp.setRepairName(rs.getString("RepairName"));
			return rp;
		}
		
	}
	
	@Override
	public List<Repair> getAllRepair() {
		
		List<Repair> rpList=new ArrayList<Repair>();
		
		String sql="select r.*,e.UsingDepartment,e.EquipmentName,e.EquipmentNo from Repair r inner join Equipment e "
				+ "on r.Host=e.Uid ";
		
		rpList=jdbcTemplate.query(sql, new RepairMapper());
		
		return rpList;
	}

	@Override
	public int deleteRepairById(String uid) {
		String sql="delete from Repair where Uid = ?";
		int state=jdbcTemplate.update(sql,new Object[]{uid});
		return state;
		
	}

	@Override
	public void deleteRepairByHost(String Host) {
		String sql ="delete from Repair where Host = ?";
		int state = jdbcTemplate.update(sql,new Object[]{Host});
	}

	@Override
	public int insertRepair(Repair repair) {
		String sql = "insert into Repair (Uid,EndTime,Fault,Host,RepairCharge,RepairNo,RepairProposer,RepairReason,RepairState,RepairTime,StartTime,RepairName) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		int state = jdbcTemplate.update(sql,new Object[]{repair.getUid(),repair.getEndTime(),repair.getFault(),repair.getHost(),
				repair.getRepairCharge(),repair.getRepairNo(),repair.getRepairProposer(),repair.getRepairReason()
				,repair.getRepairState(),repair.getRepairTime(),repair.getStartTime(),repair.getRepairName()});
		return state;
	}

	@Override
	public int updateRepair(Repair repair) {
		String sql ="update Repair set EndTime = ?,Fault = ?,Host = ?,RepairCharge = ?,RepairNo = ?,RepairProposer = ?,RepairReason = ?,RepairState = ?,RepairTime = ?,StartTime = ?,RepairName=? where Uid = ?";
		int state = jdbcTemplate.update(sql,new Object[]{repair.getEndTime(),repair.getFault(),repair.getHost(),
				repair.getRepairCharge(),repair.getRepairNo(),repair.getRepairProposer(),repair.getRepairReason()
				,repair.getRepairState(),repair.getRepairTime(),repair.getStartTime(),repair.getRepairName(),repair.getUid()});
		return state;
	}

}
