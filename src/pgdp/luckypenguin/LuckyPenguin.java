package pgdp.luckypenguin;

import static pgdp.MiniJava.*;

public class LuckyPenguin {
	public static void main(String[] args) {
      int n = readInt("Number of penguins:");
        while (n <= 1) n = readInt("Number of penguins should be >1:");
        int m = readInt("Starting fish per penguin:");
        while (m <= 0) m = readInt("Starting fish should be >0:");
        int[] f = new int[9];
        int[] p = new int[n];
        int[] y = new int[n];
        int maxfish = 0;
        int z = 0;
        for (int j = 0; j < n; j++)
            p[j] = m;
        int x = n;
        while (x > 0) {
            for (int i = 0; i < n; i++) {
                if (p[i] == 0) x--;
                else {
                    writeBoard(f[0], f[1], f[2], f[3], f[4], f[5], f[6], f[7], f[8]);
                    write("It's penguin " + i + " turn:");
                    if (x == 1) {
                        write("You are the last penguin to play! You win all the fish on the board!");
                        p[i] += f[0] + f[1] + f[2] + f[3] + f[4] + f[5] + f[6] + f[7] + f[8];
                        for (int j = 0; j < n; j++) {
                            if (y[j] >= maxfish) maxfish = y[j];
                            if(maxfish<p[i]) maxfish=p[i];
                        }
                        if (z == 0) {
                            if (p[i] == 1)
                                write("The winning penguins with " + 1 + " fish:");
                            else write("The winning penguins with " + p[i] + " fishes:");
                            return;
                        }
                        else{
                            if(maxfish>1) write("The winning penguins with " + maxfish + " fishes:");
                            else write("The winning penguins with " + 1 + " fish:");
                            for (int t = 0; t < i; t++) {
                                if (y[t] == maxfish) {
                                    write("Penguin " + t);
                                }
                            }
                            if(p[i]==maxfish) write("Penguin " + i);
                            for (int t = i+1; t < n; t++) {
                                if (y[t] == maxfish) {
                                    write("Penguin " + t);
                                }
                            }
                            return;
                        }
                    }
                    else{
                        int a = dice();
                        int b = dice();
                        int s = a + b;
                        write(a + " + " + b + " = " + s + " was rolled.");
                        if (s == 7) {
                            f[4]++;
                            p[i]--;
                            write("Wedding! You give a fish and place it on F7.");
                        } else if (s == 2) {
                            p[i] += f[0] + f[1] + f[2] + f[3] + f[5] + f[6] + f[7] + f[8];
                            write("Lucky penguin! You win all fish on the board except F7!");
                            f[0] = 0;
                            f[1] = 0;
                            f[2] = 0;
                            f[3] = 0;
                            f[5] = 0;
                            f[6] = 0;
                            f[7] = 0;
                            f[8] = 0;
                        } else if (s == 12) {
                            p[i] += f[0] + f[1] + f[2] + f[3] + f[4] + f[5] + f[6] + f[7] + f[8];
                            f[0] = 0;
                            f[1] = 0;
                            f[2] = 0;
                            f[3] = 0;
                            f[4] = 0;
                            f[5] = 0;
                            f[6] = 0;
                            f[7] = 0;
                            f[8] = 0;
                        } else {
                            if (f[s - 3] == 0) {
                                f[s - 3]++;
                                p[i]--;
                                write("You put a fish on F" + s + ".");
                            } else {
                                f[s - 3]--;
                                p[i]++;
                                write("They take the fish from F" + s + ".");
                            }
                        }
                        if (p[i] == 1) {
                            write("You now have 1 fish!");
                            int exit = readInt("Enter 1 to exit now:");
                            if (exit == 1) {
                                y[i] = p[i];
                                p[i] = 0;
                                x--;
                            }
                        } else if (p[i] > 1) {
                            write("You now have " + p[i] + " fishes!");
                            int exit = readInt("Enter 1 to exit now:");
                            if (exit == 1) {
                                y[i] = p[i];
                                p[i] = 0;
                                x--;
                            }
                        } else {
                            x--;
                            write("You now have " + 0 + " fishes!");
                            write("You have lost all fish, so you can no longer play!");
                        }
                    }
                }
                if (i + 1 == n) {
                    i = -1;
                    x=n;
                }
            }
        }
    }
}