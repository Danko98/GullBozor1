package uz.gullbozor.gullbozor.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AnnounceDto {

    private Long shopId;
    private Long attachmentsId;
    private Long sellerId;
    private Long mainAttachId;
    private Long categoryId;
    private String description;
    private String title;
    private double price;
    private double height;
    private double diameter;
    private double weight;
    private boolean isWithPot;
    private boolean isActive;
    private boolean isWithFertilizer;

}
