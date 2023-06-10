
package Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;   
    
    @Column(name = "post_id",nullable = false)
    private int post_id;
    
    @Column(name = "user_id",nullable = false)
    private int user_id;
    
    @Column(name = "rating",nullable = false)
    private int rating;
    
    
    @Column(name = "comment",nullable = false)
    private String comment;
    
    
    @Column(name="status",columnDefinition = "integer default '0'")
    private int status;
    
    @Column(name = "created_at",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
    
    @Column(name = "updated_at",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;
    
    
    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",insertable = false,updatable = false)
    private User user;
    
    
//    @OneToOne
//    @JoinColumn(name = "post_id",referencedColumnName = "id",insertable = false,updatable = false)
//    private Post post;
    
    
    
    @PrePersist
    protected void onCreate() {
        this.created_at = new Date();
        this.updated_at = new Date();
     }

    @PreUpdate
    protected void onUpdate() {
        this.updated_at = new Date();
    } 

   public  int getRating() {
        return 4;
    }
  
    
}
