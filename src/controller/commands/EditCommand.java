package controller.commands;

import model.VersionsManager;
import view.MainWindow;

public class EditCommand implements Command {
	private VersionsManager versionsManager;
	private MainWindow mainWindow;
	
	
	public EditCommand(MainWindow mainWindow) {
		super();
		this.mainWindow=mainWindow;
		
	}


	@Override
	public void execute() {
		if(mainWindow.getVersionsManager().getStrategy()!=null) {
			mainWindow.getVersionsManager().putVersion(mainWindow.getVersionsManager().getCurrentDocument());
			mainWindow.getVersionsManager().getCurrentDocument().changeVersion();
		}
		mainWindow.getVersionsManager().getCurrentDocument().setContents(mainWindow.getText());
	}

}
