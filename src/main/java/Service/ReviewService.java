
package Service;
import Entity.Review;
import Repository.ReviewRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ReviewService {
 
    @Autowired
    private ReviewRepository reviewRepo;
    
    public Review SaveReview(Review review)
    {
      return reviewRepo.save(review);
    }
    
    public List<Review> getAllReviews()
    {
      return reviewRepo.findAll();
    }
    public List<Review> getAllReviewsByPostId(int postId)
    {
      return reviewRepo.findAllByPostId(postId);
    }
    

}
