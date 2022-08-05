//NIKOS NIKOLOPOULOS-2o EKSAMINO-3160115
//GIWRGOS SIGAS-2o EKSAMINO-3160158
//MIXALIS XRISTIDIS-2o EKSAMINO-3160192

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class mainApp extends JFrame implements ActionListener, MouseListener {
	private String type,selectedText;
	private int lastI=-1;
	private JButton but1;
	private JButton but2;
	private JButton but3;
	private JButton but4;
	private JButton but5;
	private JButton but6;
	private JButton but7;
	private JButton but8;
	private JList STlist;
	private JList SNlist;
	private JList Clist;
	private DefaultListModel Service_Typeslist;
	private DefaultListModel Service_Nameslist;
	private DefaultListModel Contract_list;
	private JFileChooser fileChooser;
	private Catalog myCatalog = new Catalog();
	private double Ex_I_Dis=0.1;
	private double Ex_Mob_Dis=0.05;
	private double Ex_MCard_Dis=0.02;
	private int code;
	
	public mainApp() {
		setTitle("Services-Contracts");
		drawFrame();
		but1.addActionListener(this);
		but2.addActionListener(this);
		but3.addActionListener(this);
		but4.addActionListener(this);
		but5.addActionListener(this);
		but6.addActionListener(this);
		but7.addActionListener(this);
		but8.addActionListener(this);
		STlist.addMouseListener(this);
		SNlist.addMouseListener(this);
		Clist.addMouseListener(this);
		setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == but1)
		{
			fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) 
			{
				String selectedFile = fileChooser.getSelectedFile().getName();
				myCatalog.loadfile(selectedFile);	
			}
		}
		else if(e.getSource()==but2)
		{
			fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) 
			{
				String selectedFile = fileChooser.getSelectedFile().getName();
				myCatalog.loadfile(selectedFile);
			}
			code=myCatalog.getConCode();
		}
		else if(e.getSource()==but3)
		{
			fileChooser = new JFileChooser();
			int returnValue = fileChooser.showSaveDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) 
			{
				String selectedFile = fileChooser.getSelectedFile().getName();
				myCatalog.createFile(selectedFile);
			}
		}
		else if(e.getSource()==but4)
		{
			Contract Con=null;
			int i=STlist.getSelectedIndex();
			int j=SNlist.getSelectedIndex();
			if(i!=-1 && j!=-1)
			{
				String timeStamp = new SimpleDateFormat("dd MMMM yyyy").format(Calendar.getInstance().getTime());
				String Name=(String)JOptionPane.showInputDialog(this, "Name: ");
				String Phone_num=(String)JOptionPane.showInputDialog(this, "Phone Number: ");
				String payment=(String)JOptionPane.showInputDialog(this, "Payment Method: ");
				String date = (String)JOptionPane.showInputDialog(this,"Date: ", timeStamp);
				
				
				String selectedText = (String)Service_Nameslist.getElementAt(j);
				selectedText=selectedText.substring(0,2);
				if(Name!=null && Phone_num!=null && payment!=null && date!=null)
				{
					if(type.equals("Internet"))
					{
						Con=new Contract(code,type,Ex_I_Dis,Name,Phone_num,date,payment,selectedText,0.0);
					}
					else if(type.equals("Mobile"))
					{
						Con=new Contract(code,type,Ex_Mob_Dis,Name,Phone_num,date,payment,selectedText,0.0,0.0,0);
					}
					else if(type.equals("M_Card"))
					{
						Con=new Contract(code,type,Ex_MCard_Dis,Name,Phone_num,date,payment,selectedText,0.0,0.0,0);
					}
					myCatalog.addContract(Con);
					Contract_list.addElement(code+"."+"Name: "+Name+" --- Service: "+selectedText);
					Frame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Contract succesfully added!","Information",JOptionPane.INFORMATION_MESSAGE);
					code++;
				}
				else
				{
					Frame frame = new JFrame();
					JOptionPane.showMessageDialog(frame,"Unsuccesfull submit!","Error",JOptionPane.ERROR_MESSAGE);
				}
				SNlist.clearSelection();
				Clist.clearSelection();
			}
		}
		else if(e.getSource() == but5)
		{
			int z=Clist.getSelectedIndex();
			if(z!=-1)
			{
				String selectedText1 = (String)Contract_list.getElementAt(z);
				selectedText1=selectedText1.substring(0,selectedText1.indexOf("."));
				int Contractnum=Integer.parseInt(selectedText1);
				
				if(myCatalog.getContract(Contractnum).getServ_type().equals("Internet"))
				{
					String data_used=JOptionPane.showInputDialog(this, "Data_Used: ");
					if(data_used!=null)
					{
						myCatalog.getContract(Contractnum).setData(Double.parseDouble(data_used));
						Frame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, "Contract succesfully Updated!","Information",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						Frame frame = new JFrame();
						JOptionPane.showMessageDialog(frame,"Unsuccesfull update!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					String sms=JOptionPane.showInputDialog(this, "SMS sent: ");
					String Call_time_M=JOptionPane.showInputDialog(this, "Call time to mobiles(secs): ");
					String Call_time_H=JOptionPane.showInputDialog(this, "Call time to homes(secs): ");
					if(sms!=null && Call_time_M!=null && Call_time_H!=null)
					{
						myCatalog.getContract(Contractnum).setSMS(Integer.parseInt(sms));
						myCatalog.getContract(Contractnum).setTime_Mobile(Double.parseDouble(Call_time_M));
						myCatalog.getContract(Contractnum).setTime_Home(Double.parseDouble(Call_time_H));
						Frame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, "Contract succesfully Updated!","Information",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						Frame frame = new JFrame();
						JOptionPane.showMessageDialog(frame,"Unsuccesfull update!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		else if(e.getSource() == but6)
		{
			int z=Clist.getSelectedIndex();
			if(z!=-1)
			{
				String selectedText1 = (String)Contract_list.getElementAt(z);
				selectedText1=selectedText1.substring(0,selectedText1.indexOf("."));
				int Contractnum=Integer.parseInt(selectedText1);
				Frame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,"This Contract's Cost:"+ myCatalog.getCost(Contractnum)+"E","Information",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(e.getSource() == but7)
		{
			int z=Clist.getSelectedIndex();
			if(z!=-1)
			{
				String selectedText1 = (String)Contract_list.getElementAt(z);
				selectedText1=selectedText1.substring(0,selectedText1.indexOf("."));
				int Contractnum=Integer.parseInt(selectedText1);
				Frame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,myCatalog.getBalance(Contractnum),"Information",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(e.getSource() == but8)
		{
			Contract_list.removeAllElements();
			STlist.clearSelection();
			SNlist.clearSelection();
			lastI=-1;
			for(int k=0;k<myCatalog.getContractSize();k++)
				{
					Contract_list.addElement(myCatalog.getContract(k).getCode()+"."
												+"Name: "+myCatalog.getContract(k).getName()
												+" --- Service: "+myCatalog.getContract(k).getServ_name());
				}
				
				
		}
	}
	
	public void mouseClicked(MouseEvent event) 
	{
		int i=STlist.getSelectedIndex();
		int j=SNlist.getSelectedIndex();
		int z=Clist.getSelectedIndex();
		if(j!=-1)
		{
			if(z!=-1)
			{
				SNlist.clearSelection();
				Clist.clearSelection();
			}
		}
		if (event.getClickCount() == 2)
		{
			
			if(lastI!=i)
			{
				z=-1;
				j=-1;
				Contract_list.removeAllElements();
				Service_Nameslist.removeAllElements();
				if(i==0)
				{
					type="Internet";
				}
				else if(i==1)
				{
					type="Mobile";
				}
				else if(i==2)
				{
					type="M_Card";
				}
				for(int k=0;k<myCatalog.getServiceSize();k++)
				{
					if(myCatalog.getS(k).getServ_type().equals(type))
					{
						Service_Nameslist.addElement(myCatalog.getS(k).getServ_name()+
													":  Fixed cost: "+myCatalog.getS(k).getF_cost()+
													" --- Discount: "+myCatalog.getS(k).getDiscount()*100+"%");
					}
				}
				for(int k=0;k<myCatalog.getContractSize();k++)
				{
					if(myCatalog.getContract(k).getServ_type().equals(type))
					{
						Contract_list.addElement(myCatalog.getContract(k).getCode()+"."
												+"Name: "+myCatalog.getContract(k).getName()
												+" --- Service: "+myCatalog.getContract(k).getServ_name());
					}
				}
				lastI=i;
			}
			if(j!=-1)
			{
				String selectedText = (String)Service_Nameslist.getElementAt(j);
				selectedText=selectedText.substring(0,2);
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, myCatalog.getS(myCatalog.FindService(selectedText)).print(),"Information",JOptionPane.INFORMATION_MESSAGE);
				SNlist.clearSelection();
			}
			if(z!=-1)
			{
				String selectedText1 = (String)Contract_list.getElementAt(z);
				selectedText1=selectedText1.substring(0,selectedText1.indexOf("."));
				int Contractnum=Integer.parseInt(selectedText1);
				JFrame frame = new JFrame();
				if(i==-1)
				{
					type=myCatalog.getContract(Contractnum).getServ_type();
				}
				if(type.equals("Internet"))
				{
					JOptionPane.showMessageDialog(frame, myCatalog.getContract(Contractnum).print()
															+"\n"+" Data Used: "+myCatalog.getContract(Contractnum).getData()+"GB","Information",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(frame, myCatalog.getContract(Contractnum).print()
													+"\n"+" Call Time to homes: "+myCatalog.getContract(Contractnum).getTime_Home()+" secs"
													+"\n"+" Call time to mobiles: "+myCatalog.getContract(Contractnum).getTime_Mobile()+" secs"
													+"\n"+" SMS sent:"+myCatalog.getContract(Contractnum).getSMS()+" SMS","Information",JOptionPane.INFORMATION_MESSAGE);
				}
				Clist.clearSelection();
			}
		}
	}
	public void mouseExited(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mousePressed(MouseEvent event){}
	
	private void drawFrame()
	{
		setBounds(100, 100, 700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		but1 = new JButton("Load Services");
		but2 = new JButton("Load Contracts");
		but3 = new JButton("Save Contracts");
		but4 = new JButton("Add Contract");
		but5 = new JButton("Update Contract");
		but6 = new JButton("Find Cost");
		but7 = new JButton("Find Balace");
		but8 = new JButton("All Contracts");
		
		Service_Typeslist= new DefaultListModel();
		Service_Typeslist.addElement("Internet");
		Service_Typeslist.addElement("Mobile");
		Service_Typeslist.addElement("M_Card");
		
		Service_Nameslist= new DefaultListModel();
		
		Contract_list=new DefaultListModel();	
	
		
		STlist=new JList(Service_Typeslist);
		SNlist =new JList(Service_Nameslist);
		Clist=new JList(Contract_list);
		
		JScrollPane TypesScroll= new JScrollPane(STlist);
		JScrollPane NamesScroll= new JScrollPane(SNlist);
		JScrollPane ContractsScroll=new JScrollPane(Clist);
		
		TypesScroll.setPreferredSize(new Dimension(200, 300));
		NamesScroll.setPreferredSize(new Dimension(200, 300));
		ContractsScroll.setPreferredSize(new Dimension(200, 300));
	
		JTabbedPane tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane);
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		
		JPanel paneButton = new JPanel();
		JPanel SLists = new JPanel();
		SLists.setLayout(new BorderLayout());
		JPanel Contracts=new JPanel();
		Contracts.setLayout(new FlowLayout());
		
		JPanel Contractbutt=new JPanel();
		Contractbutt.add(but4);
		
		paneButton.add(but1);
		paneButton.add(but2);
		paneButton.add(but3);
		
		SLists.add(TypesScroll, BorderLayout.LINE_START);
		SLists.add(NamesScroll, BorderLayout.CENTER);
		SLists.add(Contractbutt, BorderLayout.LINE_END);
		
		Contracts.add(ContractsScroll);
		Contracts.add(but8);
		Contracts.add(but5);
		Contracts.add(but6);
		Contracts.add(but7);
		
		
		
		JScrollPane ServicesScroll= new JScrollPane(SLists);
		
		cp.add(paneButton);
		tabbedPane.add("Services",ServicesScroll);
		tabbedPane.add("Contracts",Contracts);
		cp.add(tabbedPane);
	}
		
	
	
	
	
	public static void main(String[] args) 
	{
		mainApp frame = new mainApp();
	}
}