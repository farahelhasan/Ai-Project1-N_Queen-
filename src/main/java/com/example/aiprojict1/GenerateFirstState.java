package com.example.aiprojict1;

import java.util.Random;

public class GenerateFirstState {


    public static int[] firstState(int N) {
        int[] firstState = new int[N];
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            firstState[i] = random.nextInt(N);
        }
        return firstState;
    }

}

