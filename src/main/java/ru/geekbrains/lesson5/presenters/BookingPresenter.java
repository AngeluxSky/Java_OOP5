package ru.geekbrains.lesson5.presenters;
import ru.geekbrains.lesson5.models.Table;
import java.util.Collection;
import java.util.Date;

public class BookingPresenter implements ViewObserver {

    private final View view;
    private final Model model;

    public BookingPresenter(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setObserver(this);
    }

    private Collection<Table> loadTables(){
        return model.loadTables();
    }

    public void showTables(){
        view.showTables(loadTables());
    }

    private void showReservationTableResult(int reservationId){
        view.showReservationTableResult(reservationId);
    }

    private void showChangeReservationTableResult(int reservationId){
        view.showChangeReservationTableResult(reservationId);
    }
    @Override
    public void onReservationTable(Date reservationDate, int tableNo, String name) {
       int reservationId = model.reservationTable(reservationDate, tableNo, name);
        showReservationTableResult(reservationId);
    }
    @Override
    public void onChangeReservationTable(int oldReservationId, Date newReservationDate, int newTableNo, String newName) {
        int reservationId = model.changeReservationTable(oldReservationId, newReservationDate, newTableNo, newName);
        showChangeReservationTableResult(reservationId);
    }
}
