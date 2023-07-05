package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import controller.SentenceProcessor;

/**
 * An example of server-side application using UDP
 * 
 * How to run this application:
 * 1. Open terminal
 * 2. Change to directory to /workspace-dad/udpdemo/bin
 * 3. java UDPServerSideApp
 * 4. Initial output: 
 *    UDPServerSideApp: Demonstration of UDP Server-Side Application.
 * 5. Final output: 
 *    Message received: Good morning.
 * 
 * author emalianakasmuri
 *
 */

public class UDPServerSideApp {

    public static void main(String[] args) {

        System.out.println("UDPServerSideApp: Demonstration of UDP Server-Side " + "Application.");

        // Permissible port for this application
        int portNo = 8083;

        try {

            // 1. Bind a DatagramSocket's object to a port number
            DatagramSocket datagramSocket = new DatagramSocket(portNo);

            // Continually listen for requests
            while (true) {

                // 2. Variable to receive data from the port
                // 65535 is the maximum size for a UDP packet
                byte[] receivedData = new byte[65535];

                // 3. Object representing a packet from the client
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);

                // 4. Receive packet
                datagramSocket.receive(receivedPacket);

                // 5. Extract data from packet
                receivedData = receivedPacket.getData();

                // 6. Further processing
                SentenceProcessor processor = new SentenceProcessor(receivedData);
                String sentence = processor.getSentence();
                System.out.println("\nMessage received: " + sentence + ".\n");

                // More processing
                int totalCharacters = processor.countCharacters();
                byte[] outData = processor.convertToByteArray(totalCharacters);

                // to get the total vowel
                int totalVowel = processor.countVowel();
                byte[] vowelData = processor.convertToByteArray(totalVowel);

                // to get the total consonant
                int totalConsonant = processor.countConsonant();
                byte[] consonantData = processor.convertToByteArray(totalConsonant);

                // to get the total punctuation
                int totalPunctuation = processor.countPunctuation();
                byte[] punctuationData = processor.convertToByteArray(totalPunctuation);

                // 7. Get the client information
                InetAddress clientAddress = receivedPacket.getAddress();
                int clientPort = receivedPacket.getPort();
                int sizeOutData = outData.length;
                int sizeVowelData = vowelData.length;
                int sizeConsonantData = consonantData.length;
                int sizePunctuationData = punctuationData.length;

                // 8. Wrap data into datagram packets
                DatagramPacket outPacket = new DatagramPacket(outData, sizeOutData, clientAddress, clientPort);
                DatagramPacket vowelPacket = new DatagramPacket(vowelData, sizeVowelData, clientAddress, clientPort);
                DatagramPacket consonantPacket = new DatagramPacket(consonantData, sizeConsonantData, clientAddress, clientPort);
                DatagramPacket punctuationPacket = new DatagramPacket(punctuationData, sizePunctuationData, clientAddress, clientPort);

                // 9. Reply to the client
                datagramSocket.send(outPacket);
                datagramSocket.send(vowelPacket);
                datagramSocket.send(consonantPacket);
                datagramSocket.send(punctuationPacket);
                
                System.out.println("Message sent (totalCharacters): " + totalCharacters + ".\n");
                System.out.println("Message sent (totalVowel): " + totalVowel + ".\n");
                System.out.println("Message sent (totalConsonant): " + totalConsonant + ".\n");
                System.out.println("Message sent (totalPunctuation): " + totalPunctuation + ".\n");
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        System.out.println("UDPClientSideApp: End of program.");
    }
}