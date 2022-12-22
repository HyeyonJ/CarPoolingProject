package project.carPooling.passenger.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PReservation {
	private Integer prIdx;
	private String pDate;
	private String pCommute;
	private String pBoardingTime;
	private String pStartPoint;
	private String pEndPoint;
	private String pStartlon;
	private String pStartlat;
	private String pEndlon;
	private String pEndlat;
	private String pConfirm;
}
