package View_Controller;

import Model.Envelope;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jengel1 on 11/5/2018.
 */
public class EnvelopeTab {

//    private Stage primaryStage;

    ArrayList<Envelope> listOfEnvelopes = new ArrayList<>(Arrays.asList(
            new Envelope("Rent", 1403.33),
            new Envelope("Electric", 45),
            new Envelope("Water/Trash", 80),
            new Envelope("Internet", 45),
            new Envelope("Parking", 360),
            new Envelope("Dog Sitting", 800),
            new Envelope("Student Loan", 103),
            new Envelope("Laundry", 15),
            new Envelope("Mutual Fund", 100),
            new Envelope("Hair", 72),
            new Envelope("Groceries", 400),
            new Envelope("Gas", 30),
            new Envelope("Dog Food", 150),
            new Envelope("Msc Expenses", 200),
            new Envelope("Savings", 250)
    ));

//    ArrayList<GridPane> listOfEnvelopeGPs = new ArrayList<>();

    /*
    Method to start GUI by creating primary stage
     */
//    @Override
//    public void start(Stage primaryStage) {
//        this.primaryStage = primaryStage;
//        this.primaryStage.setTitle("Budget Tracker");
//        initGUI();
//    }

    public EnvelopeTab(){}

    public BorderPane initGUI() {
//        Group root = new Group();
//        Scene scene = new Scene(root,800, 600);

        /*
        GridPane in Center of BorderPane
         */
        GridPane gp_center = new GridPane();
//        gp_center.setVgap(100);
        int columnIndex = 0;
        int rowIndex = 0;
        for(int i = 0; i < listOfEnvelopes.size(); i++){  //loop through listOfEnvelopes
            Label lbl_name = new Label(listOfEnvelopes.get(i).getName());  //envelope name
            lbl_name.setPadding(new Insets(0,5,0,0));
            Label lbl_amount = new Label("Goal: " + listOfEnvelopes.get(i).getAmount());  //envelope goal
            lbl_amount.setPadding(new Insets(0,0,0,5));

            TextField tf = new TextField("0");  //to hold current amount in envelope
            tf.setMaxSize(60,10);

            Button btn_plus = new Button("+");  //add money to envelope
            btn_plus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int currentAmt = Integer.parseInt(tf.getText());
                    tf.setText(String.valueOf(currentAmt + 20));
                }
            });
            btn_plus.setPadding(new Insets(-2,0,-2,0));
            btn_plus.setMinSize(25,5);

            Button btn_minus = new Button("-");  //subtract money from envelope
            btn_minus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int currentAmt = Integer.parseInt(tf.getText());
                    //add logic to ensure envelope cannot have a negative amount
                    tf.setText(String.valueOf(currentAmt - 20));
                }
            });
            btn_minus.setPadding(new Insets(-2,0,-2,0));
            btn_minus.setMinSize(25,5);

            FlowPane fp = new FlowPane(Orientation.VERTICAL);  //holds the plus and minus btns
            fp.setPrefHeight(26);
            fp.getChildren().addAll(btn_plus, btn_minus);

            GridPane gp = new GridPane();  //grid pane to hold btns, tf, & lbls
            gp.getColumnConstraints().add(0, new ColumnConstraints(86));  //name of envelope
            gp.getColumnConstraints().add(1, new ColumnConstraints(25));  //btns
            gp.getColumnConstraints().add(2, new ColumnConstraints(60));  //tf
            gp.getColumnConstraints().add(3, new ColumnConstraints(80));  //goal of envelope
            GridPane.setConstraints(lbl_name, 0,0);
            GridPane.setConstraints(fp, 1,0);
            GridPane.setConstraints(tf, 2,0);
            GridPane.setConstraints(lbl_amount, 3,0);
            gp.setHalignment(lbl_name, HPos.RIGHT);
            gp.setValignment(fp, VPos.CENTER);
            gp.setHalignment(lbl_amount, HPos.LEFT);
//        gp.setGridLinesVisible(true);
            gp.getChildren().addAll(lbl_name, fp, tf, lbl_amount);

