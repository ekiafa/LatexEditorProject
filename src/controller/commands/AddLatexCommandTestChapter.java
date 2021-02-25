package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import view.MainWindow;

public class AddLatexCommandTestChapter {
	private CreateCommand createCom;
	private AddLatexCommand addCom;
	private MainWindow view;
	
	public AddLatexCommandTestChapter () {
		view=new MainWindow();
		view.setType("emptyTemplate");
		view.setTypeOfAddition("chapter");
		createCom=new CreateCommand(view);
		addCom=new AddLatexCommand(view);		

	}
	
	@Test
	public void test() {
		createCom.execute();
		addCom.execute();
		Assert.assertEquals("","\\chapter{...}\n",view.getVersionsManager().getCurrentDocument().getContents());
	}

}
