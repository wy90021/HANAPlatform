package models.dao;

import java.util.List;

import models.SensorCategory;

public interface SensorCategoryDao {
	public void addSensorCategory(String sensorCategoryName, String purpose);
	public List<SensorCategory> getAllSensorCategories();
	public SensorCategory getSensorCategory(String SensorCategoryName);
}