//            listOfEnvelopeGPs.add(gp);  //add gridpane to listOfEnvelopeGPs for controller access

            GridPane.setConstraints(gp, columnIndex, rowIndex);
            gp_center.setVgap(10);
            gp_center.getChildren().add(gp);
            rowIndex++;
            if(rowIndex == 10 || rowIndex == 20){
                columnIndex++;
                rowIndex = 0;
            }
        }

        /*
        FlowPane in left of Border Pane
         */
        FlowPane fp_left = new FlowPane(Orientation.VERTICAL);
        FlowPane fp_cusion = new FlowPane();
        Label lbl_cushion = new Label("Cushion Balance");
        TextField tf_cushion = new TextField();
        tf_cushion.setMaxSize(80,10);
        fp_cusion.setPrefWidth(200);
        fp_cusion.setHgap(5);
        fp_cusion.setAlignment(Pos.CENTER_RIGHT);
        fp_cusion.getChildren().addAll(lbl_cushion, tf_cushion);

        FlowPane fp_current = new FlowPane();
        Label lbl_current = new Label("Current Balance");
        TextField tf_current = new TextField();
        tf_current.setMaxSize(80,10);
        fp_current.setPrefWidth(200);
        fp_current.setHgap(5);
        fp_current.setAlignment(Pos.CENTER_RIGHT);
        fp_current.getChildren().addAll(lbl_current, tf_current);

        FlowPane fp_float = new FlowPane();
        Label lbl_float = new Label("Float Balance");
        TextField tf_float = new TextField();
        tf_float.setMaxSize(80,10);
        fp_float.setPrefWidth(200);
        fp_float.setHgap(5);
        fp_float.setAlignment(Pos.CENTER_RIGHT);
        fp_float.getChildren().addAll(lbl_float, tf_float);

        fp_left.setVgap(30);
        fp_left.getChildren().addAll(fp_cusion, fp_current, fp_float);

//        /*
//        FlowPane in Bottom of BorderPane
//         */
//        FlowPane fp_bottom = new FlowPane();
//        Button btn1 = new Button("Categories");
//        btn1.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
////                Application.launch(CategoriesTab.class, null);  //Application launch cannot be called more than once
////                primaryStage.setScene(CategoriesTab.class.);
//            }
//        });
//        Button btn2 = new Button("Envelopes");
//        Button btn3 = new Button("Reports");
//        Button btn4 = new Button("Ledger");
//        Button btn5 = new Button("Exit");
//        fp_bottom.setHgap(10);
//        fp_bottom.setAlignment(Pos.CENTER);
//        fp_bottom.getChildren().addAll(btn1, btn2, btn3, btn4, btn5);

        /*
        FlowPane in Top of BorderPane
         */
        FlowPane fp_top = new FlowPane();
        Label lbl_appName = new Label("Budget Envelopes");
        fp_top.setAlignment(Pos.CENTER);
        fp_top.setPrefHeight(50);
        fp_top.setPrefWidth(800);
        fp_top.getChildren().add(lbl_appName);

        /*
        BorderPane in root
         */
        BorderPane bp = new BorderPane();
        bp.setPrefSize(800, 490);
        bp.setTop(fp_top);
        bp.setLeft(fp_left);
        bp.setCenter(gp_center);

//        bp.setBottom(fp_bottom);

//        root.getChildren().add(bp);
//        primaryStage.setScene(scene);
//        primaryStage.show();
        return bp;
    }

    /*
    Updates the goal amount of each envelope
     */
    private void updateEnvAmount(ActionEvent e) {
        Button btn = (Button) e.getSource();
        GridPane gp = (GridPane) btn.getParent();
        Node node_tf = gp.getChildren().get(2);
        if(node_tf instanceof TextField){
            ((TextField) node_tf).setText("new Text");
        }
    }

//    /*
//    Method allows controller access to gridPanes
//     */
//    public GridPane getEnvelopeGP(String envName){
//        GridPane gp = null;
//        boolean found = false;
//        int index = 0;
//        while (!found){
//            Node nameNode = listOfEnvelopeGPs.get(index).getChildren().get(0);
//            if(nameNode instanceof Label){
//                if(((Label) nameNode).getText().equals(envName)){
//                    gp = listOfEnvelopeGPs.get(index);
//                    found = true;
//                }
//            }
//            index++;
//        }
////        for(int i = 0; i < listOfEnvelopeGPs.size(); i++){
////            Node nameNode = listOfEnvelopeGPs.get(i).getChildren().get(0);
////            if(nameNode instanceof Label){
////                if(((Label) nameNode).getText().equals(envName)){
////                    gp = listOfEnvelopeGPs.get(i);
////                    break;
////                }
////            }
////        }
//        return gp;
//    }

}
