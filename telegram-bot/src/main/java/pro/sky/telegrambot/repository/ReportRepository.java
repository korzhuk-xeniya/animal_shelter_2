package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.Report;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {

    Optional<Report> findById(long id);


    Optional<Report> findReportByPhotoNameId(String namePhotoId);
    List<Report> findReportByCheckReportIsFalse();

    Report findReportById(Long idReport);
}
