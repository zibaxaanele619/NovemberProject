/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anelenovemberproject;

/**
 *
 * @author SOMEONE
 */
import java.time.LocalDateTime;

public class Messages {

    private int messageID;

    private int senderID;
    private int receiverID;

    private String encryptedContent;

    private LocalDateTime dateSent;

    public Messages(
            int messageID,
            int senderID,
            int receiverID,
            String encryptedContent) {

        this.messageID = messageID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.encryptedContent = encryptedContent;

        this.dateSent = LocalDateTime.now();
    }

    public int getMessageID() {
        return messageID;
    }

    public int getSenderID() {
        return senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public String getEncryptedContent() {
        return encryptedContent;
    }

    public LocalDateTime getDateSent() {
        return dateSent;
    }
}
