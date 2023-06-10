
package Service;

import Entity.Post;
import Repository.PostRepository;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;



@Service
public class PostService {
    
    @Autowired
    private PostRepository postRepository;
    
    public List<Post> getAllPosts(String keyword ,String status)
    {
      if(keyword!=null)
      {
          return postRepository.findByTitleAndStatus(keyword);  
      }else{
          return postRepository.findAll();  
          //return postRepository.findByTitle(keyword);  
      }
        
    
      
      
      
    }
    public Post saveData(Post post)
    {
       return postRepository.save(post);
    }
    
    public void deletePostById(int id)
    {
         postRepository.deleteById(id);
    }
    
    public int getPostCount()
    {
       return (int) postRepository.count();
    }
    public Optional<Post> getPostById(int id)
    {
       return postRepository.findById(id);
    }
   
    
    
    
    
    
    
}
