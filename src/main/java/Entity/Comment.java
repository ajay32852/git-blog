
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
@Table(name = "comments")
public class Comment {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;   
    
    @Column(name = "post_id",nullable = false)
    private int post_id;
    
    @Column(name = "user_id",nullable = false)
    private int user_id;
    
    @Column(name = "message",nullable = false)
    private String message;
    
    @Column(name="status",columnDefinition = "integer default '0'")
    private int status;
    
    @Column(name = "created_at",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
    
    @Column(name = "updated_at",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;
    
    
     @PrePersist
     protected void onCreate() {
        this.created_at = new Date();
        this.updated_at = new Date();
     }

    @PreUpdate
    protected void onUpdate() {
        this.updated_at = new Date();
    } 
    
    
    
    
    
}
