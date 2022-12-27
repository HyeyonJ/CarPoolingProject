package project.carPooling.driver.repository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.mapper.AcceptMapper;

@Repository
@RequiredArgsConstructor
public class MybatisAcceptRepository implements AcceptRepository{

	private final AcceptMapper acceptMapper;
	
	@Override
	public boolean update(Integer rIdx) {
		boolean result = false;
		try {
			acceptMapper.update(rIdx);
			result = true;
		} catch (Exception e) {
		}
		return result;
	}
	
}
