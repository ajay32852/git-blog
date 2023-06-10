
package Repository;
import Entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);
    
    public boolean existsByEmail(String email);
    
     // @Query("SELECT * FROM users where is_admin=0")
     @Query("SELECT u FROM User u WHERE u.isAdmin = '0' ")
     public List<User> findAllActiveUsers();
     
     @Query("select u from User u  where u.isAdmin='1'")
     public List<User> findAdminDetail();
     
     
}
