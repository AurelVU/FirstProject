package com.company;


import com.company.view.Shower;

import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    static Shower shower = new Shower();
    public static void main(String[] args) {
        while(true)
        {
            System.out.println("1 - пользователь, 2 - предпринематель");
            int key = in.nextInt();
            if (key == 1)
                shower.UserPanel();
            if (key == 2)
                shower.EmployerPanel();
        }
    }
}
