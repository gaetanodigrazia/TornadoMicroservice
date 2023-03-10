package com.tornado.init;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.std.ArraySerializerBase;
import com.github.javafaker.Faker;
import com.tornado.custom.StringUtils;
import com.tornado.helper.file.Serialize;
import com.tornado.models.Documentation;
import com.tornado.models.enumerations.ComponentType;

@Component
public class InitApi {
	private static final Logger logger = LoggerFactory.getLogger(InitApi.class);

	@Autowired
	Serialize serialize;
	private String stringRootPackage;
	private Package rootPackage;

	public InitApi() {

	}

	public void initCrud(Class<?> beanClass, Documentation documentationConfig) throws IOException {
		this.initService(beanClass);
		this.initServiceImpl(beanClass);
		this.initController(beanClass);
		this.initControllerAdvice(beanClass);
		this.initRepository(beanClass);
		this.initExceptions(beanClass);
		this.initDocumentation(beanClass, documentationConfig);
	}

	public void initController(Class<?> beanClass) throws IOException {
		this.printController(this.createPackage(ComponentType.Controller), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.Controller);
	}

	public void initControllerAdvice(Class<?> beanClass) throws IOException {
		this.printControllerAdvice(this.createPackage(ComponentType.ControllerAdvice), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.ControllerAdvice);
	}

	public void initRepository(Class<?> beanClass) throws IOException {
		this.printRepositoryClass(this.createPackage(ComponentType.Repository), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.Repository);
	}

	public void initExceptions(Class<?> beanClass) throws IOException {
		this.printExceptionClass(this.createPackage(ComponentType.Exceptions), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.Exceptions);

	}

