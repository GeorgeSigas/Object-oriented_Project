public class Service{
	
	private double f_cost;
	private double discount;
	private String serv_name;
	private String serv_type;
	
	public Service(){
		
	}
	
	public Service(String serv_name,String serv_type,double f_cost,double discount)
	{
		this.f_cost=f_cost;
		this.discount=discount;
		this.serv_name=serv_name;
		this.serv_type=serv_type;
		
	}
	
	public String print()
	{
		return "\n"+"Service: "+this.serv_name+"\nType: "+this.serv_type+"\nF_Cost: "+this.f_cost+" E\nDiscount: "+this.discount*100+"%\n";
	}
	
	public String getServ_name()
	{
		return this.serv_name;
	}
	public double getF_cost()
	{
		return this.f_cost;
	}
	public double getDiscount()
	{
		return this.discount;
	}
	public String getServ_type()
	{
		return this.serv_type;
	}
	public void setServ_name(String serv_name)
	{
		this.serv_name=serv_name;
	}
	public void setF_cost(double f_cost)
	{
		this.f_cost=f_cost;
	}
	public void setDiscount(double discount)
	{
		this.discount=discount;
	}
	public void setServ_type(String serv_type)
	{
		this.serv_type=serv_type;
	}
	
}