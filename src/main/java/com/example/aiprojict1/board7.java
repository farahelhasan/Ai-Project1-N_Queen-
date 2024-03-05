package com.example.aiprojict1;

import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.aiprojict1.GenerateFirstState.firstState;

public class board7 {
    int [] firstState;
    @FXML
    private Button backButton;

    @FXML
    private GridPane chessBoard;

    @FXML
    private Button generateButtom;

    @FXML
    private Button playAginButton;

    @FXML
    private Button startButton;
    @FXML
    private Label solutionLable;
    @FXML
    void backButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homepage.fxml"));
        Parent root = fxmlLoader.load();
        // Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("N-Queen");
        stage.setScene(new Scene(root));
        stage.show();
    }




    @FXML
    void playAginButtonClick(ActionEvent event) {
        cleanChessBoard();
        solutionLable.setText(" ");

    }

    @FXML
    void startButtonClick(ActionEvent event) {
        cleanChessBoard();
        firstState =  firstState(7);
        displayInBord(firstState);
    }



    @FXML
    void generateButtomClick(ActionEvent event) {
        cleanChessBoard();
        //  int [] firstState =  firstState(6);
        int valueOfFirstState = findTheValueOfState(firstState);
        int [] currentState =  firstState;


        if(valueOfFirstState == 0) { //solution
            displayInBord(firstState);
          printBoard(firstState);
            solutionLable.setText("DONE :)");


        }else {
            while(true){
                cleanChessBoard();
                displayInBord(currentState);
                printBoard(currentState);
                int [] nextState =  bestSuccessor(currentState);
                int valueOfCurrentState = findTheValueOfState(currentState);
                int valueOfNextState = findTheValueOfState(nextState);


                if (valueOfNextState == 0){
                    cleanChessBoard();
                    displayInBord(nextState);
                    printBoard(nextState);
                    solutionLable.setText("DONE :)");
                    break;
                }else if(valueOfNextState > valueOfCurrentState){
                    currentState = nextState;
                    solutionLable.setText("loading...");

                }else {
                    solutionLable.setText("Sorry, I Can't Solve It.. :( ");
                    break;
                }
            }
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
    public void displayInBord(int [] state){
        for(int i = 0 ; i < state.length ; i++){ // display in bord
            Image queen = new Image("C:\\Users\\hp\\IdeaProjects\\AiProjict1\\src\\main\\resources\\com\\example\\aiprojict1\\images\\queen.png");
            int row = state[i];
            int col = i;
            ImageView queenView = new ImageView(queen);
            chessBoard.add(queenView, col, row);

            GridPane.setHalignment(queenView, HPos.CENTER);
            GridPane.setValignment(queenView, VPos.CENTER);
        }
    }
    public int [][] findTheSuccessors(int [] currentState ){
        int j =0 ;
        int numOfSuccessor = currentState.length * (currentState.length - 1);
        int [][] successor = new int [numOfSuccessor][currentState.length];
        for (int i = 0 ; i < currentState.length; i++ ){
            for (int c = 0 ; c < currentState.length; c++ ) {
                if (!(currentState[i] == c)){
                    System.arraycopy(currentState, 0, successor[j], 0, currentState.length);
                    successor[j++][i] = c;
                }
            }
        }
        return successor;
    }

    public int [] findTheValueOfSuccessor(int [] currentState,  int [][] successor ){
        int value = 0;
        int numOfSuccessor = currentState.length * (currentState.length - 1);
        int [] valuesOfSuccessor  = new int [numOfSuccessor];

        for (int r = 0 ; r < numOfSuccessor ; r++){
            for (int c = 0 ; c < currentState.length ; c++){

                for(int i = c+1 ; i < currentState.length ; i++){ // search if we have 2 queen in same row
                    if( successor[r][i] == successor[r][c] ){
                        value ++;
                    }
                }
                for(int k = c+1 ; k < currentState.length ; k++){ // search if we have 2 queen in same diagonal
                    if( Math.abs(successor[r][c] - successor[r][k] )== Math.abs(c - k  )){ //IF  colQ1 - colQ2 == row1 - rowQ2
                        value ++;
                    }
                }
            }
            valuesOfSuccessor[r] = value;
            value=0;
        }
        return valuesOfSuccessor;
    }

    public int[] heuristicFunction(int[] valuesOfSuccessor) {
        int []heuristicValue = new int [valuesOfSuccessor.length] ;
        for(int i = 0 ; i < valuesOfSuccessor.length ; i++){
            heuristicValue[i] = valuesOfSuccessor[i]* -1;
        }
        return heuristicValue;
    }

    public int [] bestSuccessor(int [] currentState ){
        int index = -1;
        int [][] successor = findTheSuccessors(currentState);
        int [] valuesOfSuccessor = findTheValueOfSuccessor(currentState, successor);
        int [] heuristicValue = heuristicFunction(valuesOfSuccessor);

        ArrayList<Integer> arrayList = new ArrayList<>();
        double max = Double.NEGATIVE_INFINITY;
        for (int i = 0 ; i< heuristicValue.length ; i++){
            if (max < heuristicValue[i]){
                max = heuristicValue[i];
                arrayList.clear();
                arrayList.add(i);
                //  index = i;
            }else if (max == heuristicValue[i]){
                arrayList.add(i);
            }
        }
        Random random = new Random();
        int size = arrayList.size();
        index = arrayList.get(random.nextInt(size));
        System.out.println(index);
        return successor[index];
    }
    public void printBoard(int [] state) {
        for (int i = 0; i < state.length; i++) {
            System.out.print(state[i]);
        }
        System.out.println();
    }
    public int  findTheValueOfState(int [] currentState){
        int value=0;
        for (int c = 0 ; c < currentState.length ; c++){

            for(int i = c+1 ; i < currentState.length ; i++){ // search if we have 2 queen in same row
                if( currentState[i] == currentState[c] ){
                    value ++;
                }
            }
            for(int k = c+1 ; k < currentState.length ; k++){ // search if we have 2 queen in same diagonal
                if( Math.abs(currentState[c] - currentState[k] )== Math.abs(c - k  )){ //IF  colQ1 - colQ2 == row1 - rowQ2
                    value ++;
                }
            }
        }
        return value * -1;
    }


}
