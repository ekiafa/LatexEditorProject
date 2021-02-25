package controller.commands;

import view.MainWindow;

public class Rot13EncryptionCommand implements Command {

	
	private MainWindow mainWindow;

	public Rot13EncryptionCommand(MainWindow mainWindow) {
		this.mainWindow=mainWindow;
		
	}
	
	
	public void execute() {
		mainWindow.getVersionsManager().isEncryptedRot13();
		String contentsOfDocument=mainWindow.getVersionsManager().getCurrentDocument().getContents();
		String encContents=mainWindow.getDocumentManager().rot13(contentsOfDocument);
		//System.out.println(encContents);
		mainWindow.getVersionsManager().getCurrentDocument().setContents(encContents);
		
	}
}
