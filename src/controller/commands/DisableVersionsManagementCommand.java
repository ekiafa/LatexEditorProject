package controller.commands;

import model.VersionsManager;
import view.MainWindow;

public class DisableVersionsManagementCommand implements Command {

	private VersionsManager versionsManager;
	private MainWindow mainWindow;
	
	public DisableVersionsManagementCommand(MainWindow mainWindow) {
		super();
		this.mainWindow=mainWindow;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		mainWindow.getVersionsManager().disable();
	}

}
