package project.carPooling.driver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AcceptMapper {
	public void update(@Param("rIdx")Integer rIdx);
}
