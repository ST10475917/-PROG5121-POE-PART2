/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.progpoepart1;

import java.util.Scanner;

public class ProgPOEPART1 {
    // --- Part 1 methods you already had ---
    public boolean checkCellPhoneNumber(String number) {
        return number.startsWith("+") && number.length() >= 10 && number.length() <= 13;
    }
    public boolean checkUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*") && password.matches(".*[!@#$%^&*].*");
    }
    public boolean checkPassword(String password){ return checkPasswordComplexity(password); }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // For quick testing, we skip strict Part 1 registration
        System.out.println("Welcome to QuickChat.");

        OUTER:
        while (true) {
            System.out.println("\nMENU: 1) Send Messages  2) Show recently sent messages  3) Quit");
            System.out.print("Choose: ");
            int menu = sc.nextInt();
            sc.nextLine();
            switch (menu) {
                case 2 -> System.out.println("Coming Soon.");
                case 3 -> {
                    System.out.println("Goodbye! Total: " + Message.returnTotalMessages());
                    break OUTER;
                }
                case 1 -> {
                    System.out.print("How many messages do you want to enter? ");
                    int howMany = sc.nextInt();
                    sc.nextLine();
                    for (int i = 1; i <= howMany; i++) {
                        System.out.println("\n--- Message " + i + " ---");
                        System.out.print("Enter recipient (+27718693002): ");
                        String rec = sc.nextLine();
                        System.out.print("Enter message: ");
                        String txt = sc.nextLine();
                        Message m = new Message(i-1, rec, txt);
                        String checkRec = m.checkRecipientCell();
                        System.out.println(checkRec);
                        if (!checkRec.contains("successfully")) { i--; continue; }
                        System.out.println(m.checkMessageLength());
                        if (m.checkMessageLength().contains("exceeds")) { i--; continue; }
                        System.out.println("Hash: " + m.hash + " | ID: " + m.messageID);
                        System.out.println("1) Send  2) Disregard  3) Store");
                        int c = sc.nextInt(); sc.nextLine();
                        System.out.println(m.SentMessage(c));
                        if (c == 1) { System.out.println("\nFULL DETAILS:\n" + m.printMessages()); }
                    }   System.out.println("Total messages: " + Message.returnTotalMessages());
                }
                default -> {
                }
            }
        }
    }
}