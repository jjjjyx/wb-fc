package jyx.dao;

import jyx.common.Code;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataDao {
    private static DataDao ourInstance = new DataDao();
    private String DATE_PATH = "upload";
    private String IMG_PATH = "img_upload";
    private String POST_PATH = "dist";

    public static DataDao getInstance() {
        return ourInstance;
    }

    private DataDao() {

    }

    public List<Map<String,String>> loadDataAll() {
//        ServletContext rel= ServletActionContext.getServletContext();
//        System.out.println(this.getClass().getClassLoader().getResource("upload").getFile());
        ServletContext rel= ServletActionContext.getServletContext();
        File uploadFile = new File(rel.getRealPath( DATE_PATH));

        return this.loadAll(uploadFile);
    }
    private List<Map<String,String>> loadAll(File uploadFile){
        File[] files = uploadFile.listFiles();
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> f;
        for (File file : files) {
            String fileName = file.getName();
            f = new HashMap<>();
            f.put("fn",fileName);
            f.put("name",fileName.substring(fileName.indexOf('_')+1));
            f.put("type",fileName.substring(fileName.lastIndexOf(".") + 1));
            f.put("size", FileUtils.byteCountToDisplaySize(file.length()));
            list.add(f);
        }
        return list;
    }

    public List<Map<String,String>> loadImgAll() {
        ServletContext rel= ServletActionContext.getServletContext();

        File uploadFile = new File(rel.getRealPath(IMG_PATH));

        return this.loadAll(uploadFile);
    }

    public List<Map<String,String>> loadPostAll() {
        ServletContext rel= ServletActionContext.getServletContext();
        File uploadFile = new File(rel.getRealPath(POST_PATH));

        return this.loadAll(uploadFile);
    }



    public File getFileByFn(String fn) {
        ServletContext rel= ServletActionContext.getServletContext();
        File uploadFile = new File(rel.getRealPath(DATE_PATH),fn);
        return uploadFile;
    }
    public File getImgByFn(String fn) {
        ServletContext rel= ServletActionContext.getServletContext();
        File uploadFile = new File(rel.getRealPath( IMG_PATH),fn);
        return uploadFile;
    }

    public File getPostByFn(String fn) {
        ServletContext rel= ServletActionContext.getServletContext();
        File uploadFile = new File(rel.getRealPath(POST_PATH),fn);
        return uploadFile;
    }

    public Code delFCData(String[] fns) {
        ServletContext rel= ServletActionContext.getServletContext();
        File uploadFile = new File(rel.getRealPath( DATE_PATH));
        for (String fn : fns) {
            File file = new File(uploadFile,fn);
            if(file.exists()) {
                file.delete();
            }
        }

        return Code.SUCCESS;
    }
}
