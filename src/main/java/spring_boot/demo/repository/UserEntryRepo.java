package spring_boot.demo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import spring_boot.demo.entry.UserEntry;

public interface UserEntryRepo extends MongoRepository<UserEntry, ObjectId> {
    UserEntry findByUserName(String username);
    void deleteByUserName(String username);
}
