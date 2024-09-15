package baslangic_seviye_java_backend_web_patikasi.oop.mineSweeper;

import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    Scanner inp = new Scanner(System.in);
    Random rand = new Random();
    String[][] board;
    String[][] map;
    int line;
    int column;
    int selectLine;
    int selectColumn;
    int mine;

    MineSweeper(int line, int column) {
        this.line = line;
        this.column = column;
        this.mine = (this.line * this.column) / 4;

        this.board = new String[this.line][this.column];
        this.map = new String[this.line][this.column];

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = "-";
                this.map[i][j] = "-";
            }
        }
    }

    void play() {
        if (this.line < 2 || this.column < 2) {
            System.out.println("Satır veya stün 2 den az olamaz!");
        } else {
            plantMine();
            System.out.println("<<<<Mayın tarlası oyununa hoş geldiniz>>>>");
            int succes = 0;
            while (true) {
                printBoard(board);

                System.out.print("Satır : ");
                this.selectLine = inp.nextInt();

                System.out.print("Stün : ");
                this.selectColumn = inp.nextInt();

                if (!this.board[this.selectLine][this.selectColumn].equals("-")) {
                    System.out.println("Bu koordinat daha önce seçildi başka bir koordinat giriniz!");
                    continue;
                }
                if (this.selectLine < 0 || selectLine >= this.line) {
                    System.out.println("Hatalı koordinat!");
                    continue;
                }
                if (this.selectColumn < 0 || selectColumn >= this.column) {
                    System.out.println("Hatalı koordinat!");
                    continue;
                }

                if (isMineFind()) {
                    break;
                } else {
                    succes++;
                    mineNumber(this.selectLine, this.selectColumn);
                    if (succes == (this.line * this.column) - this.mine) {
                        System.out.println("Kazandınız!");
                        printBoard(board);
                        System.out.println("Mayınların konumu: ");
                        printBoard(map);
                        break;
                    }
                }
            }
        }
    }

    int mineNumber(int l, int c) {
        int count = 0;
        if (this.map[l][c].equals("-")) {
            if (c - 1 >= 0 && this.map[l][c - 1].equals("*")) {
                ++count;
            }
            if (l + 1 < this.line && c - 1 >= 0 && this.map[l + 1][c - 1].equals("*")) {
                ++count;
            }
            if (l + 1 < this.line && this.map[l + 1][c].equals("*")) {
                ++count;
            }
            if (l + 1 < this.line && c + 1 < this.column && this.map[l + 1][c + 1].equals("*")) {
                ++count;
            }
            if (c + 1 < this.column && this.map[l][c + 1].equals("*")) {
                ++count;
            }
            if (l - 1 >= 0 && c + 1 < this.column && this.map[l - 1][c + 1].equals("*")) {
                ++count;
            }
            if (l - 1 >= 0 && this.map[l - 1][c].equals("*")) {
                ++count;
            }
            if (l - 1 >= 0 && c - 1 >= 0 && this.map[l - 1][c - 1].equals("*")) {
                ++count;
            } else {
                this.board[l][c] = String.valueOf(0);
            }
            this.board[l][c] = String.valueOf(count);
        }
        return count;
    }

    boolean isMineFind() {
        if (this.map[this.selectLine][this.selectColumn].equals("*")) {
            System.out.println("Mayına bastınız!");
            printBoard(board);
            System.out.println("Mayınların konumu: ");
            printBoard(map);
            return true;
        }
        return false;
    }

    void plantMine() {
        int randomLine;
        int randomColumn;
        int i = 0;
        while (i < this.mine) {
            randomLine = rand.nextInt(this.line);
            randomColumn = rand.nextInt(this.column);
            if (this.map[randomLine][randomColumn].equals("-")) {
                this.map[randomLine][randomColumn] = "*";
                i++;
            }
        }
    }

    void printBoard(String[][] mtrx) {
        for (int i = 0; i < mtrx.length; i++) {
            for (int j = 0; j < mtrx[i].length; j++) {
                System.out.print(mtrx[i][j] + " ");
            }
            System.out.println();
        }
    }
}
