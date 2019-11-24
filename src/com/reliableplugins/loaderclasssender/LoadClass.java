package com.reliableplugins.loaderclasssender;

public class LoadClass
{
    public static void loadClass(String name, byte[] data)
    {
        ByteClassLoader loader = new ByteClassLoader();
        Class clazz = loader.findClass(name, data);

        try
        {
            clazz.newInstance();
        }
        catch(Exception e)
        {
            System.out.println("Failed to instantiate class: " + e.toString());
            return;
        }
    }
}
