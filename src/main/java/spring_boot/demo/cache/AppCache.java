package spring_boot.demo.cache;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring_boot.demo.entry.ConfigJournalEntity;
import spring_boot.demo.repository.ConfigJournalAppRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }


    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> APP_CACHE;

    @PostConstruct
    public void init(){
        APP_CACHE = new HashMap<>();
        List<ConfigJournalEntity> all = configJournalAppRepository.findAll();

        for(ConfigJournalEntity configJournalEntity : all){
            APP_CACHE.put(configJournalEntity.getKey(), configJournalEntity.getValue());
        }
    }
}
