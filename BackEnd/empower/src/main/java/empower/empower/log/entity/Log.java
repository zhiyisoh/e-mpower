package empower.empower.log.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import empower.empower.springjwt.models.User;

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

    @Column(name = "itemType")
    private String itemType;

    @Column(name = "createdDate")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="emissions_id", nullable=false)
    private Emissions emissions;

    
    public Log() {
    }


    public Log(String itemName, String itemNotes, String itemType, Date createdDate){
        this.itemName=itemName;
        this.itemNotes = itemNotes;
        this.itemType = itemType;
        this.createdDate = createdDate;
    }

    public Log(String itemName, String itemNotes, String itemType, Date createdDate, User user, Emissions emissions) {
        this.itemName = itemName;
        this.itemNotes = itemNotes;
        this.itemType = itemType;
        this.createdDate = createdDate;
        this.user = user;
        this.emissions = emissions;
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

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }


    public Emissions getEmissions() {
        return emissions;
    }


    public void setEmissions(Emissions emissions) {
        this.emissions = emissions;
    }

}
