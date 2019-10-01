package com.crealytics.reports.model.builder;

import com.crealytics.reports.model.Report;

import java.math.BigDecimal;

public class ReportBuider {

    public static Report build(String month, String site, Integer requests, Integer impressions, Integer clicks, Integer conversions,
                        BigDecimal revenue) {
        BigDecimal ctr = new BigDecimal((clicks / impressions) * 100);
        BigDecimal cr = new BigDecimal((conversions / impressions) * 100);
        BigDecimal filLRate = new BigDecimal((impressions / requests) * 100);
        BigDecimal eCpm = revenue.multiply(new BigDecimal(1000)).divide(new BigDecimal(impressions));
        return new Report(null, month, site, requests, impressions, clicks, conversions, revenue, ctr, cr, filLRate, eCpm);
    }


}
