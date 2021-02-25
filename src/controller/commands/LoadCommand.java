package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Document;
import model.VersionsManager;
import view.MainWindow;

public class LoadCommand implements Command {
	private VersionsManager versionsManager;
	private MainWindow mainWindow;
	private Document currentDocument;
	private String type;
	private Document document;
	
	public LoadCommand(MainWindow mainWindow) {
		super();
		this.mainWindow=mainWindow;
	}

	@Override
	public void execute() {
		String fileContents = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream(mainWindow.getFilename()));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document= new Document();
		currentDocument= mainWindow.getVersionsManager().setCurrentDocument(document);
		currentDocument.setContents(fileContents);
		
		type = "emptyTemplate";
		
		fileContents = fileContents.trim();
		if(fileContents.startsWith("\\documentclass[11pt,twocolumn,a4paper]{article}")) {
			type = "articleTemplate";
		}
		else if(fileContents.startsWith("\\documentclass[11pt,a4paper]{book}")) {
			type = "bookTemplate";
		}
		else if(fileContents.startsWith("\\documentclass[11pt,a4paper]{report}")) {
			type = "reportTemplate";
		}
		else if(fileContents.startsWith("\\documentclass{letter}")) {
			type = "letterTemplate";
		}
	}
	
}
