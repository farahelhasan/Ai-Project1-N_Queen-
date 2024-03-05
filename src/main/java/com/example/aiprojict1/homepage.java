package com.example.aiprojict1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class homepage implements Initializable {


    public static Double it;
    public  static  Double cr;
    public static   Double ft;
    public static int flage;

    public static int getFlage(){
        return flage;

    }
    public static double ith(){
        return it;

    }
    public static double crh(){
        return cr;

    }
    public static double fth(){
        return ft;

    }
    @FXML
    private RadioButton firstFun;

    @FXML
    private ToggleGroup functions;
    @FXML
    private Text tempFunctionLable;

    @FXML
    private RadioButton secoundFun;

    @FXML
    void funSelecte(ActionEvent event) {
        functions = new ToggleGroup();
        firstFun.setToggleGroup(functions);
        secoundFun.setToggleGroup(functions);
    }
    @FXML
    private ChoiceBox<Integer> Size;

    @FXML
    private ChoiceBox<String> Salgo;

    @FXML
    private Text initaltemp;

    @FXML
    private Text coolrate;

    @FXML
    private Text finaltemp;


    @FXML
    private TextField textit;

    @FXML
    private TextField textcr;

    @FXML
    private TextField textft;

    @FXML
    private Button submit;


    @FXML
    void getdata(MouseEvent event) throws IOException {
        String search = Salgo.getSelectionModel().getSelectedItem();
        if("Simulated annealing".equals(search)) {
            firstFun.setVisible(true);
            secoundFun.setVisible(true);
            tempFunctionLable.setVisible(true);
            System.out.println("initial temperature");
            initaltemp.setVisible(true);
            textit.setVisible(true);
            System.out.println("cooling rate");
            coolrate.setVisible(true);
            textcr.setVisible(true);
            System.out.println("maximum iteration or final temperature");
            finaltemp.setVisible(true);
            textft.setVisible(true);
            submit.setVisible(true);

        }else if("Hill Climbing ".equals(search)) {
            firstFun.setVisible(false);
            secoundFun.setVisible(false);
            tempFunctionLable.setVisible(false);
            initaltemp.setVisible(false);
            textit.setVisible(false);
            coolrate.setVisible(false);
            textcr.setVisible(false);
            finaltemp.setVisible(false);
            textft.setVisible(false);
            submit.setVisible(false);
            System.out.println("farah");
            if(Size.getValue()==4)
            {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("board4.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage =new Stage();
                stage.setTitle("Board4!");
                stage.setScene(scene);
                stage.show();
            }
            else  if(Size.getValue()==5)
            {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("board5.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage =new Stage();
                stage.setTitle("Board5!");
                stage.setScene(scene);
                stage.show();
            }
            else  if(Size.getValue()==6)
            {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("board6.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage =new Stage();
                stage.setTitle("Board6!");
                stage.setScene(scene);
                stage.show();
            }
            else  if(Size.getValue()==7)
            {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("board7.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage =new Stage();
                stage.setTitle("Board7!");
                stage.setScene(scene);
                stage.show();
            }
            else  if(Size.getValue()==8)
            {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("board8.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage =new Stage();
                stage.setTitle("Board8!");
                stage.setScene(scene);
                stage.show();
            }
        }
    }
    @FXML
    void submitdata(MouseEvent event) throws IOException {
        it = Double.parseDouble(textit.getText());
        cr = Double.parseDouble(textcr.getText());
        ft = Double.parseDouble(textft.getText());
        if (firstFun.isSelected()){
            flage = 1;
        }else if(secoundFun.isSelected()) {
            flage = 2;
        }
        firstFun.setVisible(false);
        secoundFun.setVisible(false);
        tempFunctionLable.setVisible(false);
        initaltemp.setVisible(false);
        textit.setVisible(false);
        coolrate.setVisible(false);
        textcr.setVisible(false);
        finaltemp.setVisible(false);
        textft.setVisible(false);
        submit.setVisible(false);
        if(Size.getValue()==4)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Sboard4.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage =new Stage();
            stage.setTitle("Board4!");
            stage.setScene(scene);
            stage.show();
        }
        else  if(Size.getValue()==5)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Sboard5.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage =new Stage();
            stage.setTitle("Board5!");
            stage.setScene(scene);
            stage.show();
        }
        else  if(Size.getValue()==6)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Sboard6.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage =new Stage();
            stage.setTitle("Board6!");
            stage.setScene(scene);
            stage.show();
        }
        else  if(Size.getValue()==7)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Sboard7.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage =new Stage();
            stage.setTitle("Board7!");
            stage.setScene(scene);
            stage.show();
        }
        else  if(Size.getValue()==8)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Sboard8.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage =new Stage();
            stage.setTitle("Board8!");
            stage.setScene(scene);
            stage.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Start");
        Size.getItems().addAll(4, 5, 6, 7, 8);
        Salgo.getItems().addAll("Hill Climbing ","Simulated annealing");
    }
}