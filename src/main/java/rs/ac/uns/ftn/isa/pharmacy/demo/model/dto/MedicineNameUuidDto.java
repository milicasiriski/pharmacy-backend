package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class MedicineNameUuidDto implements Serializable {

    String uuid;
    String name;

    public MedicineNameUuidDto(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public MedicineNameUuidDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineNameUuidDto that = (MedicineNameUuidDto) o;
        return Objects.equals(uuid, that.uuid) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name);
    }

    @Override
    public String toString() {
        return "MedicineNameUuidDto{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
