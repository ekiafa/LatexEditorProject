package controller.commands;

import javax.swing.JOptionPane;

import model.Document;
import model.VersionsManager;
import view.MainWindow;

public class RollbackToPreviousVersionCommand implements Command {
	private VersionsManager versionsManager;
	private MainWindow mainWindow;
	
	
	public RollbackToPreviousVersionCommand(MainWindow mainWindow) {
		this.mainWindow=mainWindow;
	}


	public void execute() {
		// TODO Auto-generated method stub
		if(mainWindow.getVersionsManager().getStrategy()==null) {
			JOptionPane.showMessageDialog(null, "Strategy is not enabled", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			Document doc = mainWindow.getVersionsManager().getStrategy().getVersion();
			if(doc == null) {
				JOptionPane.showMessageDialog(null, "No version available", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				mainWindow.getVersionsManager().getStrategy().removeVersion();
				mainWindow.getVersionsManager().setCurrentVersion(doc);
			}
		}
		
	}

}
