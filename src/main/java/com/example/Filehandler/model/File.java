package com.example.Filehandler.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;

    @Lob
    @Column(name="filedata")
    private byte[] fileData;

}
