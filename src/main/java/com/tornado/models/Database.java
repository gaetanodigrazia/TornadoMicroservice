package com.tornado.models;

import java.lang.reflect.Constructor;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.github.javafaker.Faker;

public class Database {
	
	public Database() {
		
	}
	
	public Object[] fillDatabaseForClass(String rootPackage, Class<?> beanClass) throws ClassNotFoundException {
		Faker faker = new Faker();
	    Class<?> clazz = Class.forName(rootPackage+".beans."+beanClass.getSimpleName());
		Constructor<?>[] constructors = clazz.getConstructors();
		List<Class<?>> paramsType = Arrays.asList(constructors[0].getParameterTypes());
		Object[] params = new Object[paramsType.size()];
		for(int i = 0; i < paramsType.size(); i++) {
			switch(paramsType.get(i).getSimpleName()) {
			case "String":
				params[i] = faker.regexify("[a-z1-9]{10}");
				break;
			case "Integer":
			case "int":
			case "Int":
				params[i] = Integer.valueOf(faker.address().buildingNumber());
				break;
			case "Long":
				params[i] = Long.valueOf(faker.address().buildingNumber());
				break;
			case "Float":
			case "float":
				params[i] = Float.valueOf(faker.address().buildingNumber());
				break;
			case "Double":
			case "double":
				params[i] = Double.valueOf(faker.address().buildingNumber());
				break;
			case "Date":
				params[i] = new Date(System.currentTimeMillis());
				break;
			case "LocalDate":
				params[i] = LocalDate.now();
				break;
			case "Boolean":
			case "boolean":
				params[i] = Integer.valueOf(faker.address().buildingNumber()) % 2 == 0 ? true : false;
				break;
			}
		}
		// SHOULD CALL THE SERVICE TO INSERT THE OBJECT
//		Object element = constructors[0].newInstance(params);
		return params;
	}

}
