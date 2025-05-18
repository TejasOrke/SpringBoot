package spring_boot.demo.entry;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config_journal_app")
@Data
public class ConfigJournalEntity {

    private String key;
    private String value;


}
