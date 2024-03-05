package com.example.aiprojict1;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;
public class Test implements Initializable {

        @FXML
        private GridPane chessBoard;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            // Load images (example)
            Image whitePawnImage = new Image("C:\\Users\\hp\\Downloads\\part1P2P\\AiProjict1\\src\\main\\resources\\com\\example\\aiprojict1\\images\\queen.png");
            Image blackPawnImage = new Image("C:\\Users\\hp\\Downloads\\part1P2P\\AiProjict1\\src\\main\\resources\\com\\example\\aiprojict1\\images\\queen.png");

            // Sample: Add images to specific cells
            int whitePawnRow = 0;
            int whitePawnColumn = 2;
            ImageView whitePawnImageView = new ImageView(whitePawnImage);
            chessBoard.add(whitePawnImageView, whitePawnColumn, whitePawnRow);

            int blackPawnRow = 1;
            int blackPawnColumn = 3;
            ImageView blackPawnImageView = new ImageView(blackPawnImage);
            chessBoard.add(blackPawnImageView, blackPawnColumn, blackPawnRow);

        }

        // You can add more methods for handling user interactions or other functionality
    }


