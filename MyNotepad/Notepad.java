import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import com.ozten.font.JFontChooser;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JCheckBox;
import javax.swing.JPopupMenu;
import java.awt.Component;

public class Notepad extends JFrame {

	private JPanel contentPane;
	JScrollPane scrollPane;
	JTextArea textArea;
	JCheckBoxMenuItem mntmWordWrap;
    Notepad frame;
    JCheckBox chckbxStatusBar;
    UndoManager editManager;
    String value;
    JMenuItem mntmRedo;
    JMenuItem mntmUndo;
    String defaultPath;
    static boolean flag=false;
    String textss=null;
    //JPanel panel;
    int offset=1,offset1;
	 int lineNo;
/////////////////////////////////MAIN METHOD AREA/////////////////////////  
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					Notepad frame = new Notepad();
					frame.setVisible(true);
					flag=true;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
				
	}
     
////////////////////////RESIZE TEXT AREA///////////////////////////////////
	private void resizeTextArea(){
		scrollPane.setSize(this.getWidth()-24,this.getHeight()-62);
		textArea.setSize(this.getWidth()-60,this.getHeight()-30);
	}
//////////////////////////////OPEN NEW FRAME////////////////////////////////	
	void openNewFrame()
    {
		if(value.equals(""))
		{
			if(flag==true)
				Notepad.this.setVisible(false);
			 frame=new Notepad();
		   	 frame.setVisible(true);	
		}
		else 
		{
			int choice=JOptionPane.showConfirmDialog(this, "Do you want to save this file?", "Notepad", JOptionPane.YES_NO_OPTION);
   	        if(choice==JOptionPane.YES_OPTION)
   	        {
   	        	saveFile();
   	        	textArea.setText("");
   	       }
   	        else if(choice==JOptionPane.NO_OPTION);
   	        {
   	        	return;
   	        	}
	    }
    }	
///////////////////////////OPEN A FILE///////////////////////////////////	
	
	void openFile()
	{
		defaultPath="Documents";
		JFileChooser opnFile=new JFileChooser(defaultPath);
		opnFile.showOpenDialog(this);
		
		
		File file=opnFile.getSelectedFile();
		String path=file.getPath();
		
		try {
			textArea.setText(OperationNotepad.readFile(path));
		} catch (IOException e) {
			textArea.setText(e.toString());
		}
		
	}
	
