package br.ufrgs.inf;

import br.ufrgs.inf.controller.EquipmentService;
import br.ufrgs.inf.controller.AppController;
import br.ufrgs.inf.controller.EventManager;
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

        final Queue queue = new Queue();

        final LocalDateTime start = LocalDateTime.now().plusMinutes(5);
        final LocalDateTime end = start.plusMinutes(5);

        final AquecedorHandler aquecedorHandler = new AquecedorHandler(aquecedor, queue);
        final CameraHandler cameraHandler = new CameraHandler(camera, queue);
        final MobileHandler mobileHandler = new MobileHandler(mobile, queue);
        final SomHandler somHandler = new SomHandler(som, queue);
        final LuzHandler luzHandler = new LuzHandler(luz, queue);

        new Dispatcher(queue,
                Collections.singletonList(aquecedorHandler),
                Collections.singletonList(cameraHandler),
                Collections.singletonList(luzHandler),
                Collections.singletonList(mobileHandler),
                Collections.singletonList(somHandler)
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

        appController.wakeUpBaby();

        Thread.sleep(1000);

        appController.babySleep();

//        final URL fxml = Paths.get("./src/main/resources/app-main.fxml")
//                              .toUri()
//                              .toURL();
//
//        final FXMLLoader loader = new FXMLLoader(fxml);
//
//        final AppUI controller = new AppUI(appController, eventManager);
//
//        loader.setController(controller);
//
//        final Parent root = loader.load();
//
//        stage.setTitle("Berço Inteligente");
//        stage.setScene(new Scene(root));
//
//        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
