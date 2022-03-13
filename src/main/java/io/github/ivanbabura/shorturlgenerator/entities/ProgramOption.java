package io.github.ivanbabura.shorturlgenerator.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table()
public class ProgramOption {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idOption;

    @Column(nullable = false, unique = true)
    String nameOption;

    @Column(nullable = false)
    String valueOption;

    public Integer getIdOption() {
        return idOption;
    }

    public String getNameOption() {
        return nameOption;
    }

    public void setNameOption(String nameOption) {
        this.nameOption = nameOption;
    }

    public String getValueOption() {
        return valueOption;
    }

    public void setValueOption(String valueOption) {
        this.valueOption = valueOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramOption pm = (ProgramOption) o;
        return idOption.equals(pm.idOption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOption);
    }
}