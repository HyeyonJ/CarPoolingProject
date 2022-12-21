package project.carPooling.driver.domain;

import java.security.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarPoolRegistration {
	private Integer dr_idx;
	private Integer d_idx;
	private Timestamp d_date;
	private String d_starttime;
	private String d_endtime;
	private String d_startpoint;
	private String d_endpoint;
	private String d_commute;
	private int	 d_fee;
	private int d_distance;
	private String d_lonlat;
}
