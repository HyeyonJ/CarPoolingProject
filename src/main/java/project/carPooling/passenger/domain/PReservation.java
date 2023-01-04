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
	private String pStartLon;
	private String pStartLat;
	private String pEndLon;
	private String pEndLat;
	private String pConfirm;
}
