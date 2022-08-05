public class M_Card extends Mobile{
	
	private double balance;
	
	public M_Card(){
		super();
	}
	
	public M_Card(String serv_name,String serv_type,double discount,double f_cost,double free_time_mobile, double cost_time, int free_sms, double cost_sms,double free_time_home, double cost_time_home,double balance)
	{
		super(serv_name,serv_type,discount,f_cost,free_time_mobile,cost_time,free_sms,cost_sms,free_time_home,cost_time_home);
		this.balance=balance;
	}
	public double getBalance()
	{
		return this.balance;
	}
	public void setBalance (double balance)
	{
		this.balance=balance;
	}
	
	
	public String print()
	{
		return super.print()+" Balance: "+this.balance+" E\n";
	}
}