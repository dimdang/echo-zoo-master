package org.cool.zoo.util;

import java.util.ArrayList;
import java.util.List;

public final class ResponseFactory {

	public static <T> JResponseEntity<T> build() {
		return new JResponseEntity();
	}

	public static <T> JResponseEntity<T> build(List<T> data){
		JResponseEntity<T> JResponseEntity =build();
		try{
			
			if(data!=null&&!data.isEmpty()){
				JResponseEntity.setMessage(Message.SUCCESSED.getText());
				JResponseEntity.setCode(StatusMessage.OK.getStatusCode());
				JResponseEntity.setData(data);
			}else{
				JResponseEntity.setMessage(Message.NO_DATA.getText());
				JResponseEntity.setCode(StatusMessage.OK.getStatusCode());
				JResponseEntity.setData(new ArrayList<T>());
			}
			
		}catch(Exception e){
				JResponseEntity.setMessage(Message.INTERNAL_SERVER_ERROR.getText());
				JResponseEntity.setCode(StatusMessage.INTERNAL_SERVER_ERROR.getStatusCode());
				JResponseEntity.setData(new ArrayList<T>());
				e.printStackTrace();
		}
		return JResponseEntity;
	}


	public static <T> JResponseEntity<T> created(List<T> data){
		JResponseEntity<T> JResponseEntity =build();
		JResponseEntity.setMessage(Message.CREATED.getText());
		JResponseEntity.setCode(StatusMessage.CREATED.getStatusCode());
		JResponseEntity.setData(data);
		return JResponseEntity;
	}
	
	public static <T> JResponseEntity<T> fail(){
		JResponseEntity<T> JResponseEntity =build();
		JResponseEntity.setMessage(Message.INTERNAL_SERVER_ERROR.getText());
		JResponseEntity.setCode(StatusMessage.INTERNAL_SERVER_ERROR.getStatusCode());
		JResponseEntity.setData(new ArrayList<T>());
		return JResponseEntity;
	}

	public static <T> JResponseEntity<T> build(String message, Integer code, T body) {
		JResponseEntity<T> responseEntity = build();
		responseEntity.setMessage(message);
		responseEntity.setCode(code);
		responseEntity.addBody(body);
		return responseEntity;
	}

}
