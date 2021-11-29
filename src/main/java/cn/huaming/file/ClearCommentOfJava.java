package cn.huaming.file;


import java.io.*;
import java.util.Scanner;

/**
 * 清除Java注释
 *
 * @author Jorvey
 * @author 2019年12月06日
 *
 */
public class ClearCommentOfJava {

    public static void clearComment(File file, String charset) {
        try {
            // 递归处理文件夹
            if (!file.exists()) {
                return;
            }

            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    clearComment(f, charset);
                }
                return;
            } else if (!file.getName().endsWith(".java")) {
                // 非java文件直接返回
                return;
            }
            System.out.println("开始处理文件：" + file.getAbsolutePath());

            // 根据对应的编码格式读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
            StringBuffer content = new StringBuffer();
            String tmp = null;
            while ((tmp = reader.readLine()) != null) {
                content.append(tmp);
                content.append("\n");
            }
            reader.close();
            String target = content.toString();
            String s = target.replaceAll("\\/\\/[^\\n]*|\\/\\*([^\\*^\\/]*|[\\*^\\/*]*|[^\\**\\/]*)*\\*+\\/", "");
            // 使用对应的编码格式输出
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
            out.write(s);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearComment(String filePath, String charset) {
        clearComment(new File(filePath), charset);
    }

    public static void clearComment(String filePath) {
        clearComment(new File(filePath), "UTF-8");
    }

    public static void clearComment(File file) {
        clearComment(file, "UTF-8");
    }

    public static void main(String[] args) {
        System.out.println("输入要删除Java注释的目录或文件路径:");
        Scanner in = new Scanner(System.in);
        String filePath = in.nextLine();
        clearComment(filePath);
    }

}