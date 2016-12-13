package by.bsu.melnik.hospital.controller;

public enum CommandName {

	SIGN_IN("sign-in");
	
	private final String commandName;
	
	CommandName(String commandName){
		this.commandName = commandName;
	}
	
	public String getStringValue(){
		return this.commandName;
	}
	
	public static CommandName getCommandNameByString (String commandName){
		for(CommandName cn: CommandName.values()){
			if(commandName.equals(cn.getStringValue())){
				return cn;
			}
		}
		return null;
	}
	
}
