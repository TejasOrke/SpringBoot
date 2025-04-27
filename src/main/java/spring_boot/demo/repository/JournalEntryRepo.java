package spring_boot.demo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import spring_boot.demo.entry.JournalEntry;

public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {
}
