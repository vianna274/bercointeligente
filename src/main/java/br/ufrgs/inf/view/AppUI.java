package br.ufrgs.inf.view;

import br.ufrgs.inf.controller.AppController;
import br.ufrgs.inf.controller.EventManager;
import br.ufrgs.inf.data.domain.*;
import br.ufrgs.inf.data.events.*;
import br.ufrgs.inf.event.EventListener;
import br.ufrgs.inf.view.components.DateTimePickerCell;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tornadofx.control.DateTimePicker;

import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppUI implements EventListener<DefaultEvent> {

    @FXML
    private ComboBox<String> type;

    @FXML
    private Button addEvent;
    @FXML
    private Button deteleEvent;

    @FXML
    private Label eventTypeTitle;

    @FXML
    private Label babyStatus;
    @FXML
    private Label babyBottleStatus;
    @FXML
    private Label cameraStatus;

    @FXML
    private TableView<CameraEvent> tableViewCamera;
    @FXML
    private TableColumn<CameraEvent, Recording> recordingCameraCol;
    @FXML
    private TableColumn<CameraEvent, EquipmentStatus> equipmentStatusCameraCol;
    @FXML
    private TableColumn<CameraEvent, LocalDateTime> startCameraCol;
    @FXML
    private TableColumn<CameraEvent, LocalDateTime> endCameraCol;

    @FXML
    private TableView<LuzEvent> tableViewLight;
    @FXML
    private TableColumn<LuzEvent, EquipmentStatus> equipmentStatusLightCol;
    @FXML
    private TableColumn<LuzEvent, LocalDateTime> startLightCol;
    @FXML
    private TableColumn<LuzEvent, LocalDateTime> endLightCol;

    @FXML
    private TableView<SomEvent> tableViewSound;
    @FXML
    private TableColumn<SomEvent, MusicVolume> volumeSoundCol;
    @FXML
    private TableColumn<SomEvent, Song> musicSoundCol;
    @FXML
    private TableColumn<SomEvent, EquipmentStatus> equipmentStatusSoundCol;
    @FXML
    private TableColumn<SomEvent, LocalDateTime> startSoundCol;
    @FXML
    private TableColumn<SomEvent, LocalDateTime> endSoundCol;

    @FXML
    private TableView<MobileEvent> tableViewMobile;
    @FXML
    private TableColumn<MobileEvent, MobileSpeed> velocityMobileCol;
    @FXML
    private TableColumn<MobileEvent, EquipmentStatus> equipmentStatusMobileCol;
    @FXML
    private TableColumn<MobileEvent, LocalDateTime> startMobileCol;
    @FXML
    private TableColumn<MobileEvent, LocalDateTime> endMobileCol;

    @FXML
    private TableView<AquecedorEvent> tableViewHeater;
    @FXML
    private TableColumn<AquecedorEvent, Temperature> temperatureHeaterCol;
    @FXML
    private TableColumn<AquecedorEvent, EquipmentStatus> equipmentStatusHeaterCol;
    @FXML
    private TableColumn<AquecedorEvent, LocalDateTime> startHeaterCol;
    @FXML
    private TableColumn<AquecedorEvent, LocalDateTime> endHeaterCol;

    @FXML
    private Pane mobilePane;
    @FXML
    private Pane heaterPane;
    @FXML
    private Pane cameraPane;
    @FXML
    private Pane soundPane;
    @FXML
    private Pane lightPane;

    @FXML
    private ComboBox<Recording> cameraRecording;
    @FXML
    private ComboBox<EquipmentStatus> cameraEquipmentStatus;
    @FXML
    private DateTimePicker cameraStart;
    @FXML
    private DateTimePicker cameraEnd;

    @FXML
    private ComboBox<EquipmentStatus> lightEquipmentStatus;
    @FXML
    private DateTimePicker lightStart;
    @FXML
    private DateTimePicker lightEnd;

    @FXML
    private ComboBox<Temperature> heaterTemperature;
    @FXML
    private ComboBox<EquipmentStatus> heaterEquipmentStatus;
    @FXML
    private DateTimePicker heaterStart;
    @FXML
    private DateTimePicker heaterEnd;

    @FXML
    private ComboBox<Song> soundMusic;
    @FXML
    private ComboBox<MusicVolume> soundVolume;
    @FXML
    private ComboBox<EquipmentStatus> soundEquipmentStatus;
    @FXML
    private DateTimePicker soundStart;
    @FXML
    private DateTimePicker soundEnd;

    @FXML
    private ComboBox<MobileSpeed> mobileVelocity;
    @FXML
    private ComboBox<EquipmentStatus> mobileEquipmentStatus;
    @FXML
    private DateTimePicker mobileStart;
    @FXML
    private DateTimePicker mobileEnd;

    private Map<Equipment, Pane> paneViews;

    private Map<Equipment, TableView<?>> tableViews;
    private TableView<?> currentTable;

    private AquecedorEvent heaterEvent;
    private LuzEvent lightEvent;
    private MobileEvent mobileEvent;
    private SomEvent soundEvent;
    private CameraEvent cameraEvent;

    private Map<String, Object> userData;

    private final AppController appController;
    private final EventManager eventManager;

    public AppUI(final AppController appController, final EventManager eventManager) {
        this.userData = new HashMap<>();
        this.appController = appController;
        this.eventManager = eventManager;
    }

    @FXML
    public void initialize() {
        this.tableViews = new HashMap<>();
        this.paneViews = new HashMap<>();

        this.tableViews.put(Equipment.CAMERA, this.tableViewCamera);
        this.tableViews.put(Equipment.LIGHT, this.tableViewLight);
        this.tableViews.put(Equipment.SOUND, this.tableViewSound);
        this.tableViews.put(Equipment.MOBILE, this.tableViewMobile);
        this.tableViews.put(Equipment.HEATER, this.tableViewHeater);

        this.paneViews.put(Equipment.CAMERA, this.cameraPane);
        this.paneViews.put(Equipment.LIGHT, this.lightPane);
        this.paneViews.put(Equipment.SOUND, this.soundPane);
        this.paneViews.put(Equipment.MOBILE, this.mobilePane);
        this.paneViews.put(Equipment.HEATER, this.heaterPane);

        this.hideAllTables();
        this.hideAllPanes();

        this.currentTable = this.tableViewCamera;

        Optional.ofNullable(this.type).ifPresent(this::configTypeComboBox);
        Optional.ofNullable(this.tableViewHeater).ifPresent(this::configHeaterTable);
        Optional.ofNullable(this.tableViewCamera).ifPresent(this::configCameraTable);
        Optional.ofNullable(this.tableViewLight).ifPresent(this::configLightTable);
        Optional.ofNullable(this.tableViewMobile).ifPresent(this::configMobileTable);
        Optional.ofNullable(this.tableViewSound).ifPresent(this::configSoundTable);
        Optional.ofNullable(this.babyStatus).ifPresent(this::configBabyStatus);
        Optional.ofNullable(this.babyBottleStatus).ifPresent(this::configBabyBottleStatus);
        Optional.ofNullable(this.cameraStatus).ifPresent(this::configCameraStatus);
        Optional.ofNullable(this.eventTypeTitle).ifPresent(this::configEventTypeTitle);
        Optional.ofNullable(this.cameraPane).ifPresent(this::configCameraPane);
        Optional.ofNullable(this.lightPane).ifPresent(this::configLightPane);
        Optional.ofNullable(this.heaterPane).ifPresent(this::configHeaterPane);
        Optional.ofNullable(this.soundPane).ifPresent(this::configSoundPane);
        Optional.ofNullable(this.mobilePane).ifPresent(this::configMobilePane);

        if (this.userData.containsKey("type")) {
            final Equipment equipment = (Equipment) this.userData.get("type");

            final Pane pane = this.paneViews.get(equipment);

            if (pane != null) {
                pane.setVisible(true);
            }
        }
    }

    private void configMobilePane(final Pane mobilePane) {
        this.mobileEvent = MobileEvent.defaultInstance();

        this.mobileVelocity.getItems().addAll(MobileSpeed.values());
        this.mobileVelocity.getSelectionModel().selectFirst();
        this.mobileVelocity.setOnAction(igr -> this.mobileEvent.setSpeed(this.mobileVelocity.getSelectionModel().getSelectedItem()));

        this.mobileEquipmentStatus.getItems().addAll(EquipmentStatus.values());
        this.mobileEquipmentStatus.getSelectionModel().selectFirst();
        this.mobileEquipmentStatus.setOnAction(igr -> this.mobileEvent.setEquipmentStatus(this.mobileEquipmentStatus.getSelectionModel().getSelectedItem()));

        this.mobileStart.setValue(LocalDate.now());
        this.mobileStart.setOnAction(igr -> this.mobileEvent.setStart(this.mobileStart.getDateTimeValue()));
        this.mobileStart.focusedProperty().addListener((a, b, isFocused) -> {
            if (!isFocused) this.mobileEvent.setStart(this.mobileStart.getDateTimeValue());
        });

        this.mobileEnd.setValue(LocalDate.now());
        this.mobileEnd.setOnAction(igr -> this.mobileEvent.setEnd(this.mobileEnd.getDateTimeValue()));
        this.mobileEnd.focusedProperty().addListener((a, b, isFocused) -> {
            if (!isFocused) this.mobileEvent.setEnd(this.mobileEnd.getDateTimeValue());
        });
    }

    private void configSoundPane(final Pane soundPane) {
        this.soundEvent = SomEvent.defaultInstance();

        this.soundMusic.getItems().addAll(Song.values());
        this.soundMusic.getSelectionModel().selectFirst();
        this.soundMusic.setOnAction(igr -> this.soundEvent.setCurrentSong(this.soundMusic.getSelectionModel().getSelectedItem()));

        this.soundVolume.getItems().addAll(MusicVolume.values());
        this.soundVolume.getSelectionModel().selectFirst();
        this.soundVolume.setOnAction(igr -> this.soundEvent.setMusicVolume(this.soundVolume.getSelectionModel().getSelectedItem()));

        this.soundEquipmentStatus.getItems().addAll(EquipmentStatus.values());
        this.soundEquipmentStatus.getSelectionModel().selectFirst();
        this.soundEquipmentStatus.setOnAction(igr -> this.soundEvent.setEquipmentStatus(this.soundEquipmentStatus.getSelectionModel().getSelectedItem()));

        this.soundStart.setValue(LocalDate.now());
        this.soundStart.setOnAction(igr -> this.soundEvent.setStart(this.soundStart.getDateTimeValue()));
        this.soundStart.focusedProperty().addListener((a, b, isFocused) -> {
            if (!isFocused) this.soundEvent.setStart(this.soundEnd.getDateTimeValue());
        });

        this.soundEnd.setValue(LocalDate.now());
        this.soundEnd.setOnAction(igr -> this.soundEvent.setEnd(this.soundEnd.getDateTimeValue()));
        this.soundEnd.focusedProperty().addListener((a, b, isFocused) -> {
            if (!isFocused) this.soundEvent.setEnd(this.soundEnd.getDateTimeValue());
        });
    }

    private void configHeaterPane(final Pane heaterPane) {
        this.heaterEvent = AquecedorEvent.defaultInstance();

        this.heaterTemperature.getItems().addAll(Temperature.values());
        this.heaterTemperature.getSelectionModel().selectFirst();
        this.heaterTemperature.setOnAction(igr -> this.heaterEvent.setTemperature(this.heaterTemperature.getSelectionModel().getSelectedItem()));

        this.heaterEquipmentStatus.getItems().addAll(EquipmentStatus.values());
        this.heaterEquipmentStatus.getSelectionModel().selectFirst();
        this.heaterEquipmentStatus.setOnAction(igr -> this.heaterEvent.setEquipmentStatus(this.heaterEquipmentStatus.getSelectionModel().getSelectedItem()));

        this.heaterStart.setValue(LocalDate.now());
        this.heaterStart.setOnAction(igr -> this.heaterEvent.setStart(this.heaterStart.getDateTimeValue()));
        this.heaterStart.focusedProperty().addListener((a, b, isFocused) -> {
            if (!isFocused) this.heaterEvent.setStart(this.heaterStart.getDateTimeValue());
        });

        this.heaterEnd.setValue(LocalDate.now());
        this.heaterEnd.setOnAction(igr -> this.heaterEvent.setEnd(this.heaterEnd.getDateTimeValue()));
        this.heaterEnd.focusedProperty().addListener((a, b, isFocused) -> {
            if (!isFocused) this.heaterEvent.setEnd(this.heaterEnd.getDateTimeValue());
        });
    }

    private void configLightPane(final Pane lightPane) {
        this.lightEvent = LuzEvent.defaultInstance();

        this.lightEquipmentStatus.getItems().addAll(EquipmentStatus.values());
        this.lightEquipmentStatus.getSelectionModel().selectFirst();
        this.lightEquipmentStatus.setOnAction(igr -> this.lightEvent.setEquipmentStatus(this.lightEquipmentStatus.getSelectionModel().getSelectedItem()));

        this.lightStart.setValue(LocalDate.now());
        this.lightStart.setOnAction(igr -> this.lightEvent.setStart(this.lightStart.getDateTimeValue()));
        this.lightStart.focusedProperty().addListener((a, b, isFocused) -> {
            if (!isFocused) this.lightEvent.setStart(this.lightStart.getDateTimeValue());
        });

        this.lightEnd.setValue(LocalDate.now());
        this.lightEnd.setOnAction(igr -> this.lightEvent.setEnd(this.lightEnd.getDateTimeValue()));
        this.lightEnd.focusedProperty().addListener((a, b, isFocused) -> {
            if (!isFocused) this.lightEvent.setEnd(this.lightEnd.getDateTimeValue());
        });
    }

    private void configCameraPane(final Pane cameraPane) {
        this.cameraEvent = CameraEvent.defaultInstance();

        this.cameraRecording.getItems().addAll(Recording.values());
        this.cameraRecording.getSelectionModel().selectFirst();
        this.cameraRecording.setOnAction(igr -> this.cameraEvent.setRecording(this.cameraRecording.getSelectionModel().getSelectedItem()));

        this.cameraEquipmentStatus.getItems().addAll(EquipmentStatus.values());
        this.cameraEquipmentStatus.getSelectionModel().selectFirst();
        this.cameraEquipmentStatus.setOnAction(igr -> this.cameraEvent.setEquipmentStatus(this.cameraEquipmentStatus.getSelectionModel().getSelectedItem()));

        this.cameraStart.setValue(LocalDate.now());
        this.cameraStart.setOnAction(igr -> this.cameraEvent.setStart(this.cameraStart.getDateTimeValue()));
        this.cameraStart.focusedProperty().addListener((a, b, isFocused) -> {
            if (!isFocused) this.cameraEvent.setStart(this.cameraStart.getDateTimeValue());
        });

        this.cameraEnd.setValue(LocalDate.now());
        this.cameraEnd.setOnAction(igr -> this.cameraEvent.setEnd(this.cameraEnd.getDateTimeValue()));
        this.cameraEnd.focusedProperty().addListener((a, b, isFocused) -> {
            if (!isFocused) this.cameraEvent.setEnd(this.cameraEnd.getDateTimeValue());
        });

    }

    private void configEventTypeTitle(final Label eventTypeTitle) {
        if (this.userData.containsKey("type")) {
            final Equipment equipment = (Equipment) this.userData.get("type");
            eventTypeTitle.setText(equipment.getLabel());
        }
    }

    private void configBabyStatus(final Label babyStatus) {
        babyStatus.setText(this.appController.getBabyStatus().toString());
    }

    private void configBabyBottleStatus(final Label babyBottleStatus) {
        this.appController.addBabyBottleListener(b -> this.babyBottleStatus.setText(b.getBabyBottleStatus().toString()));
        this.babyBottleStatus.setText(this.appController.getBabyBottle().getBabyBottleStatus().toString());
    }

    private void configCameraStatus(final Label cameraStatus) {
        this.configCameraStatus(cameraStatus, Optional.empty());
    }

    private void configCameraStatus(final Label cameraStatus, final Optional<Recording> recording) {
        recording.ifPresent(r -> cameraStatus.setText(r.getLabel()));
    }

    private void configSoundTable(final TableView<SomEvent> tableViewSound) {
        this.startSoundCol.setCellFactory(DateTimePickerCell.instance());
        this.startSoundCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getStart()));

        this.endSoundCol.setCellFactory(DateTimePickerCell.instance());
        this.endSoundCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEnd()));

        this.volumeSoundCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getMusicVolume()));
        this.volumeSoundCol.setCellFactory(ComboBoxTableCell.forTableColumn(MusicVolume.values()));
        this.volumeSoundCol.setOnEditCommit(t -> t.getRowValue().setMusicVolume(t.getNewValue()));

        this.musicSoundCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getCurrentSong()));
        this.musicSoundCol.setCellFactory(ComboBoxTableCell.forTableColumn(Song.values()));
        this.musicSoundCol.setOnEditCommit(t -> t.getRowValue().setCurrentSong(t.getNewValue()));

        this.equipmentStatusSoundCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEquipmentStatus()));
        this.equipmentStatusSoundCol.setCellFactory(ComboBoxTableCell.forTableColumn(EquipmentStatus.values()));
        this.equipmentStatusSoundCol.setOnEditCommit(t -> t.getRowValue().setEquipmentStatus(t.getNewValue()));

        tableViewSound.setEditable(true);
        tableViewSound.setItems(FXCollections.observableList(this.eventManager.listEventByClass(SomEvent.class)));
    }

    private void configMobileTable(final TableView<MobileEvent> tableViewMobile) {
        this.startMobileCol.setCellFactory(DateTimePickerCell.instance());
        this.startMobileCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getStart()));

        this.endMobileCol.setCellFactory(DateTimePickerCell.instance());
        this.endMobileCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEnd()));

        this.velocityMobileCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getSpeed()));
        this.velocityMobileCol.setCellFactory(ComboBoxTableCell.forTableColumn(MobileSpeed.values()));
        this.velocityMobileCol.setOnEditCommit(t -> t.getRowValue().setSpeed(t.getNewValue()));

        this.equipmentStatusMobileCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEquipmentStatus()));
        this.equipmentStatusMobileCol.setCellFactory(ComboBoxTableCell.forTableColumn(EquipmentStatus.values()));
        this.equipmentStatusMobileCol.setOnEditCommit(t -> t.getRowValue().setEquipmentStatus(t.getNewValue()));

        tableViewMobile.setEditable(true);
        tableViewMobile.setItems(FXCollections.observableList(this.eventManager.listEventByClass(MobileEvent.class)));
    }

    private void configCameraTable(final TableView<CameraEvent> tableViewCamera) {
        this.startCameraCol.setCellFactory(DateTimePickerCell.instance());
        this.startCameraCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getStart()));

        this.endCameraCol.setCellFactory(DateTimePickerCell.instance());
        this.endCameraCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEnd()));

        this.recordingCameraCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getRecording()));
        this.recordingCameraCol.setCellFactory(ComboBoxTableCell.forTableColumn(Recording.values()));
        this.recordingCameraCol.setOnEditCommit(t -> t.getRowValue().setRecording(t.getNewValue()));

        this.equipmentStatusCameraCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEquipmentStatus()));
        this.equipmentStatusCameraCol.setCellFactory(ComboBoxTableCell.forTableColumn(EquipmentStatus.values()));
        this.equipmentStatusCameraCol.setOnEditCommit(t -> t.getRowValue().setEquipmentStatus(t.getNewValue()));

        tableViewCamera.setEditable(true);
        tableViewCamera.setItems(FXCollections.observableList(this.eventManager.listEventByClass(CameraEvent.class)));
        tableViewCamera.setVisible(true);
    }

    private void configHeaterTable(final TableView<AquecedorEvent> tableViewHeater) {
        this.startHeaterCol.setCellFactory(DateTimePickerCell.instance());
        this.startHeaterCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getStart()));

        this.endHeaterCol.setCellFactory(DateTimePickerCell.instance());
        this.endHeaterCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEnd()));

        this.temperatureHeaterCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getTemperature()));
        this.temperatureHeaterCol.setCellFactory(ComboBoxTableCell.forTableColumn(Temperature.values()));
        this.temperatureHeaterCol.setOnEditCommit(t -> t.getRowValue().setTemperature(t.getNewValue()));

        this.equipmentStatusHeaterCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEquipmentStatus()));
        this.equipmentStatusHeaterCol.setCellFactory(ComboBoxTableCell.forTableColumn(EquipmentStatus.values()));
        this.equipmentStatusHeaterCol.setOnEditCommit(t -> t.getRowValue().setEquipmentStatus(t.getNewValue()));

        tableViewHeater.setEditable(true);
        tableViewHeater.setItems(FXCollections.observableList(this.eventManager.listEventByClass(AquecedorEvent.class)));
    }

    private void configLightTable(final TableView<LuzEvent> tableViewLight) {
        this.startLightCol.setCellFactory(DateTimePickerCell.instance());
        this.startLightCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getStart()));

        this.endLightCol.setCellFactory(DateTimePickerCell.instance());
        this.endLightCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEnd()));

        this.equipmentStatusLightCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEquipmentStatus()));
        this.equipmentStatusLightCol.setCellFactory(ComboBoxTableCell.forTableColumn(EquipmentStatus.values()));
        this.equipmentStatusLightCol.setOnEditCommit(t -> t.getRowValue().setEquipmentStatus(t.getNewValue()));

        tableViewLight.setEditable(true);
        tableViewLight.setItems(FXCollections.observableList(this.eventManager.listEventByClass(LuzEvent.class)));
    }

    private void configTypeComboBox(final ComboBox<String> type) {
        type.getItems().addAll(Stream.of(Equipment.values()).map(Equipment::getLabel).collect(Collectors.toList()));
        type.getSelectionModel().selectFirst();
    }

    private Stage loadConfigStage() throws Exception {
        return loadStage("./src/main/resources/app-config.fxml", "Berço Inteligente - Configuração");
    }

    private Stage loadAddStage(final Map<String, Object> userData) throws Exception {
        return loadStage("./src/main/resources/app-add.fxml", "Berço Inteligente - Adicionar", Optional.ofNullable(userData));
    }

    private Stage loadAppStage() throws Exception {
        return loadStage("./src/main/resources/app-main.fxml", "Berço Inteligente");
    }

    private Stage loadStage(final String fxmlPath, final String title) throws Exception {
        return loadStage(fxmlPath, title, Optional.empty());
    }

    private Stage loadStage(final String fxmlPath, final String title, final Optional<Map<String, Object>> userData) throws Exception {
        final URL fxml = Paths.get(fxmlPath)
                .toUri()
                .toURL();

        final FXMLLoader loader = new FXMLLoader(fxml);

        final AppUI controller = new AppUI(this.appController, this.eventManager);

        userData.ifPresent(this.userData::putAll);

        controller.setUserData(this.userData);

        loader.setController(controller);

        final Parent root = loader.load();

        final Stage stage = new Stage();

        stage.setTitle(title);
        stage.setScene(new Scene(root));

        return stage;
    }

    private void hideAllPanes() {
        this.paneViews.values()
                .stream()
                .filter(Objects::nonNull)
                .forEach(t -> t.setVisible(false));
    }

    private void hideAllTables() {
        this.tableViews.values()
                .stream()
                .filter(Objects::nonNull)
                .forEach(t -> t.setVisible(false));
    }

    public void onClickExit() {
        System.exit(0);
    }

    public void onClickAdd(final ActionEvent event) throws Exception {
        final Map<String, Object> userData = new HashMap<>();

        final Equipment equipment = Equipment.of(this.type.getValue());

        userData.put("type", equipment);

        this.showStage(this.loadAddStage(userData), event);
    }

    public void onTypeChanged(final ActionEvent event) {
        final String selected = type.getSelectionModel().getSelectedItem();

        final Equipment equipment = Equipment.of(selected);

        this.hideAllTables();

        final TableView<?> tableView = this.tableViews.get(equipment);

        this.currentTable = tableView;

        tableView.setVisible(true);
    }

    public void onClickConfig(final ActionEvent event) throws Exception {
        this.showStage(this.loadConfigStage(), event);
    }

    public void onClickBackApp(final ActionEvent event) throws Exception {
        this.showStage(this.loadAppStage(), event);
    }

    public void onClickDeleteEvent(final ActionEvent event) {
        final Object selected = this.currentTable.getSelectionModel().getSelectedItem();
        this.currentTable.getItems().remove(selected);

        final Event enqueued = (Event) selected;

//        this.appController.deleteScheduledEvent(enqueued.getId());
        this.eventManager.remove(enqueued);
    }

    public void onClickSaveEvent(final ActionEvent event) throws Exception {
        final Equipment equipment = (Equipment) this.userData.get("type");

        if (equipment == Equipment.CAMERA) {
            this.appController.createCameraEvent(
                    this.cameraEvent.getId(),
                    this.cameraEvent.getStart(),
                    this.cameraEvent.getEnd(),
                    this.cameraEvent.getRecording(),
                    this.cameraEvent.getEquipmentStatus()
            );

            this.eventManager.add(this.cameraEvent);

        } else if (equipment == Equipment.HEATER) {
            this.appController.createAquecedorEvent(
                    this.heaterEvent.getId(),
                    this.heaterEvent.getStart(),
                    this.heaterEvent.getEnd(),
                    this.heaterEvent.getTemperature(),
                    this.heaterEvent.getEquipmentStatus()
            );

            this.eventManager.add(this.heaterEvent);

        } else if (equipment == Equipment.LIGHT) {
            this.appController.createLuzEvent(
                    this.lightEvent.getId(),
                    this.lightEvent.getStart(),
                    this.lightEvent.getEnd(),
                    this.lightEvent.getEquipmentStatus()
            );

            this.eventManager.add(this.lightEvent);

        } else if (equipment == Equipment.MOBILE) {
            this.appController.createMobileEvent(
                    this.mobileEvent.getId(),
                    this.mobileEvent.getStart(),
                    this.mobileEvent.getEnd(),
                    this.mobileEvent.getSpeed(),
                    this.mobileEvent.getEquipmentStatus()
            );

            this.eventManager.add(this.mobileEvent);

        } else if (equipment == Equipment.SOUND) {
            this.appController.createSomEvent(
                    this.soundEvent.getId(),
                    this.soundEvent.getStart(),
                    this.soundEvent.getEnd(),
                    this.soundEvent.getMusicVolume(),
                    this.soundEvent.getCurrentSong(),
                    this.soundEvent.getEquipmentStatus()
            );

            this.eventManager.add(this.soundEvent);
        }

        this.showStage(this.loadConfigStage(), event);
    }

    public void onClickToggleBaby(final ActionEvent event) {
        this.appController.toggleBabyStatus();
        this.configBabyStatus(this.babyStatus);
    }

    private void showStage(final Stage stage, final ActionEvent event) {
        this.closeCurrentScreen(event);
        stage.show();
    }

    private Scene getCurrentScene(final ActionEvent event) {
        return ((Node) (event.getSource())).getScene();
    }

    private void closeCurrentScreen(final ActionEvent event) {
        this.getCurrentScene(event).getWindow().hide();
    }

    public Map<String, Object> getUserData() {
        return userData;
    }

    public void setUserData(Map<String, Object> userData) {
        this.userData = userData;
    }

    @Override
    public void onEvent(final DefaultEvent event) {
        if (event.getOperation() == Operation.STATUS_CHANGED) {

            if (event instanceof CameraEvent) {
                final CameraEvent e = (CameraEvent) event;

                final CameraEvent s = (CameraEvent) this.eventManager.listEventByClass(CameraEvent.class).get(0);

                e.setId(s.getId());

                final LocalDateTime now = LocalDateTime.now();

                e.setStart(now);
                e.setEnd(now);

                this.eventManager.replaceById(e);

                //this.configCameraStatus(this.cameraStatus, Optional.ofNullable(e.getRecording()));
            }
        }
    }
}
