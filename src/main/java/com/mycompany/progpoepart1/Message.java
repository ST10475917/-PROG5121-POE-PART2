/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpoepart1;

/**
 *
 * @author Student
 */
 import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public final class Message {
    public String messageID;
    int num;
    String recipient;
    String content;
    String hash;
    static int total = 0;
    static ArrayList<Message> allMessages = new ArrayList<>();
    Random rand = new Random();

    public Message(int num, String recipient, String content) {
        this.messageID = "" + (1000000000L + rand.nextInt(900000000));
        this.num = num;
        this.recipient = recipient;
        this.content = content;
        this.hash = createMessageHash();
    }

    public boolean checkMessageID() { return messageID.length() <= 10; }

    public String checkRecipientCell() {
        if (recipient.startsWith("+") && recipient.length() >= 10) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String checkMessageLength() {
        if (content.length() <= 250) return "Message ready to send.";
        int extra = content.length() - 250;
        return "Message exceeds 250 characters by " + extra + ", please reduce the size.";
    }

    public String createMessageHash() {
        String firstTwo = messageID.substring(0, 2);
        String[] words = content.split(" ");
        String firstWord = words[0].replaceAll("[^A-Za-z]", "").toUpperCase();
        String lastWord = words[words.length - 1].replaceAll("[^A-Za-z]", "").toUpperCase();
        return firstTwo + ":" + num + ":" + firstWord + lastWord;
    }

    public String SentMessage(int choice) {
        switch (choice) {
            case 1 -> {
                allMessages.add(this); total++; storeMessage(); return "Message successfully sent.";
            }
            case 2 -> {
                return "Press 0 to delete the message.";
            }
            default -> {
                storeMessage(); total++; return "Message successfully stored.";
            }
        }
    }

    public String printMessages() {
        return "MessageID: " + messageID + "\nMessageHash: " + hash + "\nRecipient: " + recipient + "\nMessage: " + content;
    }

    public static int returnTotalMessages() { return total; }

    public void storeMessage() {
        try {
            try (FileWriter fw = new FileWriter("stored_messages.json", true)) {
                fw.write("{\"ID\":\"" + messageID + "\", \"Hash\":\"" + hash + "\", \"Recipient\":\"" + recipient + "\", \"Message\":\"" + content + "\"}\n");
            }
        } catch (IOException e) {}
    }
}   

