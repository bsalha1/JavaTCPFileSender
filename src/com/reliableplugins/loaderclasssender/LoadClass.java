package com.reliableplugins.loaderclasssender;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class LoadClass
{
    public static void loadClass(String name, byte[] data)
    {
        ByteClassLoader loader = new ByteClassLoader();
        Class clazz = loader.findClass(name, data);
        System.out.println("Class loaded: " + clazz.toString() + "\n");

        try
        {
            // getConstructor(argument_type)
            Constructor constructor = clazz.getConstructor(String.class);
            Object object = constructor.newInstance("test message for constructor");
            System.out.println();

            // getMethod(name_of_method, argument_type)
            Method instanceMethod = clazz.getMethod("instanceTest", String.class);
            instanceMethod.invoke(object, "test message for instance method");
            System.out.println();

            Method staticMethod = clazz.getMethod("staticTest", String.class);
            staticMethod.invoke(null, "test message for static method");
            System.out.println();

            // getField(name_of_field)
            Field instanceField = clazz.getField("instanceField");
            System.out.println("Instance field value: " + instanceField.get(object));
            System.out.println();

            Field staticField = clazz.getField("staticField");
            System.out.println("Static field value: " + staticField.get(null).toString());
            System.out.println();

        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
            return;
        }
    }
}
