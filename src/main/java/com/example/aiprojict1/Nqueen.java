package com.example.aiprojict1;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import static com.example.aiprojict1.GenerateFirstState.firstState;

public class Nqueen implements Initializable {
    @FXML
    private TextField text;
    @FXML
    private TextField firstState;

    @FXML
    private TextField numOfSe;

    @FXML
    private TextField va;

    @Override
    public void initialize (URL url , ResourceBundle rb){
       int [] r=  firstState(4);
       int valuec = findTheValueOfCurrentState(r);
        String rr ="";
        for (int k = 0 ; k < r.length ; k++){
            rr = rr + r[k] +" ";
        }
        firstState.setText(rr);
       String s ="";
        int [][] successor = findTheSuccessors(r);
        int [] valuesOfSuccessor = findTheValueOfSuccessor(r, successor);

        for (int i = 0 ; i < r.length*(r.length-1) ; i++){
            for (int k = 0 ; k < r.length ; k++){
                s = s + successor[i][k] +" ";
            }
            s = s + "--";
        }
        String v="";
        for (int k = 0 ; k < 12 ; k++){
            v = v + valuesOfSuccessor[k] +" ";
        }
       text.setText(s);
        int [] t =  bestSuccessor(r);
        String tt ="";
        for (int k = 0 ; k < t.length ; k++){
            tt = tt + t[k] +" ";
        }
        numOfSe.setText(tt);
        va.setText(v);
    }

//    public int calculateNumOfSuccessor(int[] currentState) {
//        int numOfSuccessor = 0 ;
//        for(int i = 0 ; i < currentState.length; i++){
//            if (currentState[i] == 0 ||currentState[i] ==currentState.length -1 ){
//                 numOfSuccessor = numOfSuccessor + 1 ;
//            }else {
//                numOfSuccessor = numOfSuccessor + 2;
//            }
//        }
//        return numOfSuccessor;
//    }

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

        double max = Double.NEGATIVE_INFINITY;
        for (int i = 0 ; i< heuristicValue.length ; i++){
           if (max < heuristicValue[i]){
               max = heuristicValue[i];
               index = i;
           }
        }

        return successor[index];
    }

    public int  findTheValueOfCurrentState(int [] currentState){
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


