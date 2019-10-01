package com.crealytics.reports.repository;

import com.crealytics.reports.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    public List<Report> findBySiteAndMonth(@Param("site") String site, @Param("month") String month);

}
