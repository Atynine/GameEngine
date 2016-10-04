package engine.tests.input;

import engine.Screen;
import engine.input.Keyboard;
import engine.input.Mouse;

import java.awt.*;
import java.awt.event.KeyEvent;


/*
    Tests the two input classes, Mouse and Keyboard and displays information about the two
 */


public class InputTest extends Screen {

    public InputTest(String title, int width, int height) {
        super(title, width, height);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPaint(Graphics2D g) {
        g.setColor(Color.BLACK);
        int y = 10;
        //Loop through all mouse buttons and display if they're pressed
        for(int i = 0; i < MouseInfo.getNumberOfButtons()+1; i++){
            g.drawString("Button " + i + ": " + (Mouse.isButtonPressed(i) ? "true" : "false"), 10, y);
            y+=10;
        }
        int x = 100;
        y = 10;
        //Loop through all possible keyboard buttons and display if they're pressed
        for(int i = 0; i < 500000; i++){
            String text = KeyEvent.getKeyText(i);
            if(text.contains("Unknown")) continue;
            g.drawString("Key " + text + ": " + (Keyboard.isKeyPressed(i) ? "true" : "false"), x, y);
            y+=10;
            if(y > 450){
                y = 10;
                x+=175;
            }
        }

    }

    @Override
    public void update(long delta) {

    }
    public static void main(String[] args){
        InputTest test = new InputTest("Input Test", 1000,500);
    }
}
