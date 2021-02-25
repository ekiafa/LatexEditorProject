package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import view.MainWindow;

public class AddLatexCommandTestSubsubsection {

	private CreateCommand createCom;
	private AddLatexCommand addCom;
	private MainWindow view;
		
	public AddLatexCommandTestSubsubsection () {
		view=new MainWindow();
		view.setType("emptyTemplate");
		view.setTypeOfAddition("chapter");
		createCom=new CreateCommand(view);
		addCom=new AddLatexCommand(view);	
		
		}
		
		
		public void test() {
			createCom.execute();
			addCom.execute();
			Assert.assertEquals("","\\subsubsection{}",view.getVersionsManager().getCurrentDocument().getContents());
		}
}

	


