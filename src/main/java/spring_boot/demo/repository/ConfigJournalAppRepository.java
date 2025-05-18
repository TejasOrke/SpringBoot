package spring_boot.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring_boot.demo.entry.ConfigJournalEntity;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalEntity, String> {
}
