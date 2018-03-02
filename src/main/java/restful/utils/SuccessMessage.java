package restful.utils;

public class SuccessMessage {
	private String success;

	public SuccessMessage() {}
	public SuccessMessage(String msg)
	{
		this.success = msg;
	}
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

}
