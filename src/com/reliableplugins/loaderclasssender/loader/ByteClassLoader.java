package com.reliableplugins.loaderclasssender.loader;

public class ByteClassLoader extends ClassLoader
{
    public Class findClass(String name, byte[] data)
    {
        return defineClass(name, data,0, data.length);
    }
}
