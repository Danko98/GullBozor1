package uz.gullbozor.gullbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullbozor.gullbozor.entity.Announce;

public interface AnnounceRepo extends JpaRepository<Announce, Long> {
}
