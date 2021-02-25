package controller;

import java.util.HashMap;

import controller.commands.AddLatexCommand;
import controller.commands.ChangeVersionsStrategyCommand;
import controller.commands.Command;
import controller.commands.CommandFactory;
import controller.commands.CreateCommand;
import controller.commands.DisableVersionsManagementCommand;
import controller.commands.EditCommand;
import controller.commands.EnableVersionsManagementCommand;
import controller.commands.LoadCommand;
import controller.commands.RollbackToPreviousVersionCommand;
import controller.commands.SaveCommand;
import model.VersionsManager;
import view.MainWindow;

public class LatexEditorController{
	private HashMap<String, Command> commands;
	private MainWindow mainWindow;
	CommandFactory commandFactory;
	
	public LatexEditorController(MainWindow window) {
		commandFactory = new CommandFactory(window);
		
		commands = new HashMap<String, Command>(); 
		
		//Substitute Algorithm
		String [] commandsNames={"addLatex","changeVersionsStrategy","create","disableVersionsManagement",
				"edit","enableVersionsManagement","load","rollbackToPreviousVersion","save","rot13Encryption","atBashEncryption"
		};			
		
		for(int i=0;i<11;i++){
			commands.put(commandsNames[i], commandFactory.createCommand(commandsNames[i]));	
		}
		
	}
	public void enact(String command) {
		commands.get(command).execute();
	}
}
