package hcl.hackthon.interview.error;

public class AppException extends Exception{

	private int code;
	private String message;
	public AppException(int code, String message) {
		
		this.code = code;
		this.message = message;
	}
	public AppException(String message) {
		
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public AppException() {
		
	}
	@Override
	public String toString() {
		return "AppException [code=" + code + ", message=" + message + "]";
	}
	
	
	
	
	
}
