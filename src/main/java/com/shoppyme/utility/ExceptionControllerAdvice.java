package com.shoppyme.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.shoppyme.exception.ShoppyMeException;

public class ExceptionControllerAdvice {

	private static final Log LOGGER = LogFactory.getLog(ExceptionControllerAdvice.class);
	
	@Autowired
	private Environment environment;
	
	public ResponseEntity<ErrorInfo> meetingSchedulerExceptionHandler(ShoppyMeException exception){
		LOGGER.error(exception.getMessage(), exception);
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}
	
}
