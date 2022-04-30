package uz.gullbozor.gullbozor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.AnnounceDto;
import uz.gullbozor.gullbozor.entity.Announce;
import uz.gullbozor.gullbozor.entity.Category;
import uz.gullbozor.gullbozor.entity.UserEntity;
import uz.gullbozor.gullbozor.repository.AnnounceRepo;
import uz.gullbozor.gullbozor.repository.CategoryRepo;
import uz.gullbozor.gullbozor.repository.UserRepo;

import java.util.Optional;

@Service
public class AnnounceService {

    @Autowired
    private AnnounceRepo announceRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private UserRepo userRepo;

    public ApiResponse addAnnounce(AnnounceDto announceDto) {

        if (!categoryRepo.existsById(announceDto.getCategoryId())) {
            return new ApiResponse("Not found announce",false);
        }

        if (!userRepo.existsById(announceDto.getSellerId())) {
            return new ApiResponse("Not found Seller",false);
        }


        Optional<Category> optionalCategory = categoryRepo.findById(announceDto.getCategoryId());
        Optional<UserEntity> optionalSellerEntity = userRepo.findById(announceDto.getSellerId());

        Announce announce = new Announce();



        if (announceDto.getMainAttachId() != null) {
            announce.setId(announceDto.getMainAttachId());
        }

        if (announceDto.getAttachmentsId() != null) {
            announce.setId(announceDto.getAttachmentsId());
        }

        announce.setActive(true);
        announce.setCategory(optionalCategory.get());
        announce.setUserEntity(optionalSellerEntity.get());
        //announce.setAttachMain();
        announce.setDescription(announceDto.getDescription());
        announce.setDiameter(announceDto.getDiameter());
        announce.setHeight(announceDto.getHeight());
        announce.setPrice(announceDto.getPrice());
        announce.setTitle(announceDto.getTitle());
        announce.setWeight(announceDto.getWeight());
        announce.setWithFertilizer(announceDto.isWithFertilizer());
        announce.setWithPot(announceDto.isWithPot());

        announceRepo.save(announce);
        return new ApiResponse("Successfully saved",true);

    }

    public ApiResponse editAnnounce(AnnounceDto announceDto, Long id) {

        if (!announceRepo.existsById(id)) {
            return new ApiResponse("Not found announce",false);
        }

        if (!categoryRepo.existsById(announceDto.getCategoryId())) {
            return new ApiResponse("Not found announce",false);
        }

        if (!userRepo.existsById(announceDto.getSellerId())) {
            return new ApiResponse("Not found Serller",false);
        }


        Optional<Category> optionalCategory = categoryRepo.findById(announceDto.getCategoryId());
        Optional<UserEntity> optionalSellerEntity = userRepo.findById(announceDto.getSellerId());

        Optional<Announce> optionalAnnounce = announceRepo.findById(id);

        Announce announce = optionalAnnounce.get();


        announce.setActive(announceDto.isActive());
        announce.setCategory(optionalCategory.get());
        announce.setUserEntity(optionalSellerEntity.get());
        //announce.setAttachMain();
        announce.setDescription(announceDto.getDescription());
        announce.setDiameter(announceDto.getDiameter());
        announce.setHeight(announceDto.getHeight());
        announce.setPrice(announceDto.getPrice());
        announce.setTitle(announceDto.getTitle());
        announce.setWeight(announceDto.getWeight());
        announce.setWithFertilizer(announceDto.isWithFertilizer());
        announce.setWithPot(announceDto.isWithPot());

        announceRepo.save(announce);
        return new ApiResponse("Successfully edited",true);

    }

    public ApiResponse getAnnounceById(Long id) {
        if (!announceRepo.existsById(id)) {
            return new ApiResponse("Not found announce",false);
        }
        Optional<Announce> optionalAnnounce = announceRepo.findById(id);
        return new ApiResponse(optionalAnnounce.get());
    }

    public Page<Announce> getAnnouncePage(int page) {

        Pageable pageable = PageRequest.of(page, 20);
        return announceRepo.findAll(pageable);


    }

    public ApiResponse deleteAnnounceById(Long id) {
        if (!announceRepo.existsById(id)) {
            return new ApiResponse("Not found announce",false);
        }
        announceRepo.deleteById(id);
        return new ApiResponse("Successfully deleted",true);
    }



}
