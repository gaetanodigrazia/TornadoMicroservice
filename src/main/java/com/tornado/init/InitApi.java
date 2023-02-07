package com.tornado.init;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tornado.custom.StringUtils;
import com.tornado.helper.file.Serialize;
import com.tornado.models.enumerations.ComponentType;

@Component
public class InitApi {
	@Autowired
	Serialize serialize;
	private String stringRootPackage;
	private Package rootPackage;

	public InitApi() {

	}
	
	public void initCrud(Class<?> beanClass) throws IOException {
		this.initService(beanClass);
		this.initServiceImpl(beanClass);
		this.initFilter(beanClass);
		this.initController(beanClass);
		this.initControllerAdvice(beanClass);
		this.initRepository(beanClass);
		this.initExceptions(beanClass);
//		this.initDatabase(beanClass);
	}
	
	public void initController(Class<?> beanClass) throws IOException {
		this.printController(this.createPackage(ComponentType.Controller), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.Controller);
	}
	
	public List<String> initControllerAPI(Class<?> beanClass) throws IOException {
		return this.printControllerAPI(this.createPackage(ComponentType.Controller), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.Controller);
	}

	public void initControllerAdvice(Class<?> beanClass) throws IOException {
		this.printControllerAdvice(this.createPackage(ComponentType.ControllerAdvice), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.ControllerAdvice);
	}
	
	public List<String> initControllerAdviceAPI(Class<?> beanClass) throws IOException {
		return this.printControllerAdviceAPI(this.createPackage(ComponentType.ControllerAdvice), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.ControllerAdvice);
	}

	public void initRepository(Class<?> beanClass) throws IOException {
		this.printRepositoryClass(this.createPackage(ComponentType.Repository), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.Repository);
	}
	
	
	
	public List<String> initRepositoryAPI(Class<?> beanClass) throws IOException {
		return this.printRepositoryClassAPI(this.createPackage(ComponentType.Repository), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.Repository);
	}

	public void initExceptions(Class<?> beanClass) throws IOException {
		this.printExceptionClass(this.createPackage(ComponentType.Exceptions), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.Exceptions);

	}

	public List<String> initExceptionsAPI(Class<?> beanClass) throws IOException {
		return this.printExceptionClassAPI(this.createPackage(ComponentType.Exceptions), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.Exceptions);

	}

	public void initDatabase(Class<?> beanClass) throws IOException {
		this.printExceptionClass(this.createPackage(ComponentType.Database), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.Database);
	}
	
	public List<String> initDatabaseAPI(Class<?> beanClass) throws IOException {
		return this.printExceptionClassAPI(this.createPackage(ComponentType.Database), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.Database);
	}
	
	public void initFilter(Class<?> beanClass) throws IOException {
		this.printFilter(this.createPackage(ComponentType.Filter), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.Filter);
	}
	
	public List<String> initFilterAPI(Class<?> beanClass) throws IOException {
		return this.printFilterAPI(this.createPackage(ComponentType.Filter), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.Filter);
	}
	
	public void initService(Class<?> beanClass) throws IOException {
		this.printService(this.createPackage(ComponentType.Service), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.Service, beanClass);
	}
	
	public List<String> initServiceAPI(Class<?> beanClass) throws IOException {
		return this.printServiceAPI(this.createPackage(ComponentType.Service), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.Service, beanClass);
	}
	
	public void initServiceImpl(Class<?> beanClass) throws IOException {
		this.printServiceImpl(this.createPackage(ComponentType.ServiceImpl), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.ServiceImpl, beanClass);
	}

	public List<String> initServiceImplAPI(Class<?> beanClass) throws IOException {
		return this.printServiceImplAPI(this.createPackage(ComponentType.ServiceImpl), beanClass.getSimpleName(),
				beanClass.getSimpleName() + ComponentType.ServiceImpl, beanClass);
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
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("controlleradvice", "exceptions")
				+"."+beanName+"NotFoundException;");	
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

