package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import view.MainWindow;

public class DisableVersionsManagementCommandTest {
	
	private MainWindow window;
	private EnableVersionsManagementCommand enableCom;
	private DisableVersionsManagementCommand disableCom;
	private EditCommand editCom;
	
	public DisableVersionsManagementCommandTest(){
		window= new MainWindow();
		enableCom= new EnableVersionsManagementCommand(window);
		disableCom= new DisableVersionsManagementCommand(window);
		editCom= new EditCommand(window);
	}
	
	
	
	
	@Test
	public void test() {
		window.setStrategy("Volatile");
		enableCom.execute();
		disableCom.execute();
		window.setText("TEST");
		editCom.execute();
		Assert.assertEquals(0,window.getVersionsManager().getStrategy().getEntireHistory().size());
		
		
	}

}
