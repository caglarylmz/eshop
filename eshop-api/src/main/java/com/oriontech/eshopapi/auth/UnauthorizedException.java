package com.oriontech.eshopapi.auth;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	protected static MessageSourceAccessor msg = SpringSecurityMessageSource.getAccessor();

	public UnauthorizedException() {
		super(msg.getMessage("AbstractAccessDecisionManager.accessDenied","Access is denied"));
	}
	
	public UnauthorizedException(String msg) {
		super(msg);
	}
}
