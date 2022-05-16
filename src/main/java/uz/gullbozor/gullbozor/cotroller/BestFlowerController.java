package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.gullbozor.gullbozor.service.BestFlowerService;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@RestController
@RequestMapping("/bestFlower")
public class BestFlowerController {


    @Autowired
    private BestFlowerService bestFlowerService;

    @PostMapping("/uploadSystem")
    public String uploadToSystem(MultipartHttpServletRequest request) throws IOException {
        String result = bestFlowerService.uploadToSystem(request);
        return result;
    }

    @GetMapping("getFileFromSystem/{id}")
    public void getFileFromSystem(@PathVariable Long id, HttpServletResponse response) throws IOException {
        bestFlowerService.getFileFromSystem(id, response);
    }


}
