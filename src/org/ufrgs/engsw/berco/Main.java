package org.ufrgs.engsw.berco;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.ufrgs.engsw.berco.controller.EventCRUD;
import org.ufrgs.engsw.berco.controller.Exibicao;
import org.ufrgs.engsw.berco.equipment.*;
import org.ufrgs.engsw.berco.event.Dispatcher;
import org.ufrgs.engsw.berco.event.Queue;
import org.ufrgs.engsw.berco.handler.*;
import org.ufrgs.engsw.berco.ui.UI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;

public class Main extends Application implements  EventHandler<ActionEvent>{

    Button babyStatusBtn;
    Button equipmentStatusBtn;
    Button wakeUpBabyBtn;

    Button createEventBtn;
    Button deleteEventBtn;
    Button editEventBtn;
    Button pauseEventBtn;
    Button continueEventBtn;

    Button showComponentsBtn;

    Aquecedor aquecedor;
    Camera camera;
    Som som;
    Mobile mobile;
    Luz luz;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public UI stuffInitialize() {
        // Queue criada (comunicacao entre os componentes)
        Queue queue = new Queue();
        // Handlers com seus hardwares
        aquecedor = new Aquecedor();
        camera = new Camera();
        som = new Som();
        mobile = new Mobile();
        luz = new Luz();

        AquecedorHandler aquecedorHandler = new AquecedorHandler(aquecedor, queue);
        CameraHandler cameraHandler = new CameraHandler(camera, queue);
        MobileHandler mobileHandler = new MobileHandler(mobile, queue);
        SomHandler somHandler = new SomHandler(som, queue);
        LuzHandler luzHandler = new LuzHandler(luz, queue);

        // Dispatcher da queue
        Dispatcher dispatcher = new Dispatcher(queue, Collections.singletonList(aquecedorHandler),
                Collections.singletonList(cameraHandler), Collections.singletonList(luzHandler),
                Collections.singletonList(mobileHandler), Collections.singletonList(somHandler)); //POP queue events and, based on this event type(on, off...) it dispatch/emit the the assigned handler
        new Thread(dispatcher::run).start(); // Thread para rodar o dispatcher
        // EventCrud e Exibicao
        EventCRUD eventCrud = new EventCRUD(queue);
        Exibicao exibicao = new Exibicao(aquecedor, camera, luz, mobile, som);
        // UI
        UI ui = new UI(eventCrud, exibicao);
        return ui;
    }

    public void aquecedorWindow() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(10);

