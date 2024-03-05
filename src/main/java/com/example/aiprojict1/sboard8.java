package com.example.aiprojict1;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class sboard8 implements Initializable {
    public double it8= homepage.ith();
    public double cr8=homepage.crh();
    public double ft8=homepage.fth();

    @FXML
    private GridPane chessBoard;

    @FXML
    private Text displayitration;

    @FXML
    private Text displaytemp;

    @FXML
    private Button playAginButton;

    @FXML
    private Label solutionLable;

    @FXML
    private Button startButton;
    int [] firstState;
    int []solution ;

    int itra;
    double finaltt;
    @FXML
    void startButtonClick(ActionEvent event) {
        cleanChessBoard();
        NQueensSimulatedAnnealing solver =  new NQueensSimulatedAnnealing(8,it8,cr8,ft8);
        firstState =  solver.initializeBoard();
        solution = solver.solve(firstState);
        displayInBord(firstState);
        itra = solver.getItration();
        finaltt= solver.getFinaltempprint();
    }
    @FXML
    void playAginButtonClick(ActionEvent event) {
        cleanChessBoard();
        solutionLable.setText(" ");
        displayitration.setText(" ");
        displaytemp.setText(" ");
        solutionLable.setText(" ");
    }
    @FXML
    void genrate8(MouseEvent event) {
        cleanChessBoard();
        displayInBord(solution);
        displayitration.setText(String.valueOf(itra));
        displaytemp.setText(String.valueOf(finaltt));
        if(finaltt <= ft8){
            solutionLable.setText("Sorry, I Can't Solve It.. :( ");
        }
        else {
            solutionLable.setText("DONE :)");
        }
    }
    public void displayInBord(int [] state){
        for(int i = 0 ; i < state.length ; i++){ // display in bord
            Image queen = new Image("C:\\Users\\hp\\IdeaProjects\\aiJana\\Ai\\src\\main\\resources\\com\\example\\ai\\image\\queen.png");
            int row = state[i];
            int col = i;
            ImageView queenView = new ImageView(queen);
            chessBoard.add(queenView, col, row);

            GridPane.setHalignment(queenView, HPos.CENTER);
            GridPane.setValignment(queenView, VPos.CENTER);
        }
    }
    private void cleanChessBoard() {
        ObservableList<Node> children = chessBoard.getChildren();
        List<Node> copyOfChildren = new ArrayList<>(children);
        for (Node child : copyOfChildren) {
            if (child instanceof ImageView) {
                children.remove(child);
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Board8");
    }
}
