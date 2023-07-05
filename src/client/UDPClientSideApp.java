package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * An example of client-side application using UDP
 * 
 * author emalianakasmuri
 *
 */
public class UDPClientSideApp {

    public static void main(String[] args) {

        System.out.println("\n\tUDPClientSideApp: Demonstration of UDP " + "Client-Side Application.");

        try {

            // 1. Define server port number and address
            int portNo = 8083;
            InetAddress ip = InetAddress.getLocalHost();

            // 2. Prepare and transform data into bytes
            String text = "Good morning Malaysia Singapore Vietnam";
            byte buf[] = text.getBytes();

            // 3. Wrap data in datagram packet (constructor no 5)
            DatagramPacket outPacket = new DatagramPacket(buf, buf.length, ip, portNo);

            // 4. Create the socket object to transmit the data.
            DatagramSocket datagramSocket = new DatagramSocket();

            // 5. Send datagram packet
            datagramSocket.send(outPacket);
            System.out.println("\n\tSending '" + text + "' (" + text.length() + ") out on network.");

            // 6. New buffer to extract the received data
            byte[] inData = new byte[65535];

            // 7. Packet to receive data
            DatagramPacket inPacket = new DatagramPacket(inData, inData.length);
            DatagramPacket vowelPacket = new DatagramPacket(inData, inData.length);
            DatagramPacket consonantPacket = new DatagramPacket(inData, inData.length);
            DatagramPacket punctuationPacket = new DatagramPacket(inData, inData.length);

            // 8. Receive data
            datagramSocket.receive(inPacket);
            datagramSocket.receive(vowelPacket);
            datagramSocket.receive(consonantPacket);
            datagramSocket.receive(punctuationPacket);

            // 9. Extract data
            String length = new String(inData, 0, inPacket.getLength());
            String vowelLength = new String(inData, 0, vowelPacket.getLength());
            String consonantLength = new String(inData, 0, consonantPacket.getLength());
            String punctuationLength = new String(inData, 0, punctuationPacket.getLength());

            // 10. Display the data received from the server
            System.out.println("\tLength from the server is: " + length);
            System.out.println("\tVowel Length from the server is: " + vowelLength);
            System.out.println("\tConsonant Length from the server is: " + consonantLength);
            System.out.println("\tPunctuation Length from the server is: " + punctuationLength);

            datagramSocket.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

        System.out.println("\n\tUDPClientSideApp: End of program.");
    }
}