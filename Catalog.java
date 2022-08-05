import java.io.*;
import java.util.*;

public class Catalog

{
	private boolean FTMobile,CTMobile,FTHome,CTHome,Balance,FSMS,CSMS,DataCharge,Data,Discount,Service_name,Type,Monthly_price,Contract_number,Customer,Phone_number,Activation_date;
	private int i;
	private int code=0;
	private ArrayList <Service> Services = new ArrayList <Service>();
	private ArrayList <Contract> Contracts = new ArrayList <Contract>();
	
	
	public Service getS(int num)
	{
		return Services.get(num);
	}
	public int getConCode()
	{
		return code;
	}
	public void addContract(Contract s)
	{
		Contracts.add(s);
	}
	
	public void addInternet(Internet s)
	{
		Services.add(s);
	}
	
	public void addMobile(Mobile s)
	{
		Services.add(s);
	}
	
	public void addM_Card(M_Card s)
	{
		Services.add(s);
	}
	
	public void listServices () 
	{
		for(int i=0;i<Services.size();i++)
		{
			System.out.print(Services.get(i).print()+"\n");
		}
	}
	
	public int getServiceSize()
	{
		return Services.size();
	}
	public int getContractSize()
	{
		return Contracts.size();
	}
	
	public Contract getContract(int num)
	{
		return Contracts.get(num);
	}
	
	public String getServType(String serv_name)
	{
		
		for(i=0;i<Services.size();i++)
		{
			if(Services.get(i).getServ_name().equals(serv_name))
			{
				break;
			}
			
		}
		return Services.get(i).getClass().getName();
		
	}
	public boolean CheckServices(String serv_name)
	{
		for(int i=0;i<Services.size();i++)
		{
			if(Services.get(i).getServ_name().equals(serv_name))
			{
				return true;
			}
		}
		return false;
	}
	
	public void listContracts(String serv_name)
	{
		for(int i=0;i<Contracts.size();i++)
		{
			if(getServType(Contracts.get(i).getServ_name()).equals(serv_name))
			{
				System.out.print(printContract(i,serv_name));
			}
		}
	}
	
	public String printContract(int c_num,String Serv_type)
	{
		if (Serv_type.equals("Internet"))
		{
			return Contracts.get(c_num).print()+"\n"+" Data Used: "+Contracts.get(c_num).getData()+"\n"+"\n";
		}
		else
		{
			return Contracts.get(c_num).print()+"\n"+" Call Time to homes: "+Contracts.get(c_num).getTime_Home()+"\n"+" Call time to mobiles: "+Contracts.get(c_num).getTime_Mobile()+"\n"+" SMS sent:"+Contracts.get(c_num).getSMS()+"\n"+"\n" ;
		}
	}
	public int FindService(String serv_name)
	{
		for(int j=0;j<Services.size();j++)
		{
			if(Services.get(j).getServ_name().equals(serv_name))
			{
				i=j;
			}
		}
		return i;
	}
	
	public double getCost(int c_num)
	{
		int index=FindService(Contracts.get(c_num).getServ_name());
		double Charge;
		
		if(getServType(Contracts.get(c_num).getServ_name()).equals("Internet"))
		{
			if(Contracts.get(c_num).getData()>((Internet)Services.get(index)).getData())
			{	
				Charge=(Contracts.get(c_num).getData()-((Internet)Services.get(index)).getData())*((Internet)Services.get(index)).getData_charge()+((Internet)Services.get(index)).getF_cost();
			}
			else
			{
				Charge=((Internet)Services.get(index)).getF_cost();
			}
			
			Charge=Charge-Charge*((Internet)Services.get(index)).getDiscount();
				
			return Charge-Charge*Contracts.get(c_num).getEx_discount();
			
			
		}
		else
		{
			if(Contracts.get(c_num).getTime_Home()/60>((Mobile)Services.get(index)).getFree_time_home())
			{
				Charge=(Contracts.get(c_num).getTime_Home()/60-((Mobile)Services.get(index)).getFree_time_home())*((Mobile)Services.get(index)).getCost_time_home();
			}
			else
			{
				Charge=0;
			}
			if(Contracts.get(c_num).getTime_Mobile()/60>((Mobile)Services.get(index)).getFree_time_mobile())
			{
				Charge=Charge+(Contracts.get(c_num).getTime_Mobile()/60-((Mobile)Services.get(index)).getFree_time_mobile())*((Mobile)Services.get(index)).getCost_time_mobile();
			}
			else
			{
				Charge=Charge+0;
			}
			if(Contracts.get(c_num).getSMS()>((Mobile)Services.get(index)).getFree_sms())
			{
				Charge=Charge+(Contracts.get(c_num).getSMS()-((Mobile)Services.get(index)).getFree_sms())*((Mobile)Services.get(index)).getCost_sms();
			}
			else
			{
				Charge=Charge+0;
			}
			
			Charge=Charge+((Mobile)Services.get(index)).getF_cost();
			
			Charge=Charge-Charge*((Mobile)Services.get(index)).getDiscount();
			
			return Charge-Charge*Contracts.get(c_num).getEx_discount();
		}
		
	}
	
