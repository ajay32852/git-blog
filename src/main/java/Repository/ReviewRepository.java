
package Repository;
import Entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

   @Query(value = "SELECT * FROM reviews WHERE post_id = :postId", nativeQuery = true)
   List<Review> findAllByPostId(@Param("postId") int postId);
   
    
    
    
}
