package project.carPooling.driver.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DRegistration {
	private Integer drIdx;
	private Integer dIdx;
	private String dDate;
	private String dHopeGender;
	private String dCommute;
	private String dStartTime;
	private String dEndTime;
	private String dStartPoint;
	private String dEndPoint;
	private int	dFee;
	private float dDistance;
	private int	dTime;
	private String dStartLon;
	private String dStartLat;
	private String dEndLon;
	private String dEndLat;
}
