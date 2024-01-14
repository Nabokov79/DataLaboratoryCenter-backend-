package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.data.models.Application;
import ru.nabokovsg.data.models.ReportingDocumentData;

import java.util.List;

public interface ReportingDocumentDataRepository extends JpaRepository<ReportingDocumentData, Long>
                                                       , QuerydslPredicateExecutor<ReportingDocumentData> {

    @Query("select r from ReportingDocumentData r where r.application in :applications")
    List<ReportingDocumentData> findAllByApplication(@Param("applications") List<Application> applications);
}