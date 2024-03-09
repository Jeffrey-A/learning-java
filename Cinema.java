
//package cinema;
import java.util.Scanner;
import java.lang.Math;

public class Cinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();

        String[][] room = new String[rows][seatsPerRow];
        fillRoom(room, rows, seatsPerRow);

        while (true) {
            System.out.println("");
            int selectedOption = showMenuGetResponse(scanner);
            System.out.println("");

            if (selectedOption == 0) {
                break;
            } else if (selectedOption == 1) {
                printSeats(room);
            } else if (selectedOption == 2) {
                buyTicket(scanner, room);
            } else if (selectedOption == 3) {
                showStats(room);
            }
        }
    }

    public static int showMenuGetResponse(Scanner scanner) {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        return scanner.nextInt();
    }

    public static void showStats(String[][] room) {
        int rows = room.length;
        int seatsPerRow = room[0].length;

        int ticketsSold = getNumOfTicketsSold(room);
        int totalNumOfSeats = rows * seatsPerRow;
        double percentage = (double) ticketsSold / totalNumOfSeats;
        percentage *= 100;
        String displayPercentage = String.format("%.2f", percentage);
        int targetProfit = getTargetProfit(rows, seatsPerRow);
        int currentProfit = getCurrentIncome(room);

        System.out.println("Number of purchased tickets: " + ticketsSold);
        System.out.println("Percentage: " + displayPercentage + "%");
        System.out.println("Current income: " + "$" + currentProfit);
        System.out.println("Total income: " + "$" + targetProfit);
    }

    public static int getNumOfTicketsSold(String[][] room) {
        int rows = room.length;
        int seatsPerRow = room[0].length;
        int ticketsSold = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                if (room[i][j] == "B") {
                    ticketsSold += 1;
                }
            }
        }

        return ticketsSold;
    }

    public static int getCurrentIncome(String[][] room) {
        int rows = room.length;
        int seatsPerRow = room[0].length;
        int currentProfit = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                if (room[i][j] == "B") {
                    currentProfit += getPriceForSeat(room, i);
                }
            }
        }
        return currentProfit;
    }

    public static void printSeats(String[][] room) {
        int rows = room.length;
        int seatsPerRow = room[0].length;

        System.out.println("Cinema:");

        for (int i = 0; i <= seatsPerRow; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print(i + " ");
            }

        }

        System.out.println("");

        for (int i = 0; i < rows; i++) {
            System.out.print((i + 1) + " ");

            for (int j = 0; j < seatsPerRow; j++) {
                System.out.print(room[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void buyTicket(Scanner scanner, String[][] room) {
        int rows = room.length;
        int seatsPerRow = room[0].length;

        int selectedRow;
        int selectedSeat;

        while (true) {
            System.out.println("Enter a row number:");
            selectedRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            selectedSeat = scanner.nextInt();

            boolean isValidRow = selectedRow >= 1 && selectedRow <= rows;
            boolean isValidSeat = selectedSeat >= 1 && selectedSeat <= seatsPerRow;

            if (!isValidRow || !isValidSeat) {
                System.out.println("");
                System.out.println("Wrong input!");
                System.out.println("");
            } else if (room[selectedRow - 1][selectedSeat - 1] == "B") {
                System.out.println("");
                System.out.println("That ticket has already been purchased!");
                System.out.println("");
            } else {
                room[selectedRow - 1][selectedSeat - 1] = "B";
                printPriceForSeat(room, selectedRow);
                break;
            }
        }
    }

    public static void fillRoom(String[][] room, int rows, int seatsPerRow) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                room[i][j] = "S";
            }
        }
    }

    public static void printPriceForSeat(String[][] room, int selectedRow) {
        int rows = room.length;
        int seatsPerRow = room[0].length;

        int firstHalfRoomPrice = 10;
        int secondHalfRoomPrice = 8;
        int totalNumberOfSeats = rows * seatsPerRow;
        int smallRoomSize = 60;

        System.out.println("");
        if (totalNumberOfSeats <= smallRoomSize) {
            System.out.println("Ticket price: $" + firstHalfRoomPrice);
        } else {
            double halfRows = (double) rows / 2;
            double firstHalfRows = rows % 2 == 0 ? halfRows : Math.floor(halfRows);
            double secondHalfRows = rows % 2 == 0 ? halfRows : Math.ceil(halfRows);

            if (selectedRow <= firstHalfRows) {
                System.out.println("Ticket price: $" + firstHalfRoomPrice);
            } else {
                System.out.println("Ticket price: $" + secondHalfRoomPrice);
            }
        }

    }

    public static int getTargetProfit(int rows, int seatsPerRow) {
        int firstHalfRoomPrice = 10;
        int secondHalfRoomPrice = 8;
        int totalNumberOfSeats = rows * seatsPerRow;
        int smallRoomSize = 60;

        if (totalNumberOfSeats <= smallRoomSize) {
            return totalNumberOfSeats * firstHalfRoomPrice;
        } else {
            double halfRows = (double) rows / 2;
            double firstHalfRows = rows % 2 == 0 ? halfRows : Math.floor(halfRows);
            double secondHalfRows = rows % 2 == 0 ? halfRows : Math.ceil(halfRows);

            double firstHalfProfit = (firstHalfRows * seatsPerRow) * firstHalfRoomPrice;
            double secondHalfProfit = (secondHalfRows * seatsPerRow) * secondHalfRoomPrice;

            return (int) (firstHalfProfit + secondHalfProfit);
        }
    }

    public static int getPriceForSeat(String[][] room, int selectedRow) {
        int rows = room.length;
        int seatsPerRow = room[0].length;

        int firstHalfRoomPrice = 10;
        int secondHalfRoomPrice = 8;
        int totalNumberOfSeats = rows * seatsPerRow;
        int smallRoomSize = 60;

        if (totalNumberOfSeats <= smallRoomSize) {
            return firstHalfRoomPrice;
        } else {
            double halfRows = (double) rows / 2;
            double firstHalfRows = rows % 2 == 0 ? halfRows : Math.floor(halfRows);
            double secondHalfRows = rows % 2 == 0 ? halfRows : Math.ceil(halfRows);

            if (selectedRow < firstHalfRows) {
                return firstHalfRoomPrice;
            } else {
                return secondHalfRoomPrice;
            }
        }
    }

    public static void printSelectedSeat(int rows, int seatsPerRow, int selectedRow, int selectedSeat) {
        System.out.println("Cinema:");

        for (int i = 0; i <= seatsPerRow; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print(i + " ");
            }

        }

        System.out.println("");

        for (int i = 1; i <= rows; i++) {
            System.out.print(i + " ");

            for (int j = 0; j < seatsPerRow; j++) {
                if (i == selectedRow && j == selectedSeat - 1) {
                    System.out.print("B ");
                } else {
                    System.out.print("S ");
                }
            }
            System.out.println("");
        }
    }
}