	public String getBalance(int c_num)
	{
		double call_time_m_left,call_time_h_left,balance,cost;
		int sms_left;
		int index=FindService(Contracts.get(c_num).getServ_name());
		if(getServType(Contracts.get(c_num).getServ_name()).equals("Internet"))
		{
			if(Contracts.get(c_num).getData()<((Internet)Services.get(index)).getData())
			{	
				return "this.contract has: "+(((Internet)Services.get(index)).getData()-Contracts.get(c_num).getData()+" MB left.");
			}
			else
			{
				return "this Contract has no free data left.";
			}
		}
		else
		{
			if(Contracts.get(c_num).getTime_Home()/60<((Mobile)Services.get(index)).getFree_time_home())
			{
				call_time_h_left=((Mobile)Services.get(index)).getFree_time_home()-Contracts.get(c_num).getTime_Home()/60;
			}
			else
			{
				call_time_h_left=0.0;
			}
			if(Contracts.get(c_num).getTime_Mobile()/60<((Mobile)Services.get(index)).getFree_time_mobile())
			{
				call_time_m_left=((Mobile)Services.get(index)).getFree_time_mobile()-Contracts.get(c_num).getTime_Mobile()/60;
			}
			else
			{
				call_time_m_left=0.0;
			}
			if(Contracts.get(c_num).getSMS()<((Mobile)Services.get(index)).getFree_sms())
			{
				sms_left=((Mobile)Services.get(index)).getFree_sms()-Contracts.get(c_num).getSMS();
			}
			else
			{
				sms_left=0;
			}
			if(getServType(Contracts.get(c_num).getServ_name()).equals("Mobile"))
			{
				return "This Contract has: \nCall time to home: "+call_time_h_left+" mins\nCall time to mobiles: "+call_time_m_left+" mins\nSMS : "+sms_left;
			}
			else
			{
				cost=getCost(c_num);
				if(cost<((M_Card)Services.get(index)).getBalance())
				{
					balance=((M_Card)Services.get(index)).getBalance()-cost;
				}
				else
				{
					balance=0.0;
				}
				return "This Contract has: \nFree call time to homes: "+call_time_h_left+" mins \nFree call time to mobiles: "+call_time_m_left+" mins \nFree SMS : "+sms_left+" \nbalance:"+balance;
			}	
		}
			
		
	}
	
