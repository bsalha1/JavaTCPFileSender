package com.reliableplugins.loaderclasssender;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient
{
    public static void main(String args[]) throws Exception
    {
        int totalBytes = 2;
        System.out.print("Enter the class name: ");
        Scanner scanner = new Scanner(System.in);
        String classname = scanner.nextLine();

        byte[] aByte = new byte[1];
        int bytesRead;

        Socket clientSocket;
        InputStream is;

        try
        {
            clientSocket = new Socket("127.0.0.1", 3248);
            is = clientSocket.getInputStream();
        }
        catch (IOException e)
        {
            System.out.println(e.toString());
            return;
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try
        {
            bytesRead = is.read(aByte, 0, aByte.length);
            do
            {
                output.write(aByte);
                bytesRead = is.read(aByte);
                totalBytes += bytesRead;
            } while (bytesRead != -1);

            System.out.println("Downloaded: " + totalBytes + " bytes.");
            clientSocket.close();
        }
        catch (IOException e)
        {
            System.out.println(e.toString());
            return;
        }

        LoadClass.loadClass(classname, output.toByteArray());
    }
}
