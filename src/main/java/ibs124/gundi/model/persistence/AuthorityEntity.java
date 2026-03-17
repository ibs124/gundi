package ibs124.gundi.model.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class AuthorityEntity extends AbstractEntity {

    private String name;

    public AuthorityEntity() {
        super();
    }

    public AuthorityEntity(String name) {
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
