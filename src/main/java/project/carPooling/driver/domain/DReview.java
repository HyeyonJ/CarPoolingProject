package project.carPooling.driver.domain;

import lombok.Data;

@Data
public class DReview {
	public Integer rIdx;
	public Integer fromIdx;
	public Integer toIdx;
	public int starPoint;
	public String nickname;
	public String content;
}
