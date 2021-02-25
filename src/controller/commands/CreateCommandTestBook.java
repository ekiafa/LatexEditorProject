package controller.commands;
import org.junit.Assert;
import org.junit.Test;

import view.*;


import view.MainWindow;

	public class CreateCommandTestBook {

		CreateCommand createCom;
		MainWindow view;
		public CreateCommandTestBook () {
			view=new MainWindow();
			view.setType("bookTemplate");
			createCom=new CreateCommand(view);
		}
		@Test
		public void test() {
			createCom.execute();
			Assert.assertEquals("","\\documentclass[11pt,a4paper]{book}\n" + 
					"\n" + 
					"\\begin{document}\n" + 
					"\\title{Book: How to Structure a LaTeX Document}\n" + 
					"\\author{Author1 \\and Author2 \\and ...}\n" + 
					"\\date{\\today}\n" + 
					"\n" + 
					"\\maketitle\n" + 
					"\n" + 
					"\\frontmatter\n" + 
					"\n" + 
					"\\chapter{Preface}\n" + 
					"% ...\n" + 
					"\n" + 
					"\\mainmatter\n" + 
					"\\chapter{First chapter}\n" + 
					"\\section{Section Title 1}\n" + 
					"\\section{Section Title 2}\n" + 
					"\\section{Section Title.....}\n" + 
					"\n" + 
					"\\chapter{....}\n" + 
					"\n" + 
					"\\chapter{Conclusion}\n" + 
					"\n" + 
					"\\chapter*{References}\n" + 
					"\n" + 
					"\n" + 
					"\\backmatter\n" + 
					"\\chapter{Last note}\n" + 
					"\n" + 
					"\\end{document}",view.getVersionsManager().getCurrentDocument().getContents());
			
		}

	}

