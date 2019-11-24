package com.reliableplugins.loaderclasssender;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer
{
    public static void main(String args[])
    {
        System.out.print("Enter the filename: ");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();

        while(true)
        {
            ServerSocket serverSocket;
            Socket clientSocket;
            BufferedOutputStream toClient;

            try
            {
                serverSocket = new ServerSocket(3248);
                clientSocket = serverSocket.accept();
                toClient = new BufferedOutputStream(clientSocket.getOutputStream());
            }
            catch (IOException e)
            {
                System.out.println(e.toString());
                return;
            }

            File myFile = new File(filename);
            byte[] mybytearray = new byte[(int) myFile.length()];

            FileInputStream fileInputStream;

            try
            {
                fileInputStream = new FileInputStream(myFile);
            }
            catch (FileNotFoundException e)
            {
                System.out.println(e.toString());
                return;
            }
            BufferedInputStream inputStream = new BufferedInputStream(fileInputStream);

            try
            {
                inputStream.read(mybytearray, 0, mybytearray.length);
                toClient.write(mybytearray, 0, mybytearray.length);

                System.out.println("Data sent.");
                toClient.flush();
                toClient.close();
                clientSocket.close();
                return;
            }
            catch (IOException e)
            {
                System.out.println(e.toString());
                return;
            }
        }
    }
}
