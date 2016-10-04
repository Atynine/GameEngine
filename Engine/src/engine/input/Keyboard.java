package engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/*
@author Rylan Hoss
@description Handles keyboard input for games
 */

public class Keyboard implements KeyListener {
    private static HashMap<Integer, Boolean> keys = new HashMap<>();
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        keys.put(e.getKeyCode(),true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys.remove(e.getKeyCode());
    }

    public static boolean isKeyPressed(int i){
        return keys.getOrDefault(i,false);
    }
}