/////////////////////////////SAVE A FILE//////////////////////////////	
	void saveFile(){
		
		 defaultPath = "Documents";
		JFileChooser saveDialog = new JFileChooser(defaultPath);
		saveDialog.showSaveDialog(this);
		File file = saveDialog.getSelectedFile();
		String path = file.getPath();
	
		try {
			OperationNotepad.writeFile(path,textArea.getText());
			this.setTitle(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			textArea.setText(e.toString());
			//e.printStackTrace();
		}
	}
////////////////////////////SAVE ON CLOSING WINDOW///////////////////////	
	void wsaveFile(){
		 defaultPath = "Documents";
		JFileChooser saveDialog = new JFileChooser(defaultPath);
		Notepad fr = new Notepad();
		saveDialog.showSaveDialog(fr);
		File file = saveDialog.getSelectedFile();
		String path = file.getPath();
		try {
			OperationNotepad.writeFile(path,textArea.getText());
			this.setTitle(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			textArea.setText(e.toString());
			//e.printStackTrace();
		}
	}
////////////////////////////////////UNDO TEXT////////////////////////////	
	
	void undoText()
	{
		if(textArea.getText().equals(""))
			mntmUndo.setEnabled(false);
		
		if(editManager.canUndo())
		{
			editManager.undo();
		mntmRedo.setEnabled(true);}	
	}
////////////////////////////REDO TEXT//////////////////////////////////////	
	
	void redoText()
	{
		 if(editManager.canRedo())
 		{  
 			editManager.redo();
 		}	
	}
///////////////////////////////DELETE TEXT//////////////////////////////////	
	
	void deleteText()
	{
		textArea.setText(textArea.getText().replace(textArea.getSelectedText(),""));
	}
/////////////////////////////////PRINT DATE AND TIME///////////////////////////////////	
	
	void printDate()
	{
		Date date=new Date();
	      SimpleDateFormat ft = new SimpleDateFormat ("E dd-MM-yy hh:mm a ");
        textArea.append(ft.format(date));
	}
////////////////////////////////SET CUSTOM FONT////////////////////////////////////////	
	
	void chooseFont()
	{
		JFontChooser fc = new JFontChooser();
		JOptionPane.showMessageDialog(null, fc, "Choose Font", JOptionPane.PLAIN_MESSAGE);
		textArea.setFont(fc.getPreviewFont());
	}
//////////////////////////////SET FONT COLOR////////////////////////////////////////	
	
	void chooseFontColor()
	{
		Color c= JColorChooser.showDialog(frame, "Choose Color", Color.black);
		textArea.setForeground(c);
	}
///////////////////////////////SET BACKGROUND COLOR////////////////////////////////////	
	
	void chooseBackgroundColor()
	{
		Color c= JColorChooser.showDialog(frame, "Choose Color", Color.black);
		textArea.setBackground(c);
	}
////////////////////////////////////CHECK CURRENT CURSOR POSITION//////////////////////////	
	
	void checkStatus()
	{
		if(chckbxStatusBar.isSelected())
		{
		
		JOptionPane.showMessageDialog(null, "Cursor On:- Coloum no:"+ (offset1+1) +" "+"Line no: "+( lineNo+1));		
	}
	}
//////////////////////////////////GO TO BROWSER FOR HELP/////////////////////////////////////////	
	
	void viewForHelp()
	{
		try {
  		  Desktop desktop = java.awt.Desktop.getDesktop();
  		  URI oURL = new URI("http://www.google.com");
  		  desktop.browse(oURL);
  		} catch (Exception e1) {
  		  e1.printStackTrace();
  		}
	}
////////////////////////////////INFORMATION ABOUT NOTEPAD///////////////////////////////////	
	
	void aboutNotepad()
	{   
		AboutNotepad screen = new AboutNotepad();
		screen.setVisible(true);
	}
////////////////////////////////COPY A FILE////////////////////////////	

	void copyFile()
	{
		String content=textArea.getText();
		//FileInputStream fi=new FileInputStream()
		
	}
//////////////////////////////EXIT FROM NOTEPAD/////////////////////////	
	
	void exitFile()
	{
		System.exit(0);
	}
////////////////////////////////PRINT THE FILE//////////////////////////	
	void PrintFile() throws PrinterException
	{
		textArea.print();
	}
//////////////////////////////WORD WRAPING STYLE/////////////////////////	
	
	void wordWrap()
	{
		if(mntmWordWrap.isSelected())
		{   textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
		}
		else
		{
			textArea.setWrapStyleWord(false);	
		}
	}
///////////////////////////////NOTEPAD CLASS CONSTRUCTOR//////////////////	
	
	public Notepad() {
		
		this.setTitle("Notepad");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
/////////////////////////////ADD COMPONENT LISTENER////////////////////////		
		
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				resizeTextArea();
			}
		});
///////////////////////////ADD WINDOW LISTENER/////////////////////////////		
		
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				if(textArea.getText().equals(""))
				{
				    System.exit(0);
				}
				else
				{
				int n=JOptionPane.showOptionDialog(null, "Do you want to Save Changes to Notepad ?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			     if(n==JOptionPane.YES_OPTION)
			    	 wsaveFile();
			     else if(n==JOptionPane.NO_OPTION)
			    	 System.exit(0); 
			     else if(n==JOptionPane.CANCEL_OPTION)
			     {
			    	frame.setVisible(true);
			    	
			     }
			    	 
			    	 }
			     }
			});
