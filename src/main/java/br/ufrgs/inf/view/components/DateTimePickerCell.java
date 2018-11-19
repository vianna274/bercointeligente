package br.ufrgs.inf.view.components;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import tornadofx.control.DateTimePicker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimePickerCell<T> extends TableCell<T, LocalDateTime> {

    private final DateTimeFormatter formatter;
    private final DateTimePicker dateTimePicker;

    public DateTimePickerCell() {
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.dateTimePicker = new DateTimePicker();

        this.dateTimePicker.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {
                this.dateTimePicker.setValue(this.dateTimePicker.getConverter().fromString(this.dateTimePicker.getEditor().getText()));

                final LocalDateTime value = this.dateTimePicker.getDateTimeValue();

                commitEdit(value);
            }

            if (event.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });

        contentDisplayProperty().bind(Bindings.when(editingProperty())
                                .then(ContentDisplay.GRAPHIC_ONLY)
                                .otherwise(ContentDisplay.TEXT_ONLY));
    }

    public static <T> Callback<TableColumn<T, LocalDateTime>, TableCell<T, LocalDateTime>> instance() {
        return p -> new DateTimePickerCell<>();
    }

    @Override
    public void updateItem(LocalDateTime time, boolean empty) {
        super.updateItem(time, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(formatter.format(time));
            setGraphic(dateTimePicker);
        }
    }

    @Override
    public void startEdit() {
        super.startEdit();
        if (!isEmpty()) {
            this.dateTimePicker.setDateTimeValue(getItem());
        }
    }

}
