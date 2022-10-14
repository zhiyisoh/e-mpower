package empower.empower.bin.entity;

import javax.persistence.*;

@Entity
@Table(name = "ewaste")
public class Ewaste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="type")
    private String type;

    public Ewaste(){

    }

    public Ewaste(Long id, String type){
        this.id=id;
        this.type=type;
    }
}