	public List<String> printControllerAdviceAPI(String packageName, String beanName, String className) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		toWrite.add(getLicence());
		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");
		toWrite.add("import org.springframework.http.HttpStatus;");
		toWrite.add("import org.springframework.web.bind.annotation.ControllerAdvice;");
		toWrite.add("import org.springframework.web.bind.annotation.ExceptionHandler;");
		toWrite.add("import org.springframework.web.bind.annotation.ResponseBody;");
		toWrite.add("import org.springframework.web.bind.annotation.ResponseStatus;");
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("controlleradvice", "exceptions")
				+"."+beanName+"NotFoundException;");	
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
		return toWrite;
	}

	public void printController(String packageName, String beanName, String className) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		char quotes = '"';
		toWrite.add(getLicence());

		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");
		toWrite.add("import java.util.List;");
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
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("controller", "beans")
				+"."+beanName+";");		
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("controller", "repository")
						+"."+beanName+"Repository;");
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("controller", "exceptions")
				+"."+beanName+"NotFoundException;");
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("controller", "filter")
				+"."+beanName+"Filter;");		

		toWrite.add("@CrossOrigin(origins = \"*\")");
				toWrite.add("@RestController");
						//toWrite.add("@RequestMapping(value = \"/" + beanName.toLowerCase()+"\")");
		toWrite.add("public class " + beanName + "RestController {");
		toWrite.add("@Autowired");
		toWrite.add("private " + beanName + "Repository repository;");
		toWrite.add("@Autowired");
		toWrite.add("private " + beanName + "Filter "+beanName.toLowerCase()+"Filter;");
		toWrite.add(beanName + "RestController() {");
		toWrite.add("}");

		toWrite.add("@GetMapping(" + quotes + "/" + beanName.toLowerCase() + "" + quotes + ")");
		toWrite.add("List<" + beanName + "> all() {");
		toWrite.add("return repository.findAll();");
		toWrite.add("}");

		toWrite.add("@PostMapping(" + quotes + "/" + beanName.toLowerCase() + "" + quotes + ")");
		toWrite.add(beanName + " newEmployee(@RequestBody " + beanName + " new"+beanName+") {");
		toWrite.add("return (" + beanName + ") repository.save(new"+beanName+");");
		toWrite.add("}");

		toWrite.add("@GetMapping(" + quotes + "/" + beanName.toLowerCase() + "/{id}" + quotes + ")");
		toWrite.add(beanName + " get"+beanName+"(@PathVariable Long id) throws Throwable {");

		toWrite.add("return (" + beanName + ") repository.findById(id)");
		toWrite.add(".orElseThrow(() -> new " + beanName + "NotFoundException(id));");
		toWrite.add("}");

		toWrite.add("@PutMapping(" + quotes + "/" + beanName.toLowerCase() + "/{id}" + quotes + ")");
		toWrite.add(beanName + " replace" + beanName+ "(@RequestBody " + beanName + " new"+beanName+", @PathVariable Long id) {");

		toWrite.add("return (" + beanName + ") repository.findById(id)");
		toWrite.add(".map(" + beanName.toLowerCase() + " -> {");

		toWrite.add("return repository.save(" + beanName.toLowerCase() + ");");
		toWrite.add("})");
		toWrite.add(".orElseGet(() -> {");
		toWrite.add("new"+beanName+".setId(id);");
		toWrite.add("return repository.save(new"+beanName+");");
		toWrite.add("});");
		toWrite.add("}");

		toWrite.add("@DeleteMapping(" + quotes + "/" + beanName.toLowerCase() + "/{id}" + quotes + ")");
		toWrite.add("void delete" + beanName + "(@PathVariable Long id) {");
		toWrite.add("repository.deleteById(id);");
		toWrite.add("}");


		toWrite.add("@GetMapping(value = " + quotes + "/search" + quotes + ", produces = " + quotes + "application/json"
				+ quotes + ")");
		toWrite.add("public List<" + beanName + "> search" + beanName + "(@RequestParam(name = " + quotes
				+ "searchMethod" + quotes + ") String searchMethod,");
		toWrite.add("@RequestParam(name = " + quotes + "value" + quotes + ") Object... params) {	");
		toWrite.add(
				"return (List<" + beanName + ">) " + beanName.toLowerCase() + "Filter.filter(searchMethod, params);");
		toWrite.add("}");
		toWrite.add("}");
		serialize.appendStringFromList(packageName, className.replace("Controller", "RestController"), toWrite);
	}

	public List<String> printControllerAPI(String packageName, String beanName, String className) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		char quotes = '"';
		toWrite.add(getLicence());

		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");
		toWrite.add("import java.util.List;");
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
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("controller", "beans")
				+"."+beanName+";");		
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("controller", "repository")
						+"."+beanName+"Repository;");
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("controller", "exceptions")
				+"."+beanName+"NotFoundException;");
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("controller", "filter")
				+"."+beanName+"Filter;");		

		toWrite.add("@CrossOrigin(origins = \"*\")");
				toWrite.add("@RestController");
						//toWrite.add("@RequestMapping(value = \"/" + beanName.toLowerCase()+"\")");
		toWrite.add("public class " + beanName + "RestController {");
		toWrite.add("@Autowired");
		toWrite.add("private " + beanName + "Repository repository;");
		toWrite.add("@Autowired");
		toWrite.add("private " + beanName + "Filter "+beanName.toLowerCase()+"Filter;");
		toWrite.add(beanName + "RestController() {");
		toWrite.add("}");

		toWrite.add("@GetMapping(" + quotes + "/" + beanName.toLowerCase() + "" + quotes + ")");
		toWrite.add("List<" + beanName + "> all() {");
		toWrite.add("return repository.findAll();");
		toWrite.add("}");

		toWrite.add("@PostMapping(" + quotes + "/" + beanName.toLowerCase() + "" + quotes + ")");
		toWrite.add(beanName + " newEmployee(@RequestBody " + beanName + " new"+beanName+") {");
		toWrite.add("return (" + beanName + ") repository.save(new"+beanName+");");
		toWrite.add("}");

		toWrite.add("@GetMapping(" + quotes + "/" + beanName.toLowerCase() + "/{id}" + quotes + ")");
		toWrite.add(beanName + " get"+beanName+"(@PathVariable Long id) throws Throwable {");

		toWrite.add("return (" + beanName + ") repository.findById(id)");
		toWrite.add(".orElseThrow(() -> new " + beanName + "NotFoundException(id));");
		toWrite.add("}");

		toWrite.add("@PutMapping(" + quotes + "/" + beanName.toLowerCase() + "/{id}" + quotes + ")");
		toWrite.add(beanName + " replace" + beanName+ "(@RequestBody " + beanName + " new"+beanName+", @PathVariable Long id) {");

		toWrite.add("return (" + beanName + ") repository.findById(id)");
		toWrite.add(".map(" + beanName.toLowerCase() + " -> {");

		toWrite.add("return repository.save(" + beanName.toLowerCase() + ");");
		toWrite.add("})");
		toWrite.add(".orElseGet(() -> {");
		toWrite.add("new"+beanName+".setId(id);");
		toWrite.add("return repository.save(new"+beanName+");");
		toWrite.add("});");
		toWrite.add("}");

		toWrite.add("@DeleteMapping(" + quotes + "/" + beanName.toLowerCase() + "/{id}" + quotes + ")");
		toWrite.add("void delete" + beanName + "(@PathVariable Long id) {");
		toWrite.add("repository.deleteById(id);");
		toWrite.add("}");


		toWrite.add("@GetMapping(value = " + quotes + "/search" + quotes + ", produces = " + quotes + "application/json"
				+ quotes + ")");
		toWrite.add("public List<" + beanName + "> search" + beanName + "(@RequestParam(name = " + quotes
				+ "searchMethod" + quotes + ") String searchMethod,");
		toWrite.add("@RequestParam(name = " + quotes + "value" + quotes + ") Object... params) {	");
		toWrite.add(
				"return (List<" + beanName + ">) " + beanName.toLowerCase() + "Filter.filter(searchMethod, params);");
		toWrite.add("}");
		toWrite.add("}");
		return toWrite;
	}

	public void printRepositoryClass(String packageName, String beanName, String className) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		toWrite.add(getLicence());

		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");
		toWrite.add("import org.springframework.data.jpa.repository.JpaRepository;");
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("repository", "beans")
				+"."+beanName+";");
		toWrite.add("public interface " + beanName + "Repository<T> extends JpaRepository<" + beanName + ", T> {");
		toWrite.add("");
		toWrite.add("}");
		serialize.appendStringFromList(packageName, className, toWrite);
	}

	public List<String> printRepositoryClassAPI(String packageName, String beanName, String className) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		toWrite.add(getLicence());

		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");
		toWrite.add("import org.springframework.data.jpa.repository.JpaRepository;");
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("repository", "beans")
				+"."+beanName+";");
		toWrite.add("public interface " + beanName + "Repository<T> extends JpaRepository<" + beanName + ", T> {");
		toWrite.add("");
		toWrite.add("}");
		return toWrite;
	}

	public void printExceptionClass(String packageName, String beanName,String className) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		toWrite.add(getLicence());

		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");
		toWrite.add("public class " + beanName + "NotFoundException extends RuntimeException { \n");
		toWrite.add("public " +beanName + "NotFoundException(Long id) {\n");
		toWrite.add("super(\"Could not find " + beanName.toLowerCase() + " \" + id);\n");
		toWrite.add("}\n");
		toWrite.add("}\n");
		serialize.appendStringFromList(packageName, beanName+"NotFoundException", toWrite);
	}

	public List<String> printExceptionClassAPI(String packageName, String beanName,String className) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		toWrite.add(getLicence());

		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");
		toWrite.add("public class " + beanName + "NotFoundException extends RuntimeException { \n");
		toWrite.add("public " +beanName + "NotFoundException(Long id) {\n");
		toWrite.add("super(\"Could not find " + beanName.toLowerCase() + " \" + id);\n");
		toWrite.add("}\n");
		toWrite.add("}\n");
		return toWrite;
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

	public List<String> printDatabaseAPI(String packageName, String beanName, String className) throws IOException {
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
		return toWrite;
	}

	public void printService(String packageName, String beanName, String className, Class<?> clazz) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		toWrite.add(getLicence());
		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");
		toWrite.add("		import java.util.List;\r\n");
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("service", "beans")
				+"."+beanName+";");
		toWrite.add("		/**\n"
				+ "* The Interface "+beanName+"Service.\n"
				+ "*/\n");
		toWrite.add("public interface "+beanName+"Service<T> {");

		toWrite.add("public T create("+beanName+" element);\n");
		toWrite.add("public List<"+beanName+"> getAll();\n");
		Field[] allFields;
		allFields = printAllFields(clazz);
		for(int i = 0; i < allFields.length; i++) {
			if(allFields[i].getName().equals("serialVersionUID")) {
				continue;
			}
			toWrite.add("public List<"+beanName+"> getBy"+StringUtils.capitalize(allFields[i].getName())+"(" + allFields[i].getType().getSimpleName() + " " + allFields[i].getName() + ");\n");
		}
		toWrite.add("}");
		serialize.appendStringFromList(packageName, className, toWrite);
	}
	
	public List<String> printServiceAPI(String packageName, String beanName, String className, Class<?> clazz) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		toWrite.add(getLicence());
		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");
		toWrite.add("		import java.util.List;\r\n");
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("service", "beans")
				+"."+beanName+";");
		toWrite.add("		/**\n"
				+ "* The Interface "+beanName+"Service.\n"
				+ "*/\n");
		toWrite.add("public interface "+beanName+"Service<T> {");

		toWrite.add("public T create("+beanName+" element);\n");
		toWrite.add("public List<"+beanName+"> getAll();\n");
		Field[] allFields;
		allFields = printAllFields(clazz);
		for(int i = 0; i < allFields.length; i++) {
			if(allFields[i].getName().equals("serialVersionUID")) {
				continue;
			}
			toWrite.add("public List<"+beanName+"> getBy"+StringUtils.capitalize(allFields[i].getName())+"(" + allFields[i].getType().getSimpleName() + " " + allFields[i].getName() + ");\n");
		}
		toWrite.add("}");
		return toWrite;
	}

	public void printServiceImpl(String packageName, String beanName, String className, Class<?> clazz) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		toWrite.add(getLicence());

		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".").replace("serviceimpl", "service.impl") + ";\n");
		toWrite.add("import java.util.List;");
		toWrite.add("import java.util.stream.Collectors;");
		toWrite.add("import lombok.extern.slf4j.Slf4j;");
		toWrite.add("import org.springframework.beans.factory.annotation.Autowired;");
		toWrite.add("import org.springframework.stereotype.Service;");
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("service.impl", "beans")
				+"."+beanName+";");
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("service.impl", "repository")
				+"."+beanName+"Repository;");
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("service.impl", "service")
				+"."+beanName+"Service;");
		toWrite.add("/**");
		toWrite.add("* The Class "+beanName+"ServiceImpl.");
		toWrite.add("*/");


		toWrite.add("@Service");
		toWrite.add("public class "+beanName+"ServiceImpl implements "+beanName+"Service<"+beanName+"> {");
		toWrite.add("	private static final Logger logger = LoggerFactory.getLogger("+beanName+"ServiceImpl.class);");
		toWrite.add("	@Autowired");
		toWrite.add("	"+beanName+"Repository<"+beanName+"> "+beanName.toLowerCase()+"Repo;");
		toWrite.add("	");
		toWrite.add("	public "+beanName+"ServiceImpl() {}");
		toWrite.add("	public "+beanName+" create("+beanName+" element) {");
		toWrite.add("		logger.info(\"-------------- Added "+beanName.toLowerCase()+" ------------------\", element.toString());");
		toWrite.add("		return ("+beanName+") "+beanName.toLowerCase()+"Repo.save(element);");
		toWrite.add("	}");
		toWrite.add("	public "+beanName+" update("+beanName+" data, String id) {");
		toWrite.add("		return ("+beanName+") "+beanName.toLowerCase()+"Repo.save(data);");
		toWrite.add("	}");
		toWrite.add("	@Override");
		toWrite.add("	public List<"+beanName+"> getAll() {");
		toWrite.add("		return "+beanName.toLowerCase()+"Repo.findAll();");
		toWrite.add("	}");
		Field[] allFields;
		allFields = printAllFields(clazz);
		for(int i = 0; i < allFields.length; i++) {
			if(allFields[i].getName().equals("serialVersionUID")) {
				continue;
			}
			toWrite.add("@Override");
			toWrite.add("public List<"+beanName+"> getBy"+StringUtils.capitalize(allFields[i].getName())+"(" + allFields[i].getType().getSimpleName() + " " + allFields[i].getName() + "){\n");
			if(allFields[i].getType().getSimpleName().equals("String")) {
				toWrite.add("return "+beanName.toLowerCase()+"Repo.findAll().stream().filter(x -> x.get"+
						StringUtils.capitalize(allFields[i].getName())+"().equals("+allFields[i].getName()+")).collect(Collectors.toList());");
			} else {
				toWrite.add("return "+beanName.toLowerCase()+"Repo.findAll().stream().filter(x -> x.get"+
						StringUtils.capitalize(allFields[i].getName())+"() == "+allFields[i].getName()+").collect(Collectors.toList());");
			}
			toWrite.add("}");
		}		
				toWrite.add("");
						toWrite.add("");
								toWrite.add("}");

		serialize.appendStringFromList(packageName, className, toWrite);
	}

	public List<String> printServiceImplAPI(String packageName, String beanName, String className, Class<?> clazz) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		toWrite.add(getLicence());

		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".").replace("serviceimpl", "service.impl") + ";\n");
		toWrite.add("import java.util.List;");
		toWrite.add("import java.util.stream.Collectors;");
		toWrite.add("import lombok.extern.slf4j.Slf4j;");
		toWrite.add("import org.springframework.beans.factory.annotation.Autowired;");
		toWrite.add("import org.springframework.stereotype.Service;");
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("service.impl", "beans")
				+"."+beanName+";");
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("service.impl", "repository")
				+"."+beanName+"Repository;");
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("service.impl", "service")
				+"."+beanName+"Service;");
		toWrite.add("/**");
		toWrite.add("* The Class "+beanName+"ServiceImpl.");
		toWrite.add("*/");


		toWrite.add("@Service");
		toWrite.add("public class "+beanName+"ServiceImpl implements "+beanName+"Service<"+beanName+"> {");
		toWrite.add("	private static final Logger logger = LoggerFactory.getLogger("+beanName+"ServiceImpl.class);");
		toWrite.add("	@Autowired");
		toWrite.add("	"+beanName+"Repository<"+beanName+"> "+beanName.toLowerCase()+"Repo;");
		toWrite.add("	");
		toWrite.add("	public "+beanName+"ServiceImpl() {}");
		toWrite.add("	public "+beanName+" create("+beanName+" element) {");
		toWrite.add("		logger.info(\"-------------- Added "+beanName.toLowerCase()+" ------------------\", element.toString());");
		toWrite.add("		return ("+beanName+") "+beanName.toLowerCase()+"Repo.save(element);");
		toWrite.add("	}");
		toWrite.add("	public "+beanName+" update("+beanName+" data, String id) {");
		toWrite.add("		return ("+beanName+") "+beanName.toLowerCase()+"Repo.save(data);");
		toWrite.add("	}");
		toWrite.add("	@Override");
		toWrite.add("	public List<"+beanName+"> getAll() {");
		toWrite.add("		return "+beanName.toLowerCase()+"Repo.findAll();");
		toWrite.add("	}");
		Field[] allFields;
		allFields = printAllFields(clazz);
		for(int i = 0; i < allFields.length; i++) {
			if(allFields[i].getName().equals("serialVersionUID")) {
				continue;
			}
			toWrite.add("@Override");
			toWrite.add("public List<"+beanName+"> getBy"+StringUtils.capitalize(allFields[i].getName())+"(" + allFields[i].getType().getSimpleName() + " " + allFields[i].getName() + "){\n");
			if(allFields[i].getType().getSimpleName().equals("String")) {
				toWrite.add("return "+beanName.toLowerCase()+"Repo.findAll().stream().filter(x -> x.get"+
						StringUtils.capitalize(allFields[i].getName())+"().equals("+allFields[i].getName()+")).collect(Collectors.toList());");
			} else {
				toWrite.add("return "+beanName.toLowerCase()+"Repo.findAll().stream().filter(x -> x.get"+
						StringUtils.capitalize(allFields[i].getName())+"() == "+allFields[i].getName()+").collect(Collectors.toList());");
			}
			toWrite.add("}");
		}		
				toWrite.add("");
						toWrite.add("");
								toWrite.add("}");

		return toWrite;
	}

	public void printFilter(String packageName, String beanName, String className) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		toWrite.add(getLicence());
		
		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");
		
		toWrite.add("import org.springframework.stereotype.Component;");
		toWrite.add("import com.tornado.filter.common.Filter;");
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("filter", "service.impl."+beanName+"ServiceImpl;"));


		toWrite.add("@Component");
		toWrite.add("public class "+beanName+"Filter extends Filter {");
			
		toWrite.add("public "+beanName+"Filter() throws NoSuchMethodException, SecurityException {");
		toWrite.add("super.setInjectedClass("+beanName+"ServiceImpl.class);");
				toWrite.add("super.initCases();");
				toWrite.add("}");
	toWrite.add("}");
		serialize.appendStringFromList(packageName, className, toWrite);

	}
	
	public List<String> printFilterAPI(String packageName, String beanName, String className) throws IOException {
		List<String> toWrite = new ArrayList<String>();
		toWrite.add(getLicence());
		
		toWrite.add("package " + packageName.replace("src/main/java/", "").replace("/", ".") + ";\n");
		
		toWrite.add("import org.springframework.stereotype.Component;");
		toWrite.add("import com.tornado.filter.common.Filter;");
		toWrite.add("import " + packageName.replace("src/main/java/", "").replace("/", ".").replace("filter", "service.impl."+beanName+"ServiceImpl;"));


		toWrite.add("@Component");
		toWrite.add("public class "+beanName+"Filter extends Filter {");
			
		toWrite.add("public "+beanName+"Filter() throws NoSuchMethodException, SecurityException {");
		toWrite.add("super.setInjectedClass("+beanName+"ServiceImpl.class);");
				toWrite.add("super.initCases();");
				toWrite.add("}");
	toWrite.add("}");
		return toWrite;

	}

	
	public String createPackage(ComponentType componentType) {
		String packageName = "";
		if(componentType == ComponentType.ServiceImpl) {
		 packageName = "src/main/java/" + this.getStringRootPackage().replace('.', '/') + "/"
					+ componentType.toString().toLowerCase().replace("serviceimpl", "service/impl");
		 System.out.println("PACKAGENAME "+ packageName);
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
	
	public String getPackageName(Class clazz) {
		return clazz.getClass().getPackage().getName();
	}

	public Package getPackage(Class clazz) {
		return clazz.getClass().getPackage();
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