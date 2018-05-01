package jyx.action;

import jyx.common.Code;
import jyx.common.ResultUtils;
import jyx.dao.DataDao;
import jyx.model.DataBean;
import jyx.model.UserBean;
import jyx.server.UserServer;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletContext;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 材料管理
 */
@Controller
@ParentPackage("default-package")
@Namespace("/")
@Action(value = "file",
        results = {
            @Result(name = "index", location = "/admin/index.jsp"),
            @Result(name = "file", type = "stream", params = {
                    "inputName", "inputStream","contentType","${contentType}","contentDisposition","attachment;filename=${fileFileName}", "bufferSize", "4096"
            })
        },
        interceptorRefs = {
                @InterceptorRef("userStack"),
                @InterceptorRef ("fileUploadStack")
        }
)
public class DataAction extends BaseAction{
    private File file;
    private String fileContentType;
    // 封装上传文件名的属性
    private String fileFileName;
    private String fn;
    private String dir;
    private FileInputStream inputStream;
    private String contentType;
    private int integral;
    private int id;
//    private DataDao dataDao = DataDao.getInstance();
    @Autowired
    private UserServer userServer;

    public String upload(){
        System.out.println("文件名:" + fileFileName );
        System.out.println("文件类型:" + fileContentType );
        System.out.println("文件大小:" + file.length());
        System.out.println("文件临时路径:" +file.getAbsolutePath());
        ServletContext rel = ServletActionContext.getServletContext();
        File uploadFile = new File(rel.getRealPath(dir));

        if (!uploadFile .exists()) {//判断输出路径是否存在
            uploadFile.mkdir();
        }
        long l = new Date().getTime();
        String name = l+"_" + fileFileName;
        File f = new File(uploadFile, name);
        Map<String,Object> o = new HashMap<>();
        o.put("fn",name);
        o.put("name",name.substring(name.indexOf('_')+1));
        o.put("type",name.substring(name.lastIndexOf(".") + 1));
        o.put("size", FileUtils.byteCountToDisplaySize(file.length()));
        try {
            FileUtils.copyFile(file, f);
            ResultUtils.set(data, Code.SUCCESS, o);
        } catch (IOException e) {
            ResultUtils.set(data, Code.ERROR);
        }
        return JSON;
    }

    public String uploadDate(){
        System.out.println("上传资料");
        System.out.println("文件名:" +fileFileName );
        System.out.println("文件类型:" +fileContentType );
        System.out.println("文件大小:" +file.length());
        System.out.println("文件临时路径:" +file .getAbsolutePath());
        UserBean u = (UserBean) session.getAttribute("user");
        Object o = userServer.uploadDate(u,file,fileContentType,fileFileName,integral);
        if (o!=null) {
            ResultUtils.set(data,Code.SUCCESS,o);
        }else {
            ResultUtils.set(data,Code.ERROR);
        }
        return JSON;
    }

    public String down(){
//        File path = dataDao.getFileByFn(fn);
        DataBean db = userServer.getDate(id);
        ServletContext rel = ServletActionContext.getServletContext();
        File path = new File(rel.getRealPath(db.getSrc()));

        Path p = path.toPath();
        try {
            this.inputStream = new FileInputStream(path);
            this.fileFileName = new String(db.getName().getBytes(),"ISO8859-1");
            this.contentType = Files.probeContentType(p);
        } catch (IOException e) {
        }
        return "file";
    }



//    public static void main(String[] args){
//        Path path = Paths.get("E:\\Desktop\\xxx.pptx");
//        String contentType = null;
//        try {
//            contentType = Files.probeContentType(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("File content type is : " + contentType);
//    }


    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public FileInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(FileInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public void setId(int id) {
        this.id = id;
    }
}
