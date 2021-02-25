package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import view.MainWindow;

public class AddLatexCommandTestEnumerate {

	private CreateCommand createCom;
	private AddLatexCommand addCom;
	private MainWindow view;
		
	public AddLatexCommandTestEnumerate () {
		view=new MainWindow();
		view.setType("emptyTemplate");
		view.setTypeOfAddition("enumerate");
		createCom=new CreateCommand(view);
		addCom=new AddLatexCommand(view);	
		
		}
		
		
		public void test() {
			createCom.execute();
			addCom.execute();
			Assert.assertEquals("","\\begin{enumerate}\n\\item ...\n\\item ...\n\\end{enumerate}",view.getVersionsManager().getCurrentDocument().getContents());
		}
}
