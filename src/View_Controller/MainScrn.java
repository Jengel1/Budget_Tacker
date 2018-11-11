package View_Controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Created by Jengel1 on 10/20/2018.
 */
public class MainScrn extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root,800, 600);

        /*
        TabPane in Center of BorderPane
         */
        TabPane tb_center = new TabPane();
        Tab tab1 = new Tab("Envelope");
        Tab tab2 = new Tab("Categories");
        Tab tab3 = new Tab("Ledger");
        Tab tab4 = new Tab("Reports");
        tab1.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);
        tab4.setClosable(false);
        tab1.setContent(new EnvelopeTab().initGUI());
        tab2.setContent(new CategoriesTab().initGUI());
//        tab1.setContent(new Rectangle(200,490, Color.LIGHTSTEELBLUE));
//        tab2.setContent(new Rectangle(200,490, Color.BISQUE));
        tab3.setContent(new Rectangle(200,490, Color.AQUA));
        tab4.setContent(new Rectangle(200,490, Color.LAVENDER));
        tb_center.getTabs().addAll(tab1, tab2, tab3, tab4);

        /*
        FlowPane in Top of BorderPane
         */
        FlowPane fp_top = new FlowPane();
        Label lbl_appName = new Label("Budget Tracker");
        fp_top.setAlignment(Pos.CENTER);
        fp_top.setPrefHeight(50);
        fp_top.setPrefWidth(800);
        fp_top.getChildren().add(lbl_appName);

        /*
        FlowPane in Bottom of BorderPane
         */
        FlowPane fp_bottom = new FlowPane();
        Button btn1 = new Button("Exit");
        btn1.setOnAction(new EventHandler<ActionEvent>() {  //handle exit
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        fp_bottom.setMaxHeight(20);
        //fp_bottom.setHgap(10);
        fp_bottom.setAlignment(Pos.BOTTOM_RIGHT);
        fp_bottom.getChildren().add(btn1);


        /*
        BorderPane in root
         */
        BorderPane bp = new BorderPane();
        bp.setTop(fp_top);
//        bp.setLeft(fp_left);
        bp.setCenter(tb_center);
        bp.setBottom(fp_bottom);

        root.getChildren().add(bp);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}