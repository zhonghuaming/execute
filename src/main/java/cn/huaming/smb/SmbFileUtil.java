package cn.huaming.smb;


import java.util.ArrayList;
import java.util.List;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmbFileUtil {
    private static String username = "share";
    private static String password = "Isc123456";
    private static String domain = null;
    public static void main(String[] args) throws Exception{
        String shareDir = "smb://192.168.1.147";
        getFiles(shareDir);
    }

    public static void getFiles(String directory) throws Exception{
        long startTime = System.currentTimeMillis();
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(domain,username,password);
        SmbFile remoteFile = new SmbFile(directory,auth);
        log.info("访问远程目录耗时："+ (System.currentTimeMillis() - startTime));
        if(remoteFile.exists()){
            SmbFile[] files = remoteFile.listFiles();
            List<SmbFile> dirs = new ArrayList<>();
            for(SmbFile f : files){
                log.info(f.getName());
//                if(f.getName().indexOf("共享")>=0){
//                    dirs.add(f);
//                }
            }
            for (SmbFile f:dirs){
                listFiles(f);
            }
        }else{
            log.info("文件不存在");
        }
    }

    public static void listFiles(SmbFile file) throws Exception{
        SmbFile[] files = file.listFiles();
        for (SmbFile f:files){
            log.info(f.getName());
        }
    }
}
