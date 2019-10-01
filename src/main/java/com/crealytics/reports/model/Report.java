package com.crealytics.reports.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
public class Report {
    public Report() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 5, max = 20, message = "Month too short or too long")
    @NotNull(message = "Month should not be null")
    @Column(nullable = false, length = 20)
    private String month;

    @Size(min = 5, max = 50, message = "Month too short or too long")
    @NotNull(message = "Site should not be null")
    @Column(nullable = false, length = 50)
    private String site;

    @Column(nullable = false)
    private Integer requests;

    @Column(nullable = false)
    private Integer impressions;

    @Column(nullable = false)
    private Integer clicks;

    @Column(nullable = false)
    private Integer conversions;

    @Column(nullable = false)
    private BigDecimal revenue;

    @Column(nullable = false)
    private BigDecimal CTR;

    @Column(nullable = false)
    private BigDecimal CR;

    @Column(nullable = false)
    private BigDecimal fill_rate;

    @Column(nullable = false)
    private BigDecimal eCPM;
}