package cn.huaming.file;

import java.io.File;

/**
 * 删除文文件
 */
public class DeleteFileUtil {

    /**
     * 删除
     */
    public static void deleteFile(File directory) {
        if (!directory.isDirectory()) {
            directory.delete();
        } else {
            File[] files = directory.listFiles();

            // 空文件夹
            if (files.length == 0) {
                directory.delete();
                System.out.println("删除" + directory.getAbsolutePath());
                return;
            }

            // 删除子文件夹和子文件
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFile(file);
                } else {
                    file.delete();
                    System.out.println("删除" + file.getAbsolutePath());
                }
            }

            // 删除文件夹本身
            directory.delete();
            System.out.println("删除" + directory.getAbsolutePath());
        }
    }

    /**
     * 删除目录下的包含word所有文件
     */
    public static void deleteFileNameLike(File file, String word) {
        //目录存在，且为文件夹
        if (file.exists() && file.isDirectory()) {
            String[] list = file.list();
            for (String name : list) {
                File nextFile = new File(file.getAbsolutePath() + "\\" + name);
                if (nextFile.isDirectory()) {
                    deleteFileNameLike(nextFile, word);
                } else {
                    if (name.contains(word)) {
                        File prepareDelFile = new File(file + "\\" + name);
                        deleteFile(prepareDelFile);
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
        File file = new File("E:\\workspace\\scloud-all-F.SR4");
        String word = "hs_err_pid";
        deleteFileNameLike(file, word);

    }
}