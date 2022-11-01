package empower.empower.log.entity;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Emissions")
public class Emissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String itemName;

    @Column(name = "emissionsSaved")
    private Double EmissionsSaved;

    @OneToMany(mappedBy="emissions", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Log> logs;

    public Emissions() {
    }

    public Emissions(String itemName, Double emissionsSaved) {
        this.itemName = itemName;
        EmissionsSaved = emissionsSaved;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getEmissionsSaved() {
        return EmissionsSaved;
    }

    public void setEmissionsSaved(Double emissionsSaved) {
        EmissionsSaved = emissionsSaved;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }
    
}
