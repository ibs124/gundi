package ibs124.gundi.model.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Authority extends AbstractDomainModel {

    private String name;

    public Authority() {
        super();
    }

    public Authority(String name) {
        this();
        this.setName(name);
    }

    @NotBlank
    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
