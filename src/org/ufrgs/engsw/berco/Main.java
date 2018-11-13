package org.ufrgs.engsw.berco;

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

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public UI stuffInitialize() {
        // Queue criada (comunicacao entre os componentes)
        Queue queue = new Queue();
        // Handlers com seus hardwares
        Aquecedor aquecedor = new Aquecedor();
        Camera camera = new Camera();
        Som som = new Som();
        Mobile mobile = new Mobile();
        Luz luz = new Luz();

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

    @Override
    public void start(Stage primaryStage) throws Exception {
        UI ui = stuffInitialize();
        babyStatusBtn = new Button();
        babyStatusBtn.setText("Status do Bebe");
        babyStatusBtn.setLayoutX(100);
        babyStatusBtn.setLayoutY(10);
        babyStatusBtn.setOnAction(e -> {
            ui.execute(1);
        });

        equipmentStatusBtn = new Button();
        equipmentStatusBtn.setText("Status dos Equipamentos");
        equipmentStatusBtn.setLayoutX(100);
        equipmentStatusBtn.setLayoutY(50);
        equipmentStatusBtn.setOnAction(e -> {
            ui.execute(2);
        });

        wakeUpBabyBtn = new Button();
        wakeUpBabyBtn.setText("Acordar o Bebe");
        wakeUpBabyBtn.setLayoutX(100);
        wakeUpBabyBtn.setLayoutY(90);
        wakeUpBabyBtn.setOnAction(e -> {
            ui.execute(3);
        });

        createEventBtn = new Button();
        createEventBtn.setText("Criar Eventos");
        createEventBtn.setOnAction(this);
        createEventBtn.setLayoutX(100);
        createEventBtn.setLayoutY(130);

        deleteEventBtn = new Button();
        deleteEventBtn.setText("Deletar Eventos");
        deleteEventBtn.setOnAction(this);
        deleteEventBtn.setLayoutX(100);
        deleteEventBtn.setLayoutY(170);

        editEventBtn = new Button();
        editEventBtn.setText("Editar Eventos");
        editEventBtn.setOnAction(this);
        editEventBtn.setLayoutX(100);
        editEventBtn.setLayoutY(210);

        pauseEventBtn = new Button();
        pauseEventBtn.setText("Pausar Evento");
        pauseEventBtn.setOnAction(this);
        pauseEventBtn.setLayoutX(100);
        pauseEventBtn.setLayoutY(250);

        continueEventBtn = new Button();
        continueEventBtn.setText("Continuar Evento");
        continueEventBtn.setOnAction(this);
        continueEventBtn.setLayoutX(100);
        continueEventBtn.setLayoutY(290);

        primaryStage.setTitle("Berco Inteligente");

        Pane layout = new Pane();
        layout.getChildren().add(babyStatusBtn);
        layout.getChildren().add(equipmentStatusBtn);
        layout.getChildren().add(wakeUpBabyBtn);
        layout.getChildren().add(createEventBtn);
        layout.getChildren().add(deleteEventBtn);
        layout.getChildren().add(editEventBtn);
        layout.getChildren().add(pauseEventBtn);
        layout.getChildren().add(continueEventBtn);


        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println(event);
    }
}
