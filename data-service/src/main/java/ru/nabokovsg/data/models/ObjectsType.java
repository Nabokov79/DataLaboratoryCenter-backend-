package ru.nabokovsg.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "object_types")
public class ObjectsType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "object_name")
    private String objectName;
    @Column(name = "model")
    private String model;
    @Column(name = "orientation")
    private String orientation;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "object_types_repair_methods",
            joinColumns = {@JoinColumn(name = "object_type_id")},
            inverseJoinColumns = {@JoinColumn(name = "repair_method_id")})
    @ToString.Exclude
    private List<RepairMethod> repairMethods;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "object_types_documentations",
            joinColumns = {@JoinColumn(name = "object_type_id")},
            inverseJoinColumns = {@JoinColumn(name = "document_id")})
    @ToString.Exclude
    private List<Documentation> documentations;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "object_types_defects",
            joinColumns = {@JoinColumn(name = "object_type_id")},
            inverseJoinColumns = {@JoinColumn(name = "defect_id")})
    @ToString.Exclude
    private List<Defect> defects;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "object_types_elements",
            joinColumns = {@JoinColumn(name = "object_type_id")},
            inverseJoinColumns = {@JoinColumn(name = "element_id")})
    @ToString.Exclude
    private List<Element> elements;

    @Override
    public String toString() {
        return "ObjectsType{" +
                "id=" + id +
                ", objectName='" + objectName + '\'' +
                ", orientation='" + orientation + '\'' +
                ", repairMethods=" + repairMethods +
                ", documentations=" + documentations +
                ", defects=" + defects +
                ", elements=" + elements +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectsType that = (ObjectsType) o;
        return id == that.id && Objects.equals(objectName, that.objectName)
                             && Objects.equals(orientation, that.orientation)
                             && Objects.equals(repairMethods, that.repairMethods)
                             && Objects.equals(documentations, that.documentations)
                             && Objects.equals(defects, that.defects)
                             && Objects.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, objectName, orientation, repairMethods, documentations, defects, elements);
    }
}