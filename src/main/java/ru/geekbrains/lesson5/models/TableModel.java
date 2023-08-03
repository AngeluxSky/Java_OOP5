package ru.geekbrains.lesson5.models;
import ru.geekbrains.lesson5.presenters.Model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class TableModel implements Model {

    private Collection<Table> tables;
    public Collection<Table> loadTables() {
        if (tables == null){
            tables = new ArrayList<>();

            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
        }

        return tables;

    }

    public int reservationTable(Date reservationDate, int tableNo, String name){
        for (Table table: loadTables()) {
            if (table.getNo() == tableNo){
                Reservation reservation = new Reservation(reservationDate, name);
                table.getReservations().add(reservation);
                return  reservation.getId();
            }
        }
        throw new RuntimeException("Некорректный номер столика.");
    }
    public int changeReservationTable(int oldReservationId, Date newReservationDate, int newTableNo, String newName) {
        for (Table table : loadTables()) {
            for (Reservation reservation : table.getReservations()) {
                if (reservation.getId() == oldReservationId) {
                    table.getReservations().remove(reservation);
                    boolean isTableAvailable = true;
                    for (Table existingTable : loadTables()) {
                        for (Reservation existingReservation : existingTable.getReservations()) {
                            if (existingReservation.getDate().equals(newReservationDate) && existingTable.getNo() == newTableNo) {
                                isTableAvailable = false;
                                break;
                            }
                        }
                    }
                    if (isTableAvailable) {
                        Reservation newReservation = new Reservation(newReservationDate, newName);
                        table.getReservations().add(newReservation);
                        return newReservation.getId();
                    }
                    else {
                        table.getReservations().add(reservation);
                        throw new RuntimeException("Выбранный столик на указанное время уже занят.");
                    }
                }
            }
        }
        throw new RuntimeException("Бронь с указанным номером не найдена.");
    }
}
