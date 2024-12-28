package com.ashfaq.example.simple_maven_project;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import io.fission.Context;
import io.fission.Function;

public class HelloFunction implements Function {

//	@Override
//	public ResponseEntity call(RequestEntity req, Context context) {
//		String name = context.getQueryParam("name").orElse("World");
//		String message = String.format("Hello, %s!", name);
//
//		return Response.builder().body(message).status(200).header("Content-Type", "text/plain").build();
//	}

	@Override
	public ResponseEntity<?> call(RequestEntity req, Context context) {
		return ResponseEntity.ok("Hello World!");
	}

}