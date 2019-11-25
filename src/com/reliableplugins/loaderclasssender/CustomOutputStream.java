package com.reliableplugins.loaderclasssender;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class CustomOutputStream extends OutputStream
{
    private JTextArea textArea;

    public CustomOutputStream(JTextArea textArea)
    {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException
    {
        // Redirects data to the text area
        textArea.append(String.valueOf((char)b));

        // Scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
