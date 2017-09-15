import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

public class AboutNotepad extends JFrame {

	private JPanel contentPane;
      
 	public AboutNotepad()
	{        
		/*System.out.println(object);
	      
     		this.addWindowListener(new WindowAdapter() {
			
             public void windowClosing(WindowEvent e) 
				{
            	      ((Window) object).setVisible(true);
            	      dispose();
            	 
				
			}});*/
			
		
				
			
		
		setTitle("About Notepad");
		setResizable(false);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 738, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(10, 11, 266, 361);
		ImageIcon icon=new ImageIcon(getClass().getClassLoader().getResource("prish.jpg.jpg"));
	     contentPane.setLayout(null);
	     lblNewLabel.setIcon(icon);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PRASHANT RAI");
		lblNewLabel_1.setBounds(286, 11, 363, 54);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Lucida Handwriting", Font.BOLD, 24));
		lblNewLabel_1.setBackground(new Color(0, 255, 255));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		lblNewLabel_2.setBounds(299, 76, 423, 23);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setText("Developed By:-The Three RKGITians");
		
		JLabel lblNewLabel_6 = new JLabel("PRASHANT RAI");
		lblNewLabel_6.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblNewLabel_6.setBounds(295, 101, 140, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("PRAVIN ANAND");
		lblNewLabel_7.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblNewLabel_7.setBounds(295, 123, 140, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("PRINSHU SONI");
		lblNewLabel_8.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblNewLabel_8.setBounds(295, 145, 140, 14);
		contentPane.add(lblNewLabel_8);
		
		
		
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_3.setBounds(299, 188, 423, 23);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setText("Version 1.00. © 2016 RKGIT COLLEGE.All Rights Reserved.");
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_4.setBounds(299, 222, 423, 23);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_4.setText("This Product is prepared with good Interface to perform all");
		
		JLabel lblNewLabel_9 = new JLabel("Notepad Operations.");
		lblNewLabel_9.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_9.setBounds(299, 256, 423, 23);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_5.setBounds(299, 290, 423, 23);
		contentPane.add(lblNewLabel_5);
		lblNewLabel_5.setText("This Product is Liscensed Under RKGIT.");
		
		JButton btnOk = new JButton("OK");
		btnOk.setBackground(Color.GREEN);
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOk.setBounds(611, 349, 89, 23);
		contentPane.add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    setVisible(false);  
			}
		});
		
		
			
		
	}
}
