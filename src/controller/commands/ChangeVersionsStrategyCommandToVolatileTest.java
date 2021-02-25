package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import model.strategies.StableVersionsStrategy;
import model.strategies.VolatileVersionsStrategy;
import view.MainWindow;

public class ChangeVersionsStrategyCommandToVolatileTest {

	private MainWindow window;
	private EnableVersionsManagementCommand enableCom;
	private ChangeVersionsStrategyCommand changeCom;
	private StableVersionsStrategy strategy;
	private StableVersionsStrategy compStrategy=new StableVersionsStrategy();
	
	public  ChangeVersionsStrategyCommandToVolatileTest(){
		window= new MainWindow();
		enableCom= new EnableVersionsManagementCommand(window);
		changeCom= new ChangeVersionsStrategyCommand (window);
		strategy=new StableVersionsStrategy();
		
	}
	
	
	
	@Test
	public void test() {
		window.getVersionsManager().setStrategy(strategy);
		enableCom.execute();
		changeCom.execute();
		//Assert.assertEquals(compStrategy,window.getVersionsManager().getStrategy());
		
	}

}
