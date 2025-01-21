package com.training.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.training.resource.ProductResource.Status;

@Provider
public class ProductAccessExceptionMapper implements ExceptionMapper<ProductAccessException> {

	@Override
	public Response toResponse(ProductAccessException e) {
		return  Response
				.status(400)
				.entity(new Status(123, e.getMessage()))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	public static class Status {
		private int errorCode;
		private String messageIfAny;
		
		public Status() {
		}
		public Status(int errorCode, String messageIfAny) {
			this.errorCode = errorCode;
			this.messageIfAny = messageIfAny;
		}
		public int getErrorCode() {
			return errorCode;
		}
		public void setErrorCode(int errorCode) {
			this.errorCode = errorCode;
		}
		public String getMessageIfAny() {
			return messageIfAny;
		}
		public void setMessageIfAny(String messageIfAny) {
			this.messageIfAny = messageIfAny;
		}
	}

}
