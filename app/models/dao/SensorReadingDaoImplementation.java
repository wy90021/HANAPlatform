package models.dao;

import java.sql.Timestamp;
import java.util.List;

import models.SensorReading;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class SensorReadingDaoImplementation implements SensorReadingDao{
	private SimpleJdbcTemplate simpleJdbcTemplate;
	
	public void setSimpleJdbcTemplate(SimpleJdbcTemplate jdbcTemplate) {
		this.simpleJdbcTemplate = jdbcTemplate;
	}

	@Override
	public SensorReading searchReading(String sensorName, Long timeStamp) {
		final String SQL = "SELECT * FROM CMU.COURSE_DISCRETE_SENSOR_READING DSR, CMU.COURSE_SENSOR S WHERE DSR.SENSOR_ID=S.SENSOR_ID AND S.SENSOR_NAME=? AND DSR.timeStamp<=? ORDER BY timeStamp DESC LIMIT 1";
		try {
		SensorReading sensorReading = simpleJdbcTemplate.queryForObject(SQL, ParameterizedBeanPropertyRowMapper.newInstance(SensorReading.class), sensorName, new Timestamp(timeStamp));
		return sensorReading;
		} catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addReading(String sensorName, Boolean isIndoor, long timestamp, String value, Double longitude, Double latitude, Double altitude, String locationInterpreter) {
		final String FETCH_SENSOR_ID = "SELECT SENSOR_ID FROM CMU.COURSE_SENSOR S WHERE S.SENSOR_NAME = ?";
		int sensorId = simpleJdbcTemplate.queryForInt(FETCH_SENSOR_ID, sensorName);
		
		final String SQL = "INSERT INTO CMU.COURSE_DISCRETE_SENSOR_READING (SENSOR_ID, IS_INDOOR, LOCATION_INTERPRETER, TIMESTAMP, VALUE, LONGITUDE, LATITUDE, ALTITUDE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; 
				
		try{
			int num = simpleJdbcTemplate.update(SQL, sensorId, String.valueOf(isIndoor), locationInterpreter, new Timestamp(timestamp), value, longitude, latitude, altitude);
			if (num == 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<SensorReading> searchReading(String sensorName, Long startTime, Long endTime) {
		final String SQL = "SELECT SENSOR_NAME, IS_INDOOR, LOCATION_INTERPRETER, TIMESTAMP, VALUE, LONGITUDE, LATITUDE, ALTITUDE FROM CMU.COURSE_DISCRETE_SENSOR_READING DSR, CMU.COURSE_SENSOR S" 
				+ " WHERE S.SENSOR_NAME = ? AND DSR.SENSOR_ID = S.SENSOR_ID AND TIMESTAMP >= ? AND TIMESTAMP <= ? ORDER BY TIMESTAMP DESC";
		List<SensorReading> sensorReadings = simpleJdbcTemplate.query(SQL, ParameterizedBeanPropertyRowMapper.newInstance(SensorReading.class), sensorName, new Timestamp(startTime), new Timestamp(endTime));
		return sensorReadings;
	}

	@Override
	public List<SensorReading> lastReadingFromAllDevices(Long timeStamp, String sensorType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SensorReading> lastestReadingFromAllDevices(String sensorType) {
		final String SQL2 = "SELECT SENSOR_NAME, IS_INDOOR, TIMESTAMP, LOCATION_INTERPRETER, VALUE, LONGITUDE, LATITUDE, ALTITUDE FROM CMU.COURSE_SENSOR CS, CMU.COURSE_DISCRETE_SENSOR_READING SR INNER JOIN (SELECT DSR.SENSOR_ID AS ID, MAX(DSR.TIMESTAMP) AS TMSTP FROM CMU.COURSE_DISCRETE_SENSOR_READING DSR, CMU.COURSE_SENSOR S, CMU.COURSE_DEVICE D, CMU.COURSE_DEVICE_TYPE_SENSOR_TYPE DTST, CMU.COURSE_SENSOR_TYPE ST WHERE DTST.SENSOR_TYPE_ID = ST.SENSOR_TYPE_ID AND ST.SENSOR_TYPE_NAME = ? AND D.DEVICE_TYPE_ID = DTST.DEVICE_TYPE_ID AND S.DEVICE_ID = D.DEVICE_ID AND S.SENSOR_TYPE_ID = ST.SENSOR_TYPE_ID AND DSR.SENSOR_ID = S.SENSOR_ID GROUP BY DSR.SENSOR_ID) TMP ON SR.SENSOR_ID = TMP.ID AND SR.TIMESTAMP = TMP.TMSTP WHERE CS.SENSOR_ID = SR.SENSOR_ID";
		
		List<SensorReading> sensorReadings = simpleJdbcTemplate.query(SQL2, ParameterizedBeanPropertyRowMapper.newInstance(SensorReading.class), sensorType);
		return sensorReadings;
	}

	

}
