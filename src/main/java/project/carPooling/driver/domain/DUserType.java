package project.carPooling.driver.domain;

public enum DUserType {
	 CARPOOLING("일반"), KAKAO("카카오"), NAVER("네이버"), GOOGLE("구글");
	//UserType은 CARPOOLING, KAKAO, NAVER, GOOGLE;
	//()안의 문자열은 memo
	
	private final String memo;
	
	DUserType(String memo) {
		this.memo = memo;
	}
	
	public String getMemo() {
		return memo;
	}
}
