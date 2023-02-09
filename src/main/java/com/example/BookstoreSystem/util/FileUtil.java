package com.example.BookstoreSystem.util;

import java.io.File;

import static com.example.BookstoreSystem.util.Constant.IMAGE_SAVE_PATH;

public class FileUtil {
    public static File getFile(String fileName){
        String path=IMAGE_SAVE_PATH+fileName;
        File file = new File(path);
        return file;
    }

}
