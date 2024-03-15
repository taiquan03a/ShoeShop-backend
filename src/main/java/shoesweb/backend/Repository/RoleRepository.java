package shoesweb.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shoesweb.backend.Entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {

}
