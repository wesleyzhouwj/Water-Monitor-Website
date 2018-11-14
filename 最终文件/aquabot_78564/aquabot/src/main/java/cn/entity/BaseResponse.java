package cn.entity;

public class BaseResponse<T> {

	String code;
	String message;
	T data;
	
	static final String SUCCESS_CODE = "200";
	
	public static <T> BaseResponse<T> successWithData(String tcode,String tmess, T data){
		BaseResponse<T> response = new BaseResponse<T>();
		response.setData(data);
		response.setCode(tcode);
		response.setMessage(tmess);
		return response;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
