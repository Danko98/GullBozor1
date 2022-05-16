package uz.gullbozor.gullbozor.cotroller;

import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.gullbozor.gullbozor.entity.Attach;
import uz.gullbozor.gullbozor.entity.MainAttach;
import uz.gullbozor.gullbozor.repository.AttachRepo;
import uz.gullbozor.gullbozor.repository.MainAttachRepo;
import uz.gullbozor.gullbozor.service.AttachService;


import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    private AttachService attachService;

    @Autowired
    private AttachRepo attachRepo;

    @Autowired
    private MainAttachRepo mainAttachRepo;

    private static final String uploadDirectory= "yuklanganlar";


    @PostMapping("/uploadSystem")
    public Long uploadToSystem(MultipartHttpServletRequest request) throws IOException {
        boolean isMain = true;
        Iterator<String> fileNames = request.getFileNames();
        Long mainAttachId = null;

        while (fileNames.hasNext()) {

            if (isMain) {
                MultipartFile file = request.getFile(fileNames.next());
                if (file != null) {
                    MainAttach mainAttach = new MainAttach();
                    mainAttach.setContentType(file.getContentType());
                    mainAttach.setSize(file.getSize());
                    mainAttach.setFileOriginalName(file.getOriginalFilename());
                    String originalFilename = file.getOriginalFilename();
                    String[] split = originalFilename.split("\\.");
                    String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
                    mainAttach.setName(name);
                    mainAttachRepo.save(mainAttach);
                    Path path = Paths.get(uploadDirectory + "/" + name);
                    Files.copy(file.getInputStream(), path);
                    mainAttachId = mainAttach.getId();
                    isMain = false;
                }
            }else {

                MultipartFile file = request.getFile(fileNames.next());
                if (file != null) {
                    Attach attach = new Attach();
                    attach.setContentType(file.getContentType());
                    attach.setSize(file.getSize());
                    attach.setFileOriginalName(file.getOriginalFilename());
                    String originalFilename = file.getOriginalFilename();
                    String[] split = originalFilename.split("\\.");
                    String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
                    attach.setName(name);
                    attach.setMainAttachId(mainAttachId);
                    attachRepo.save(attach);
                    Path path = Paths.get(uploadDirectory + "/" + name);
                    Files.copy(file.getInputStream(), path);

                }
            }
        }

        return mainAttachId;
    }


//    @PostMapping(value = "/uploadSystem")
//    public String uploadFilToSystem(MultipartHttpServletRequest request) throws IOException {
//        Iterator<String> fileNames = request.getFileNames();
//
//        while (fileNames.hasNext()) {
//            MultipartFile file = request.getFile(fileNames.next());
//
//            if (file != null) {
//
//                String originalFilename = file.getOriginalFilename();
//
//                Attach attach = new Attach();
//                attach.setFileOriginalName(originalFilename);
//                attach.setSize(file.getSize());
//                attach.setContentType(file.getContentType());
//
//                String[] split = originalFilename.split("\\.");
//                ///32tg3g4t3t45g5834988ryh293wy3y294yr9.jpg
//                String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
//
//                attach.setName(name);
//                attachRepo.save(attach);
//                Path path = Paths.get(uploadDirectory + "/" + name);
//                Files.copy(file.getInputStream(), path);
//
//
//            }
//        }
//        return "Fayllar saqlandi.";
//    }

    @GetMapping("getFileFromSystem/{id}")
    public void getFileFromSystem(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Optional<Attach> optionalAttach = attachRepo.findById(id);
        if (optionalAttach.isPresent()) {
            Attach attach = optionalAttach.get();
            response.setHeader("Content-Disposition",
                    "attachment; filename\"" + attach.getFileOriginalName() + "\"");

            response.setContentType(attach.getContentType());

            FileInputStream fileInputStream = new FileInputStream(uploadDirectory+"/"+attach.getName());
            FileCopyUtils.copy(fileInputStream,response.getOutputStream());

        }
    }

    @GetMapping("getMainAttachmentFromSystem/{id}")
    public void getMainAttachFromSystem(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Optional<MainAttach> optionalMainAttach = mainAttachRepo.findById(id);
        if (optionalMainAttach.isPresent()) {
            MainAttach mainAttach = optionalMainAttach.get();
            response.setHeader("Content-Disposition",
                    "attachment; filename\"" + mainAttach.getFileOriginalName() + "\"");

            response.setContentType(mainAttach.getContentType());

            FileInputStream fileInputStream = new FileInputStream(uploadDirectory+"/"+mainAttach.getName());
            FileCopyUtils.copy(fileInputStream,response.getOutputStream());
        }
    }


}
