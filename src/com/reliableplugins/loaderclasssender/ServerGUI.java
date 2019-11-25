package com.reliableplugins.loaderclasssender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.PrintStream;
import java.net.InetAddress;

public class ServerGUI extends JFrame
{
    private static final long serialVersionUID = 1L;
    private JTextField portField;
    private JTextField filenameField;
    private JButton startButton;
    private JButton stopButton;
    private Server server = null;
    private Thread serverThread;
    public static PrintStream outputStream;

    private ServerGUI()
    {
        super("ClassSender");

        int gap = 20;
        this.setSize(500, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        getContentPane().setLayout(null);

        /* Enter Port Field */
        portField = new JTextField("3248");
        portField.setSize(100, 20);
        portField.setLocation(gap, 40);
        JLabel portLabel = new JLabel("Port");
        portLabel.setLocation(portField.getX(), portField.getY() - 20);
        portLabel.setSize(portLabel.getPreferredSize());

        /* IP Address Label */
        try
        {
            JLabel addressLabel = new JLabel("Server IP: " + InetAddress.getLocalHost().getHostAddress());
            addressLabel.setSize(addressLabel.getPreferredSize());
            addressLabel.setLocation((this.getWidth() - addressLabel.getWidth()) / 2, 10);
            add(addressLabel);
        }
        catch(Exception ignored){}

        /* Enter Filename Field */
        filenameField = new JTextField("classes/Loader.class");
        filenameField.setSize(200, 20);
        filenameField.setLocation(portField.getX(),portField.getY() + 40);
        JLabel filenameLabel = new JLabel("File Name");
        filenameLabel.setLocation(filenameField.getX(), filenameField.getY() - 20);
        filenameLabel.setSize(filenameLabel.getPreferredSize());

        /* Start Button */
        startButton = new JButton("Start");
        startButton.setSize(100, 20);
        startButton.setLocation((this.getWidth() - startButton.getWidth()) / 2, filenameField.getY() + 40);
        startButton.addActionListener(this::initServer);

        /* Live Text Log */
        JTextArea textArea = new JTextArea();
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(printStream);
        System.setErr(printStream);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setLocation(gap, startButton.getY() + 40);
        scrollPane.setSize(this.getWidth() - 45, this.getHeight() - scrollPane.getY() - 55);
        scrollPane.getViewport().getView().setBackground(Color.BLACK);
        scrollPane.getViewport().getView().setForeground(Color.GREEN);

        this.add(startButton);
        this.add(portField);
        this.add(filenameField);
        this.add(portLabel);
        this.add(filenameLabel);
        this.add(scrollPane);
    }

    public static void main(String args[])
    {
        new ServerGUI().setVisible(true);
    }

    private void initServer(ActionEvent event)
    {
        if(this.server != null)
        {
            System.out.println("[ERROR] Server already running");
            return;
        }
        this.server = new Server(Integer.parseInt(this.portField.getText()), this.filenameField.getText());
        serverThread = new Thread(server::runServer);
        serverThread.start();
    }
}
