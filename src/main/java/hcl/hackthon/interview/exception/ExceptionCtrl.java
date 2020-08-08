package hcl.hackthon.interview.exception;

public class ExceptionCtrl extends Exception{

	private static final long serialVersionUID = 1L;
	private String message;
	
	public ExceptionCtrl(String message) {
		
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ExceptionCtrl() {
		
	}
	@Override
	public String toString() {
		return "AppException [message=" + message + "]";
	}
	
	
	
	
	
}
