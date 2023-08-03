package ru.geekbrains.lesson5.presenters;
import ru.geekbrains.lesson5.models.Table;
import java.util.Collection;
import java.util.Date;

public interface View {
    void showTables(Collection<Table> tables);

    void setObserver(ViewObserver observer);

    void reservationTable(Date reservationDate, int tableNo, String name);

    void changeReservationTable(int oldReservationId, Date newReservationDate, int newTableNo, String newName);

    void showReservationTableResult(int reservationId);

    void showChangeReservationTableResult(int reservationId);
}
