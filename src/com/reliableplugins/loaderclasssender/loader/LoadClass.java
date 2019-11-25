package com.reliableplugins.loaderclasssender.loader;

import java.lang.reflect.Field;

public class LoadClass
{
    public static int getBuildId(String name, byte[] data)
    {
        int buildId;
        ByteClassLoader loader = new ByteClassLoader();
        Class clazz = loader.findClass(name, data);
        System.out.println("Class loaded: " + clazz.toString() + "\n");

        try
        {
            Field staticField = clazz.getField("buildId");
            buildId = Integer.parseInt(staticField.toString());
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
            return -1;
        }

        return buildId;
    }
}
