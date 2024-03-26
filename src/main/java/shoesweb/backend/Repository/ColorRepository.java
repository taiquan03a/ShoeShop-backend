package shoesweb.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shoesweb.backend.Entity.Color;

public interface ColorRepository extends JpaRepository<Color,Integer> {
    Color findById(int id);
}
