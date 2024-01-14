package ru.nabokovsg.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sub_elements")
public class SubElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sub_element_name")
    private String subElementName;

    @Override
    public String toString() {
        return "SubElement{" +
                "id=" + id +
                ", subElementName='" + subElementName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubElement that = (SubElement) o;
        return id == that.id && Objects.equals(subElementName, that.subElementName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subElementName);
    }
}