        Label status = new Label("Status:");
        Label statusValue = new Label(aquecedor.getEquipmentStatus().toString());
        Platform.setImplicitExit(false);
        aquecedor.setStatusCallback(newStatus -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    statusValue.setText(newStatus);
                }
            });
            return 0;
        });

        Label temperature = new Label("Temperatura:");
        Label temperatureValue = new Label(aquecedor.getTemperature().toString());
        aquecedor.setTemperatureCallback(newTemperature -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    temperatureValue.setText(newTemperature);
                }
            });
            return 0;
        });

        GridPane.setConstraints(status, 0, 0);
        GridPane.setConstraints(statusValue, 1 , 0);
        GridPane.setConstraints(temperature, 0, 1);
        GridPane.setConstraints(temperatureValue, 1, 1);

        grid.getChildren().addAll(status, statusValue, temperature, temperatureValue);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(grid);

        Scene secondScene = new Scene(secondaryLayout, 200, 100);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Aquecedor");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(100);
        newWindow.setY(100);

        newWindow.show();
    }

    public void somWindow() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(10);

        Label status = new Label("Status:");
        Label statusValue = new Label(som.getEquipmentStatus().toString());
        som.setStatusCallback(newStatus -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    statusValue.setText(newStatus);
                }
            });
            return 0;
        });
        Label song = new Label("Musica:");
        Label songValue = new Label(som.getCurrentSong().toString());
        som.setCurrentSongCallback(newSong -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    songValue.setText(newSong);
                }
            });
            return 0;
        });
        Label musicVolume = new Label("Volume:");
        Label musicVolumeValue = new Label(som.getMusicVolume().toString());
        som.setMusicVolumeCallback(newMusicVolume -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    musicVolumeValue.setText(newMusicVolume);
                }
            });
            return 0;
        });
        GridPane.setConstraints(status, 0, 0);
        GridPane.setConstraints(statusValue, 1 , 0);
        GridPane.setConstraints(song, 0, 1);
        GridPane.setConstraints(songValue, 1, 1);
        GridPane.setConstraints(musicVolume, 0, 2);
        GridPane.setConstraints(musicVolumeValue, 1, 2);

        grid.getChildren().addAll(status, statusValue, song, songValue, musicVolume, musicVolumeValue);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(grid);

        Scene secondScene = new Scene(secondaryLayout, 200, 100);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Som");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(100);
        newWindow.setY(250);

        newWindow.show();
    }

    public void luzWindow() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(10);

        Label status = new Label("Status:");
        Label statusValue = new Label(luz.getEquipmentStatus().toString());
        luz.setEquipmentStatusCallback(newStatus -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    statusValue.setText(newStatus);
                }
            });
            return 0;
        });

        GridPane.setConstraints(status, 0, 0);
        GridPane.setConstraints(statusValue, 1 , 0);

        grid.getChildren().addAll(status, statusValue);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(grid);

        Scene secondScene = new Scene(secondaryLayout, 200, 50);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Luz");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(100);
        newWindow.setY(400);

        newWindow.show();
    }

    public void cameraWindow() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(10);

        Label status = new Label("Status:");
        Label statusValue = new Label(camera.getEquipmentStatus().toString());
        camera.setEquipmentStatusCallback(newStatus -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    statusValue.setText(newStatus);
                }
            });
            return 0;
        });

        Label recording = new Label("Gravando:");
        Label recordingValue = new Label(camera.getRecording().toString());
        camera.setRecordingCallback(newRecording -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    recordingValue.setText(newRecording);
                }
            });
            return 0;
        });

        Label babyStatus = new Label("Status do Bebe:");
        Label babyStatusValue= new Label(camera.getBabyStatus().toString());
        camera.setBabyStatusCallback(newBabyStatus-> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    babyStatusValue.setText(newBabyStatus);
                }
            });
            return 0;
        });

        GridPane.setConstraints(status, 0, 0);
        GridPane.setConstraints(statusValue, 1 , 0);
        GridPane.setConstraints(recording, 0, 1);
        GridPane.setConstraints(recordingValue, 1, 1);
        GridPane.setConstraints(babyStatus, 0, 2);
        GridPane.setConstraints(babyStatusValue, 1, 3);

        grid.getChildren().addAll(status, statusValue, recording, recordingValue, babyStatus, babyStatusValue);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(grid);

        Scene secondScene = new Scene(secondaryLayout, 200, 100);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Camera");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(300);
        newWindow.setY(100);

        newWindow.show();
    }

    public void mobileWindow() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(10);

        Label status = new Label("Status:");
        Label statusValue = new Label(mobile.getEquipmentStatus().toString());
        mobile.setEquipmentCallback(newStatus-> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    statusValue.setText(newStatus);
                }
            });
            return 0;
        });

        Label mobileSpeed = new Label("Velocidade:");
        Label mobileSpeedValue = new Label(mobile.getSpeed().toString());
        mobile.setMobileSpeedCallback(newSpeed-> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    mobileSpeedValue.setText(newSpeed);
                }
            });
            return 0;
        });

        GridPane.setConstraints(status, 0, 0);
        GridPane.setConstraints(statusValue, 1 , 0);
        GridPane.setConstraints(mobileSpeed, 0, 1);
        GridPane.setConstraints(mobileSpeedValue, 1, 1);

        grid.getChildren().addAll(status, statusValue, mobileSpeed, mobileSpeedValue);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(grid);

        Scene secondScene = new Scene(secondaryLayout, 200, 100);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Mobile");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(300);
        newWindow.setY(250);

        newWindow.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        UI ui = stuffInitialize();
        babyStatusBtn = new Button();
        babyStatusBtn.setText("Status do Bebe");
        babyStatusBtn.setLayoutX(30);
        babyStatusBtn.setLayoutY(10);
        Label statusBabyLabel = new Label("");
        statusBabyLabel.setLayoutX(200);
        statusBabyLabel.setLayoutY(13);

        babyStatusBtn.setOnAction(e -> {
            statusBabyLabel.setText(ui.execute(1));
        });


        equipmentStatusBtn = new Button();
        equipmentStatusBtn.setText("Status dos Equipamentos");
        equipmentStatusBtn.setLayoutX(30);
        equipmentStatusBtn.setLayoutY(50);
        equipmentStatusBtn.setOnAction(e -> {
            String response = ui.execute(2);
            Label label = new Label(response);
            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(label);

            Scene secondScene = new Scene(secondaryLayout, 600, 100);

            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Status dos Equipamentos");
            newWindow.setScene(secondScene);

            // Set position of second window, related to primary window.
            newWindow.setX(100);
            newWindow.setY(250);

            newWindow.show();
        });

        wakeUpBabyBtn = new Button();
        wakeUpBabyBtn.setText("Acordar o Bebe");
        wakeUpBabyBtn.setLayoutX(30);
        wakeUpBabyBtn.setLayoutY(90);
        wakeUpBabyBtn.setOnAction(e -> {
            ui.execute(3);
        });

        createEventBtn = new Button();
        createEventBtn.setText("Criar Eventos");
        createEventBtn.setOnAction(this);
        createEventBtn.setLayoutX(30);
        createEventBtn.setLayoutY(130);

        deleteEventBtn = new Button();
        deleteEventBtn.setText("Deletar Eventos");
        deleteEventBtn.setOnAction(this);
        deleteEventBtn.setLayoutX(30);
        deleteEventBtn.setLayoutY(170);

        editEventBtn = new Button();
        editEventBtn.setText("Editar Eventos");
        editEventBtn.setOnAction(this);
        editEventBtn.setLayoutX(30);
        editEventBtn.setLayoutY(210);

        pauseEventBtn = new Button();
        pauseEventBtn.setText("Pausar Evento");
        pauseEventBtn.setOnAction(this);
        pauseEventBtn.setLayoutX(30);
        pauseEventBtn.setLayoutY(250);

        continueEventBtn = new Button();
        continueEventBtn.setText("Continuar Evento");
        continueEventBtn.setOnAction(this);
        continueEventBtn.setLayoutX(30);
        continueEventBtn.setLayoutY(290);

        showComponentsBtn = new Button();
        showComponentsBtn.setText("Mostrar Componentes");
        showComponentsBtn.setLayoutX(30);
        showComponentsBtn.setLayoutY(350);
        showComponentsBtn.setOnAction(e -> {
            this.aquecedorWindow();
            this.somWindow();
            this.luzWindow();
            this.cameraWindow();
            this.mobileWindow();
        });

        primaryStage.setTitle("Berco Inteligente");

        Pane layout = new Pane();
        layout.getChildren().addAll(babyStatusBtn, equipmentStatusBtn, wakeUpBabyBtn, createEventBtn, deleteEventBtn,
                editEventBtn, pauseEventBtn, continueEventBtn, showComponentsBtn, statusBabyLabel);


        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
    }
}
