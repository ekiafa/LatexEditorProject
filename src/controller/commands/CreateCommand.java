package controller.commands;
import view.*;
import model.Document;
import model.DocumentManager;
import model.VersionsManager;

public class CreateCommand implements Command {
	private DocumentManager documentManager;
	private VersionsManager versionsManager;
	private MainWindow mainWindow;
	private Document newDocument;
	public CreateCommand(MainWindow mainWindow){

		this.mainWindow=mainWindow;
		this.documentManager=mainWindow.getDocumentManager();
		this.versionsManager=mainWindow.getVersionsManager();
	}

	@Override
	public void execute() {
		
		newDocument=documentManager.createDocument(mainWindow.getType());
		versionsManager.setCurrentVersion(newDocument);
	}

}
