package controller.commands;

import java.util.HashMap;

import model.DocumentManager;
import view.MainWindow;

public class CommandFactory {
	private DocumentManager documentManager;
		private MainWindow mainWindow;
	
	
	public CommandFactory(MainWindow mainWindow) {
		super();
		this.mainWindow=mainWindow;
		//this.mainWindow = mainWindow;
		//documentManager = new DocumentManager();
	}


	public Command createCommand(String type) {
		if(type.equals("addLatex")) {
			return new AddLatexCommand(mainWindow);
		}
		if(type.equals("changeVersionsStrategy")) {
			return new ChangeVersionsStrategyCommand(mainWindow);
		}
		if(type.equals("create")) {
			return new CreateCommand(mainWindow);
		}
		if(type.equals("disableVersionsManagement")) {
			return new DisableVersionsManagementCommand(mainWindow);
		}
		if(type.equals("edit")) {
			return new EditCommand(mainWindow);
		}
		if(type.equals("enableVersionsManagement")) {
			return new EnableVersionsManagementCommand(mainWindow);
		}
		if(type.equals("load")) {
			return new LoadCommand(mainWindow);
		}
		if(type.equals("rollbackToPreviousVersion")) {
			return new RollbackToPreviousVersionCommand(mainWindow);
		}
		if(type.equals("save")) {
			return new SaveCommand(mainWindow);
		}
		if(type.equals("rot13Encryption")) {
			return new Rot13EncryptionCommand(mainWindow);
		}
		if(type.equals("atBashEncryption")) {
			return new AtBashEncryptionCommand(mainWindow);
		}
		return null;
	}
}
