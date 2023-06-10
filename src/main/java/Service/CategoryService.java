
package Service;

import Entity.Category;
import Repository.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    
    @Autowired 
    private CategoryRepository categoryRepository;
    public Category  StoreCategory(Category category)
    {
      return categoryRepository.save(category);   
    }
    
    public List<Category> getAllcategory()
    {
      return categoryRepository.findAll();
    }
    public void deleteCategoryById(int id)
    {
       categoryRepository.deleteById(id);
    }

    public void deletePostById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int getCategoryCount()
    {
        return (int) categoryRepository.count();
    }
    
    
    
    
}
