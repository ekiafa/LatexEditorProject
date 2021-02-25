//Eftihia Kiafa AM:3003
package view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

import controller.LatexEditorController;
import model.Document;
import model.DocumentManager;
import model.VersionsManager;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;

public class MainWindow {

	private JFrame frame;
	private JEditorPane editorPane = new JEditorPane();
	private VersionsManager versionsManager;
	private String type;
	protected static MainWindow mainWindow;
	private String filename;
	private VersionsStrategy strategy;
	private String text;
	private static LatexEditorController controller;
	private DocumentManager documentManager;
	private JFrame frame1;
	private JFrame frame2;
	private String previous;
	private Document currentDocument;
	private String typeOfAdditition;
	private String typeOfAddition;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow = new MainWindow();
					controller=new LatexEditorController(mainWindow);
					mainWindow.chooseTemplate("main");
					mainWindow.frame2.setVisible(true);
					mainWindow.openingWindow();					
					mainWindow.frame1.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	public MainWindow() {
		strategy=new VolatileVersionsStrategy();
		versionsManager=new VersionsManager(strategy);
		documentManager=new DocumentManager();		
		//initialize();
		//frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 823, 566);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 805, 26);
		frame.getContentPane().add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewFile = new JMenuItem("New file");
		mntmNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.chooseTemplate("main");
				frame2.setVisible(true);
				frame.dispose();
			}
		});
		mnFile.add(mntmNewFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setText(editorPane.getText());
				mainWindow.getController().enact("edit");
			}
		});
		mnFile.add(mntmSave);
		JMenuItem addChapter = new JMenuItem("Add chapter");
		JMenu mnCommands = new JMenu("Commands");
		JMenuItem mntmLoadFile = new JMenuItem("Load file");
		mntmLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				int option = filechooser.showOpenDialog(null);
				if(option == JFileChooser.APPROVE_OPTION) {
					String filename = filechooser.getSelectedFile().toString();
					
					mainWindow.setFilename(filename);
					mainWindow.getController().enact("load");
					mnCommands.setEnabled(true);
					addChapter.setEnabled(true);
					if(mainWindow.getType().equals("letterTemplate")) {
						mnCommands.setEnabled(false);
					}
					if(mainWindow.getType().equals("articleTemplate")) {
						addChapter.setEnabled(false);
					}
					if(mainWindow.getVersionsManager().isEncryptedAtBash()) {
								
						mainWindow.getDocumentManager().decode(mainWindow.getVersionsManager().getCurrentDocument().getContents());
						mainWindow.getVersionsManager().getCurrentDocument().setContents(mainWindow.getDocumentManager().decode(mainWindow.getVersionsManager().getCurrentDocument().getContents()));
						editorPane.setText(mainWindow.getVersionsManager().getCurrentDocument().getContents());
					}
					
					if(mainWindow.getVersionsManager().isEncryptedRot13()) {
						
						mainWindow.getDocumentManager().rot13(mainWindow.getVersionsManager().getCurrentDocument().getContents());
						mainWindow.getVersionsManager().getCurrentDocument().setContents(mainWindow.getDocumentManager().rot13(mainWindow.getVersionsManager().getCurrentDocument().getContents()));
						editorPane.setText(mainWindow.getVersionsManager().getCurrentDocument().getContents());
					}

					editorPane.setText(mainWindow.getVersionsManager().getCurrentDocument().getContents());
				}
			}
		});
		mnFile.add(mntmLoadFile);
		
		JMenuItem mntmSaveFile = new JMenuItem("Save file");
		mntmSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				int option = filechooser.showSaveDialog(null);
				if(option == JFileChooser.APPROVE_OPTION) {
					String filename = filechooser.getSelectedFile().toString();
					if(filename.endsWith(".tex") == false) {
						filename = filename+".tex";
					}
					setFilename(filename);
					mainWindow.getController().enact("save");
				}
				
			}

	
		});
		mnFile.add(mntmSaveFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		
		menuBar.add(mnCommands);
		if(mainWindow.getType().equals("letterTemplate")) {
			mnCommands.setEnabled(false);
		}
		
		addChapter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.setTypeOfAddition("chapter");				
				mainWindow.getController().enact("addLatex");
				

			}
		});
		mnCommands.add(addChapter);
		if(mainWindow.getType().equals("articleTemplate")) {
			addChapter.setEnabled(false);
		}
		
		JMenu addSection = new JMenu("Add Section");
		mnCommands.add(addSection);
		
		JMenuItem mntmAddSection = new JMenuItem("Add section");
		mntmAddSection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.setTypeOfAddition("section");				
				mainWindow.getController().enact("addLatex");
			}
		});
		addSection.add(mntmAddSection);
		
		JMenuItem mntmAddSubsection = new JMenuItem("Add subsection");
		mntmAddSubsection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.setTypeOfAddition("subsection");				
				mainWindow.getController().enact("addLatex");
			}
		});
		addSection.add(mntmAddSubsection);
		
		JMenuItem mntmAddSubsubsection = new JMenuItem("Add subsubsection");
		mntmAddSubsubsection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.setTypeOfAddition("subsubsection");				
				mainWindow.getController().enact("addLatex");
			}
		});
		addSection.add(mntmAddSubsubsection);
		
		JMenu addEnumerationList = new JMenu("Add enumeration list");
		mnCommands.add(addEnumerationList);
		
		JMenuItem mntmItemize = new JMenuItem("Itemize");
		mntmItemize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.setTypeOfAddition("itemize");				
				mainWindow.getController().enact("addLatex");
			}
		});
		addEnumerationList.add(mntmItemize);
		
		JMenuItem mntmEnumerate = new JMenuItem("Enumerate");
		mntmEnumerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.setTypeOfAddition("enumerate");				
				mainWindow.getController().enact("addLatex");
			}
		});
		addEnumerationList.add(mntmEnumerate);
		
		JMenuItem addTable = new JMenuItem("Add table");
		addTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.setTypeOfAddition("table");				
				mainWindow.getController().enact("addLatex");
			}
		});
		mnCommands.add(addTable);
		
		JMenuItem addFigure = new JMenuItem("Add figure");
		addFigure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.setTypeOfAddition("figure");				
				mainWindow.getController().enact("addLatex");
			}
		});
		mnCommands.add(addFigure);
		
		JMenu mnStrategy = new JMenu("Strategy");
		menuBar.add(mnStrategy);
		
		JMenu mnEnable = new JMenu("Enable");
		mnStrategy.add(mnEnable);
		
		JCheckBoxMenuItem menuVolatile = new JCheckBoxMenuItem("Volatile");
		JCheckBoxMenuItem menuStable = new JCheckBoxMenuItem("Stable");
		menuStable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStrategy("stable");
				if(mainWindow.getVersionsManager().isEnabled() == false) {
					mainWindow.getController().enact("enableVersionsManagement");
				}
				else {
					mainWindow.getController().enact("changeVersionsStrategy");
				}
				menuVolatile.setSelected(false);
				menuStable.setEnabled(false);
				menuVolatile.setEnabled(true);
			}
		});

		menuVolatile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setStrategy("volatile");
				if(mainWindow.getVersionsManager().isEnabled() == false) {
					mainWindow.getController().enact("enableVersionsManagement");
				}
				else {
					mainWindow.getController().enact("changeVersionsStrategy");
				}
				menuStable.setSelected(false);
				menuVolatile.setEnabled(false);
				menuStable.setEnabled(true);
			}
		});
		mnEnable.add(menuVolatile);
		
		mnEnable.add(menuStable);
		
		JMenuItem mntmDisable = new JMenuItem("Disable");
		mntmDisable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.getController().enact("disableVersionsManagement");
			}
		});
		mnStrategy.add(mntmDisable);
		
		JMenuItem mntmRollback = new JMenuItem("Rollback");
		mntmRollback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.getController().enact("rollbackToPreviousVersion");
				Document doc = mainWindow.getVersionsManager().getCurrentDocument();
				editorPane.setText(doc.getContents());
			}
		});
		mnStrategy.add(mntmRollback);
		
		
		//encryption---------------------------------------------------------------
		
		JMenuItem mntmRot13Encryption = new JMenuItem("Rot13-Encryption");
		mntmRot13Encryption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.getController().enact("rot13Encryption");
				Document doc = mainWindow.getVersionsManager().getCurrentDocument();
				mainWindow.getController().enact("save");
			}
		});
		mnStrategy.add(mntmRot13Encryption);
		
		
		JMenuItem mntmAtBashEncryption = new JMenuItem("AtBash-Encryption");
		mntmAtBashEncryption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.getController().enact("atBashEncryption");
				Document doc = mainWindow.getVersionsManager().getCurrentDocument();
				mainWindow.getController().enact("save");
				
			}
		});
		mnStrategy.add(mntmAtBashEncryption);
		
		
		//----------------------------------------------------------------------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 783, 467);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(editorPane);
		
		
		editorPane.setText(getVersionsManager().getCurrentDocument().getContents());
		//System.out.print(getVersionsManager().getCurrentDocument().getContents());
	}
