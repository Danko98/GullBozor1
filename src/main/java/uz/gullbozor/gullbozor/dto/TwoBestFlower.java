package uz.gullbozor.gullbozor.dto;

import lombok.Getter;
import lombok.Setter;
import uz.gullbozor.gullbozor.entity.BestFlowerEntity;

@Getter
@Setter
public class TwoBestFlower {

    private BestFlowerEntity firstFlower;
    private BestFlowerEntity secondFlower;

}
