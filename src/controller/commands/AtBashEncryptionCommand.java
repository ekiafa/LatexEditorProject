package controller.commands;

import model.DocumentManager;
import view.MainWindow;

public class AtBashEncryptionCommand implements Command{


	private MainWindow mainWindow;
	
	
	public AtBashEncryptionCommand(MainWindow mainWindow) {
			this.mainWindow=mainWindow;
		
	}
	
	public void execute() {
			mainWindow.getVersionsManager().isEncryptedAtBash();
			String contentsOfDocument=mainWindow.getVersionsManager().getCurrentDocument().getContents();
			String encContents=mainWindow.getDocumentManager().encode(contentsOfDocument);
			//System.out.println(encContents);
			mainWindow.getVersionsManager().getCurrentDocument().setContents(encContents);
			
			
	}



}