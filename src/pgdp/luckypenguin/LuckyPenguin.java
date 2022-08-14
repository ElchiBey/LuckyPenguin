package pgdp.luckypenguin;

import pgdp.MiniJava;

public class LuckyPenguin extends MiniJava {
    public static void main(String[] args) {
        int penguinNumber = readInt("Number of penguins:");
        while (penguinNumber <= 1) penguinNumber = readInt("Number of penguins should be >1:");

        int startingFishNum = readInt("Starting fish per penguin:");
        while (startingFishNum <= 0) startingFishNum = readInt("Starting fish should be >0:");

        int[] fields = new int[9];
        int[] penguins = new int[penguinNumber];
        int[] fishes = new int[penguinNumber];
        int maxFish = 0;

        for (int i = 0; i < penguinNumber; i++)
            penguins[i] = startingFishNum;

        int playerPengus = penguinNumber;

        while (playerPengus > 0) {
            for (int i = 0; i < penguinNumber; i++) {
                if (penguins[i] != 0) {
                    writeBoard(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7], fields[8]);
                    write("It's penguin " + i + " turn:");
                    if (playerPengus == 1) {
                        write("You are the last penguin to play! You win all the fish on the board!");
                        penguins[i] += fields[0] + fields[1] + fields[2] + fields[3] + fields[4] + fields[5] + fields[6] + fields[7] + fields[8];
                        for (int j = 0; j < penguinNumber; j++) {
                            if (fishes[j] >= maxFish) maxFish = fishes[j];
                        }
                        if (maxFish <= penguins[i]) maxFish = penguins[i];
                        if (maxFish > 1) write("The winning penguins with " + maxFish + " fishes:");
                        else write("The winning penguins with " + 1 + " fish:");
                        for (int t = 0; t < i; t++) {
                            if (fishes[t] == maxFish) {
                                write("Penguin " + t);
                            }
                        }
                        if (penguins[i] == maxFish) write("Penguin " + i);
                        for (int t = i + 1; t < penguinNumber; t++) {
                            if (fishes[t] == maxFish) {
                                write("Penguin " + t);
                            }
                        }
                        return;
                    } else {
                        int a = dice();
                        int b = dice();
                        int sum = a + b;
                        write(a + " + " + b + " = " + sum + " was rolled.");
                        if (sum == 7) {
                            fields[4]++;
                            penguins[i]--;
                            write("Wedding! You give a fish and place it on F7.");
                        } else if (sum == 2) {
                            for (int g = 0; g < 9; g++) {
                                penguins[i] += fields[g];
                            }
                            penguins[i] -= fields[4];
                            write("Lucky penguin! You win all fish on the board except F7!");
                            for (int j = 0; j < 9; j++){
                                if (j != 4)
                                    fields[j] = 0;
                            }
                        } else if (sum == 12) {
                            for (int g = 0; g < 9; g++) {
                                penguins[i] += fields[g];
                                fields[g] = 0;
                            }
                        } else {
                            if (fields[sum - 3] == 0) {
                                fields[sum - 3]++;
                                penguins[i]--;
                                write("You put a fish on F" + sum + ".");
                            } else {
                                fields[sum - 3]--;
                                penguins[i]++;
                                write("They take the fish from F" + sum + ".");
                            }
                        }

                        if (penguins[i] == 1) {
                            write("You now have 1 fish!");
                            int exit = readInt("Enter 1 to exit now:");
                            if (exit == 1) {
                                fishes[i] = penguins[i];
                                penguins[i] = 0;
                                playerPengus--;
                            }
                        } else if (penguins[i] > 1) {
                            write("You now have " + penguins[i] + " fishes!");
                            int exit = readInt("Enter 1 to exit now:");
                            if (exit == 1) {
                                fishes[i] = penguins[i];
                                penguins[i] = 0;
                                playerPengus--;
                            }
                        } else if (penguins[i] == 0) {
                            playerPengus--;
                            penguins[i] = -1;
                            write("You now have " + 0 + " fishes!");
                            write("You have lost all fish, so you can no longer play!");
                        }
                    }
                }

                if (i == penguinNumber - 1) {
                    i = -1;
                }
            }
        }
    }
}
