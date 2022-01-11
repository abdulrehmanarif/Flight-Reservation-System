import java.util.Scanner;
public class Aircraft {
	int numEconomySeats;
	int numFirstClassSeats;
	int totalSeats;
	String[][] seatLayout;
	int row;
	int col;
	String model;

	public Aircraft(int seats, String model) {
		this.numEconomySeats = seats;
		this.numFirstClassSeats = 0;
		this.totalSeats = seats;
		this.col = totalSeats / 4;
		this.row = totalSeats / col;
		this.seatLayout = new String[row][col];
		this.model = model;
		setSeatLayout(totalSeats);
	}

	public Aircraft(int economy, int firstClass, String model) {
		this.numEconomySeats = economy;
		this.numFirstClassSeats = firstClass;
		this.model = model;
		this.totalSeats = firstClass + economy;
		this.col = totalSeats / 4;
		this.row = totalSeats / col;
		this.seatLayout = new String[row][col];
		setSeatLayout(totalSeats);

	}

	public int getNumSeats() {
		return numEconomySeats;
	}

	public int getTotalSeats() {
		return numEconomySeats + numFirstClassSeats;
	}

	public int getNumFirstClassSeats() {
		return numFirstClassSeats;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void print() {
		System.out.println("Model: " + model + "\t Economy Seats: " + numEconomySeats + "\t First Class Seats: " + numFirstClassSeats);
	}

	public void setSeatLayout(int totalSeats) {
		if (numFirstClassSeats == 0) {
			col = totalSeats / 4;
			row = totalSeats / col;
			seatLayout = new String[row][col];
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (i == 0) {
					seatLayout[i][j] = (j + 1) + "A";
				} else if (i == 1) {
					seatLayout[i][j] = (j + 1) + "B";
				} else if (i == 2) {
					seatLayout[i][j] = (j + 1) + "C";
				} else if (i == 3) {
					seatLayout[i][j] = (j + 1) + "D";
				}
			}
		}
	}
	else

	{
		seatLayout = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (i == 0) {
					if ((i == 0) && (j == 0 || j == 1 || j == 2)) {
						seatLayout[i][j] = (j + 1) + "A+";
					} else {
						seatLayout[i][j] = (j + 1) + "A";
					}
				} else if (i == 1) {
					if ((i == 1) && (j == 0 || j == 1 || j == 2)) {
						seatLayout[i][j] = (j + 1) + "B+";
					} else {
						seatLayout[i][j] = (j + 1) + "B";
					}
				} else if (i == 2) {
					if ((i == 2) && (j == 0 || j == 1 || j == 2)) {
						seatLayout[i][j] = (j + 1) + "C+";
					} else {
						seatLayout[i][j] = (j + 1) + "C";
					}
				} else if (i == 3) {
					if ((i == 3) && (j == 0 || j == 1 || j == 2)) {
						seatLayout[i][j] = (j + 1) + "D+";
					} else {
						seatLayout[i][j] = (j + 1) + "D";
					}
				}
			}
		}

	}

}
	public boolean reserveSeatLayout (String seat){
		String seatRow = "";
		String seatCol = "";
		for (int i = 0; i < seat.length(); i++){
			boolean isDigit = Character.isDigit(seatcharAt(i));
			if (isDigit){
				seatCol = seatCol + seat.charAt(i);
			}
			else{
				seatRow = seatRow + seatcharAt(i);
			}
		}
		if (seatRow.equalsIgnoreCase("A") || seatRow.equalsIgnoreCase("A+")) {
			if (seatLayout[0][Integer.parseInt(seatCol) - 1] != "XX") {
				seatLayout[0][Integer.parseInt(seatCol) - 1] = "XX";
				return true;
			}
			return false;
		} else if (seatRow.equalsIgnoreCase("B") || seatRow.equalsIgnoreCase("B+")) {
			if (seatLayout[1][Integer.parseInt(seatCol) - 1] != "XX") {
				seatLayout[1][Integer.parseInt(seatCol) - 1] = "XX";
				return true;
			}
			return false;
		} else if (seatRow.equalsIgnoreCase("C") || seatRow.equalsIgnoreCase("C+")) {
			if (seatLayout[2][Integer.parseInt(seatCol) - 1] != "XX") {
				seatLayout[2][Integer.parseInt(seatCol) - 1] = "XX";
				return true;
			}
			return false;
		} else if (seatRow.equalsIgnoreCase("D") || seatRow.equalsIgnoreCase("D+")) {
			if (seatLayout[3][Integer.parseInt(seatCol) - 1] != "XX") {
				seatLayout[3][Integer.parseInt(seatCol) - 1] = "XX";
				return true;
			}
			return false;
		}
		return false;
	}
}
	public void cancelSeatLayout(String seat){
		String seatRow = "";
		String seatCol = " ";
		for (int i = 0; i<seat.length; i++){
			boolean isDigit = Character.isDigit (seat.charAt(i));
		}
			if (isDigit){
				seatCol = seatCol + seat.charAt(i);
			}
			else{
				seatRow = seatRow + seat.charAt(i);
			}
		}
		if (seatRow.equalsIgnoreCase("A") || seatRow.equalsIgnoreCase("A+")){
			if(seatRow.equalsIgnoreCase("A+")){
				seatLayout[0][Integer.parseInt(seatCol)-1]=seatCol+"A+";
			}
			else{
				seatLayout[0][Integer.parseInt(seatCol)-1]=seatCol+"A";
			}
			}
		else if (seatRow.equalsIgnoreCase("B") || seatRow.equalsIgnoreCase("B+")){
				if(seatRow.equalsIgnoreCase("B+")){
					seatLayout[1][Integer.parseInt(seatCol)-1]=seatCol+"B+";
			}
			else{
				seatLayout[1][Integer.parseInt(seatCol)-1]=seatCol+"B";
			}
			}
		else if (seatRow.equalsIgnoreCase("C") || seatRow.equalsIgnoreCase("C+")){
			if(seatRow.equalsIgnoreCase("C+")){
				seatLayout[2][Integer.parseInt(seatCol)-1]=seatCol+"C+";}
			else{
				seatLayout[2][Integer.parseInt(seatCol)-1]=seatCol+"C";
			}
			}
		else if (seatRow.equalsIgnoreCase("D") || seatRow.equalsIgnoreCase("D+")){
			if(seatRow.equalsIgnoreCase("D+")){
				seatLayout[3][Integer.parseInt(seatCol)-1]=seatCol+"D+";
		}
		else{
			seatLayout[3][Integer.parseInt(seatCol)-1]=seatCol+"D";
		}
		}














