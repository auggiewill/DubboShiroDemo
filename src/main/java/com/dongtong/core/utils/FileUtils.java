package com.dongtong.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件处理类
 * @author auggie
 *
 */
public class FileUtils {
	
	public static FileOutputStream openOutputStream(String filepath) throws IOException {
        return openOutputStream(new File(filepath));
    }

	public static FileOutputStream openOutputStream(File file) throws IOException {
        return openOutputStream(file, false);
    }
	
	public static FileOutputStream openOutputStream(File file, boolean append) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (file.canWrite() == false) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null) {
                if (!parent.mkdirs() && !parent.isDirectory()) {
                    throw new IOException("Directory '" + parent + "' could not be created");
                }
            }
        }
        return new FileOutputStream(file, append);
    }
	
	public static FileInputStream openInputStream(String filepath) throws IOException {
        return openInputStream(new File(filepath));
    }
	
	public static FileInputStream openInputStream(File file) throws IOException {
		if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (file.canRead() == false) {
                throw new IOException("File '" + file + "' cannot be read");
            }
        } else {
            return null;
        }
        return new FileInputStream(file);
    }
}
