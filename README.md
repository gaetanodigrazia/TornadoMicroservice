## USAGE		

CRUD crud = new CRUD
				  .CRUDBuilder()
				  .withStringPackage("com.test.TestTornado")
				  .withRestController(Skill.class)
				  .withControllerAdvice(Skill.class)
				  .withExceptions(Skill.class)
				  .withFilter(Skill.class)
				  .withRepository(Skill.class)
				  .withServiceImplementation(Skill.class)
				  .withServiceInterface(Skill.class)
				  .build();
