package edu.du.airline_project.handler.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {
	private int statusCode;
	private String code;
	private String message;
	private String resultCode;
	private List<ExceptionFieldMessage> exceptionFieldMessages;
}