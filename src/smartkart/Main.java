package smartkart;

import java.util.Scanner;

/**Main
 * (Iris)
 * Attributes:
 * -String IrisFilePath is the file path on my computer for the tsv file will need to be changed for other devices
 * -String MaddyFilePath a empty string for Maddy to save her file path on for the tsv file
 * -boolean running to keep the program running and allow the end of program
 * -boolean running2 to keep the methods running
 */
public class Main {
    public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		String IrisFilePath = "C:\\Users\\Iris\\Downloads\\Project 1-Cos 161 - Sheet1 (1).tsv";
		StoreManager.FileReader(IrisFilePath);
		//String MaddyFilePath = "C:\\Users\\dhigs\\Desktop\\Eclipse Workspace\\Project1-cos161\\src\\smartkart\\Project 1-Cos 161 - Sheet1.tsv";
		//StoreManager.FileReader(IrisFilePath);
		boolean running = true;
		boolean running1 = true;
		while(running == true) {
			while(running1 == true) {
				StoreManager.selection(scanner);
                int choice = StoreManager.selection2(scanner);

                if(choice == 1) {
                    running1 = true;
                } else if(choice == 2) {
                    running1 = false;
                    running = false;
                }

			}
		}
		scanner.close();

	}

}
