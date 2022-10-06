package empower.empower.log.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "itemNotes")
    private String itemNotes;

    @Column(name = "imagePath")
    private String imagePath;

    @Column(name = "itemType")
    private String itemType;

    @Column(name = "createdDate")
    private Date createdDate;

    
    public Log() {
    }


    public Log(String itemName, String itemNotes, String imagePath, String itemType, Date createdDate){
        this.itemName=itemName;
        this.itemNotes = itemNotes;
        this.imagePath = imagePath;
        this.itemType = itemType;
        this.createdDate = createdDate;
    }

    public Log(long id, String itemName){
        this.id=id;
        this.itemName=itemName;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNotes() {
        return itemNotes;
    }

    public void setItemNotes(String itemNotes) {
        this.itemNotes = itemNotes;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


}
