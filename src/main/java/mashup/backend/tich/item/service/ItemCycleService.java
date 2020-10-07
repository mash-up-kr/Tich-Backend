package mashup.backend.tich.item.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.tich.device.domain.Device;
import mashup.backend.tich.user.domain.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemCycleService {

    public LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
    }

    public LocalDate calculateScheduledDate(LocalDate latestDate, Integer cycle) {
        return latestDate.plusDays(cycle);
    }

    public List<String> getTokens(User user) {
        List<String> tokens = new ArrayList<String>();

        List<Device> devices = user.getDevices();
        for(Device d : devices) {
            tokens.add(d.getToken());
        }

        return tokens;
    }
}
