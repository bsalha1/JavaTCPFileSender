package com.reliableplugins.loaderclasssender;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server
{
    private int port;
    private String filename;
    private ServerSocket serverSocket;

    public Server(int port, String filename)
    {
        this.port = port;
        this.filename = filename;
    }

    public void runServer()
    {
        Socket clientSocket;
        BufferedOutputStream clientSocketStream;

        /* Initialize file */
        File file = new File(filename);
        byte[] bytes = new byte[(int) file.length()];
        BufferedInputStream inputStream;

        /* Initialize server socket */
        try
        {
            this.serverSocket = new ServerSocket(port);
            this.serverSocket.setReuseAddress(true);
        }
        catch(IOException e)
        {
            e.printStackTrace(System.out);
            return;
        }

        System.out.println(getHeader() + "Server initialized on port " + serverSocket.getLocalPort());

        while(true)
        {
            try
            {
                inputStream = new BufferedInputStream(new FileInputStream(file));
                inputStream.read(bytes, 0, bytes.length);
                System.out.println(getHeader() + "Loaded " + filename + " (" + bytes.length + " bytes) into input stream");

                clientSocket = serverSocket.accept();
                clientSocketStream = new BufferedOutputStream(clientSocket.getOutputStream());
                clientSocketStream.write(bytes, 0, bytes.length);
                System.out.println(getHeader() + filename + " (" + bytes.length + " bytes) " + "sent to " + clientSocket.getInetAddress().toString().replace("/",""));

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

    private String getHeader()
    {
        return "[" + DateTimeFormatter.ofPattern("MM/dd HH:mm:ss").format(LocalDateTime.now()) + "] ";
    }

}
