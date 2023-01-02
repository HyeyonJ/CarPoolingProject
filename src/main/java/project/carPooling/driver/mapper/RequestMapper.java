package project.carPooling.driver.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RequestMapper {
	public List<Map<String, Object>> selectRequestByDIdx(@Param("dIdx") Integer dIdx);
	
	public void updateAccepted(@Param("drIdx")Integer drIdx, @Param("pIdx")Integer pIdx);

	public void updateRefused(@Param("drIdx")Integer drIdx, @Param("pIdx")Integer pIdx);
	
	public void updateConfirm(@Param("drIdx")Integer drIdx);
}
