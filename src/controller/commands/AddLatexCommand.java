package controller.commands;

import javax.swing.JEditorPane;

import model.VersionsManager;
import view.MainWindow;

public class AddLatexCommand implements Command  {
	private VersionsManager versionsManager;
	private MainWindow mainWindow;
	private JEditorPane editorPane;
	
	private String [] additionsNames= {"chapter","section","subsection","subsubsection","enumerate","itemize","table","figure"};
	private String [] additionContents= {"\\chapter{...}","\\section{...}","\n\\subsection{...}","\n\\subsubsection{...}","\\begin{enumerate}\n"+
			"\\item ...\n"+
			"\\item ...\n"+
			"\\end{enumerate}\n",
			"\\begin{itemize}\n"+
			"\\item ...\n"+
			"\\item ...\n"+
			"\\end{itemize}\n",
			"\\begin{table}\n"+
			"\\caption{....}\\label{...}\n"+
			"\\begin{tabular}{|c|c|c|}\n"+
			"\\hline\n"+
			"... &...&...\\\\\n"+
			"... &...&...\\\\\n"+
			"... &...&...\\\\\n"+
			"\\hline\n"+
			"\\end{tabular}\n"+
			"\\end{table}\n",
			"\\begin{figure}\n"+
			"\\includegraphics[width=...,height=...]{...}\n"+
			"\\caption{....}\\label{...}\n"+
			"\\end{figure}\n"};
	
	public AddLatexCommand(MainWindow mainWindow) {
		super();
		this.mainWindow=mainWindow;
	}

	
	@Override
	public void execute() {
		String contents = mainWindow.getEditorPane().getText();
		String type=mainWindow.getTypeOfAddition();
		String before = contents.substring(0, mainWindow.getEditorPane().getCaretPosition());
		String after = contents.substring(mainWindow.getEditorPane().getCaretPosition());
		
		
		for(int i=0;i<8;i++) {
			if(type.equals(additionsNames[i])) {
				contents = before +additionContents[i]+"\n"+after;
			}
		}
		mainWindow.getVersionsManager().getCurrentDocument().setContents(contents);
		mainWindow.setText(contents);
		mainWindow.getEditorPane().setText(contents);
	}

}