	public void loadfile(String file)
	{
		int counter=0;
		
		File f = null;
        BufferedReader reader = null;
		String line;

        try 
		{
            f = new File(file);
        } 
		catch (NullPointerException e) //an den vriskei to antikeimeno txt
		{
            System.err.println("File not found.");
        }

        try 
		{
            reader = new BufferedReader(new FileReader(f));
        } 
		catch (FileNotFoundException e) //an den anoigei
		{
            System.err.println("Error opening file!");
        }
		try
		{
			line=reader.readLine();
			if(line.trim().equalsIgnoreCase("SERVICE_LIST"))
			{
				line=reader.readLine();
				if(line.trim().equals("{"))
				{
					while(line!=null)
					{	
						if(line.trim().equalsIgnoreCase("SERVICE"))
						{
							FTMobile=false;
							CTMobile=false;
							FTHome=false;
							CTHome=false;
							Balance=false;
							FSMS=false;
							CSMS=false;
							Discount=false;
							DataCharge=false;
							Data=false;
							Service_name=false;
							Monthly_price=false;
							Service serv=null;
							line=reader.readLine();
							if(line.trim().equals("{"))
							{
								reader.mark(1000);
								while(serv==null && !line.trim().equals("}"))
								{
									line=reader.readLine();
									if(line.trim().toUpperCase().startsWith("TYPE"))
									{
										if(line.trim().substring(5).trim().equals("Mobile"))
										{
											serv=new Mobile();
											serv.setServ_type("Mobile");
										}
										else if(line.trim().substring(5).trim().equals("Internet"))
										{
											serv=new Internet();
											serv.setServ_type("Internet");
										}
										else if(line.trim().substring(5).trim().equals("M_Card"))
										{
											serv=new M_Card();
											serv.setServ_type("M_Card");
										}
										reader.reset();
									}
								}
								while(!line.trim().equals("}") && serv!=null)
								{
									line=reader.readLine();
									
									if(line.trim().toUpperCase().startsWith("SERVICE_NAME"))
									{
										StringTokenizer st=new StringTokenizer(line.trim().substring(13).trim().substring(1));
										serv.setServ_name(st.nextToken("\""));
										Service_name=true;
									}
									
									else if(line.trim().toUpperCase().startsWith("MONTHLY_PRICE"))
									{
										serv.setF_cost(Double.parseDouble(line.trim().substring(14).trim()));
										Monthly_price=true;
									}
									
									else if(line.trim().toUpperCase().startsWith("DISCOUNT"))
									{
										serv.setDiscount(Double.parseDouble(line.trim().substring(9).trim()));
										Discount=true;
									}
									
									else if(line.trim().toUpperCase().startsWith("FREE_DATA")&& serv.getClass().getName().equals("Internet"))
									{
										((Internet)serv).setData(Double.parseDouble(line.trim().substring(10).trim()));
										Data=true;
									}
									
									else if(line.trim().toUpperCase().startsWith("DATA_CHARGE")&& serv.getClass().getName().equals("Internet"))
									{
										((Internet)serv).setData_charge(Double.parseDouble(line.trim().substring(12).trim()));
										DataCharge=true;
									}
									
									else if(line.trim().toUpperCase().startsWith("FREE_MOBILE_MINUTES")&&(serv.getClass().getName().equals("Mobile") ||serv.getClass().getName().equals("M_Card")))
									{
										((Mobile)serv).setFree_Time_Mobile(Double.parseDouble(line.trim().substring(20).trim()));
										FTMobile=true;
									}
									
									else if(line.trim().toUpperCase().startsWith("FREE_HOME_MINUTES")&&(serv.getClass().getName().equals("Mobile") ||serv.getClass().getName().equals("M_Card")))
									{
										((Mobile)serv).setFree_Time_Home(Double.parseDouble(line.trim().substring(18).trim()));
										FTHome=true;
									}
									
									else if(line.trim().toUpperCase().startsWith("FREE_SMS")&&(serv.getClass().getName().equals("Mobile") ||serv.getClass().getName().equals("M_Card")))
									{
										((Mobile)serv).setFree_sms(Integer.parseInt(line.trim().substring(9).trim()));
										FSMS=true;
									}
									
									else if(line.trim().toUpperCase().startsWith("COST_MOBILE_PERMIN")&&(serv.getClass().getName().equals("Mobile") ||serv.getClass().getName().equals("M_Card")))
									{
										((Mobile)serv).setCost_Time_Mobile(Double.parseDouble(line.trim().substring(19).trim()));
										CTMobile=true;
									}
									
									else if(line.trim().toUpperCase().startsWith("COST_HOME_PERMIN")&&(serv.getClass().getName().equals("Mobile") ||serv.getClass().getName().equals("M_Card")))
									{
										((Mobile)serv).setCost_Time_Home(Double.parseDouble(line.trim().substring(17).trim()));
										CTHome=true;
									}
									
									else if(line.trim().toUpperCase().startsWith("COST_SMS")&&(serv.getClass().getName().equals("Mobile") ||serv.getClass().getName().equals("M_Card")))
									{
										((Mobile)serv).setCost_sms(Double.parseDouble(line.trim().substring(9).trim()));
										CSMS=true;
									}
									
									else if(line.trim().toUpperCase().startsWith("BALANCE")&&serv.getClass().getName().equals("M_Card"))
									{
										((M_Card)serv).setBalance(Double.parseDouble(line.trim().substring(8).trim()));
										Balance=true;
									}
									
									else if(line.trim().equals("}")&& Monthly_price && Service_name)
									{
										if(Monthly_price && Service_name)
										{
											if(serv.getServ_type().equals("Internet"))
											{
												if(!DataCharge)
												{
													((Internet)serv).setData_charge(1.0);
												}
												if(!Data)
												{
													((Internet)serv).setData(500);
												}
												if(!Discount)
												{
													serv.setDiscount(0.2);
												}
											}
											else if(serv.getServ_type().equals("Mobile") ||serv.getServ_type().equals("M_Card"))
											{
												if(!FSMS)
												{
													((Mobile)serv).setFree_sms(100);
												}
												if(!CSMS)
												{
													((Mobile)serv).setCost_sms(1.0);
												}
												if(!FTMobile)
												{
													((Mobile)serv).setFree_Time_Mobile(200);
												}
												if(!CTMobile)
												{
													((Mobile)serv).setCost_Time_Mobile(1.0);
												}
												if(!FTHome)
												{
													((Mobile)serv).setFree_Time_Home(100);
												}
												if(!CTHome)
												{
													((Mobile)serv).setCost_Time_Home(2.0);
												}
												if(!Discount)
												{
													serv.setDiscount(0.3);
												}
												if(serv.getServ_type().equals("M_Card"))
												{
													if(!Balance)
													{
														((M_Card)serv).setBalance(5.0);
													}
												}
											}
											Services.add(serv);
										}
										else
										{
											System.out.println("Not enough inputs for this Service..");
										}
									}
								}
								if(serv==null)
								{
									System.out.println("Couldnt find Service Type");
								}
							}
						}
						line=reader.readLine();
					}
				}
			}
			else if(line.trim().equalsIgnoreCase("CONTRACT_LIST"))
			{
				line=reader.readLine();
				if(line.trim().equals("{"))
				{
					while(line!=null)
					{
						if(line.trim().equalsIgnoreCase("CONTRACT"))
						{
							Contract_number=false;
							Service_name=false;
							Customer=false;
							Type=false;
							Phone_number=false;
							Activation_date=false;
							Contract con=new Contract();
							line=reader.readLine();
							if(line.trim().equals("{"))
							{
								while(true)
								{
									line=reader.readLine();
									if(line.trim().toUpperCase().startsWith("CONTRACT_NUMBER"))
									{
										con.setCode(Integer.parseInt(line.trim().substring(16).trim()));
										Contract_number=true;
									}
									else if(line.trim().toUpperCase().startsWith("SERVICE_NAME"))
									{
										StringTokenizer st=new StringTokenizer(line.trim().substring(13).trim().substring(1));
										con.setServ_name(st.nextToken("\""));
										Service_name=Check_Service(con.getServ_name());
									}
									else if(line.trim().toUpperCase().startsWith("TYPE"))
									{
										con.setServ_type(line.trim().substring(5).trim());
										Type=true;
									}
									else if(line.trim().toUpperCase().startsWith("CUSTOMER"))
									{
										StringTokenizer st=new StringTokenizer(line.trim().substring(9).trim().substring(1));
										con.setName(st.nextToken("\""));
										Customer=true;
									}
									else if(line.trim().toUpperCase().startsWith("PHONE_NUMBER"))
									{
										StringTokenizer st=new StringTokenizer(line.trim().substring(13).trim().substring(1));
										con.setPhone_num(st.nextToken("\""));
										Phone_number=true;
									}
									else if(line.trim().toUpperCase().startsWith("ACTIVATION_DATE"))
									{
										StringTokenizer st=new StringTokenizer(line.trim().substring(16).trim().substring(1));
										con.setDate(st.nextToken("\""));
										Activation_date=true;
									}
									else if(line.trim().toUpperCase().startsWith("PAYMENT"))
									{
										con.setPayment(line.trim().substring(8).trim());
									}
									else if(line.trim().toUpperCase().startsWith("DISCOUNT"))
									{
										con.setEx_discount(Double.parseDouble(line.trim().substring(9).trim()));
									}
									else if(line.trim().toUpperCase().startsWith("MONTHLY USAGE"))
									{
										line=reader.readLine();
										while(!line.trim().equals("{"))
										{
											line=reader.readLine();
										}
										if(line.trim().equals("{"))
										{
											while(!line.trim().equals("}"))
											{
												line=reader.readLine();
												
												if(line.trim().toUpperCase().startsWith("MOBILE")&& (!con.getServ_type().equals("Internet")))
												{
													con.setTime_Mobile(Double.parseDouble(line.trim().substring(7).trim()));
												}
												
												else if(line.trim().toUpperCase().startsWith("FIXED")&& (!con.getServ_type().equals("Internet")))
												{
													con.setTime_Home(Double.parseDouble(line.trim().substring(6).trim()));
												}
												
												else if(line.trim().toUpperCase().startsWith("SMS")&& (!con.getServ_type().equals("Internet")))
												{
													con.setSMS(Integer.parseInt(line.trim().substring(4).trim()));
												}
											
												else if(line.trim().toUpperCase().startsWith("DATA")&& (con.getServ_type().equals("Internet")))
												{
													con.setData(Double.parseDouble(line.trim().substring(5).trim()));
												}
											}
											
										}
									}
									else if(line.trim().equals("}"))
									{
										if(Contract_number && Service_name && Customer && Type &&Phone_number&&Activation_date)
										{
											Contracts.add(con);
											code++;
										}
										else
										{
											System.out.println("Unavailable Contract Type");
										}
										break;
									}
								}
							}
						}
						line=reader.readLine();
						
					}
				}
			}
			
		}
		catch (IOException e) 
		{
            System.out.println("Error reading line " + counter + ".");
        }
		try 
		{
           reader.close();
		}
		catch (IOException e) 
		{
			System.err.println("Error closing file.");
        }
	}
	
