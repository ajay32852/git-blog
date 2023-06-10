
package Entity;


import Security.ApplicationProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import java.io.File;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Transient;
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "posts")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name="category_id",nullable = false)
    private int category_id;
    
    @Column(name="user_id",nullable = false,columnDefinition = "integer default '1'")
    private int user_id;
    
    @NotEmpty(message = "Title is Required")
    @Column(name = "title",nullable = false)
    private String title;
    
    @Column(name = "description",nullable = false)
    private String description;
    
    @Column(name = "image",nullable = false)
    private String image;
    @Column(name = "image_url",nullable = true)
    private String imageUrl;
    
    
    @Column(name="is_popular",columnDefinition = "integer default '1'")
    private int isPopular;
    
    @Column(name="is_active",columnDefinition = "integer default '1'")
    private int isActive;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    
     
    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Category category;  
    
    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",insertable = false,updatable = false)
    private User user;
    
    
//    @OneToMany
//    @JoinColumn(name = "id", referencedColumnName = "post_id",insertable = false,updatable = false)
//    private Review review;
    
     @OneToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "id", referencedColumnName = "post_id",insertable = false,updatable = false)
     private Review reviews; 
    
   
    public double  getAverageRating()
    {
       return 2.33;
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
    
    public String getPost_img_complete() {
        File file = new File("src/main/resources/static/"+imageUrl);
        return "http://localhost:9080/Blog/"+imageUrl;
        //return "https://bucket.s3.us-east-2.amazonaws.com/" + imageUrl;
       // return contextPath+imageUrl;
    }
    
           
    
    
    
    
}
