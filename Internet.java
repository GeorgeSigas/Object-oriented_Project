public class Internet extends Service{
	
	private double data;
	private double data_charge;
	
	public Internet()
	{
		super();
	}
	
	public Internet(String serv_name,String serv_type,double discount,double f_cost,double data,double data_charge)
	{
			super(serv_name,serv_type,f_cost,discount);
			this.data=data;
			this.data_charge=data_charge;
	}
	public double getData()
	{
		return this.data;
	}
	public double getData_charge()
	{
		return this.data_charge;
	}
	public void setData(double data)
	{
		this.data=data;
	}
	public void setData_charge(double data_charge)
	{
		this.data_charge=data_charge;
	}
	
	public String print()
	{
		return super.print()+" Free Data: "+data+" GB"+"\n"+" Charge: "+data_charge+" E/GB"+"\n";
	}
}