package project.carPooling.driver.repository;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.mapper.RequestMapper;

@Repository @Primary
@RequiredArgsConstructor
public class MybatisRequestRepository implements RequestRepository{

	private final RequestMapper requestMapper;
	
	@Override
	public boolean update(Integer rIdx) {
		boolean result = false;
		try {
			requestMapper.update(rIdx);
			result = true;
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> selectRequestByDIdx(Integer dIdx) {
		List<Map<String, Object>> reqList = requestMapper.selectRequestByDIdx(dIdx);
		return reqList;
	}
	
}
