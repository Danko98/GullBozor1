package uz.gullbozor.gullbozor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.entity.Attach;
import uz.gullbozor.gullbozor.entity.AttachContent;
import uz.gullbozor.gullbozor.repository.AttachContentRepo;
import uz.gullbozor.gullbozor.repository.AttachRepo;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;


@Service
public class AttachService {

    public Long generatedLong;

    @Autowired
    private AttachRepo attachRepo;

    @Autowired
    private AttachContentRepo attachContentRepo;






//    public void  getFileList(MultipartHttpServletRequest response, Long announceContactId) {
//        Optional<Attach> optionalAttach = attachRepo.findById();
//        if (optionalAttach.isPresent()) {
//            Attach attach = optionalAttach.get();
//
//            Optional<AttachContent> optionalAttachContent = attachContentRepo.findByAttachId(uuid);
//            if (optionalAttachContent.isPresent()) {
//                AttachContent attachContent = optionalAttachContent.get();
//                response.setHeader("Content-Disposition", "attachment; filename=\""+attach.getFileOriginalName()+"\"" );
//                response.setContentType(attach.getContentType());
//
//                FileCopyUtils.copy(attachContent.getBytes(),response.getOutputStream());
//            }
//        }
//    }


}
