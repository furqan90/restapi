package restful.utils;

public class ErrorMessage 
{
	private String error;
	
	public ErrorMessage() {}
	public ErrorMessage(String msg)
	{
		this.error = msg;
	}

	String getError() {
		return error;
	}

	void setError(String error) {
		this.error = error;
	}
}
