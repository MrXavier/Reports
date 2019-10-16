package com.crealytics.reports.model.builder;

import com.crealytics.reports.model.Report;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ReportBuider {

    public static Report build(String month, String site, Integer requests, Integer impressions, Integer clicks, Integer conversions,
                        BigDecimal revenue) {
        double clicksFloat = (double) clicks;
        double impressionsFloat = (double) impressions;
        double conversionsFloat = (double) conversions;
        double requestsFloat = (double) requests;

        BigDecimal ctr = new BigDecimal((clicksFloat/ impressionsFloat) * 100).setScale(3, RoundingMode.valueOf(1));
        BigDecimal cr = new BigDecimal((conversionsFloat / impressionsFloat) * 100).setScale(3, RoundingMode.valueOf(1));
        BigDecimal filLRate = new BigDecimal((impressionsFloat / requestsFloat) * 100).setScale(3, RoundingMode.valueOf(1));
        BigDecimal eCpm = revenue.multiply(new BigDecimal(1000)).divide(new BigDecimal(impressions), 3, RoundingMode.valueOf(1));
        return new Report(null, month, site, requests, impressions, clicks, conversions, revenue, ctr, cr, filLRate, eCpm);
    }

}
