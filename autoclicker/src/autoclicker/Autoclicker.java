package autoclicker;

import java.awt.AWTException;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;

public class Autoclicker implements NativeMouseInputListener {

    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        if (e.getButton() == 5) {
            
            Globals.clicking = Globals.clicking == false;
            
            System.out.println("Clicking: " + Globals.clicking);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("AUTO CLICKER");
        Scanner sc = new Scanner(System.in);
        System.out.println(MouseInfo.getPointerInfo().getLocation());
        System.out.print("Click rate: ");
        int click_rate = sc.nextInt();
        System.out.println("CLICKS PER SECOND: " + click_rate);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        Autoclicker example = new Autoclicker();

        GlobalScreen.addNativeMouseListener(example);
        GlobalScreen.addNativeMouseMotionListener(example);

        try {
            Robot robot = new Robot();
            int time = 1000 / click_rate;

            while (true) {
                while (Globals.clicking == true) {
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    robot.delay(time);
                }

            }

        } catch (AWTException ex) {
            Logger.getLogger(Autoclicker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR");
        }

    }

}
