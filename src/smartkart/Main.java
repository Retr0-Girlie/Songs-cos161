package smartkart;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		String IrisFilePath = "C:\\Users\\Iris\\Downloads\\Project 1-Cos 161 - Sheet1 (1).tsv";
		StoreManager.FileReader(IrisFilePath);
		//ArrayList<Product> test = StoreManager.getInventory();
		boolean running = true;
		boolean running1 = true;
		while(running == true) {
			while(running1 == true) {
				StoreManager.options();
				StoreManager.selection(scanner);
                int choice = StoreManager.selection2(scanner);

                if(choice == 1) {
                    running1 = true;
                } else if(choice == 2) {
                    running1 = false;
                    running = false;
                }

			}
			//System.out.println("r1:" + running1 + " r:" + running);
		}
		scanner.close();

	}

}
