package com.reliableplugins.loaderclasssender.debug;

import com.reliableplugins.loaderclasssender.loader.LoadClass;

import java.io.*;
import java.net.Socket;

public class TestClient
{
    public static void main(String args[]) throws Exception
    {
        if(args.length != 3)
        {
            System.out.println("[FAIL] Need two arguments, server ip, port, class name");
        }
        int totalBytes = 2;
        String ipAddress = args[0];
        int port = Integer.parseInt(args[1]);
        String classname = args[2];

        byte[] aByte = new byte[1];
        int bytesRead;

        Socket localSocket;
        InputStream socketStream;
        ByteArrayOutputStream outputStream;

        try
        {
            localSocket = new Socket(ipAddress, port);
            System.out.println("Socket connected to " + ipAddress + ":" + port);
            socketStream = localSocket.getInputStream();
            outputStream = new ByteArrayOutputStream();

            System.out.println("Downloading class...");
            bytesRead = socketStream.read(aByte, 0, aByte.length);
            do
            {
                outputStream.write(aByte); // Write sent bytes to output stream
                bytesRead = socketStream.read(aByte);
                totalBytes += bytesRead;
            } while (bytesRead != -1);

            System.out.println("Downloaded: " + totalBytes + " bytes");
            localSocket.close();
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
            return;
        }

        LoadClass.getBuildId(classname, outputStream.toByteArray());
    }
}
