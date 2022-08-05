public class Mobile extends Service{
	
	private double free_time_mobile;
	private double cost_time_mobile;
	private int free_sms;
	private double cost_sms;
	private double free_time_home;
	private double cost_time_home;
	
	public Mobile()
	{
		super();
	}
	
	public Mobile(String serv_name,String serv_type,double discount,double f_cost,double free_time_mobile, double cost_time_mobile, int free_sms, double cost_sms,double free_time_home, double cost_time_home)
	{
		super(serv_name,serv_type,f_cost,discount);
		this.free_time_mobile=free_time_mobile;
		this.cost_time_mobile=cost_time_mobile;
		this.free_sms=free_sms;
		this.cost_sms=cost_sms;
		this.free_time_home=free_time_home;
		this.cost_time_home=cost_time_home;
	}
	
	public double getFree_time_mobile()
	{
		return this.free_time_mobile;
	}
	
	public double getCost_time_mobile()
	{
		return this.cost_time_mobile;
	}
	public double getFree_time_home()
	{
		return this.free_time_home;
	}
	
	public double getCost_time_home()
	{
		return this.cost_time_home;
	}
	
	public int getFree_sms()
	{
		return this.free_sms;
	}
	
	public double getCost_sms()
	{
		return this.cost_sms;
	}
	
	public String print()
	{
		return super.print()+" Free calling time(mobiles): "+this.free_time_mobile+" mins"+"\n"+" Free SMS: "+this.free_sms+" sms\n"+" Free calling time(houses): "+this.free_time_home+" mins"+"\n"+" Call Charge(mobiles): "+this.cost_time_mobile+" E/min"+"\n"+" SMS Charge: "+this.cost_sms+" E/sms"+"\n"+" Call Charge(houses): "+this.cost_time_home+" E/min"+"\n";
	}
	
	public void setFree_Time_Mobile(double free_time_mobile)
	{
		this.free_time_mobile=free_time_mobile;
	}
	
	public void setCost_Time_Mobile(double cost_time_mobile)
	{
		this.cost_time_mobile=cost_time_mobile;
	}
	
	public void setFree_Time_Home (double free_time_home)
	{
		this.free_time_home=free_time_home;
	}
	
	public void setCost_Time_Home(double cost_time_home)
	{
		this.cost_time_home=cost_time_home;
	}
	
	public void setFree_sms(int free_sms)
	{
		this.free_sms=free_sms;
	}
	
	public void setCost_sms(double cost_sms)
	{
		this.cost_sms=cost_sms;
	}
}