
package Service;

import Entity.Banner;
import Repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class BannerService {
    @Autowired
    private BannerRepository bannerRepo;
    
    public List<Banner> getBannersByIsActive(int isActive)
    {
     return bannerRepo.findByIsActive(isActive);   
    }
    public void deleteBannerById(int id)
    {
         bannerRepo.deleteById(id);
    }
    
    public Banner getUserById(int id)
    {
      return bannerRepo.findById(id).orElse(null);
    }
    
    public int getBannerCount()
    {
        return (int) bannerRepo.count();
    }
    public Banner findBannerById(int id)
    {
     return bannerRepo.findById(id).orElse(null);
    }
    
    
    
    
    
}
