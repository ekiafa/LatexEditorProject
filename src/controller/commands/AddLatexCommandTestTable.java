package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import view.MainWindow;

public class AddLatexCommandTestTable {

	private CreateCommand createCom;
	private AddLatexCommand addCom;
	private MainWindow view;
		
	public AddLatexCommandTestTable () {
		view=new MainWindow();
		view.setType("emptyTemplate");
		view.setTypeOfAddition("table");
		createCom=new CreateCommand(view);
		addCom=new AddLatexCommand(view);	
		
		}
		
		
		public void test() {
			createCom.execute();
			addCom.execute();
			Assert.assertEquals("","\\begin{table}\n\\caption{....}\\label{...} \n \\begin{tabular}{|c|c|c|} \n \\hline \n... &...&...\\ \n ... &...&...\\ \n ... &...&...\\ \n\\hline \n\\end{tabular} \n\\end{table}",view.getVersionsManager().getCurrentDocument().getContents());
		}
}
