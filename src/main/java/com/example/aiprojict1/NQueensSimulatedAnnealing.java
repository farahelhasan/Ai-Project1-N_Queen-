package com.example.aiprojict1;

import java.util.Arrays;
import java.util.Random;

public class NQueensSimulatedAnnealing {



    public int N;    // Size of the chessboard (number of queens)
    public   int itration;
    public double intialtemp;
    public double coolrate;
    public double finaltemp;
    public double Tc;


    // Current arrangement of queens on the chessboard
    private int[] queens;


    ///constructor
    public NQueensSimulatedAnnealing(int N,Double intialtemp ,Double coolrate , Double finaltemp) {
        this.N = N;
        this.intialtemp=intialtemp;
        this.coolrate=coolrate;
        this.finaltemp=finaltemp;
        queens = new int[N];
    }



    // Method to initialize the chessboard randomly
    public int[] initializeBoard() {
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            queens[i] = random.nextInt(N);
        }
        printBoard();
        return queens;
    }



    // Method to compute the number of attacking pairs of queens
    private int countAttacks(int[] state) {
        int a = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (state[i] == state[j] || Math.abs(j - i) == Math.abs(state[i] - state[j])) {
                    a++;
                }
            }
        }
        System.out.println("the value of a : "+a);
        return a;
    }



    // Simulated Annealing algorithm
    public int[] solve(int[] state) {
        queens = state;     //first state of queens  random
        Tc = intialtemp;


        int currentAttacks = countAttacks(queens);

        int bestAttacks = currentAttacks;
        int [] bestAttacksState = queens;

        while ( currentAttacks!=0 )
        {
            System.out.println("first-state : ");printBoard();

            // function of temp
            if( homepage.getFlage() == 1){
                Tc = Tc * (1-coolrate);
            }
            else if(homepage.getFlage() == 2){
                Tc = Tc - (coolrate * itration);
            }



            int[] nextState = Arrays.copyOf(queens, N);
            int randomRow = new Random().nextInt(N);
            int randomCol = new Random().nextInt(N);
            nextState[randomCol] = randomRow;


            int newAttacks = countAttacks(nextState);

            //deltaAttacks
            int deltaAttacks = newAttacks - currentAttacks;

            if(deltaAttacks > 0)
            {
                queens = nextState;
                currentAttacks = newAttacks;
                if(bestAttacks  <  currentAttacks) {
                    bestAttacksState = queens;
                    bestAttacks = currentAttacks;
                }
            }

            else  if ( Math.exp(-deltaAttacks / Tc) > Math.random()) {
                queens = nextState;
                currentAttacks = newAttacks;
            }

            itration++;
            if(Tc <= finaltemp){
                break;
            }
        }

      System.out.println("final board : ") ; printBoard();
        System.out.println("final temp : "+Tc);
        System.out.println("num of itration : "+itration);
        return queens;

    }

    public void printBoard() {
        for (int i = 0; i < N; i++) {
            System.out.print(queens[i]);
        }
        System.out.println();
    }

    public int getItration(){

        return itration;
    }
    public double getFinaltempprint(){

        return Tc;
    }
    public static void main(String[] args) {


    }
}

