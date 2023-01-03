package project.carPooling.driver.repository;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.mapper.RequestMapper;

@Repository @Primary
@RequiredArgsConstructor
public class MybatisRequestRepository implements RequestRepository{

	private final RequestMapper requestMapper;
	

	@Override
	public List<Map<String, Object>> selectRequestByDIdx(Integer dIdx) {
		List<Map<String, Object>> reqList = requestMapper.selectRequestByDIdx(dIdx);
		return reqList;
	}

	@Transactional
	@Override
	public boolean updateAccepted(Integer drIdx, Integer pIdx) {
		boolean result = false;
		try {
			requestMapper.updateAccepted(drIdx, pIdx);
			requestMapper.updateConfirm(drIdx);
			result = true;
		} catch (Exception e) {
		}
		return result;
	}

	@Transactional
	@Override
	public boolean updateRefused(Integer drIdx, Integer pIdx) {
		boolean result = false;
		try {
			requestMapper.updateRefused(drIdx, pIdx);
			result = true;
		} catch (Exception e) {
		}
		return result;
	}
	
}