	public Object[] initDatabase(String rootPackage, Class<?> beanClass) throws IOException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Faker faker = new Faker();
		Class<?> clazz = Class.forName(rootPackage + ".beans." + beanClass.getSimpleName());
		Constructor<?>[] constructors = clazz.getConstructors();
		List<Class<?>> paramsType = Arrays.asList(constructors[0].getParameterTypes());
		Object[] params = new Object[paramsType.size()];
		for (int i = 0; i < paramsType.size(); i++) {
			switch (paramsType.get(i).getSimpleName()) {
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



	public void initService(Class<?> beanClass) throws IOException {
		this.printService(this.createPackage(ComponentType.Service), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.Service, beanClass);
	}

	public void initServiceImpl(Class<?> beanClass) throws IOException {
		this.printServiceImpl(this.createPackage(ComponentType.ServiceImpl), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.ServiceImpl, beanClass);
	}

	public void initDocumentation(Class<?> beanClass, Documentation documentationConfig) throws IOException {
		this.printDocumentation(this.createPackage(ComponentType.Configuration), beanClass.getSimpleName(), beanClass,
				documentationConfig);
	}

	public void printControllerAdvice(String packageName, String beanName, String className) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		toWrite.add(getLicence());
		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");
		toWrite.add("import org.springframework.http.HttpStatus;");
		toWrite.add("import org.springframework.web.bind.annotation.ControllerAdvice;");
		toWrite.add("import org.springframework.web.bind.annotation.ExceptionHandler;");
		toWrite.add("import org.springframework.web.bind.annotation.ResponseBody;");
		toWrite.add("import org.springframework.web.bind.annotation.ResponseStatus;");
		toWrite.add("import "
				+ packageName.replace("src/main/java/", "").replace("/", ".").replace("controlleradvice", "exceptions")
				+ "." + beanName + "NotFoundException;");
		toWrite.add("");
		toWrite.add("@ControllerAdvice");
		toWrite.add("class " + beanName + "NotFoundAdvice {");
		toWrite.add("");
		toWrite.add("@ResponseBody");
		toWrite.add("@ExceptionHandler(" + beanName + "NotFoundException.class)");
		toWrite.add("@ResponseStatus(HttpStatus.NOT_FOUND)");
		toWrite.add("String " + beanName.toLowerCase() + "NotFoundHandler(" + beanName + "NotFoundException ex) {");
		toWrite.add("return ex.getMessage();");
		toWrite.add("}");
		toWrite.add("}");
		serialize.appendStringFromList(packageName, className, toWrite);
	}

	public void printController(String packageName, String beanName, String className) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		char quotes = '"';
		toWrite.add(getLicence());

		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");
		toWrite.add("import java.util.List;");
		toWrite.add("import io.swagger.v3.oas.annotations.Operation;");
		toWrite.add("import io.swagger.v3.oas.annotations.Parameter;");
		toWrite.add("import io.swagger.v3.oas.annotations.enums.ParameterIn;");
		toWrite.add("import org.springframework.beans.factory.annotation.Autowired;");
		toWrite.add("import org.springframework.web.bind.annotation.CrossOrigin;");
		toWrite.add("import org.springframework.web.bind.annotation.DeleteMapping;");
		toWrite.add("import org.springframework.web.bind.annotation.GetMapping;");
		toWrite.add("import org.springframework.web.bind.annotation.PathVariable;");
		toWrite.add("import org.springframework.web.bind.annotation.PostMapping;");
		toWrite.add("import org.springframework.web.bind.annotation.PutMapping;");
		toWrite.add("import org.springframework.web.bind.annotation.RequestBody;");
		toWrite.add("import org.springframework.web.bind.annotation.RequestMapping;");
		toWrite.add("import org.springframework.web.bind.annotation.RequestParam;");

		toWrite.add("import org.springframework.web.bind.annotation.RestController;");
		toWrite.add(
				"import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("controller", "beans")
						+ "." + beanName + ";");
		toWrite.add(
				"import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("controller", "service")
						+ "." + beanName + "Service;");
		toWrite.add("import "
				+ packageName.replace("src/main/java/", "").replace("/", ".").replace("controller", "repository") + "."
				+ beanName + "Repository;");
		toWrite.add("import "
				+ packageName.replace("src/main/java/", "").replace("/", ".").replace("controller", "exceptions") + "."
				+ beanName + "NotFoundException;");

		toWrite.add("@CrossOrigin(origins = \"*\")");
		toWrite.add("@RestController");
		toWrite.add("@RequestMapping(value = \"/" + beanName.toLowerCase() + "\")");
		toWrite.add("public class " + beanName + "RestController {");
		toWrite.add("@Autowired");
		toWrite.add("private " + beanName + "Service " + beanName.toLowerCase() + "Service;");
		toWrite.add("@Autowired");
		toWrite.add("private " + beanName + "Repository repository;");
		toWrite.add(beanName + "RestController() {");
		toWrite.add("}");

		toWrite.add("@GetMapping");
		toWrite.add("List<" + beanName + "> all() {");
		toWrite.add("return repository.findAll();");
		toWrite.add("}");

		toWrite.add("@PostMapping");
		toWrite.add(beanName + " newEmployee(@RequestBody " + beanName + " new" + beanName + ") {");
		toWrite.add("return (" + beanName + ") repository.save(new" + beanName + ");");
		toWrite.add("}");

		toWrite.add("@Operation(operationId = " + quotes + "findAll" + quotes + ", description = " + quotes
				+ "Retrieve a list of " + quotes + ")");
		toWrite.add("@Parameter(required = true, name = " + quotes + "id" + quotes + ", description = " + quotes
				+ "The id of the element to get" + quotes + ", in = ParameterIn.PATH)");
		toWrite.add("@GetMapping(" + quotes + "/{id}" + quotes + ")");
		toWrite.add(beanName + " get" + beanName + "(@PathVariable Long id) throws Throwable {");

		toWrite.add("return (" + beanName + ") repository.findById(id)");
		toWrite.add(".orElseThrow(() -> new " + beanName + "NotFoundException(id));");
		toWrite.add("}");

		toWrite.add("@PutMapping(" + quotes + "/{id}" + quotes + ")");
		toWrite.add(beanName + " replace" + beanName + "(@RequestBody " + beanName + " new" + beanName
				+ ", @PathVariable Long id) {");

		toWrite.add("return (" + beanName + ") repository.findById(id)");
		toWrite.add(".map(" + beanName.toLowerCase() + " -> {");

		toWrite.add("return repository.save(" + beanName.toLowerCase() + ");");
		toWrite.add("})");
		toWrite.add(".orElseGet(() -> {");
		toWrite.add("new" + beanName + ".setId(id);");
		toWrite.add("return repository.save(new" + beanName + ");");
		toWrite.add("});");
		toWrite.add("}");

		toWrite.add("@DeleteMapping(" + quotes + "/{id}" + quotes + ")");
		toWrite.add("void delete" + beanName + "(@PathVariable Long id) {");
		toWrite.add("repository.deleteById(id);");
		toWrite.add("}");

		toWrite.add("\n");
		toWrite.add("\n");
		toWrite.add("}");
		serialize.appendStringFromList(packageName, className.replace("Controller", "RestController"), toWrite);
	}

