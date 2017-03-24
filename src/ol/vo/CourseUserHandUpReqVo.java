package ol.vo;

public class CourseUserHandUpReqVo {
	private Integer courseId;
	private Integer userId;
	private boolean isNoHandUp;
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public boolean isNoHandUp() {
		return isNoHandUp;
	}
	public void setNoHandUp(boolean isNoHandUp) {
		this.isNoHandUp = isNoHandUp;
	}
}
