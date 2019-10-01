package com.crealytics.reports.listener;

import com.crealytics.reports.model.Report;
import com.crealytics.reports.model.builder.ReportBuider;
import com.crealytics.reports.repository.ReportRepository;
import com.crealytics.reports.service.ReportServiceImpl;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Component
public class OnStartupListener implements ApplicationListener<ContextRefreshedEvent> {

    final String JANUARY_REPORTS_CSV_FILE_PATH = "static/2018_01_report.csv";
    final String FEBRUARY_REPORTS_CSV_FILE_PATH = "static/2018_02_report.csv";

    private static final Logger log = LoggerFactory.getLogger(OnStartupListener.class);

    @Autowired
    ReportRepository reportRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        readCsvFileAndStoreRecords(JANUARY_REPORTS_CSV_FILE_PATH);
        readCsvFileAndStoreRecords(FEBRUARY_REPORTS_CSV_FILE_PATH);
    }

    private void readCsvFileAndStoreRecords(String filePath) {
        try(CSVReader reader = new CSVReader(new FileReader(JANUARY_REPORTS_CSV_FILE_PATH))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                if(!"site".equals(line[0])) {
                    Report report = ReportBuider.build(line[0], line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3]),
                            Integer.parseInt(line[4]), Integer.parseInt(line[5]),
                            new BigDecimal(line[6]));
                    Report resp = reportRepository.save(report);
                    log.info("Storing Report from csv file = {}", resp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
