package com.oriontech.eshopapi.payloads.res;

import lombok.Data;

@Data
public class ApiResponse {
	private final Boolean success;
	private final String message;
}
