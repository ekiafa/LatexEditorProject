package controller.commands;

import static org.junit.Assert.*;
import model.Document;

import org.junit.Assert;
import org.junit.Test;

import view.MainWindow;

public class LoadCommandTest {

	private MainWindow window;	
	private EnableVersionsManagementCommand enableCom;
	private CreateCommand createCom;
	private SaveCommand saveCom;
	private LoadCommand loadCom;
	
	
	
	public LoadCommandTest(){
		window= new MainWindow();
		enableCom= new EnableVersionsManagementCommand(window);
		saveCom= new SaveCommand(window);
		loadCom=new LoadCommand(window);
		createCom = new CreateCommand(window);
		
	}
	
	
	@Test
	public void test() {
		window.setStrategy("Stable");
		window.setType("emptyTemplate");
	
	}

}
