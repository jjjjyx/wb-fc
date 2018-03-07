package jyx.action;

import org.apache.struts2.convention.annotation.*;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
                    "inputName", "inputStream", "bufferSize", "4096"
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

    private FileInputStream inputStream;
    public String upload(){
        System.out.println("文件名" +fileFileName );
        System.out.println("文件类型" +fileContentType );
        System.out.println("文件大小" +file.length());
        System.out.println("文件临时路径" +file .getAbsolutePath());
        return JSON;
    }
    public String down(){
//        this.response.setContentType();
        return "file";
    }

//    public static void main(String[] args){
//        Path path = Paths.get("E:\\Desktop\\蓝鲨信息BP（2.28）_ 第二版 .pptx");
//        String contentType = null;
//        try {
//            contentType = Files.probeContentType(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("File content type is : " + contentType);
//    }


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
}
