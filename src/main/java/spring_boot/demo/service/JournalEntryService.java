package spring_boot.demo.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_boot.demo.entry.JournalEntry;
import spring_boot.demo.entry.UserEntry;
import spring_boot.demo.repository.JournalEntryRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    UserEntryService userEntryService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry , String userName) {
        try {
            UserEntry user =  userEntryService.findByUserName(userName);
            journalEntry.setDate(LocalDate.from(LocalDateTime.now()));
            JournalEntry saved = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            userEntryService.saveEntry(user);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the entry: " + e.getMessage());
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
//        UserEntry byUserName = userEntryService.findByUserName(userName);
//        journalEntry.setDate(LocalDate.now());
//        JournalEntry save = journalEntryRepo.save(journalEntry);
//        byUserName.getJournalEntries().add(save);
//        userEntryService.saveEntry(byUserName);
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            UserEntry byUserName = userEntryService.findByUserName(userName);
            removed = byUserName.getJournalEntries().removeIf(x -> x.getId().equals(id));

            if(removed){
                userEntryService.saveEntry(byUserName);
                journalEntryRepo.deleteById(id);
            }
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry: " + e.getMessage());
        }
        return removed;
    }

    public List<JournalEntry> findByUserName(String userName){
        return List.of();
    }

//    public JournalEntry getEntryById(String id) {
//        return journalEntryRepo.findById(id).orElse(null);
//    }
//
//    public void deleteEntry(String id) {
//        journalEntryRepo.deleteById(id);
//    }
//
//    public JournalEntry updateEntry(String id, JournalEntry updatedEntry) {
//        if (journalEntryRepo.existsById(id)) {
//            updatedEntry.setId(id);
//            return journalEntryRepo.save(updatedEntry);
//        }
//        return null;
}
