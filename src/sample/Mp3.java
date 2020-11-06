 /*
  * To change this license header, choose License Headers in Project Properties.
  * To change this template file, choose Tools | Templates
  * and open the template in the editor.
  */
 package sample;


 import java.io.BufferedInputStream;
 import java.io.FileInputStream;
 import javazoom.jl.player.Player;


 public class Mp3 {
     public String filename;
     private Player player;

     // constructor that takes the name of an MP3 file
     public Mp3(String filename) {
         this.filename = filename;
     }

     public void close() { if (player != null) player.close(); }

     // play the MP3 file to the sound card
     public void play() {
         try {
             FileInputStream fis     = new FileInputStream(filename);
             BufferedInputStream bis = new BufferedInputStream(fis);
             player = new Player(bis);
         }
         catch (Exception e) {
             System.out.println("Problem playing file " + filename);
             System.out.println(e);
         }

         // run in new thread to play in background
         new Thread() {
             @Override
             public void run() {
                 try { player.play(); }
                 catch (Exception e) { System.out.println(e); }
             }
         }.start();




     }
 }