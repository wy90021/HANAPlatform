package models.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import models.Device;

public class DeviceDaoImplementation implements DeviceDao{
	private SimpleJdbcTemplate simpleJdbcTemplate;

	@Override
	public void addDevice(String deviceTypeName, String uri,
			String userDefinedFields, double longitude, double latitude,
			double altitude, String representation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Device> getAllDevices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Device getDevice(String uri) {
		// TODO Auto-generated method stub
		return null;
	}

	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		return simpleJdbcTemplate;
	}

	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}

}
