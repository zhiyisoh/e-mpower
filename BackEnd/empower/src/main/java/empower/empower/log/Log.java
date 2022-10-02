package empower.empower.log;

import javax.persistence.*;
import javax.validation.constraints.*;

import empower.empower.springjwt.models.*;

@Entity
// @Getter
// @Setter
// @ToString
// @AllArgsConstructor
// @NoArgsConstructor
// @EqualsAndHashCode
public class Log {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY) private long id;
    
    @NotNull(message="Item name should not be null")
    @Size(min=1, max=200, message="Item name should be at least 1 character long")
    private String itemName;
    
    private String itemNotes;
    private String imagePath;

  
     @ManyToOne
     @JoinColumn(name = "user_id", nullable=false)
     private User user;
    

    public Log(String itemName){
        this.itemName=itemName;
    }

    public Log(long id, String itemName, String itemNotes, String imagePath){
        this.id=id;
        this.itemName=itemName;
        this.itemNotes = itemNotes;
        this.imagePath = imagePath;
    }

    public Log(long id, String itemName){
        this.id=id;
        this.itemName=itemName;
    }

    public long getId(){
        return id;
    }

    public String getItemName(){
        return itemName;
    }

    public String getItemNotes(){
        return itemNotes;
    }

    public String getImagePath(){
        return imagePath;
    }

    public void setId(long newId){
        this.id=newId;
    }

    public void setItemName(String newItemName){
        this.itemName=newItemName;
    }

    public void setItemNotes(String newItemNotes){
        this.itemNotes = newItemNotes;
    }

    public void setImagePath(String newImagePath){
        this.imagePath = newImagePath;
    }
}
