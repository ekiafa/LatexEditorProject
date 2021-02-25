package controller.commands;

import model.VersionsManager;
import view.MainWindow;

public class SaveCommand implements Command {
	private VersionsManager versionsManager;
	private MainWindow mainWindow;
	
	public SaveCommand(MainWindow mainWindow) {
		this.mainWindow=mainWindow;
	}
	@Override
	public void execute() {
		mainWindow.getVersionsManager().getCurrentDocument().save(mainWindow.getFilename());
	}

}
