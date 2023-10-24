package thinhld.ldt.reportservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thinhld.ldt.reportservice.model.Report;

import java.util.List;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {

}
