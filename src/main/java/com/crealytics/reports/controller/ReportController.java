package com.crealytics.reports.controller;

import com.crealytics.reports.model.Report;
import com.crealytics.reports.service.ReportService;
import com.crealytics.reports.service.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class ReportController {
    ReportService reportService;

    @Autowired
    public ReportController(ReportServiceImpl reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(value = "/reports", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Report>> findBySiteAndMonth(@RequestParam String site, @RequestParam String month) {
        List<Report> reports = null;
        try {
            reports = reportService.findBySiteAndMonth(site, month);
        } catch (Exception e) {
            new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Report>>(reports, HttpStatus.OK);
    }
}
