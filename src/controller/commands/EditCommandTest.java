package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import model.*;
import view.*;

public class EditCommandTest {

	private EditCommand editCom;
	private Document doc;
	private MainWindow window;
	
	public EditCommandTest(){
		window = new MainWindow();
		editCom = new EditCommand(window);
		doc = new Document();
		
	}
	@Test
	public void test() {
		window.getVersionsManager().setCurrentVersion(doc);
		window.setText("TEST");
		editCom.execute();
		Assert.assertEquals("","TEST",window.getVersionsManager().getCurrentDocument().getContents());
	}

}
