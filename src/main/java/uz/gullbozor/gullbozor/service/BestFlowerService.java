package uz.gullbozor.gullbozor.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.gullbozor.gullbozor.dto.TwoBestFlower;
import uz.gullbozor.gullbozor.entity.BestFlowerEntity;
import uz.gullbozor.gullbozor.repository.BestFlowerRepo;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@Service
public class BestFlowerService {

    private static final String uploadDirectory= "tanlovdagirasmlar";


    @Autowired
    private BestFlowerRepo bestFlowerRepo;
    

    public String uploadToSystem(MultipartHttpServletRequest request) throws IOException {


        Iterator<String> fileNames = request.getFileNames();


        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            BestFlowerEntity attach = new BestFlowerEntity();
            attach.setContentType(file.getContentType());
            attach.setSize(file.getSize());
            attach.setFileOriginalName(file.getOriginalFilename());

            String originalFilename = file.getOriginalFilename();
            String[] split = originalFilename.split("\\.");

            String name = UUID.randomUUID().toString() + "." + split[split.length - 1];

            attach.setName(name);
            bestFlowerRepo.save(attach);
            Path path = Paths.get(uploadDirectory + "/" + name);
            Files.copy(file.getInputStream(), path);

            return "Fayl saqlandi. Id si: " + attach.getId();
        }


        return "Fayl saqlanmadi.";
    }


    public void getFileFromSystem( Long id, HttpServletResponse response) throws IOException {
        Optional<BestFlowerEntity> optionalAttach = bestFlowerRepo.findById(id);
        if (optionalAttach.isPresent()) {
            BestFlowerEntity attach = optionalAttach.get();
            response.setHeader("Content-Disposition",
                    "attachment; filename\"" + attach.getFileOriginalName() + "\"");

            response.setContentType(attach.getContentType());

            FileInputStream fileInputStream = new FileInputStream(uploadDirectory+"/"+attach.getName());
            FileCopyUtils.copy(fileInputStream,response.getOutputStream());


        }
    }

    public TwoBestFlower getBestFlowers() {

return null;

    }



}
