package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import view.MainWindow;

public class EnableVersionsManagementCommandTestVolatile {

	private MainWindow view;
	private EnableVersionsManagementCommand enableCom;
	private String contents;
	private CreateCommand createCom;
	private EditCommand editCom;
	
	public EnableVersionsManagementCommandTestVolatile(){
		view=new MainWindow();
		view.setType("articleTemplate");
		enableCom = new EnableVersionsManagementCommand(view);
		createCom = new CreateCommand(view);
		editCom = new EditCommand(view);
		view.setStrategy("Volatile");
		view.setTypeOfAddition("section");
	}
	public void test() {
		createCom.execute();
		contents=view.getVersionsManager().getCurrentDocument().getContents();
		editCom.execute();
		enableCom.execute();
		Assert.assertEquals("",contents,view.getVersionsManager().getCurrentDocument().getContents());
	}

}
