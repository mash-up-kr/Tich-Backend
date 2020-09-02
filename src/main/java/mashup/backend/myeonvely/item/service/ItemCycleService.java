package mashup.backend.myeonvely.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class ItemCycleService {

    public LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
    }

    public LocalDate calculateScheduledDate(LocalDate latestDate, Integer cycle) {
        return latestDate.plusDays(cycle);
    }
}