	public boolean Check_Service(String service_type)
	{
		for(int i=0;i<Services.size();i++)
		{
			if(Services.get(i).getServ_name().equals(service_type))
			{
				return true;
			}
		}
		return false;
	}
	
	public void createFile  (String path) 
	{
		String newline = System.getProperty("line.separator");
		File f = null;
		BufferedWriter writer = null;

		try	
		{
			f = new File(path);
		}
		catch (NullPointerException e) 
		{
			System.err.println ("Error creating file.");
		}

		try	
		{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
		}
		catch (FileNotFoundException e) 
		{
			System.err.println("Error opening file for writing!");
		}
		try				
		{
			writer.write("CONTRACT_LIST"+ newline +"{");
			for (Contract con:Contracts) 
			{
				writer.write(newline+"\t"+"CONTRACT"+newline+"\t"+"{"+
							newline +"\t"+"\t"+"CONTRACT_NUMBER "+con.getCode()+
							newline +"\t"+"\t"+"SERVICE_NAME "+"\""+con.getServ_name()+"\""+
							newline +"\t"+"\t"+"TYPE "+con.getServ_type()+
							newline +"\t"+"\t"+"CUSTOMER "+"\""+con.getName()+"\""+
							newline +"\t"+"\t"+"PHONE_NUMBER "+"\""+con.getPhone_num()+"\""+
							newline +"\t"+"\t"+"ACTIVATION_DATE "+"\""+con.getDate()+"\""+
							newline +"\t"+"\t"+"DISCOUNT "+con.getEx_discount()+
							newline +"\t"+"\t"+"PAYMENT "+con.getPayment()+
							newline +"\t"+"\t"+"MONTHLY USAGE"+ newline +"\t\t{");
				if(con.getServ_type().equals("Mobile")||con.getServ_type().equals("M_Card"))
				{
					writer.write(newline +"\t"+"\t"+"\t"+"MOBILE "+((con.getTime_Mobile()))+
								newline +"\t"+"\t"+"\t"+"FIXED "+((con.getTime_Home()))+
								newline +"\t"+"\t"+"\t"+"SMS "+con.getSMS()+
								newline +"\t"+"\t"+"}");
				}
				else if (con.getServ_type().equals("Internet"))
				{
					writer.write(newline+"\t"+"\t"+"\t"+"DATA "+con.getData()+
							newline+"\t"+"\t"+"}");
				}
				writer.write(newline+"\t"+"}");
			}
			writer.write(newline+"}");
		}
		catch (IOException e)
		{
			System.err.println("Write error!");
		}
		try 
		{
			writer.close();
		}
		catch (IOException e)
		{
			System.err.println("Error closing file.");
		}
	}
}