package controller.commands;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import view.MainWindow;

public class AddLatexCommandTestFigure {

	private CreateCommand createCom;
	private AddLatexCommand addCom;
	private MainWindow view;
		
	public AddLatexCommandTestFigure () {
		view=new MainWindow();
		view.setType("emptyTemplate");
		view.setTypeOfAddition("figure");
		createCom=new CreateCommand(view);
		addCom=new AddLatexCommand(view);	
		}
		
		
		public void test() {
			createCom.execute();
			addCom.execute();
			Assert.assertEquals("","\\begin{figure}\\includegraphics[width=...,height=...]{...} \n \\caption{....}\\label{...} \n \\end{figure}",view.getVersionsManager().getCurrentDocument().getContents());
		}
}
