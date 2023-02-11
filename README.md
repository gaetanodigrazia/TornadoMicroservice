## Usage

InitApi initApi = new InitApi();
initApi.setStringRootPackage(initApi.getPackageName(this));
initApi.setRootPackage(initApi.getPackage(this));
initApi.initCrud(Skill.class);
