import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;


public class FlightManager {
	ArrayList<Flight> flights = new ArrayList<Flight>();

	String[] cities = {"Dallas", "New York", "London", "Paris", "Tokyo"};
	final int DALLAS = 0;
	final int NEWYORK = 1;
	final int LONDON = 2;
	final int PARIS = 3;
	final int TOKYO = 4;

	int[] flightTimes = {3, // Dallas
			1, // New York
			7, // London
			8, // Paris
			16,// Tokyo
	};

	ArrayList<Aircraft> airplanes = new ArrayList<Aircraft>();
	ArrayList<String> flightNumbers = new ArrayList<String>();

	String errMsg = null;
	Random random = new Random();


	public FlightManager() {
		// Create some aircraft types with max seat capacities
		airplanes.add(new Aircraft(85, "Boeing 737"));
		airplanes.add(new Aircraft(180, "Airbus 320"));
		airplanes.add(new Aircraft(37, "Dash-8 100"));
		airplanes.add(new Aircraft(4, "Bombardier 5000"));
		airplanes.add(new Aircraft(592, 14, "Boeing 747"));
		try {
			File file = new File("flights.txt");
			Scanner in = new Scanner(file);
			Scanner inputLine;
			while (in.hasNextLine()) {
				String line = in.nextLine();
				inputLine = new Scanner(line);
				String airline = inputLine.next();
				String dest = inputLine.next;
				if (dest.equals("New York")) {
					String[] temp = dest.toString().split("_");
					dest = temp[0] + " " + temp[1];
				}
				String dep = inputLine.next();
				int numSeats = Integer.parseInt(inputLine.next());
				String flightNum = generateFlightNumber(airline.toString());
				String[] temp2 = airline.toString().split("_");
				airline = "";
				for (String word : temp2) {
					airline = temp2[0] + " " + temp2[1];
				}
				if (dest.equals("Dallas")) {
					Flight flight = new Flight(flightNum, airline, dest, dep, flightTimes[DALLAS], airplanes.get(0));
					flights.add(flight);
				} else if (dest.equals("New York")) {
					if (airline.equals("Air Canada")) {
						Flight flight = new Flight(flightNum, airline, dest, dep, flightTimes[NEWYORK], airplanes.get(2));
						flights.add(flight);
					} else if (airline.equals("United Airlines")) {
						Flight flight = new Flight(flightNum, airline, dest, dep, flightTimes[NEWYORK], airplanes.get(3));
						flights.add(flight);
					} else if (airline.equals("Porter Airlines")) {
						Flight flight = new Flight(flightNum, airline, dest, dep, flightTimes[NEWYORK], airplanes.get(2));
						flights.add(flight);
					}
				} else if (dest.equals("London")) {
					Flight flight = new Flight(flightNum, airline, dest, dep, flightTimes[LONDON], airplanes.get(1));
					flights.add(flight);
				} else if (dest.equals("Paris")) {
					if (airline.equals("Air Canada")) {
						Flight flight = new Flight(flightNum, airline, dest, dep, flightTimes[PARIS], airplanes.get(1));
						flights.add(flight);
					} else if (airline.equals("United Airlines")) {
						Flight flight new Flight(flightNum, airline, dest, dep, flightTimes[PARIS], airplanes.get(0));
						flights.add(flight)
					}
				} else if (dest.equals("Tokyo")) {
					Flight flight = newLongHaulFlight(flightNum, airline, dest, dep, flightTimes[TOKYO], airplanes.get(4));
					flights.add(flight);
				}
			}
		} catch (IOException exception) {
			System.out.println("Error processing file" + exception);
			System.exit(0);
		}
	}
}private String generate FlightNumber(String airline){
  	String flightNum = "";
  	int min = 101;
  	int max = 300;
  	int randomNum = (int) (Math.random()) * (max-min+1)+min);
	String [] word = airline.split("_");
	for (String s :  word) {
		flightNum = flightNum + s.charAt(0);
	}
	flightNum = flightNum + randomNum;
	return flightNum;
}
public String getErrorMessage() {
	return errMsg;
}
public void printAllFlights() {
	for (Flight f : flights) {
		System.out.println(f);
	}
}public boolean seatsAvaliable(String flightNum, String seatType) {
	int index = flights.indexOf(new Flight(flightNum));
	if (index == -1) {
		errMsg = "Flight" + flightNum + "Not found";
		return false;
	}
	return flights.get(index).seatsAvaliable(seatType);
}
public void seatLayout(String flightNum) {
	int index = flights.indexOf(new Flight(flightNum));
	if (index == -1) {
		errMsg = "Flight" + flightNum + "Not Found";
	}
	Flight flight = flights.get(index);
	flight.printSeats();

}
public boolean printPassengerManifest (String flightNum) {
	int index = flights.indexOf(new Flight(flightNum));
	ArrayList<Passenger> list;
	if (index == -1) {
		errMsg = "Flight" + flightNum + "Not found";
		return false;
	}
	Flight flight = flights.get(index);
	list = flight.getManifest();
	if (list.size() == 0) {
		System.out.println("No passengers in flight" + flightNum + "manifest.");
		return true;
	}
	for (int i = 0; i < list.size(); i++) {
		System.out.println(list.get(i));
	}
	return true;
}
public Reservation reserveSeatOnFlight(String flightNum, String name, String passport, String seat)
	throws Exception {
	Flight flight;
	int index = flights.indexOf(new Flight(flightNum));
	if (index == -1) {
		throw new FlightNotFoundException("Flight" + flightNum + "Not found");
	}
	if (flights.get(index) instanceof LongHaulFlight) {
		flight = (LongHaulFlight) flights.get(index);
	} else {
		flight = flights.get(index);
	}
	Passenger p = new Passenger(name, passport, seat, "ECO");
	if (seat.endsWith("+" && flight.getFlightType() == Flight.Type.LONGHAUL)) {
		p = new Passenger(name, passport, seat, "FCL");
		flight.reserveSeat(p);
		return new Reservation(flightNum, flight.toString(), name, passport, seat);
	} else {
		flight.reserveSeat(p);
		return new Reservation(flightNum, flight.toString(), name, passport, seat);
	}
}
public boolean cancelReservation (Reservation res) throws Exception {
	Flight flight;
	int index = flights.indexOf(new Flight(res.getFlightNum()));
	if (index == -1) {
		throw new FlightNotFoundException("Flight" + res.getFlightNum() + " Not found");
	}
	if (flights.get(index) instanceof LongHaulFlight) {
		flight = (LongHaulFlight) flights.get(index);
	} else {
		flight = flights.get(index);
	}
	flight.cancelSeat(res.name, res.passport, res.seat);
	return true;
}



  	
  	// Populate the list of flights with some random test flights
  	String flightNum = generateFlightNumber("United Airlines");
  	Flight flight = new Flight(flightNum, "United Airlines", "Dallas", "1400", flightTimes[DALLAS], airplanes.get(0));
  	flights.add(flight);
  	
  	flightNum = generateFlightNumber("Air Canada");
   	flight = new Flight(flightNum, "Air Canada", "London", "2300", flightTimes[LONDON], airplanes.get(1));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new Flight(flightNum, "Air Canada", "Paris", "2200", flightTimes[PARIS], airplanes.get(1));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("Porter Airlines");
   	flight = new Flight(flightNum, "Porter Airlines", "New York", "1200", flightTimes[NEWYORK], airplanes.get(2));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("United Airlines");
   	flight = new Flight(flightNum, "United Airlines", "New York", "0900", flightTimes[NEWYORK], airplanes.get(3));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new Flight(flightNum, "Air Canada", "New York", "0600", flightTimes[NEWYORK], airplanes.get(2));
   	flights.add(flight);
     	
   	flightNum = generateFlightNumber("United Airlines");
   	flight = new Flight(flightNum, "United Airlines", "Paris", "2330", flightTimes[PARIS], airplanes.get(0));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new LongHaulFlight(flightNum, "Air Canada", "Tokyo", "2200", flightTimes[TOKYO], airplanes.get(4));
   	flights.add(flight);
  }
  
  private String generateFlightNumber(String airline)
  {
  	String word1, word2;
  	Scanner scanner = new Scanner(airline);
  	word1 = scanner.next();
  	word2 = scanner.next();
  	String letter1 = word1.substring(0, 1);
  	String letter2 = word2.substring(0, 1);
  	letter1.toUpperCase(); letter2.toUpperCase();
  	
  	// Generate random number between 101 and 300
  	boolean duplicate = false;
  	int flight = random.nextInt(200) + 101;
  	String flightNum = letter1 + letter2 + flight;
   	return flightNum;
  }

  public String getErrorMessage()
  {
  	return errMsg;
  }
  
  public void printAllFlights()
  {
  	for (Flight f : flights)
  		System.out.println(f);
  }
  
  public boolean seatsAvailable(String flightNum, String seatType)
  {
    int index = flights.indexOf(new Flight(flightNum));
    if (index == -1)
  	{
  		errMsg = "Flight " + flightNum + " Not Found";
  		return false;
  	}
   	return flights.get(index).seatsAvailable(seatType);
  }
  
  public Reservation reserveSeatOnFlight(String flightNum, String name, String passport, String seatType)
  {
  	int index = flights.indexOf(new Flight(flightNum));
  	if (index == -1)
  	{
  		errMsg = "Flight " + flightNum + " Not Found";
  		return null;
  	}
  	Flight flight = flights.get(index);
  	
  	if (!flight.reserveSeat(name, passport, seatType))
  	{
  		errMsg = flight.getErrorMessage();
  		return null;
  	}
  	String seat = flight.getLastAssignedSeat();
  	return new Reservation(flightNum, flight.toString(), name, passport, seat, seatType);
  }
  
  public boolean cancelReservation(Reservation res)
  {
    int index = flights.indexOf(new Flight(res.getFlightNum()));
  	if (index == -1)
    {
		  errMsg = "Flight " + res.getFlightNum() + " Not Found";
		  return false;
		}
  	Flight flight = flights.get(index);
  	flight.cancelSeat(res.name, res.passport, res.seatType);
   	return true;
  }
  
    
  public void sortByDeparture()
  {
	  Collections.sort(flights, new DepartureTimeComparator());
  }
  
  private class DepartureTimeComparator implements Comparator<Flight>
  {
  	public int compare(Flight a, Flight b)
  	{
  	  return a.getDepartureTime().compareTo(b.getDepartureTime());	  
  	}
  }
  
  public void sortByDuration()
  {
	  Collections.sort(flights, new DurationComparator());
  }
  
  private class DurationComparator implements Comparator<Flight>
  {
  	public int compare(Flight a, Flight b)
  	{
  	  return a.getFlightDuration() - b.getFlightDuration();
   	}
  }
  
  public void printAllAircraft()
  {
  	for (Aircraft craft : airplanes)
  		craft.print();
   }
  
  public void sortAircraft()
  {
  	Collections.sort(airplanes);
  }
}
