package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import view.MainWindow;

public class AddLatexCommandTestSubsection {

	private CreateCommand createCom;
	private AddLatexCommand addCom;
	private MainWindow view;
		
	public AddLatexCommandTestSubsection () {
		view=new MainWindow();
		view.setType("emptyTemplate");
		view.setTypeOfAddition("subsection");
		createCom=new CreateCommand(view);
		addCom=new AddLatexCommand(view);	
		
		}
		
		
		public void test() {
			createCom.execute();
			addCom.execute();
			Assert.assertEquals("","\\subsection{}",view.getVersionsManager().getCurrentDocument().getContents());
		}
}

	


