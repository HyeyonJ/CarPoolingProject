package project.carPooling.driver.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import project.carPooling.driver.domain.DriverInfo;


//@Repository
//public class ListDriverInfoRepository implements DriverInfoRepository{
//	private static List<DriverInfo>db = new ArrayList<>();
//	//key, value
//	private static int seq = 1;
//	
	// 싱글톤 : 객체의 인스턴스 오직 한개만 생성되는 패턴 
	// 해당 객체에 접근 시 메모리 낭비 방지
//	private static final FoodRepository instance = new FoodRepository();
//	
//	public static FoodRepository getInstance() {
//		return instance;
//	}
//	private FoodRepository() {
//		
//	}
	
//	public DriverInfo insert(DriverInfo driverInfo) {
//		driverInfo.setUserId(seq++);
//		db.add(driverInfo);
//		return driverInfo;
//	}
//
//	public DriverInfo selectById (int id) {
//		for(DriverInfo driverInfo : db) {
//			if(driverInfo.getId() == id) {
//				return driverInfo;
//			}
//		}
//		return null;
//	}
//	
//	public DriverInfo selectByLoginId (String loginId) {
//		for(DriverInfo driverInfo : db) {
//			if(driverInfo.getLoginId().equals(loginId)) {
//				return driverInfo;	// 아이디 값 있으면
//			}
//		}
//		return null;	// 없으면
//	}
//	
//	public List<DriverInfo> selectAll(){
//		return db;
//	}
//	
//	
//	public void deleteAll(){
//		db.clear();
//	}
//}
//