package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import view.MainWindow;

public class AddLatexCommandTestItemize {

	private CreateCommand createCom;
	private AddLatexCommand addCom;
	private MainWindow view;
		
	public AddLatexCommandTestItemize () {
		view=new MainWindow();
		view.setType("emptyTemplate");
		view.setTypeOfAddition("itemize");
		createCom=new CreateCommand(view);
		addCom=new AddLatexCommand(view);	
		
		}
		
		
		public void test() {
			createCom.execute();
			addCom.execute();
			Assert.assertEquals("","\\begin{itemize} \n\\item ...\n\\item ...\n\\end{itemize}",view.getVersionsManager().getCurrentDocument().getContents());
		}
}
