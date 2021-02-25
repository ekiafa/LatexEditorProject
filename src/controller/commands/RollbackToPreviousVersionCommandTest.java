package controller.commands;

import static org.junit.Assert.*;
import model.Document;

import org.junit.Assert;
import org.junit.Test;

import view.MainWindow;

public class RollbackToPreviousVersionCommandTest {

	private MainWindow window;
	private EnableVersionsManagementCommand enableCom;
	private EditCommand editCom;
	private RollbackToPreviousVersionCommand rolCom;
	private Document doc;
	
	public RollbackToPreviousVersionCommandTest(){
		window= new MainWindow();
		enableCom= new EnableVersionsManagementCommand(window);
		editCom= new EditCommand(window);
		rolCom= new RollbackToPreviousVersionCommand(window);
		doc= new Document();
	}
	
	
	
	@Test
	public void test() {
		
		window.setStrategy("Volatile");
		enableCom.execute();
		window.getVersionsManager().setCurrentVersion(doc);
		window.setText("Test1");
		editCom.execute();
		window.setText("Test2");
		editCom.execute();
		rolCom.execute();
		Assert.assertEquals("Test1",window.getVersionsManager().getCurrentDocument().getContents());
		
	}

}
