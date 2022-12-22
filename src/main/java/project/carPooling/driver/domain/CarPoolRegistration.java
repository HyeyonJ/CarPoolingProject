package project.carPooling.driver.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarPoolRegistration {
	private Integer dr_idx;
	private Integer d_idx;
	private String d_date;
	private String d_commute;
	private String d_startTime;
	private String d_endTime;
	private String d_startPoint;
	private String d_endPoint;
	private int	 d_fee;
	private int d_distance;
	private String d_startlon;
	private String d_startlat;
	private String d_endlon;
	private String d_endlat;
}
