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
	
	public DUserType getDUserType (String memo) {
		if(memo.equals("일반")) {
			return DUserType.CARPOOLING;
		} else if(memo.equals("카카오")) {
			return DUserType.KAKAO;
		} else if(memo.equals("네이버")) {
			return DUserType.NAVER;
		} else if(memo.equals("구글")) {
			return DUserType.GOOGLE;
		} else {
			return null;
		}
	}
}
