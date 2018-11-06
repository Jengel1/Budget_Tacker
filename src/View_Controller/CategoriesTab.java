package View_Controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by Jengel1 on 11/5/2018.
 */
public class CategoriesTab extends Application {

    private Stage primaryStage;
    private Scene scene;

    public Scene getScene() {
        return scene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        this.primaryStage = primaryStage;
//        this.primaryStage.setTitle("Budget Tracker");
        initGUI();
    }

    private void initGUI() {
        Group root = new Group();
        scene = new Scene(root,800, 600);

        /*
        GridPane in center of BorderPane
         */
        GridPane gp_center = new GridPane();
        gp_center.getChildren().add(new Label("Center GridPane"));

        /*
        BorderPane in root
         */
        BorderPane bp = new BorderPane();
//        bp.setTop(fp_top);
//        bp.setLeft(fp_left);
        bp.setCenter(gp_center);
//        bp.setBottom(fp_bottom);

        root.getChildren().add(bp);
//        primaryStage.setScene(scene);
//        primaryStage.show();

    }
}
