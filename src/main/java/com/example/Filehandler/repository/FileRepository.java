package com.example.Filehandler.repository;
import com.example.Filehandler.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File,Long> {
    Optional<File> findByName(String fileName);

}
