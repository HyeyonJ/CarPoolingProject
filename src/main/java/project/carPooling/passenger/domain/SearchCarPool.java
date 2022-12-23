package project.carPooling.passenger.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchCarPool {
	private String pDate;
	private String pCommute;
	private String pBoardingTime;
	private String pStartlon;
	private String pStartlat;
	private String pEndlon;
	private String pEndlat;
}
