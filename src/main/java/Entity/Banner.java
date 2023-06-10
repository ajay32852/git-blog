
package Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.io.File;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "banners")
public class Banner {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
  
    @NotEmpty(message = "Title is Not Empty")
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = true)
    private String description;
    
    @Column(name="is_active",columnDefinition = "integer default '1'",nullable = false)
    private int isActive;
    
    @Column(nullable = false, length = 64,name = "image")
    private String image;
    
    
    
    @Column(name = "image_url",nullable = true,length = 250)
    private String imageUrl;
    
    
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

  

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public URI getBanner_img_complete() {
        File file = new File("src/main/resources/static/"+imageUrl);
        return file.toURI();
       // return "https://bucket.s3.us-east-2.amazonaws.com/" + imageUrl;
    }
    
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    @Override
    public String toString() {
        return "Banner{" + "id=" + id + ", title=" + title + ", description=" + description + ", isActive=" + isActive + ", image=" + image + ", imageUrl=" + imageUrl + '}';
    }
    

    

   
    
    
    
    
    
    
    
    
}
