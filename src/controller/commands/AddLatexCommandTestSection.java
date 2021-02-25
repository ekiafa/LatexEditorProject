package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import view.MainWindow;

public class AddLatexCommandTestSection {

	private CreateCommand createCom;
	private AddLatexCommand addCom;
	private MainWindow view;
		
	public AddLatexCommandTestSection () {
		view=new MainWindow();
		view.setType("emptyTemplate");
		view.setTypeOfAddition("section");
		createCom=new CreateCommand(view);
		addCom=new AddLatexCommand(view);	
		
		}
		
		
		public void test() {
			createCom.execute();
			addCom.execute();
			Assert.assertEquals("","\\section{}",view.getVersionsManager().getCurrentDocument().getContents());
		}
}

	


