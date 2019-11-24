package com.reliableplugins.loaderclasssender;

public class ByteClassLoader extends ClassLoader
{
    public Class findClass(String name, byte[] data)
    {
        byte[] ba = data/* go obtain your byte array by the name */;

        return defineClass(name,ba,0,ba.length);
    }
}
