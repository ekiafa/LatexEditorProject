package model;
import view.*;
import javax.swing.JOptionPane;

import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;


public class VersionsManager {
	private boolean enabled;
	private VersionsStrategy strategy;
	private MainWindow	mainWindow;
	private boolean encryptedAtBash;
	private boolean encryptedRot13;
	private String type;
	private Document currentDocument;
	private Document document;
	
	
	public VersionsManager(VersionsStrategy versionsStrategy) {
		this.strategy = versionsStrategy;
		this.currentDocument=new Document();
		
	}
	
	public boolean isEncryptedAtBash(){
		 return encryptedAtBash=true;
	}
	public boolean isEncryptedRot13(){
		return encryptedRot13=true;
}
	
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void enable() {
		enabled = true;
	}

	public void disable() {
		enabled = false;
	}
	
	
	public void setCurrentVersion(Document document) {
		if(enabled){
			strategy.putVersion(currentDocument);
		}	
		currentDocument=document.clone();		
		}


	public void  putVersion(Document document) {
		// TODO Auto-generated method stub
		strategy.putVersion(document);
	}

	public void setStrategy(VersionsStrategy strategy) {
		this.strategy = strategy;
	}
	public VersionsStrategy getStrategy() {
		// TODO Auto-generated method stub
		return strategy;
	}

	public Document getCurrentDocument() {

		return currentDocument;
	}
	
	public Document setCurrentDocument(Document document) {

		this.currentDocument=document;
		return document;
	}
}
