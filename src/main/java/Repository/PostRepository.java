
package Repository;

import Entity.Post;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "SELECT * FROM posts WHERE title LIKE %:keyword%", nativeQuery = true)
    public List<Post> findByTitleAndStatus(String keyword);
    
    
//    @Query(value = "SELECT * FROM posts WHERE title LIKE %:keyword%", nativeQuery = true)
//    public List<Post> findByTitleAndStatus(@Param("keyword") String keyword, @Param("status") String status);

//    @Query(value = "SELECT * FROM posts WHERE title LIKE %:keyword% AND (:status IS NULL OR status = :status)", nativeQuery = true)
//    public List<Post> findByTitleAndStatus(@Param("keyword") String keyword, @Param("status") String status);
//    
    
    
    //public Page<Post> findAll(Pageable pageable);

   // public List<Post> findAll();
    //public List<Post> findAll(Pageable pageable);    
}
