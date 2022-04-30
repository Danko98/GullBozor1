package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.service.AttachService;




@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    private AttachService attachService;


    @PostMapping("/upload")
    public ApiResponse uploadFile(MultipartHttpServletRequest request)  {
        return attachService.uploadFile(request);
    }


}
