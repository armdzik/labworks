/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab3.Dzikas;

import edu.ktu.ds.lab3.Dzikas.ManualTest;
import java.util.Locale;

import edu.ktu.ds.lab3.gui.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 *
 * @author Arminas
 */
public class DemoExecution extends Application {
    public static void main(String[] args) {
        edu.ktu.ds.lab3.demo.DemoExecution.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus 
        ManualTest.executeTest();
        setUserAgentStylesheet(STYLESHEET_MODENA);    //Nauja FX išvaizda
      //setUserAgentStylesheet(STYLESHEET_CASPIAN); //Sena FX išvaizda
        MainWindow.createAndShowGui(primaryStage);
    }
    
}
