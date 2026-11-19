/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anelenovemberproject;

/**
 *
 * @author SOMEONE
 */
import java.security.GeneralSecurityException;
import java.time.format.DateTimeFormatter;

public class MessageManager {

    private static final DateTimeFormatter DATE_FORMAT
            = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    // ==========================================
    // SEND MESSAGE
    // ==========================================
    public static void SendMessage() {

        System.out.println("\n========== SEND MESSAGE ==========");

        UserManager.DisplayAllUsers();

        int receiverID = AneleNovemberProject.getNumber(
                "\nEnter receiver's User ID: ");

        Users receiver = UserManager.GetUserByID(receiverID);

        if (receiver == null) {

            System.out.println("User does not exist.");
            return;
        }

        if (receiverID == AneleNovemberProject.currentUser.getUserID()) {

            System.out.println("You cannot send a message to yourself.");
            return;
        }

        System.out.println("Sending message to: "
                + receiver.getUsername());

        System.out.print("Enter your message: ");

        String content = AneleNovemberProject.scanner.nextLine();

        if (content.trim().isEmpty()) {

            System.out.println("Message cannot be empty.");
            return;
        }

        try {

            // Encrypt before storing the message.
            String encryptedContent
                    = MessageEncryption.EncryptMessage(content);

            Messages newMessage = new Messages(
                    AneleNovemberProject.nextMessageID++,
                    AneleNovemberProject.currentUser.getUserID(),
                    receiver.getUserID(),
                    encryptedContent
            );

            AneleNovemberProject.messages.add(newMessage);

            System.out.println("\nMessage sent successfully!");

            System.out.println("Message ID: "
                    + newMessage.getMessageID());

            System.out.println("Encryption: AES-GCM");

        } catch (GeneralSecurityException e) {

            System.out.println("Message encryption failed.");
            e.printStackTrace();
        }
    }

    // ==========================================
    // DECRYPT MESSAGE
    // ==========================================
    private static String GetDecryptedMessage(Messages message) {

        try {

            return MessageEncryption.DecryptMessage(
                    message.getEncryptedContent());

        } catch (GeneralSecurityException
                | IllegalArgumentException e) {

            return "[Unable to decrypt message]";
        }
    }

    // ==========================================
    // DISPLAY MESSAGE DETAILS
    // ==========================================
    public static void DisplayMessage(Messages message) {

        Users sender = UserManager.GetUserByID(
                message.getSenderID());

        Users receiver = UserManager.GetUserByID(
                message.getReceiverID());

        String content = GetDecryptedMessage(message);

        System.out.println("\n================================");

        System.out.println("MESSAGE ID: "
                + message.getMessageID());

        System.out.println("\nSENDER INFORMATION");

        System.out.println("Sender ID: "
                + message.getSenderID());

        System.out.println("Sender Username: "
                + (sender != null
                        ? sender.getUsername() : "Unknown"));

        System.out.println("\nRECIPIENT INFORMATION");

        System.out.println("Receiver ID: "
                + message.getReceiverID());

        System.out.println("Receiver Username: "
                + (receiver != null
                        ? receiver.getUsername() : "Unknown"));

        System.out.println("\nMESSAGE INFORMATION");

        System.out.println("Date Sent: "
                + message.getDateSent().format(DATE_FORMAT));

        System.out.println("Encryption: AES-GCM");

        System.out.println("Character Count: "
                + GetCharacterCount(content));

        System.out.println("Word Count: "
                + GetWordCount(content));

        System.out.println("\nMESSAGE CONTENT");

        System.out.println(content);

        System.out.println("================================");
    }

    // ==========================================
    // READ RECEIVED MESSAGES
    // ==========================================
    public static void ReadMyMessages() {

        System.out.println("\n========== RECEIVED MESSAGES ==========");

        int currentID
                = AneleNovemberProject.currentUser.getUserID();

        boolean found = false;

        for (Messages message : AneleNovemberProject.messages) {

            if (message.getReceiverID() == currentID) {

                found = true;

                DisplayMessage(message);
            }
        }

        if (!found) {

            System.out.println("You have no received messages.");
        }
    }

    // ==========================================
    // READ SENT MESSAGES
    // ==========================================
    public static void ReadSentMessages() {

        System.out.println("\n========== SENT MESSAGES ==========");

        int currentID
                = AneleNovemberProject.currentUser.getUserID();

        boolean found = false;

        for (Messages message : AneleNovemberProject.messages) {

            if (message.getSenderID() == currentID) {

                found = true;

                DisplayMessage(message);
            }
        }

        if (!found) {

            System.out.println("You have not sent any messages.");
        }
    }

    // ==========================================
    // CHARACTER COUNT
    // ==========================================
    public static int GetCharacterCount(String content) {

        return content.codePointCount(0, content.length());
    }

    // ==========================================
    // WORD COUNT
    // ==========================================
    public static int GetWordCount(String content) {

        if (content.trim().isEmpty()) {

            return 0;
        }

        return content.trim().split("\\s+").length;
    }

