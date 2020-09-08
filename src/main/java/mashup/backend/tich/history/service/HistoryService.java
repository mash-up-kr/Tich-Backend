package mashup.backend.tich.history.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.tich.exception.HistoryDoseNotExistException;
import mashup.backend.tich.history.domain.History;
import mashup.backend.tich.history.domain.HistoryRepository;
import mashup.backend.tich.item.domain.Item;
import mashup.backend.tich.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    @Transactional
    public List<History> createHistory(User user, Item item, LocalDate startDate) {
        List<History> histories = new ArrayList<>();

        History history = History.builder()
                .user(user)
                .item(item)
                .replacementDate(startDate)
                .build();

        histories.add(historyRepository.save(history));

        return histories;
    }

    @Transactional
    public List<History> updateHistory(Long userId, Long itemId, LocalDate startDate, LocalDate latestDate) {
        List<History> histories = historyRepository.findAllByUserIdAndItemId(userId, itemId);

        if (!histories.isEmpty()) {
            sortList(histories);
            replaceDate(histories.get(0), startDate);
            if (!compareDate(startDate, latestDate)) {
                replaceDate(histories.get(histories.size() - 1), latestDate);
            }
        }

        return histories;
    }

    @Transactional
    public void deleteHistory(Long userId, Long itemId) {
        if (!historyRepository.existsAllByItemId(itemId)) throw new HistoryDoseNotExistException();
        historyRepository.deleteAllByUserIdAndItemId(userId, itemId);
    }

    private void sortList(List<History> histories) {
        histories.sort(Comparator.comparing(History::getReplacementDate));
    }

    private boolean compareDate(LocalDate date1, LocalDate date2) {
        return date1.equals(date2);
    }

    private History replaceDate(History history, LocalDate date) {
        if (!compareDate(history.getReplacementDate(), date)) {
            history.updateDate(date);
        }
        return history;
    }
}
