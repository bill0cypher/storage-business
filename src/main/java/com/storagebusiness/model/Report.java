package com.storagebusiness.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "report")
@Getter
@Setter
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "report_name")
    private String fileName;
    @Column(name = "rows_count")
    private Integer rows;
    @Column(name = "created_date")
    private Date createdDate;
}
