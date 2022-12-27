package project.carPooling.driver.repository;

import java.util.List;
import java.util.Map;

public interface RequestRepository {
	public boolean update(Integer rIdx);
	
	public List<Map<String, Object>> selectRequestByDIdx(Integer dIdx);
}
