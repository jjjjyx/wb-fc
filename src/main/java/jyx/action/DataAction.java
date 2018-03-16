package jyx.action;

import jyx.common.Code;
import jyx.common.ResultUtils;
import jyx.dao.DataDao;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
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
    private DataDao dataDao = DataDao.getInstance();

    public String upload(){
        System.out.println("文件名:" +fileFileName );
        System.out.println("文件类型:" +fileContentType );
        System.out.println("文件大小:" +file.length());
        System.out.println("文件临时路径:" +file .getAbsolutePath());
        ServletContext rel = ServletActionContext.getServletContext();
        File uploadFile = new File(rel.getRealPath(dir));

        if (!uploadFile .exists()) {//判断输出路径是否存在
            uploadFile.mkdir();
        }
        File f = new File(uploadFile, new Date().getTime()+"_" + fileFileName);
        try {
            FileUtils.copyFile(file, f);
            ResultUtils.set(data, Code.SUCCESS);
        } catch (IOException e) {
            ResultUtils.set(data, Code.ERROR);
        }
        return JSON;
    }
    public String down(){
        File path = dataDao.getFileByFn(fn);
        Path p = path.toPath();
        try {
            this.inputStream = new FileInputStream(path);
//            this.fileFileName = new String(fn.substring(fn.indexOf('_')+1).getBytes(),"ISO8859-1");
            this.fileFileName = fn.substring(fn.indexOf('_')+1);
//            System.out.println("fileFileName = " + fileFileName);
            this.contentType = Files.probeContentType(p);
//            response.setContentType(contentType);
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
}
