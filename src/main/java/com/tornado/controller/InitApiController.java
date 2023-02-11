package com.tornado.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tornado.init.InitApi;
import com.tornado.models.ControllerAdvice;
import com.tornado.models.Microservice;
import com.tornado.models.TestClass;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/generate")
public class InitApiController {
	  private static final Logger logger = LoggerFactory.getLogger(InitApiController.class);

	@PostMapping(value = "/controller")
	public List<String> generateController(@RequestBody HashMap<String, Class<?>> beanClass) throws IOException {
//		InitApi initApi = new InitApi();
//		Field[] allFields = beanClass.getDeclaredFields();
//		for(int i = 0; i < allFields.length; i++)
//			System.out.println(allFields[i].getName());
//		
//		System.out.println(beanClass.getDeclaredFields().toString());
//
//		System.out.println(beanClass.toString());
//		System.out.println(beanClass.getPackageName());
//
//		System.out.println(beanClass.toGenericString());
//		initApi.setStringRootPackage(initApi.getPackageName(beanClass));
//		initApi.setRootPackage(initApi.getPackage(beanClass));
//		return initApi.initControllerAPI(beanClass);
		System.out.println(beanClass.get("beanClass"));
		return null;
	}

	@GetMapping(value = "/controllerAdvice")
	public List<String> generateControllerAdvice(@RequestBody Class<?> beanClass) throws IOException {
		InitApi initApi = new InitApi();
		initApi.setStringRootPackage(initApi.getPackageName(beanClass));
		initApi.setRootPackage(initApi.getPackage(beanClass));
		return initApi.initControllerAdviceAPI(beanClass);
	}

	@GetMapping(value = "/service")
	public List<String> generateService() throws IOException {
		InitApi initApi = new InitApi();
		initApi.setStringRootPackage(initApi.getPackageName(InitApiController.class));
		initApi.setRootPackage(initApi.getPackage(InitApiController.class));
		return initApi.initServiceAPI(TestClass.class);
	}

	@GetMapping(value = "/serviceImpl")
	public List<String> generateServiceImpl() throws IOException {
		InitApi initApi = new InitApi();
		initApi.setStringRootPackage(initApi.getPackageName(InitApiController.class));
		initApi.setRootPackage(initApi.getPackage(InitApiController.class));
		return initApi.initServiceImplAPI(TestClass.class);
	}

	@GetMapping(value = "/filter")
	public List<String> generateFilter() throws IOException {
		InitApi initApi = new InitApi();
		initApi.setStringRootPackage(initApi.getPackageName(InitApiController.class));
		initApi.setRootPackage(initApi.getPackage(InitApiController.class));
		return initApi.initFilterAPI(TestClass.class);
	}

	@GetMapping(value = "/repository")
	public List<String> generateRepository() throws IOException {
		InitApi initApi = new InitApi();
		initApi.setStringRootPackage(initApi.getPackageName(InitApiController.class));
		initApi.setRootPackage(initApi.getPackage(InitApiController.class));
		return initApi.initRepositoryAPI(TestClass.class);
	}

	@GetMapping(value = "/exceptions")
	public List<String> generateExceptions() throws IOException {
		InitApi initApi = new InitApi();
		initApi.setStringRootPackage(initApi.getPackageName(InitApiController.class));
		initApi.setRootPackage(initApi.getPackage(InitApiController.class));
		return initApi.initExceptionsAPI(TestClass.class);
	}

	@PostMapping("/initialize")
	public void initializeCode(Microservice microservice) throws IOException {
		InitApi initApi = new InitApi();
		logger.info(" Test "  + microservice.builder().getControllerAdvice());
		HashMap<Class<?>, List<String>> result = new HashMap<>();
		if (microservice.builder().getControllerAdvice() != null) {
			initApi.initFilter(microservice.builder().getControllerAdvice().getClass());

			//result.put(ControllerAdvice.class, this.generateControllerAdvice(microservice.builder().getControllerAdvice().getClass()));
		}
		if (microservice.builder().getExceptions() != null) {
			initApi.initFilter(microservice.builder().getExceptions().getClass());

			//result.put(ControllerAdvice.class, this.generateControllerAdvice(microservice.builder().getControllerAdvice().getClass()));
		}
		if (microservice.builder().getFilter() != null) {
			initApi.initFilter(microservice.builder().getFilter().getClass());
			//result.put(ControllerAdvice.class, this.generateControllerAdvice(microservice.builder().getControllerAdvice().getClass()));
		}
		if (microservice.builder().getRepository() != null) {
			initApi.initFilter(microservice.builder().getRepository().getClass());

			//result.put(ControllerAdvice.class, this.generateControllerAdvice(microservice.builder().getControllerAdvice().getClass()));
		}
		if (microservice.builder().getRestController() != null) {
			initApi.initFilter(microservice.builder().getRestController().getClass());

			//result.put(ControllerAdvice.class, this.generateControllerAdvice(microservice.builder().getControllerAdvice().getClass()));
		}
		if (microservice.builder().getServiceImplementation() != null) {
			initApi.initFilter(microservice.builder().getServiceImplementation().getClass());

			//result.put(ControllerAdvice.class, this.generateControllerAdvice(microservice.builder().getControllerAdvice().getClass()));
		}
		if (microservice.builder().getServiceInterface() != null) {
			initApi.initFilter(microservice.builder().getServiceInterface().getClass());

			//result.put(ControllerAdvice.class, this.generateControllerAdvice(microservice.builder().getControllerAdvice().getClass()));
		}
	}

}
