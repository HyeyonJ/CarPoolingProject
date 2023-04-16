package project.carPooling.passenger.domain;

import lombok.Data;

@Data
public class PReview {
	public Integer rIdx;
	public Integer fromIdx;
	public Integer toIdx;
	public int starPoint;
	public String nickname;
	public String content;
}
