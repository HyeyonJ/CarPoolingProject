package project.carPooling.passenger.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchCarPool {
	private String pDate;
	private String pCommute;
	private String pGender;
	private String pBoardingTime;
	private String pStartLon;
	private String pStartLat;
	private String pEndLon;
	private String pEndLat;
}