    // ==========================================
    // LONGEST MESSAGE BY CHARACTERS
    // ==========================================
    public static void FindLongestMessageByCharacters() {

        System.out.println("\n========== LONGEST MESSAGE (CHARACTERS) ==========");

        Messages longestMessage = null;

        int longestLength = -1;

        int currentID
                = AneleNovemberProject.currentUser.getUserID();

        for (Messages message : AneleNovemberProject.messages) {

            if (message.getSenderID() == currentID
                    || message.getReceiverID() == currentID) {

                String content = GetDecryptedMessage(message);

                if (content.startsWith("[Unable to decrypt")) {
                    continue;
                }

                int length = GetCharacterCount(content);

                if (length > longestLength) {

                    longestLength = length;

                    longestMessage = message;
                }
            }
        }

        if (longestMessage == null) {

            System.out.println("No accessible messages found.");

            return;
        }

        System.out.println("Longest message: "
                + longestLength + " characters");

        DisplayMessage(longestMessage);
    }

    // ==========================================
    // LONGEST MESSAGE BY WORDS
    // ==========================================
    public static void FindLongestMessageByWords() {

        System.out.println("\n========== LONGEST MESSAGE (WORDS) ==========");

        Messages longestMessage = null;

        int longestLength = -1;

        int currentID
                = AneleNovemberProject.currentUser.getUserID();

        for (Messages message : AneleNovemberProject.messages) {

            if (message.getSenderID() == currentID
                    || message.getReceiverID() == currentID) {

                String content = GetDecryptedMessage(message);

                if (content.startsWith("[Unable to decrypt")) {
                    continue;
                }

                int wordCount = GetWordCount(content);

                if (wordCount > longestLength) {

                    longestLength = wordCount;

                    longestMessage = message;
                }
            }
        }

        if (longestMessage == null) {

            System.out.println("No accessible messages found.");

            return;
        }

        System.out.println("Longest message: "
                + longestLength + " words");

        DisplayMessage(longestMessage);
    }

    // ==========================================
    // FIND MESSAGE BY ID
    // ==========================================
    public static void FindMessageByID() {

        System.out.println("\n========== FIND MESSAGE BY ID ==========");

        int messageID = AneleNovemberProject.getNumber(
                "Enter Message ID: ");

        int currentID
                = AneleNovemberProject.currentUser.getUserID();

        for (Messages message : AneleNovemberProject.messages) {

            if (message.getMessageID() == messageID) {

                // Users can only access messages they sent
                // or messages they received.
                if (message.getSenderID() == currentID
                        || message.getReceiverID() == currentID) {

                    System.out.println("\nMessage found!");

                    DisplayMessage(message);

                    return;
                }

                System.out.println("You do not have permission to view this message.");

                return;
            }
        }

        System.out.println("Message ID not found.");
    }

    // ==========================================
    // FIND MESSAGES SENT TO A SPECIFIC USER
    // ==========================================
    public static void FindMessagesByReceiver() {

        System.out.println("\n========== FIND MESSAGES BY RECIPIENT ==========");

        UserManager.DisplayAllUsers();

        int receiverID = AneleNovemberProject.getNumber(
                "\nEnter recipient's User ID: ");

        Users receiver = UserManager.GetUserByID(receiverID);

        if (receiver == null) {

            System.out.println("User does not exist.");

            return;
        }

        int currentID
                = AneleNovemberProject.currentUser.getUserID();

        boolean found = false;

        System.out.println("\nMessages sent to "
                + receiver.getUsername());

        for (Messages message : AneleNovemberProject.messages) {

            if (message.getSenderID() == currentID
                    && message.getReceiverID() == receiverID) {

                found = true;

                DisplayMessage(message);
            }
        }

        if (!found) {

            System.out.println("You have not sent messages to this user.");
        }
    }

    // ==========================================
    // GENERATE MESSAGE REPORT
    // ==========================================
    public static void GenerateMessageReport() {

        System.out.println("\n========== MESSAGE REPORT ==========");

        int messageID = AneleNovemberProject.getNumber(
                "Enter Message ID for report: ");

        int currentID
                = AneleNovemberProject.currentUser.getUserID();

        for (Messages message : AneleNovemberProject.messages) {

            if (message.getMessageID() == messageID) {

                if (message.getSenderID() != currentID
                        && message.getReceiverID() != currentID) {

                    System.out.println("You do not have permission to view this report.");

                    return;
                }

                System.out.println("\n");
                System.out.println("========================================");
                System.out.println("          FULL MESSAGE REPORT");
                System.out.println("========================================");

                Users sender = UserManager.GetUserByID(
                        message.getSenderID());

                Users receiver = UserManager.GetUserByID(
                        message.getReceiverID());

                String content = GetDecryptedMessage(message);

                System.out.println("\nMESSAGE IDENTIFICATION");

                System.out.println("Message ID: "
                        + message.getMessageID());

                System.out.println("\nSENDER DETAILS");

                System.out.println("Sender ID: "
                        + message.getSenderID());

                System.out.println("Sender Username: "
                        + (sender != null
                                ? sender.getUsername() : "Unknown"));

                System.out.println("\nRECIPIENT DETAILS");

                System.out.println("Recipient ID: "
                        + message.getReceiverID());

                System.out.println("Recipient Username: "
                        + (receiver != null
                                ? receiver.getUsername() : "Unknown"));

                System.out.println("\nMESSAGE DETAILS");

                System.out.println("Date: "
                        + message.getDateSent().format(DATE_FORMAT));

                System.out.println("Encryption Algorithm: AES-GCM");

                System.out.println("Character Length: "
                        + GetCharacterCount(content));

                System.out.println("Word Count: "
                        + GetWordCount(content));

                System.out.println("\nMESSAGE CONTENT");

                System.out.println(content);

                System.out.println("\n========================================");
                System.out.println("          END OF MESSAGE REPORT");
                System.out.println("========================================");

                return;
            }
        }

        System.out.println("Message not found.");
    }
}