//////////////////////////////////ADDING SCROLL PANE WITH TEXT AREA///////////////		

        setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    scrollPane = new JScrollPane();
		scrollPane.setBounds(6,22,422,266);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.add(textArea);
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Arial",Font.PLAIN,16));
//////////////////////ADD MENU ITEM ON JPOP-UP MENU/////////////////////////////////		
	
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(textArea, popupMenu);
		
		JMenuItem mntmUndo_1 = new JMenuItem("Undo");
		popupMenu.add(mntmUndo_1);
		mntmUndo_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				undoText();
			}
		});
		
		JMenuItem mntmRedo_1 = new JMenuItem("Redo");
		popupMenu.add(mntmRedo_1);
		mntmRedo_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				redoText();
			}
		});
		
		JMenuItem mntmCut_1 = new JMenuItem("Cut");
		popupMenu.add(mntmCut_1);
		mntmCut_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.cut();
			}
		});
		
		JMenuItem mntmCopy_1 = new JMenuItem("Copy");
		popupMenu.add(mntmCopy_1);
		mntmCopy_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.copy();
			}
		});
		
		JMenuItem mntmPaste_1 = new JMenuItem("Paste");
		popupMenu.add(mntmPaste_1);
		mntmPaste_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.paste();
			}
		});
		
		JMenuItem mntmDelete_1 = new JMenuItem("Delete");
		popupMenu.add(mntmDelete_1);
		mntmDelete_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.setText(textArea.getText().replace(textArea.getSelectedText(),""));				
			
			
			}
		});
		
		JMenuItem mntmSelectAll_1 = new JMenuItem("Select All");
		popupMenu.add(mntmSelectAll_1);
		mntmSelectAll_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(textArea.getText().equals(""))
					return;
				else
		    textArea.selectAll();
			
			
			}
		});
		
/////////////ADD JMENU BAR, JMENU, JMENU ITEM////////////////////////////////		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(6, 0, 422, 21);
		contentPane.add(menuBar);
		
//////////////////////////ADD FILE MENU,MENU ITEMS/////////////////////////////		
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mnFile.add(mntmNew);
		mnFile.addSeparator();
		mntmNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				value=textArea.getText();
				openNewFrame();
			
					}
			});
		
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		mnFile.addSeparator();
		mntmOpen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				openFile();
				}
		});
		
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		mnFile.addSeparator();
		mntmSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveFile();
			}
		});
		
		
		JMenuItem mntmSaveAs = new JMenuItem("Save. As");
		mnFile.add(mntmSaveAs);
		mnFile.addSeparator();
		mntmSaveAs.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveFile();
			}
		});
		
		
		JMenuItem mntmPrint = new JMenuItem("Print");
		mnFile.add(mntmPrint);
		mnFile.addSeparator();
		mntmPrint.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					PrintFile();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				exitFile();
			}
		});
		
//////////////////ADD EDIT MENU,ITS MENU ITEMS////////////////////////////		
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
///////////////////////UNDO,REDO///////////////////////////////////////	    
		
		mntmUndo = new JMenuItem("Undo");
		mnEdit.add(mntmUndo);
		mnEdit.addSeparator();
		mntmUndo.setEnabled(false);
	    editManager=new UndoManager();
		Document document=textArea.getDocument();
		document.addUndoableEditListener(new UndoableEditListener() {
			
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				editManager.addEdit(e.getEdit());
				if(editManager.canUndo())
					mntmUndo.setEnabled(true);
				}
		});
		mntmUndo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				undoText();
				}
		});
	
		
		mntmRedo = new JMenuItem("Redo");
		mnEdit.add(mntmRedo);
		mnEdit.addSeparator();
		mntmRedo.setEnabled(false);
		mntmRedo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
	            redoText();
	           
			}
		});
		
		
	   JMenuItem mntmCut = new JMenuItem("Cut");
		mnEdit.add(mntmCut);
		mnEdit.addSeparator();
		mntmCut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.cut();
			}
		});
		
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mnEdit.add(mntmCopy);
		mnEdit.addSeparator();
		mntmCopy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//copyFile();
				textArea.copy();
			}
		});
		
		
		JMenuItem mntmPaste = new JMenuItem("Paste");
		mnEdit.add(mntmPaste);
		mnEdit.addSeparator();
		mntmPaste.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.paste();
			}
		});
		
		
		JMenuItem mntmDelete= new JMenuItem("Delete");
		mnEdit.add(mntmDelete);
		mnEdit.addSeparator();
		mntmDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				deleteText();				
			
			
			}
		});
		
		
		JMenuItem mntmSelectAll = new JMenuItem("Select ALL");
		mnEdit.add(mntmSelectAll);
		mnEdit.addSeparator();
		mntmSelectAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(textArea.getText().equals(""))
					return;
				else
		    textArea.selectAll();
			
			
			}
		});
		
		
		JMenuItem mntmTimedate = new JMenuItem("Time/Date");
		mnEdit.add(mntmTimedate);
		mntmTimedate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				printDate();
			}
		});
		
