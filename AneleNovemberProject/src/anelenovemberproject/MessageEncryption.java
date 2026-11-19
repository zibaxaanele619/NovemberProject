/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anelenovemberproject;

/**
 *
 * @author SOMEONE
 */
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;

import java.util.Base64;

public class MessageEncryption {

    private static final int NONCE_LENGTH = 12;
    private static final int TAG_LENGTH = 128;

    private static final SecureRandom random = new SecureRandom();

    // One encryption key for this application session.
    private static final SecretKey secretKey = generateKey();

    private static SecretKey generateKey() {

        try {

            KeyGenerator keyGenerator
                    = KeyGenerator.getInstance("AES");

            keyGenerator.init(256);

            return keyGenerator.generateKey();

        } catch (GeneralSecurityException e) {

            throw new IllegalStateException(
                    "Could not generate encryption key.", e);
        }
    }

    public static String EncryptMessage(String message)
            throws GeneralSecurityException {

        byte[] nonce = new byte[NONCE_LENGTH];

        random.nextBytes(nonce);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        GCMParameterSpec parameterSpec
                = new GCMParameterSpec(TAG_LENGTH, nonce);

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);

        byte[] encryptedData = cipher.doFinal(
                message.getBytes(StandardCharsets.UTF_8));

        ByteBuffer buffer = ByteBuffer.allocate(
                nonce.length + encryptedData.length);

        buffer.put(nonce);
        buffer.put(encryptedData);

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public static String DecryptMessage(String encryptedMessage)
            throws GeneralSecurityException {

        byte[] decodedData
                = Base64.getDecoder().decode(encryptedMessage);

        ByteBuffer buffer = ByteBuffer.wrap(decodedData);

        byte[] nonce = new byte[NONCE_LENGTH];

        buffer.get(nonce);

        byte[] encryptedData = new byte[buffer.remaining()];

        buffer.get(encryptedData);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        GCMParameterSpec parameterSpec
                = new GCMParameterSpec(TAG_LENGTH, nonce);

        cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);

        byte[] decryptedData = cipher.doFinal(encryptedData);

        return new String(
                decryptedData,
                StandardCharsets.UTF_8);
    }
}
