package ol.vo;

public class CourseUserGagReqVo {
	private Integer courseId;
	private Integer userId;
	private boolean isGag;
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
	public boolean isGag() {
		return isGag;
	}
	public void setGag(boolean isGag) {
		this.isGag = isGag;
	}
}
