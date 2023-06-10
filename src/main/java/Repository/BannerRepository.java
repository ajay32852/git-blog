
package Repository;

import Entity.Banner;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {
    
 List<Banner> findByIsActive(int isActive);
    
    
    
    
    
}
