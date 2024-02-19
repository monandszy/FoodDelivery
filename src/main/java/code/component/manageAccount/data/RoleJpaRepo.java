package code.component.manageAccount.data;

import code.component.manageAccount.domain.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepo extends JpaRepository<RoleEntity, Integer> {
}