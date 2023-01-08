package project.carPooling.passenger.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReservationListMapper {
	public List<Map<String, Object>> selectCurrentList(@Param("pIdx") Integer pIdx);
	
	public List<Map<String, Object>> selectPastList(@Param("pIdx") Integer pIdx);

	public void deleteRsv(@Param("drIdx") Integer drIdx, @Param("pIdx") Integer pIdx);
}
