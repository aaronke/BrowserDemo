package com.browserdemo.aaron.browserdemo.helper;

import java.io.File;

/**
 * Created by Aaronke on 3/17/2016.
 */
public class StorageHelper {

    public static String getDirs(String path){
        File dir=new File(path);
        if (!dir.exists()) dir.mkdir();
        return path;
    }
}
