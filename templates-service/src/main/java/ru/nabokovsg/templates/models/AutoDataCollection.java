package ru.nabokovsg.templates.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auto_data_collections")
public class AutoDataCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "protocol_id")
    private Long protocolId;
    @Column(name = "protocol_conclusion")
    private boolean protocolConclusion;
    @Column(name = "auto_table")
    private boolean autoTable;
}