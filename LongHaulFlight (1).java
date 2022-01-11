/*
 * A Long Haul Flight is a flight that travels a long distance and has two types of seats (First Class and Economy)
 */

public class LongHaulFlight extends Flight {
	int firstClassPassengers;

	public LongHaulFlight(String flightNum, String airline, String dest, String departure, int flightDuration, Aircraft aircraft) {
		super(flightNum, airline, dest, departure, flightDuration, aircraft);
		type = Flight.Type.LONGHAUL;
	}

	public LongHaulFlight() {
		firstClassPassengers = 0;
		type = Flight.Type.LONGHAUL;
	}

	public void reserveSeat(Passenger p) throws Exception {
		if (p.getSeat().endsWith("+")) {
			if (firstClassPassengers >= aircraft.getNumFirstClassSeats()) {
				throw new FlightFullException("No first class seats are avaliable");
			}
			if (manifest.contains(p)) {
				throw new DuplicatePassengerException("Duplicate Passeneger" + p.getName() + " " + p.getPassport());
			}
			if (!aircraft.checkSeat(p.getSeat())) {
				throw new SeatOccupiedException("Seat is already occupied")
			}
			manifest.add(p);
			seatMap.put(p.getSeat(), p);
			aircraft.reserveSeatLayout(p.getSeat());
			firstClassPassengers++;
		} else {
			super.reserveSeat(p);
		}
	}

	public void cancelSeat(String name, String passport, String seat) throws Exception {
		if (seat.endsWith("+")) {
			Passenger p = new Passenger(name, passport);
			if (!manifest.contains(p)) {
				throw new PassengerNotInManifestException("Passenger" + p.getName() + " " + p.getPassport() + "Not found");
			}
			manifest.remove(p);
			seatMap.remove(seat);
			seatMap.put(seat, null);

			if (firstClassPassengers > 0) {
				firstClassPassengers--;
				aircraft.cancelSeatLayout(seat);
			}
			System.out.println("It has been Cancelled succesfully");
		} else {
			super.cancelSeat(name, passport, seat);
		}

	}

	public Type getFlightType() {
		return type;
	}

	public int getPassengerCount() {
		return getNumPassengers() + firstClassPassengers;
	}

	public booleanseatsAvaliable(String seatType) {
		if (seatType.equals("FCL"))
			return firstClassPassengers < aircraft.getNumFirstClassSeats();
		else {
			return super.seatsAvaliable(seatType);
		}
	}

	public String toString() {
		return super.toString() + "\t LongHaul"
	}

}
