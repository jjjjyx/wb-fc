package jyx.action;

import org.apache.struts2.convention.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@ParentPackage("default-package")
@Namespace("/")
@Action(value = "user",
        results = {
//                @Result(name = "index", location = "/admin/index.jsp"),
//                @Result(name = "file", type = "stream", params = {
//                        "inputName", "inputStream", "bufferSize", "4096"
//                })
        },
        interceptorRefs = {
                @InterceptorRef("userStack"),
        }
)
public class UserAction extends BaseAction{

    /**
     * 完善用户信息
     * @return
     */
    public String fillInfo(){
        return JSON;
    }
}
