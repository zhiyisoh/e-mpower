package empower.empower.log.entity;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Emissions")
public class Emissions {
    @Id
    private String itemName;

    @Column(name = "EmissionsSaved")
    private String EmissionsSaved;

    @OneToMany(mappedBy = "emissions")
    private List<Log> logs;

    public Emissions() {
    }

    public Emissions(String itemName, String emissionsSaved) {
        this.itemName = itemName;
        EmissionsSaved = emissionsSaved;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getEmissionsSaved() {
        return EmissionsSaved;
    }

    public void setEmissionsSaved(String emissionsSaved) {
        EmissionsSaved = emissionsSaved;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }
    
}
