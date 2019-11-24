package com.reliableplugins.loaderclasssender;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer extends JFrame
{
    public static void main(String args[])
    {
        if(args.length != 2)
        {
            System.out.println("[FAIL] Must enter 2 arguments; port and filename");
            return;
        }
        int port = Integer.parseInt(args[0]);
        String filename = args[1];
        ServerSocket serverSocket;
        Socket clientSocket;
        BufferedOutputStream clientSocketStream;

        /* Initialize file */
        File file = new File(filename);
        byte[] bytes = new byte[(int) file.length()];
        BufferedInputStream inputStream;

        /* Initialize server socket */
        try
        {
            serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);
        }
        catch(IOException e)
        {
            e.printStackTrace(System.out);
            return;
        }

        System.out.println("Server initialized on port " + serverSocket.getLocalPort());

        while(true)
        {
            try
            {
                inputStream = new BufferedInputStream(new FileInputStream(file));
                inputStream.read(bytes, 0, bytes.length);
                System.out.println("Loaded " + filename + " (" + bytes.length + " bytes) into input steam");

                clientSocket = serverSocket.accept();
                clientSocketStream = new BufferedOutputStream(clientSocket.getOutputStream());
                clientSocketStream.write(bytes, 0, bytes.length);
                System.out.println(filename + " (" + bytes.length + " bytes) " + "sent to " + clientSocket.getInetAddress().toString().replace("/",""));

                clientSocketStream.flush();
                clientSocketStream.close();
                clientSocket.close();
            }
            catch (Exception e)
            {
                e.printStackTrace(System.out);
                return;
            }
        }
    }
}
