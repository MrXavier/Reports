package com.crealytics.reports.listener;

import com.crealytics.reports.model.Report;
import com.crealytics.reports.model.builder.ReportBuider;
import com.crealytics.reports.repository.ReportRepository;
import com.crealytics.reports.util.Month;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

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
        String januaryFilePath = OnStartupListener.class.getClassLoader().getResource(JANUARY_REPORTS_CSV_FILE_PATH).getPath();
        String februaryFilePath = OnStartupListener.class.getClassLoader().getResource(FEBRUARY_REPORTS_CSV_FILE_PATH).getPath();
        readCsvFileAndStoreRecords(januaryFilePath, Month.JANUARY.getValue());
        readCsvFileAndStoreRecords(februaryFilePath, Month.FEBRUARY.getValue());
    }

    private void readCsvFileAndStoreRecords(String filePath, String month) {
        try(CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                if(!"site".equals(line[0])) {
                    Report report = ReportBuider.build(month, line[0].trim(), Integer.parseInt(line[1].trim()), Integer.parseInt(line[2].trim()), Integer.parseInt(line[3].trim()),
                            Integer.parseInt(line[4].trim()), new BigDecimal(line[5].trim()));
                    Report resp = reportRepository.save(report);
                    log.info("Storing Report from csv file = {}", resp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
