import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main_OY{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        String choice2 = "";
        while(!(choice.toUpperCase().equals("QUIT"))){
            System.out.println("\nONLY YOU\n");
            System.out.println("A Java Program That Prioritize Your Privacy!");
            System.out.println("\nActions:");
            System.out.println("1. Write an Entry");
            System.out.println("2. Read an Entry");
            System.out.println("\nType 'Quit' to STOP the program.");
            System.out.print("Enter your choice(1,2 or QUIT): ");
            choice = scanner.nextLine();

            //ADDING TO FILE

            if (choice.equals("1")){
                System.out.println("\n1. Add today's Entry");
                System.out.println("2. Add to an Existing Entry");
                System.out.print("\nEnter your choice(1 or 2): ");
                choice2 = scanner.nextLine();
                if (choice2.equals("1") || choice2.equals("2")){
                    String fileName = "";
                    try {
                        //Checks if main folder exists
                        File folder = new File("Diary");
                        if(!(folder.exists())){
                            folder.mkdir();
                        }

                        //Getting Entry
                        StringBuilder Entry = new StringBuilder("");
                        LocalTime nowTime = LocalTime.now().withNano(0);
                        if (choice2.equals("1")){
                            //To Print the Date
                            LocalDate nowDate = LocalDate.now();
                            File fileChecker = new File("Diary/"+nowDate+".txt");

                            if(!(fileChecker.exists())){
                                Entry.append("Date: " + nowDate +" Time: "+ nowTime + "\n" + "\n");
                            }
                            else {
                                Entry.append("\n");
                            }

                            fileName = "Diary/"+nowDate+".txt";
                        }
                        else if (choice2.equals("2")){
                            //Asking user for Date
                            System.out.print("Enter the Date to add the Entry(Format: yyyy-mm-dd, Kindly use '-'): ");
                            String Date = scanner.nextLine();

                            File fileChecker = new File("Diary/"+Date+".txt");

                            if(!(fileChecker.exists())){
                                Entry.append("Date: " + Date +" Time: "+ nowTime + "\n" + "\n");
                            }
                            else {
                                Entry.append("\n");
                            }
                            
                            fileName = "Diary/"+Date+".txt";
                        }
                        
                        System.out.println("\nType '***' in the next line after typing your entry.");
                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();
                        System.out.println("Enter Your Entry: ");
                        
                        String line = Entry.toString();
                        String encrypted = "";
                        while(!(line.equals("***"))){
                            line = scanner.nextLine();
                            for (int i=0; i<line.length(); i++){
                                encrypted += (char)((line.charAt(i) + 5) % 127);
                            }
                            Entry.append(encrypted + "\n");
                            encrypted = "";
                        }

                        //Writer code
                        
                        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true));
                        writer.append(Entry.toString());

                        System.out.println("Entry Successfully Added to Diary!");
                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();
                        writer.close();

                    } catch (Exception e) {
                        //Handles file related exceptions
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("\n---------------------------------");
                    System.out.println("Kindly Enter either '1' or '2'...");
                    System.out.println("---------------------------------");
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            }

            // READING FROM FILE

            else if (choice.equals("2")){
                File folder = new File("Diary");
                if(!(folder.exists())){
                    System.out.println("Main OnlyYOU directory is not found! Kindly write an Entry before reading...");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                }

                System.out.print("Enter the Date of Entry(yyyy-mm-dd format) to be read: ");
                String Date = scanner.nextLine();
                System.out.println("------------------------------------------------------------");
                String fileName = "Diary/"+Date+".txt";

                try {
                    BufferedReader reader = new BufferedReader(new FileReader(fileName));
                    String decrypt = "";
                    String currentLine;
                    System.out.println(reader.readLine());
                    while((currentLine=reader.readLine()) != null){
                        for(int j=0; j<currentLine.length(); j++){
                            decrypt += (char)((currentLine.charAt(j) - 5 + 127) % 127);
                        }
                        decrypt += "\n";
                    }
                    System.out.println(decrypt);
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    reader.close();
                    
                } catch (FileNotFoundException e) {
                    System.out.println("Error: File Not Found!");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (!(choice.toUpperCase().equals("QUIT"))){
                System.out.println("\n-----------------------------------------");
                System.out.println("Kindly Enter either '1', '2' or 'QUIT'...");
                System.out.println("-----------------------------------------");
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        System.out.println("\nProgram Ended!\nSee You Tomorrow!");
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
        scanner.close();
    }
}
