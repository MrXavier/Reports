package com.crealytics.reports.service;

import com.crealytics.reports.model.Report;
import com.crealytics.reports.repository.ReportRepository;
import com.crealytics.reports.util.Month;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ReportServiceImpl implements ReportService {

    private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);

    private ReportRepository reportRepository;

    private Map<String, String> monthsMap;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
        this.monthsMap = new HashMap<>(); // TODO make a property file and Object
        monthsMap.put("1", Month.JANUARY.getValue());
        monthsMap.put("2", Month.FEBRUARY.getValue());
        monthsMap.put("JAN", Month.JANUARY.getValue());
        monthsMap.put("FEB", Month.FEBRUARY.getValue());
        monthsMap.put("JANUARY", Month.JANUARY.getValue());
        monthsMap.put("FEBRUARY", Month.FEBRUARY.getValue());
    }

    @Override
    public List<Report> findBySiteAndMonth(String site, String month) throws Exception {
        log.info("Finding by site = {} and month = {} ", site, month);

        String mappedMonth = monthsMap.get(month.toUpperCase());
        log.debug("mappedMonth = {} ", mappedMonth);
        if(mappedMonth == null){
            log.error("month not found = {} ", month);
            throw new Exception(); // TODO make a validation exception with proper message
        }

        List<Report> reports = reportRepository.findBySiteAndMonth(site, mappedMonth);
        if (reports == null || reports.isEmpty()) {
            log.info("reports not found = {} ", month);
            throw new NotFoundException(null); // FIXME create NotFound Exception returning 404 with message
        }

        return reports;
    }
}