	public void printRepositoryClass(String packageName, String beanName, String className) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		toWrite.add(getLicence());

		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");
		toWrite.add("import org.springframework.data.jpa.repository.JpaRepository;");
		toWrite.add("import org.springframework.stereotype.Repository;");
		toWrite.add(
				"import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("repository", "beans")
						+ "." + beanName + ";");
		toWrite.add("@Repository");
		toWrite.add("public interface " + beanName + "Repository<T> extends JpaRepository<" + beanName + ", T> {");
		toWrite.add("");
		toWrite.add("}");
		serialize.appendStringFromList(packageName, className, toWrite);
	}

	public void printExceptionClass(String packageName, String beanName, String className) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		toWrite.add(getLicence());

		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");
		toWrite.add("public class " + beanName + "NotFoundException extends RuntimeException { \n");
		toWrite.add("public " + beanName + "NotFoundException(Long id) {\n");
		toWrite.add("super(\"Could not find " + beanName.toLowerCase() + " \" + id);\n");
		toWrite.add("}\n");
		toWrite.add("}\n");
		serialize.appendStringFromList(packageName, beanName + "NotFoundException", toWrite);
	}

	public void printDatabase(String packageName, String beanName, String className) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		toWrite.add(getLicence());

		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");
		toWrite.add("import org.slf4j.Logger;");
		toWrite.add("import org.slf4j.LoggerFactory;");
		toWrite.add("import org.springframework.boot.CommandLineRunner;");
		toWrite.add("import org.springframework.context.annotation.Bean;");
		toWrite.add("import org.springframework.context.annotation.Configuration;");
		toWrite.add("");
		toWrite.add("");
		toWrite.add("@Configuration");
		toWrite.add("class LoadDatabase {");
		toWrite.add("");
		toWrite.add("");
		toWrite.add("private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);");
		toWrite.add("");
		toWrite.add("@Bean");
		toWrite.add("CommandLineRunner initDatabase(EmployeeRepository repository) {");
		toWrite.add("");
		toWrite.add("");
		toWrite.add("return args -> {");
		toWrite.add("log.info(\"Preloading \" + repository.save(new Employee(\"Bilbo Baggins\", \"burglar\")));");
		toWrite.add("log.info(\"Preloading \" + repository.save(new Employee(\"Frodo Baggins\", \"thief\")));");
		toWrite.add("};");
		toWrite.add("}");
		serialize.appendStringFromList(packageName, className, toWrite);
	}

	public void printService(String packageName, String beanName, String className, Class<?> clazz) throws IOException {
		Field[] allFields;
		allFields = printAllFields(clazz);
		List<String> typesList = Arrays.asList(allFields).stream().map(x -> x.getType().getSimpleName())
				.collect(Collectors.toList());
		List<String> toWrite = new ArrayList<String>();
		toWrite.add(getLicence());
		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");
		if (typesList.stream().anyMatch(value -> value.equalsIgnoreCase("LocalDate"))) {
			toWrite.add("import java.time.LocalDate;");
		}
		toWrite.add("import java.util.List;");
		if (typesList.stream().anyMatch(value -> value.equalsIgnoreCase("Date"))) {
			toWrite.add("import java.util.Date;");
		}
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("service", "beans")
				+ "." + beanName + ";");
		toWrite.add("		/**\n" + "* The Interface " + beanName + "Service.\n" + "*/\n");
		toWrite.add("public interface " + beanName + "Service<T> {");
		toWrite.add("public void fillDatabase();");
		toWrite.add("public T create(" + beanName + " element);\n");
		toWrite.add("public List<" + beanName + "> getAll();\n");

		for (int i = 0; i < allFields.length; i++) {
			if (allFields[i].getName().equals("serialVersionUID")
					|| allFields[i].getType().getName().equals("Logger")) {
				continue;
			}
			toWrite.add("public List<" + beanName + "> getBy" + StringUtils.capitalize(allFields[i].getName()) + "("
					+ allFields[i].getType().getSimpleName() + " " + allFields[i].getName() + ");\n");
		}
		toWrite.add("}");
		serialize.appendStringFromList(packageName, className, toWrite);
	}

	public void printServiceImpl(String packageName, String beanName, String className, Class<?> clazz)
			throws IOException {
		char quotes = '"';

		Field[] allFields;
		allFields = printAllFields(clazz);
		List<String> typesList = Arrays.asList(allFields).stream().map(x -> x.getType().getSimpleName())
				.collect(Collectors.toList());
		List<String> toWrite = new ArrayList<String>();
		toWrite.add(getLicence());

		toWrite.add("package "
				+ packageName.replace("src/main/java/", "").replace("/", ".").replace("serviceimpl", "service.impl")
				+ ";\n");
		if (typesList.stream().anyMatch(value -> value.equalsIgnoreCase("LocalDate"))) {
			toWrite.add("import java.time.LocalDate;");
		}
		toWrite.add("import java.util.List;");
		if (typesList.stream().anyMatch(value -> value.equalsIgnoreCase("Date"))) {
			toWrite.add("import java.util.Date;");
		}
		toWrite.add("import java.lang.reflect.InvocationTargetException;\r\n");
		toWrite.add("import com.tornado.models.Database;\r\n");
		toWrite.add("import java.util.stream.Collectors;");
		toWrite.add("import org.slf4j.Logger;");
		toWrite.add("import org.slf4j.LoggerFactory;");
		toWrite.add("import org.springframework.beans.factory.annotation.Autowired;");
		toWrite.add("import org.springframework.stereotype.Service;");
		toWrite.add("import org.slf4j.Logger;");
		toWrite.add(
				"import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("service.impl", "beans")
						+ "." + beanName + ";");
		toWrite.add("import "
				+ packageName.replace("src/main/java/", "").replace("/", ".").replace("service.impl", "repository")
				+ "." + beanName + "Repository;");
		toWrite.add("import "
				+ packageName.replace("src/main/java/", "").replace("/", ".").replace("service.impl", "service") + "."
				+ beanName + "Service;");
		toWrite.add("/**");
		toWrite.add("* The Class " + beanName + "ServiceImpl.");
		toWrite.add("*/");

		toWrite.add("@Service");
		toWrite.add("public class " + beanName + "ServiceImpl implements " + beanName + "Service<" + beanName + "> {");
		toWrite.add(
				"	private static final Logger logger = LoggerFactory.getLogger(" + beanName + "ServiceImpl.class);");
		toWrite.add("	@Autowired");
		toWrite.add("	" + beanName + "Repository<" + beanName + "> " + beanName.toLowerCase() + "Repo;");
		toWrite.add("	");
		toWrite.add("	public " + beanName + "ServiceImpl() {}");
		toWrite.add("	public " + beanName + " create(" + beanName + " element) {");
		toWrite.add("		logger.info(\"-------------- Added " + beanName.toLowerCase()
				+ " ------------------\", element.toString());");
		toWrite.add("		return (" + beanName + ") " + beanName.toLowerCase() + "Repo.save(element);");
		toWrite.add("	}");
		toWrite.add("	public " + beanName + " update(" + beanName + " data, String id) {");
		toWrite.add("		return (" + beanName + ") " + beanName.toLowerCase() + "Repo.save(data);");
		toWrite.add("	}");
		toWrite.add("	@Override");
		toWrite.add("	public List<" + beanName + "> getAll() {");
		toWrite.add("		return " + beanName.toLowerCase() + "Repo.findAll();");
		toWrite.add("	}");

		for (int i = 0; i < allFields.length; i++) {
			if (allFields[i].getName().equals("serialVersionUID")) {
				continue;
			}
			if (allFields[i].getType().getName().equals("Logger")) {
				continue;
			}
			toWrite.add("@Override");
			toWrite.add("public List<" + beanName + "> getBy" + StringUtils.capitalize(allFields[i].getName()) + "("
					+ allFields[i].getType().getSimpleName() + " " + allFields[i].getName() + "){\n");
			switch (allFields[i].getType().getSimpleName()) {
			case "String":
			case "Long":
				toWrite.add("return " + beanName.toLowerCase() + "Repo.findAll().stream().filter(x -> x.get"
						+ StringUtils.capitalize(allFields[i].getName()) + "().equals(" + allFields[i].getName()
						+ ")).collect(Collectors.toList());");
				break;
			default:
				toWrite.add("return " + beanName.toLowerCase() + "Repo.findAll().stream().filter(x -> x.get"
						+ StringUtils.capitalize(allFields[i].getName()) + "() == " + allFields[i].getName()
						+ ").collect(Collectors.toList());");
				break;
			}
			toWrite.add("}");
		}
		toWrite.add("");
		toWrite.add("");

		toWrite.add("@Override");
		toWrite.add("public void fillDatabase(){");
		toWrite.add("Database database = new Database();");
		toWrite.add("for (int i = 0; i < 10; i++) {");
		toWrite.add("Object[] params;");
		toWrite.add("try {");
		toWrite.add("params = database.fillDatabaseForClass(" + quotes + this.stringRootPackage + quotes + ", "
				+ beanName + ".class);");
		toWrite.add(beanName + " " + beanName.toLowerCase() + " = (" + beanName + ")" + beanName
				+ ".class.getConstructors()[0].newInstance(params);");
		toWrite.add("this.create(" + beanName.toLowerCase() + ");");
		toWrite.add("} catch (ClassNotFoundException e) {");
		toWrite.add("// TODO Auto-generated catch block");
		toWrite.add("e.printStackTrace();");
		toWrite.add("} catch (InstantiationException e) {");
		toWrite.add("// TODO Auto-generated catch block");
		toWrite.add("e.printStackTrace();");
		toWrite.add("} catch (IllegalAccessException e) {");
		toWrite.add("// TODO Auto-generated catch block");
		toWrite.add("e.printStackTrace();");
		toWrite.add("} catch (IllegalArgumentException e) {");
		toWrite.add("// TODO Auto-generated catch block");
		toWrite.add("e.printStackTrace();");
		toWrite.add("} catch (InvocationTargetException e) {");
		toWrite.add("// TODO Auto-generated catch block");
		toWrite.add("e.printStackTrace();");
		toWrite.add("} catch (SecurityException e) {");
		toWrite.add("// TODO Auto-generated catch block");
		toWrite.add("e.printStackTrace();");
		toWrite.add("}");
		toWrite.add("}");
		toWrite.add("}");
		toWrite.add("");
		toWrite.add("");
		toWrite.add("}");

		serialize.appendStringFromList(packageName, className, toWrite);
	}


	public void printDocumentation(String packageName, String beanName, Class<?> clazz, Documentation documentation)
			throws IOException {
		List<String> toWrite = new ArrayList<String>();
		char quotes = '"';
		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");

		toWrite.add(getLicence());
		toWrite.add("import java.util.Arrays;");
		toWrite.add("import io.swagger.v3.oas.models.OpenAPI;");
		toWrite.add("import io.swagger.v3.oas.models.info.Contact;");
		toWrite.add("import io.swagger.v3.oas.models.info.Info;");
		toWrite.add("import io.swagger.v3.oas.models.servers.Server;");
		toWrite.add("import org.springframework.context.annotation.Bean;");
		toWrite.add("import org.springframework.context.annotation.Configuration;");
		toWrite.add("\r\n");
		toWrite.add("@Configuration");
		toWrite.add("public class SwaggerConfig {");

		toWrite.add("@Bean");
		toWrite.add("public OpenAPI openAPI() {");
		toWrite.add("Server localServer = new Server();");
		toWrite.add("localServer.setDescription(" + quotes + documentation.getServerDescription() + quotes + ");");
		toWrite.add("localServer.setUrl(" + quotes + documentation.getServerUrl() + quotes + ");");
//
//													toWrite.add("Server testServer = new Server();
//															toWrite.add("testServer.setDescription("test");
//																	toWrite.add("testServer.setUrl("https://example.org");

		toWrite.add("OpenAPI openAPI = new OpenAPI();");
		toWrite.add("openAPI.info(new Info()");
		toWrite.add(".title(" + quotes + documentation.getTitle() + quotes + ")");
		toWrite.add(".description(");
		toWrite.add(quotes + documentation.getDescription() + quotes + ")");
		toWrite.add(".version(" + quotes + documentation.getVersion() + quotes + ")");
		toWrite.add(".contact(new Contact().name(" + quotes + documentation.getContactName() + quotes + ").");
		toWrite.add("url(" + quotes + documentation.getContactEmail() + quotes + ").email(" + quotes
				+ documentation.getContactUrl() + quotes + ")));");
		toWrite.add("openAPI.setServers(Arrays.asList(localServer));");
		toWrite.add("\r\n");
		toWrite.add("return openAPI;");
		toWrite.add("}");

		toWrite.add("}");
		serialize.appendStringFromList(packageName, "SwaggerConfig", toWrite);

	}

	public String createPackage(ComponentType componentType) {
		String packageName = "";
		if (componentType == ComponentType.ServiceImpl) {
			packageName = "src/main/java/" + this.getStringRootPackage().replace('.', '/') + "/"
					+ componentType.toString().toLowerCase().replace("serviceimpl", "service/impl");
			System.out.println("PACKAGENAME " + packageName);
		} else {
			packageName = "src/main/java/" + this.getStringRootPackage().replace('.', '/') + "/"
					+ componentType.toString().toLowerCase();
		}

		File theDir = new File(packageName);
		if (!theDir.exists()) {
			theDir.mkdirs();
		} else {
			System.out.println("Package " + packageName + " already exists!");
		}
		return packageName;
	}

	public String getPackageName(ApplicationRunner runner) {
		return runner.getClass().getPackage().getName();
	}

	public Package getPackage(ApplicationRunner runner) {
		return runner.getClass().getPackage();
	}

	/**
	 * @return the stringRootPackage
	 */
	public String getStringRootPackage() {

		return stringRootPackage;
	}

	/**
	 * @param stringRootPackage the stringRootPackage to set
	 */
	public void setStringRootPackage(String stringRootPackage) {
		this.stringRootPackage = stringRootPackage;
	}

	/**
	 * @return the rootPackage
	 */
	public Package getRootPackage() {
		return rootPackage;
	}

	/**
	 * @param rootPackage the rootPackage to set
	 */
	public void setRootPackage(Package rootPackage) {
		this.rootPackage = rootPackage;
	}

	public String getLicence() throws IOException {
		return "/**\r\n" + "* Auto-generated Bean class using Tornado Library\r\n" + "*\r\n" + "*\r\n"
				+ "* @author  Gaetano Di Grazia\r\n" + "* @version 1.0\r\n" + "* @since   05-08-2021\r\n" + "*\r\n"
				+ "* \r\n" + "*/";
	}

	public Field[] printAllFields(Class<?> obj) {
		Field[] allFields = obj.getDeclaredFields();
//		for (int i = 0; i < allFields.length; i++) {
//			System.out.println(allFields[i].getType().getSimpleName() + " " + allFields[i].getName());
//		}
		return allFields;
	}
}
