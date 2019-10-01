package com.crealytics.reports.service;

import com.crealytics.reports.model.Report;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportService {
    List<Report> findBySiteAndMonth(String site, String month) throws Exception;
}
