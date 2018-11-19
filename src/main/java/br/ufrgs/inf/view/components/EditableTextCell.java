package br.ufrgs.inf.view.components;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class EditableTextCell<T> extends TableCell<T, String> {

    private TextField textField;

    public EditableTextCell() {}

    public static <T> Callback<TableColumn<T, String>, TableCell<T, String>> instance() {
        return p -> new EditableTextCell<>();
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();

            this.createTextField();
            this.setText(null);
            this.setGraphic(this.textField);

            this.textField.selectAll();
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        this.setText(getItem());
        this.setGraphic(null);
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            this.setText(null);
            this.setGraphic(null);

        } else {
            if (this.isEditing()) {
                if (this.textField != null) {
                    this.textField.setText(getString());
                }

                this.setText(null);
                this.setGraphic(this.textField);

            } else {
                this.setText(getString());
                this.setGraphic(null);
            }
        }
    }

    private void createTextField() {
        this.textField = new TextField(getString());

        this.textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);

        this.textField.focusedProperty().addListener((arg0, arg1, arg2) -> {
            if (!arg2) {
                commitEdit(textField.getText());
            }
        });
    }

    private String getString() {
        return this.getItem() == null ? "" : this.getItem();
    }
}