//--------------------------opening window
	

	public void openingWindow(){
		frame1 = new JFrame();
		frame1.setBounds(100, 100, 450, 300);
		frame1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		
		JButton btnCreateNewDocument = new JButton("Create New Document");
		btnCreateNewDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.chooseTemplate("opening");
				frame1.dispose();
			}
		});
		btnCreateNewDocument.setBounds(89, 26, 278, 36);
		frame1.getContentPane().add(btnCreateNewDocument);
		
		JButton btnOpenExistingDocument = new JButton("Open Existing Document");
		btnOpenExistingDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
			}
		});
		btnOpenExistingDocument.setBounds(89, 92, 278, 36);
		frame1.getContentPane().add(btnOpenExistingDocument);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(99, 169, 268, 25);
		frame1.getContentPane().add(btnExit);
	}
//------------------------------------------	
	
	//-----------------------------------------------choose template
	
	
	private void diselectRadioButtons(JRadioButton radioButton1, JRadioButton radioButton2, JRadioButton radioButton3,JRadioButton radioButton4) {
		if(radioButton1.isSelected()) {
			radioButton2.setSelected(false);
			radioButton3.setSelected(false);
			radioButton4.setSelected(false);
		}
	}
	private void chooseTemplate(String previous) {

		frame2 = new JFrame();
		frame2.setBounds(100, 100, 450, 300);
		frame2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame2.getContentPane().setLayout(null);
		
		JRadioButton book = new JRadioButton("Book");
		JRadioButton article = new JRadioButton("Article");
		JRadioButton report = new JRadioButton("Report");
		JRadioButton letter = new JRadioButton("Letter");
		
		book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diselectRadioButtons(book, article, report, letter);
			}
		});
		book.setBounds(42, 51, 127, 25);
		frame2.getContentPane().add(book);
		
		JLabel lblChooseTemplate = new JLabel("Choose template. (Leave empty for blank document)");
		lblChooseTemplate.setBounds(42, 13, 332, 16);
		frame2.getContentPane().add(lblChooseTemplate);
		
		
		article.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diselectRadioButtons(article, book, report, letter);

			}
		});
		article.setBounds(42, 137, 127, 25);
		frame2.getContentPane().add(article);
		
		
		report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diselectRadioButtons(report, article, book, letter);
				
			}
		});
		report.setBounds(213, 51, 127, 25);
		frame2.getContentPane().add(report);
		
		
		letter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diselectRadioButtons( letter, report, article, book);
			}
		});
		letter.setBounds(213, 137, 127, 25);
		frame2.getContentPane().add(letter);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			

			
			
			public void actionPerformed(ActionEvent arg0) {
				if(book.isSelected()) {
					mainWindow.setType("bookTemplate");
				}
				else if(report.isSelected()) {
					mainWindow.setType("reportTemplate");
				}
				else if(article.isSelected()) {
					mainWindow.setType("articleTemplate");
				}
				else if(letter.isSelected()) {
					mainWindow.setType("letterTemplate");
				}
				else {
					mainWindow.setType("emptyTemplate");
				}
				mainWindow.getController().enact("create");
				mainWindow.initialize();
				frame.setVisible(true);;
				frame2.dispose();
			}
		});
		btnCreate.setBounds(213, 196, 97, 25);
		frame2.getContentPane().add(btnCreate);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
					frame2.dispose();
					mainWindow.openingWindow();
					frame1.setVisible(true);
					
				
			}
		});
		btnBack.setBounds(46, 196, 97, 25);
		frame2.getContentPane().add(btnBack);
	}

	
	
	
	
	
	
	
	//=---------------------------------------------------------
	public void setText(String text) {
		this.text=text;
		
	}

	public void setFilename(String filename) {
		this.filename=filename;
		
	}

	public void setStrategy(String string) {
		this.strategy=strategy;
		
	}

	public LatexEditorController getController() {

		return controller;
	}

	public VersionsManager getVersionsManager() {
		return versionsManager;
	}

	public String getType() {

		return type;
	}
	
	public String getTypeOfAddition() {
			return typeOfAddition;
	}
	public void setTypeOfAddition(String typeOfAddition) {
		this.typeOfAddition=typeOfAddition;
	}
	
	public void setType(String type) {

		this.type=type;
	}


	public DocumentManager getDocumentManager() {
		return documentManager;
	}
	

	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}


	public JEditorPane getEditorPane() {
		// TODO Auto-generated method stub
		return editorPane;
	}




	public String getFilename() {
		// TODO Auto-generated method stub
		return filename;
	}
}
