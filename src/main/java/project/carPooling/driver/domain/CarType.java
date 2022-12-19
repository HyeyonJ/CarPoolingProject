package project.carPooling.driver.domain;

public enum CarType {
	LIGHT("경차"), SMALL("소형"), MID("중형"), FULL("대형");
	//CarType은 ;
	//()안의 문자열은 memo
	
	private final String memo;
	
	CarType(String memo) {
		this.memo = memo;
	}
	
	public String getMemo() {
		return memo;
	}
}
