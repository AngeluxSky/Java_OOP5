package ru.geekbrains.lesson5.views;
import ru.geekbrains.lesson5.models.Table;
import ru.geekbrains.lesson5.presenters.View;
import ru.geekbrains.lesson5.presenters.ViewObserver;
import java.util.Collection;
import java.util.Date;

public class BookingView implements View {
    private ViewObserver observer;
    public void showTables(Collection<Table> tables){
        for (Table table: tables) {
            System.out.println(table);
        }
    }

    public void showReservationTableResult(int reservationId){
        System.out.printf("Столик успешно забронирован. Номер вашей брони: #%d\n", reservationId);
    }
    public void showChangeReservationTableResult(int reservationId){
        System.out.printf("Бронь успешно изменена. Новый номер брони: #%d\n", reservationId);
    }
    @Override
    public void setObserver(ViewObserver observer) {
        this.observer = observer;
    }
    public void reservationTable(Date reservationDate, int tableNo, String name){
        observer.onReservationTable(reservationDate, tableNo, name);
    }
    public void changeReservationTable(int oldReservationId, Date newReservationDate, int newTableNo, String newName){
        observer.onChangeReservationTable(oldReservationId, newReservationDate, newTableNo, newName);
    }
}
