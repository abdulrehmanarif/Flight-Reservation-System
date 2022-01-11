import java.util.ArrayList;
import java.util.Scanner;

// Flight System for one single day at YYZ (Print this in title) Departing flights!!


public class FlightReservationSystem
{
	public static void main(String[] args)
	{
		FlightManager manager = new FlightManager();

		ArrayList<Reservation> myReservations = new ArrayList<Reservation>();	// my flight reservations


		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		while (scanner.hasNextLine())
		{
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}

			Scanner commandLine = new Scanner(inputLine);
			String action = commandLine.next();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}

			else if (action.equals("Q") || action.equals("QUIT"))
				return;

			else if (action.equalsIgnoreCase("LIST"))
			{
				manager.printAllFlights(); 
			}
			else if (action.equalsIgnoreCase("RES"))
			{
				String flightNum = null;
				String passengerName = "";
				String passport = "";
				String seatType = "";

				if (commandLine.hasNext())
				{
					flightNum = commandLine.next();
				}
				if (commandLine.hasNext())
				{
					passengerName = commandLine.next();
				}
				if (commandLine.hasNext())
				{
					passport = commandLine.next();
				}
				if (commandLine.hasNext())
				{
					try {
						seatType = commandLine.next();

						Reservation res = manager.reserveSeatOnFlight(flightNum, passengerName, passport, seatType);
						if (res != null) {
							myReservations.add(res);
							res.print();
						}
					catch(Exception e){
							e.printStackTrace();
						}
					}
					else
					{
						System.out.println("Error");
					}
				}
			}
			else if (action.equalsIgnoreCase("CANCEL"))
			{
				Reservation res = null;
				String flightNum = null;
				String passengerName = "";
				String passport = "";
				String seatType = "";

				if (commandLine.hasNext())
				{
					flightNum = commandLine.next();
				}
				if (commandLine.hasNext())
				{
					passengerName = commandLine.next();
				}
				if (commandLine.hasNext())
				{
					passport = commandLine.next();

					try {

						int index = myReservations.indexOf(new Reservation(flightNum, passengerName, passport));
						if (index >= 0) {
							manager.cancelReservation(myReservations.get(index));
							myReservations.remove(index);
						} else{
							System.out.println("Reservation on Flight " + flightNum + " Not Found");}
					catch(Exception e){
							e.printStackTrace();
						}
					 else{
					System.out.println("Invalid entry");
				}

			}
			else if (action.equalsIgnoreCase("SEATS"))
			{
				String flightNum = "";
				String seatType = "";
				
				if (commandLine.hasNext())
				{
					flightNum = commandLine.next();
					manager.seatLayout(flightNum);
				}
				else{
					System.out.println("Invalid entry.");
				}

			}
			else if (action.equalsIgnoreCase("MYRES")) {
					if (myReservations.size() == 0) {
						System.out.println("No reservations made");
					} else {
						for (Reservation myres : myReservations)
							if (myres.getFirstClass()) {
								myres.print();
							} else {
								myres.print();
							}
					}
				} else if (action.equalsIgnoreCase("PASMAN")) {
					String flightNum = "";
					if (commandLine.hasNext()) {
						flightNum = commandLine.next();}
						if (!manager.printPassengarManifest(flightNum)) {
							System.out.println(manager.getErrorMessage());
						}
					} else {
						System.out.println("Invalid, try again");
					}
				else{
						System.out.println("Command does not exist");
					}
					System.out.println("\n");


				}



			}

		}
	}


}

