/**
* @ author: Shihab Ahmed
* @ version: 1.00
* @ since: 03-05-2016
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GPA implements ActionListener{
	JFrame f;
	JPanel mainPanel,p1,p2,p3,p4,p5;
	CardLayout cl;
	JButton b1,b2,b3,b4,b5,b6,b7,b8,home,home2,home3,home4;
	JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8,lbl9,lbl10,pic,subjectNo[];
	JTextField tf;
	JTextArea ta[],ta1[],ta2;
	//JScrollPane sp;
	Image img;
	String s1;
	int subject=0,subHeight=70;
	float product=0,totalCredit=0,totalgp=0;
	boolean flag=false;
	
	//CONSTRUCTOR
	public GPA(){
		//instantiation
		f = new JFrame("Grade Point Calculator");
		cl = new CardLayout();
		mainPanel = new JPanel(cl);
		p1 = new JPanel();
		b1 = new JButton("GPA");
		b2 = new JButton("SGPA");
		b3 = new JButton("CGPA");
		b4 = new JButton("Help");
		b5 = new JButton("Next");
		b6 = new JButton("GPA");
		b7 = new JButton("SGPA");
		b8 = new JButton("CGPA");
		home = new JButton("HOME");//second panel home button
		home2 = new JButton();//third panel home button
		home2.setIcon(new ImageIcon(getClass().getResource("home2.png")));//setting icon
		home3 = new JButton("HOME");//fourth panel home button
		home4 = new JButton("HOME");//fifth panel home button
		lbl1 = new JLabel("Enter Total Subject");
		lbl2 = new JLabel("Grade Point Average");
		lbl3 = new JLabel("Subject");
		lbl4 = new JLabel("Grade Point");
		lbl5 = new JLabel("Semester Grade Point");
		lbl6 = new JLabel("Credits");
		lbl7 = new JLabel("Cumulative Grade Point");
		lbl8 = new JLabel("Enter Total Semester");
		lbl9 = new JLabel("<html>Total Point<br>secured</html>");
		lbl10 = new JLabel("*Additional");
		tf = new JTextField();
		//******* Panel 1 *******
		p1.setLayout(null);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		b1.setBounds(30,100,70,30);
		b2.setBounds(105,100,70,30);
		b3.setBounds(180,100,70,30);
		b4.setBounds(180,150,70,30);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b5.addActionListener(this);//2nd panel next button
		home.addActionListener(this);//2nd panel home button
		//******main panel*****
		mainPanel.add(p1,"first");
		//******frame******
		f.setSize(300,450);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.add(mainPanel);
		f.setVisible(true);
	}
	//********Event Handling*********
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == b1){ //gpa button
			s1 = "gpa";
			this.secondCard();
			cl.show(mainPanel,"second");
			
		}else if(ae.getSource() == b2){ //sgpa button
			s1 ="sgpa";
			this.secondCard();
			cl.show(mainPanel,"second");
		}else if(ae.getSource() == b3){ //cgpa button
			s1 ="cgpa";
			this.secondCard();
			cl.show(mainPanel,"second");
		}else if(ae.getSource() == b5){ //2nd panel next button
			try{
				subject = Integer.parseInt(tf.getText());
				if(s1 == "gpa"){
					this.gpa(subject);
				}else if(s1 == "sgpa"){
					this.sgpa(subject);
				}else{
					this.cgpa(subject);
				}	
			}catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(null,"Enter The Value","Warning",JOptionPane.WARNING_MESSAGE);
			}
		}else if(ae.getSource() == home||ae.getSource() == home2||ae.getSource() == home3||ae.getSource() == home4){
			flag = false;
			cl.show(mainPanel,"first");
		}else{ //showing the result (button6,button7,button8)
			if(s1 == "gpa"){
				this.gpa(subject);
			}else if(s1 == "sgpa"){
				this.sgpa(subject);
			}else{
				this.cgpa(subject);
			}
		}
	}
	//*******Second Card*******
	public void secondCard(){
		p2 = new JPanel();
		p2.setLayout(null);
		if(s1=="cgpa"){
			p2.add(lbl8);//showing total semester label
			lbl8.setBounds(80,70,150,30);
		}else{
			p2.add(lbl1);//showing total subject label
			lbl1.setBounds(80,70,150,30);
		}
		img = Toolkit.getDefaultToolkit().createImage("gpa6.png");
		pic = new JLabel(new ImageIcon(img));
		pic.setBounds(0,0,300,300);
		p2.add(home);
		p2.add(tf);
		p2.add(b5);
		p2.add(pic);//it must be added at last nor it will cover other components
		home.setBounds(200,30,70,20);
		tf.setBounds(100,95,25,30);
		b5.setBounds(140,95,70,30);
		mainPanel.add(p2,"second");
	}
	//*******GPA*******
	public void gpa(int count){
		home2.addActionListener(this);
		b6.addActionListener(this);
		if(flag == false){
			p3 = new JPanel();
			p3.setLayout(null);
			p3.add(lbl2);
			p3.add(home2);
			p3.add(lbl3);
			p3.add(lbl4);
			lbl2.setBounds(80,5,150,30);//Grade Point Average
			home2.setBounds(200,30,70,20);
			lbl3.setBounds(80,50,80,20);//subject
			lbl4.setBounds(150,50,80,20);//grade point
			ta = new JTextArea[count];
			subjectNo = new JLabel[count];
			subHeight=70;
			for(int i=0;i<count;i++){
				subjectNo[i] = new JLabel(String.valueOf(i+1)); //dynamic subjectNo
				ta[i] = new JTextArea();//dynamic textarea
				p3.add(subjectNo[i]);
				p3.add(ta[i]);
				subjectNo[i].setBounds(100,subHeight,15,20);
				ta[i].setBounds(160,subHeight,50,20);
				subHeight = subHeight+25;
			}
			ta2 = new JTextArea();//result showing textarea
			p3.add(b6);
			p3.add(ta2);
			p3.add(lbl10);
			b6.setBounds(95,subHeight,60,20);
			ta2.setBounds(160,subHeight,50,20);
			lbl10.setBounds(215,subHeight-25,80,20);
			mainPanel.add(p3,"third");
			cl.show(mainPanel,"third");
			flag = true;
		}else{ //gpa result calculation
			totalgp=0;
			for(int i=0;i<count-1;i++){
				totalgp = totalgp+(Float.parseFloat(ta[i].getText()));
			}
			totalgp = totalgp/(count-1);
			//4th subject gpa calculation
			if(totalgp<5){
				totalgp = totalgp+((Float.parseFloat(ta[count-1].getText())-2)/(count-1));
				if(totalgp>5){
					totalgp=5;
				}
			}
			ta2.setText(String.valueOf(totalgp));
		}
	}
	//*******SGPA**********
	public void sgpa(int count){
		home3.addActionListener(this);
		b7.addActionListener(this);
		if(flag==false){
			p4 = new JPanel();
			p4.setLayout(null);
			p4.add(lbl5);
			p4.add(home3);
			p4.add(lbl3);
			p4.add(lbl4);
			p4.add(lbl6);
			lbl5.setBounds(80,5,150,30);//semester grade point
			home3.setBounds(200,30,70,20);
			lbl3.setBounds(40,50,80,20);
			lbl4.setBounds(110,50,80,20);
			lbl6.setBounds(185,50,50,20);
			ta = new JTextArea[count];//grade point textarea
			ta1 = new JTextArea[count];//credits textarea
			subjectNo = new JLabel[count];
			subHeight=70;
			for(int i=0;i<count;i++){
				subjectNo[i] = new JLabel(String.valueOf(i+1));
				ta[i] = new JTextArea();
				ta1[i] = new JTextArea();
				p4.add(ta[i]);
				p4.add(ta1[i]);
				p4.add(subjectNo[i]);
				ta[i].setBounds(120,subHeight,50,20);
				ta1[i].setBounds(180,subHeight,50,20);
				subjectNo[i].setBounds(60,subHeight,15,20);
				subHeight = subHeight+25;
			}
			ta2 = new JTextArea();
			p4.add(b7);
			b7.setBounds(100,subHeight,75,20);
			p4.add(ta2);
			ta2.setBounds(180,subHeight,50,20);
			mainPanel.add(p4,"fourth");
			cl.show(mainPanel,"fourth");
			flag=true;
		}else{ //sgpa calculation
			product=0;totalCredit=0;
			for(int i=0;i<count;i++){
				product = product+(Float.parseFloat(ta[i].getText())*Float.parseFloat(ta1[i].getText()));
				totalCredit = totalCredit+(Float.parseFloat(ta1[i].getText()));
			}
			ta2.setText(String.valueOf(product/totalCredit)); //total grade point divided by totalCredit
		}
	}
	//************CGPA************
	public void cgpa(int count){
		home4.addActionListener(this);
		b8.addActionListener(this);
		if(flag == false){
			p5 = new JPanel();
			p5.setLayout(null);
			p5.add(lbl7);
			p5.add(home4);
			p5.add(lbl3);
			p5.add(lbl9);
			p5.add(lbl6);
			lbl7.setBounds(80,5,150,30);
			home4.setBounds(200,30,70,20);
			lbl3.setBounds(40,50,80,20);
			lbl9.setBounds(110,40,80,30);//TPS
			lbl6.setBounds(185,50,50,20);//credits
			ta = new JTextArea[count];
			ta1 = new JTextArea[count];
			subjectNo = new JLabel[count];
			subHeight=70;
			for(int i=0;i<count;i++){
				subjectNo[i] = new JLabel(String.valueOf(i+1));
				ta[i] = new JTextArea();
				ta1[i] = new JTextArea();
				p5.add(subjectNo[i]);
				p5.add(ta[i]);
				p5.add(ta1[i]);
				subjectNo[i].setBounds(60,subHeight,15,20);
				ta[i].setBounds(120,subHeight,30,20);
				ta1[i].setBounds(180,subHeight,50,20);
				subHeight = subHeight+25;
			}
			ta2 = new JTextArea();
			p5.add(b8);
			p5.add(ta2);
			b8.setBounds(100,subHeight,70,20);
			ta2.setBounds(180,subHeight,50,20);
			mainPanel.add(p5,"fifth");
			cl.show(mainPanel,"fifth");
			flag = true;
		}else{ //cgpa calculation part
			product=0;totalCredit=0;
			for(int i=0;i<count;i++){
				product = product+(Float.parseFloat(ta[i].getText()));
				totalCredit = totalCredit+(Float.parseFloat(ta1[i].getText()));
			}
			System.out.println(product+" "+totalCredit+" "+(product/totalCredit));
			ta2.setText(String.valueOf(product/totalCredit));
		}
	}
	public static void main(String []args){
		GPA obj = new GPA();
	}
}