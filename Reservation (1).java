
public class Reservation
{
	String flightNum;
	String flightInfo;
	String name;
	String passport;
	String seat;
	String seatType;
	boolean firstClass;
	
	public Reservation(String flightNum, String info)
	{
		this.flightNum = flightNum;
		this.flightInfo = info;
	}
	
	public Reservation(String flightNum, String name, String passport)
	{
		this.flightNum = flightNum;
		this.name = name;
		this.passport = passport;
	}
	
	public Reservation(String flightNum, String info, String name, String passport, String seat, String seatType)
	{
		this.flightNum = flightNum;
		this.flightInfo = info;
		this.name = name;
		this.passport = passport;
		this.seat = seat;
		if (seat.endsWith("+")){
			setFirstClass();
		}

	}
	public void setFirstClass(){
		firstClass = true;
	}
	
	public boolean getFirstClass()
	{
		return firstClass;
	}
	
	public String getFlightNum()
	{
		return flightNum;
	}

	public String getFlightInfo()
	{
		return flightInfo;
	}
	
	public void setFlightInfo (String flightInfo) {

		this.flightInfo = flightInfo;

	}

	public boolean equals (Object other){
		Reservation otherRes = (Reservation) other;
		return flightNum.equals(otherRes.flightNum) && name.equals(otherRes.name) && passport.equals(otherRes.passport);
	}

	public void print(){
		if (getFirstClass()){
			System.out.println(flightInfo + "\t Passenger:" + name + " " + passport + " " + seat+ "FCL");
		}
		else{
			System.out.println(flightInfo + "\t Passenger" + name + " " + passport + " " + seat);
		}
	}
}