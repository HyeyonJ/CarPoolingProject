package project.carPooling.driver.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RequestMapper {
	public void update(@Param("rIdx")Integer rIdx);
	
	public List<Map<String, Object>> selectRequestByDIdx(@Param("dIdx") Integer dIdx);
}