/////////////////////ADD FORMAT MENU/////////////////////////////////		
		
		
		JMenu mnFormat = new JMenu("Format");
		menuBar.add(mnFormat);
		
		mntmWordWrap = new JCheckBoxMenuItem("Word Wrap");
		mnFormat.add(mntmWordWrap);
		mnFormat.addSeparator();
		mntmWordWrap.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			    wordWrap();
			}
		});
		
		
		JMenuItem mntmFont = new JMenuItem("Font");
		mnFormat.add(mntmFont);
		mntmFont.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				chooseFont();
			}
		});
		
		
	    JMenuItem mntmFontColor = new JMenuItem("Font Color");
		mnFormat.add(mntmFontColor);
		mntmFontColor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				chooseFontColor();
			}
		});
		
		
		JMenuItem mntmBackgroundColor = new JMenuItem("Background Color");
		mnFormat.add(mntmBackgroundColor);
		mntmBackgroundColor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			chooseBackgroundColor();
			}
		});
		
		
///////////////////ADD VIEW MENU//////////////////////////////////////////		
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		
	    chckbxStatusBar = new JCheckBox("Status Bar");
		mnView.add(chckbxStatusBar);
            textArea.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
			
			
			  offset=textArea.getCaretPosition();
			  lineNo=0;
			try {
				lineNo = textArea.getLineOfOffset(offset);
			
			
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 offset1=offset-lineNo;
			}	

		});
		chckbxStatusBar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				checkStatus();
				}
		});
		
////////////////////////////ADD HELP MENU////////////////////////////////		
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmViewHelp = new JMenuItem("View Help");
		mnHelp.add(mntmViewHelp);
		mnHelp.addSeparator();
		
            
		
				mntmViewHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	viewForHelp();
                
		    }
		});
		
		
		JMenuItem mntmAboutNotepad = new JMenuItem("About Notepad");
		mnHelp.add(mntmAboutNotepad);
		mntmAboutNotepad .addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				aboutNotepad();
				
			}
		});
		
		
///////////////////////////ADD SHORTCUT KEYS/////////////////////////////////////
	
		mnFile.setMnemonic(KeyEvent.VK_F);  //SHIFT+F
		mnEdit.setMnemonic(KeyEvent.VK_E);  //SHIFT+E
		mnView.setMnemonic(KeyEvent.VK_V);  //SHIFT+V
		mnHelp.setMnemonic(KeyEvent.VK_H);  //SHIFT+H
		
////////////////////////////// USE THESE KEYS WITH CTRL+KEY/////////////////		
		
		KeyStroke new1=KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
		mntmNew.setAccelerator(new1);
		
		KeyStroke open=KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
		mntmOpen.setAccelerator(open);
		
		KeyStroke save=KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
		mntmSave.setAccelerator(save);
		
		KeyStroke print=KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK);
		mntmPrint.setAccelerator(print);
		
		KeyStroke quit=KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK);
		mntmExit.setAccelerator(quit);
		
		KeyStroke undo=KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
		mntmUndo.setAccelerator(undo);
		
		KeyStroke redo=KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
		mntmRedo.setAccelerator(redo);
		
		mntmDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		
		mntmSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		
		KeyStroke cut=KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK);
		mntmCut.setAccelerator(cut);
		
		KeyStroke copy=KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
		mntmCopy.setAccelerator(copy);
		
		KeyStroke paste=KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK);
		mntmPaste.setAccelerator(paste);
		
		
		mntmTimedate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
		
		
		KeyStroke wwrap=KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK);
		mntmWordWrap.setAccelerator(wwrap);
		
		KeyStroke font=KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
		mntmFont.setAccelerator(font);
		
		KeyStroke fcolor=KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK);
		mntmFontColor.setAccelerator(fcolor);
		
		KeyStroke bcolor=KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK);
		mntmBackgroundColor.setAccelerator(bcolor);
		}
	
////////////////////////ADD MOUSE LISTENER////////////////////////////////////
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
/////////////////////////////////END OF CODE?////////////////////////////////////////////