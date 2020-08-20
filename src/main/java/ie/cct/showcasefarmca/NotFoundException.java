package ie.cct.showcasefarmca;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//CA - Cloud Computing 
//Student: Yuri Andrade 
//Student number: 2019154
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
//This class is to demonstrate alternative to external class when there is an error type 404 or NOT FOUND! 
	/**
	 * 
	 */
	private static final long serialVersionUID = -2729975717322939907L;
	
	public NotFoundException(String msg) {
		super(msg);
	}

}
