package baslangic_seviye_java_backend_web_patikasi.oop.mineSweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        int line, column;

        System.out.print("Oyun kaç satırdan oluşsun: ");
        line = inp.nextInt();

        System.out.print("Oyun kaç stündan oluşsun: ");
        column = inp.nextInt();

        MineSweeper game = new MineSweeper(line, column);
        game.play();
    }
}
