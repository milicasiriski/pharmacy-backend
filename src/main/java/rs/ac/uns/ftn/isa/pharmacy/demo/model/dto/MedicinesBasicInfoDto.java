package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;

public class MedicinesBasicInfoDto implements Serializable {

    String name;
    String form;
    Long id;

    public MedicinesBasicInfoDto() {
    }

    public MedicinesBasicInfoDto(String name, String form, Long id) {
        this.name = name;
        this.form = form;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
