package com.example.api.filter;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.http.server.reactive.ServerHttpRequest;
import java.util.function.Predicate;

@Component
public class Validator {
	
	 private final AntPathMatcher antPathMatcher = new AntPathMatcher();

	    public static  final List<String> endpoints = List.of(
	            "/register-user",
	            "/generate-token",
	            "/validate-token/{token}"
	    );

	    public Predicate<ServerHttpRequest> predicate = serverHttpRequest -> {
	        String requestPath = serverHttpRequest.getURI().getPath();
	        return endpoints.stream()
	                .noneMatch(uri -> antPathMatcher.match(uri, requestPath));
	    };

}
