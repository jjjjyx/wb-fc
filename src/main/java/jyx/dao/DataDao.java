package jyx.dao;

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

    public static DataDao getInstance() {
        return ourInstance;
    }

    private DataDao() {

    }

    public List<Map<String,String>> loadAll() {
//        ServletContext rel= ServletActionContext.getServletContext();
//        System.out.println(this.getClass().getClassLoader().getResource("upload").getFile());
        ServletContext rel= ServletActionContext.getServletContext();
        File uploadFile = new File(rel.getRealPath( "upload"));
        System.out.println(uploadFile);
//
        File[] files = uploadFile.listFiles();
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> f;
        for (File file : files) {
            String fileName = file.getName();
            f = new HashMap<>();
            f.put("fn",fileName);
            f.put("name",fileName.substring(fileName.indexOf('_')+1));
            f.put("type",fileName.substring(fileName.lastIndexOf(".") + 1));
            list.add(f);
        }
        return list;
    }

}
