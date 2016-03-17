package com.browserdemo.aaron.browserdemo.util;

import java.io.File;

/**
 * Created by Aaronke on 3/17/2016.
 */
public class StorageHanlder {

    public static String getDirs(String path){
        File dir=new File(path);
        if (!dir.exists()) dir.mkdir();

        return path;
    }
}
