package com.crealytics.reports.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity

public class Report {
    public Report(Long id, @Size(min = 2, max = 20, message = "Month too short or too long") @NotNull(message = "Month should not be null") String month, @Size(min = 2, max = 50, message = "Month too short or too long") @NotNull(message = "Site should not be null") String site, Integer requests, Integer impressions, Integer clicks, Integer conversions, BigDecimal revenue, BigDecimal CTR, BigDecimal CR, BigDecimal fill_rate, BigDecimal eCPM) {
        this.id = id;
        this.month = month;
        this.site = site;
        this.requests = requests;
        this.impressions = impressions;
        this.clicks = clicks;
        this.conversions = conversions;
        this.revenue = revenue;
        this.CTR = CTR;
        this.CR = CR;
        this.fill_rate = fill_rate;
        this.eCPM = eCPM;
    }

    public Report() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, max = 20, message = "Month too short or too long")
    @NotNull(message = "Month should not be null")
    @Column(nullable = false, length = 20)
    private String month;

    @Size(min = 2, max = 50, message = "Month too short or too long")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Integer getRequests() {
        return requests;
    }

    public void setRequests(Integer requests) {
        this.requests = requests;
    }

    public Integer getImpressions() {
        return impressions;
    }

    public void setImpressions(Integer impressions) {
        this.impressions = impressions;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Integer getConversions() {
        return conversions;
    }

    public void setConversions(Integer conversions) {
        this.conversions = conversions;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public BigDecimal getCTR() {
        return CTR;
    }

    public void setCTR(BigDecimal CTR) {
        this.CTR = CTR;
    }

    public BigDecimal getCR() {
        return CR;
    }

    public void setCR(BigDecimal CR) {
        this.CR = CR;
    }

    public BigDecimal getFill_rate() {
        return fill_rate;
    }

    public void setFill_rate(BigDecimal fill_rate) {
        this.fill_rate = fill_rate;
    }

    public BigDecimal geteCPM() {
        return eCPM;
    }

    public void seteCPM(BigDecimal eCPM) {
        this.eCPM = eCPM;
    }
}