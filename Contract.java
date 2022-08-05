public class Contract
{
	private int code;
	private String serv_name;
	private String name;
	private String phone_num;
	private String date;
	private String payment;
	private double extra_discount;
	private double data_used;
	private double time_home;
	private double time_mobile;
	private int sms;
	private String serv_type;
	
	public Contract(){
		
	}
	
	public Contract(int code,String serv_type,double extra_discount,String name,String phone_num,String date,String payment,String serv_name,double data_used)//constructor for internet
	{
		this.name=name;
		this.phone_num=phone_num;
		this.date=date;
		this.payment=payment;
		this.serv_name=serv_name;
		this.data_used=data_used;
		this.extra_discount=extra_discount;
		this.code=code;
		this.serv_type=serv_type;
	}
	
	public Contract(int code,String serv_type,double extra_discount,String name,String phone_num,String date,String payment,String serv_name,double time_home, double time_mobile, int sms)//constructor for mobile
	{
		this.name=name;
		this.phone_num=phone_num;
		this.date=date;
		this.payment=payment;
		this.serv_name=serv_name;
		this.time_home=time_home;
		this.time_mobile=time_mobile;
		this.sms=sms;
		this.extra_discount=extra_discount;
		this.code=code;
		this.serv_type=serv_type;
	}
	
	public double getData()
	{
		return this.data_used;
	}
	
	public void setData(double data_used)
	{
		this.data_used=data_used;
	}
	
	public void setTime_Home(double time_home)
	{
		this.time_home=time_home;
	}
	public double getTime_Home()
	{
		return this.time_home;
	}
	
	
	public void setTime_Mobile(double time_mobile)
	{
		this.time_mobile=time_mobile;
	}
	
	public double getTime_Mobile()
	{
		return this.time_mobile;
	}
	
	public void setSMS(int sms)
	{
		this.sms=sms;
	}
	
	public void setCode(int code)
	{
		this.code=code;
	}
	
	public void setServ_name(String serv_name)
	{
		this.serv_name=serv_name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setPhone_num(String phone_num)
	{
		this.phone_num=phone_num;
	}
	
	public String getPhone_num()
	{
		return this.phone_num;
	}
	
	public void setDate(String date)
	{
		this.date=date;
	}
	
	public String getDate()
	{
		return this.date;
	}
	
	public void setPayment(String payment)
	{
		this.payment=payment;
	}
	
	public String getPayment()
	{
		return this.payment;
	}
	
	public void setEx_discount(double extra_discount)
	{
		this.extra_discount=extra_discount;
	}
	
	public void setServ_type(String serv_type)
	{
		this.serv_type=serv_type;
	}
	public String getServ_type()
	{
		return this.serv_type;
	}
	
	public int getSMS()
	{
		return this.sms;
	}
	
	public int getCode()
	{
		return this.code;
	}
	
	public String getServ_name()
	{
		return this.serv_name;
	}
	public double getEx_discount()
	{
		return this.extra_discount;
	}
	
	public String print()
	{
		return "Contract Code: "+this.code+"\n Service: "+this.serv_name+"\n Type:"+this.serv_type+"\n Name: "+this.name+"\n Phone: "+this.phone_num+"\n Date of Payment: "+this.date+"\n Payment(Credit Card or Cash): "+this.payment+"\n "+"Extra Discount: "+this.extra_discount*100+"%";
		
	}
}