package shoesweb.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shoesweb.backend.Entity.Size;

//@Repository
public interface SizeReponsitory extends JpaRepository<Size,Integer> {
    Size findById(int id);
}
