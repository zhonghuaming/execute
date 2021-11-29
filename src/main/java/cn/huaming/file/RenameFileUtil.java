package cn.huaming.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * 删除文文件
 */
@Slf4j
public class RenameFileUtil {

    /**
     * 替换所有文件名
     */
    public static void replaceAllFileName(File file, String oldStr, String newStr) {
        //目录存在，且为文件夹
        if (file.exists() && file.isDirectory()) {
            String[] list = file.list();
            assert list != null;
            for (String name : list) {
                File nextFile = new File(file.getAbsolutePath() + "\\" + name);
                if (nextFile.isDirectory()) {
                    replaceAllFileName(nextFile, oldStr, newStr);
                } else {
                    if (name.contains(oldStr)) {
                        File oldFile = new File(file + "\\" + name);
                        File newFile = new File(file + "\\" + name.replace(oldStr, newStr));
                        if (oldFile.renameTo(newFile)) {
                            System.out.println("已重命名：" + oldFile);
                        } else {
                            System.out.println("ERROR");
                        }
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
//        File file = new File("G:\\学习\\Java\\Flink+ClickHouse 玩转企业级实时大数据");
//        replaceAllFileName(file, "【IT视频学习网-www.itspxx.com】", "");
    }
}