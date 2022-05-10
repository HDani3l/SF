package hu.ps.sf;

public class PsResponseHandler extends AsyncResponseHandler {
	
	private SuccessHandler successHandler;
	private ErrorHandler errorHandler;
	
	public PsResponseHandler setSuccessHandler(SuccessHandler successHandler) {
		this.successHandler = successHandler;
		return this;
	}
	
	public PsResponseHandler setErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
		return this;
	}
	
	@Override
	protected void onSuccess(String response) {
		super.onSuccess(response);
		
		if (successHandler != null) {
			successHandler.onSuccess(response);
		}
	}
	
	@Override
	protected void onError() {
		super.onError();
		
		if (errorHandler != null) {
			errorHandler.onError();
		}
	}
	
	public interface SuccessHandler {
		void onSuccess(String response);
	}
	
	public interface ErrorHandler {
		void onError();
	}
}
