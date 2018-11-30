package br.ufrgs.inf;

import br.ufrgs.inf.controller.AppController;
import br.ufrgs.inf.controller.EquipmentService;
import br.ufrgs.inf.controller.EventManager;
import br.ufrgs.inf.data.builders.CameraEventBuilder;
import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.Recording;
import br.ufrgs.inf.data.events.CameraEvent;
import br.ufrgs.inf.equipment.*;
import br.ufrgs.inf.event.Dispatcher;
import br.ufrgs.inf.event.Queue;
import br.ufrgs.inf.handler.*;
import br.ufrgs.inf.view.AppUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collections;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        final Aquecedor aquecedor = new Aquecedor();
        final Camera camera = new Camera();
        final Som som = new Som();
        final Mobile mobile = new Mobile();
        final Luz luz = new Luz();
        final Sound sound = new Sound();

        final Queue queue = new Queue();
        final Queue uiQueue = new Queue();

        final AquecedorHandler aquecedorHandler = new AquecedorHandler(aquecedor, queue, uiQueue);
        final CameraHandler cameraHandler = new CameraHandler(camera, queue, uiQueue);
        final MobileHandler mobileHandler = new MobileHandler(mobile, queue, uiQueue);
        final SomHandler somHandler = new SomHandler(som, queue, uiQueue);
        final LuzHandler luzHandler = new LuzHandler(luz, queue, uiQueue);
        final SoundHandler soundHandler = new SoundHandler(sound, queue, uiQueue);

        new Dispatcher(queue,
                Collections.singletonList(aquecedorHandler),
                Collections.singletonList(cameraHandler),
                Collections.singletonList(luzHandler),
                Collections.singletonList(mobileHandler),
                Collections.singletonList(somHandler),
                Collections.singletonList(soundHandler)
                );

        final EventManager eventManager = new EventManager();

        final EquipmentService equipmentService = new EquipmentService(
                aquecedor,
                camera,
                luz,
                mobile,
                som
        );

        final AppController appController = new AppController(queue, equipmentService);

        final LocalDateTime now = LocalDateTime.now();


        final URL fxml = Paths.get("./src/main/resources/app-main.fxml")
                .toUri()
                .toURL();

        final FXMLLoader loader = new FXMLLoader(fxml);

        final AppUI controller = new AppUI(appController, eventManager);

        final String cameraId = appController.createCameraEvent(now, now, Recording.ON, EquipmentStatus.ON);

        final CameraEvent cameraEvent = new CameraEventBuilder()
                .start(now)
                .end(now)
                .equipmentStatus(EquipmentStatus.ON)
                .recording(Recording.ON)
                .id(cameraId)
                .build();

        eventManager.add(cameraEvent);

        new Dispatcher(uiQueue, Collections.singletonList(controller));

        loader.setController(controller);

        final Parent root = loader.load();

        stage.setTitle("Berço Inteligente");
        stage.setScene(new Scene(root));

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
