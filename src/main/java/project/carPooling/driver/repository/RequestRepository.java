package project.carPooling.driver.repository;

import java.util.List;
import java.util.Map;

public interface RequestRepository {
	public List<Map<String, Object>> selectRequestByDIdx(Integer dIdx);
	
	public boolean update(Integer drIdx, Integer pIdx);
}
