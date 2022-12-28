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
	public boolean update(Integer drIdx, Integer pIdx) {
		System.out.println("1번");
		boolean result = false;
		try {
			requestMapper.updateAcceptStatus(pIdx);
			requestMapper.updateConfirm(drIdx);
			result = true;
			System.out.println("2번");
		} catch (Exception e) {
			System.out.println("3번");
		}
		return result;
	}
	
}
