package engine.input;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
@author Rylan Hoss
@description Handles mouse input for games
 */

public class Mouse implements MouseListener {

    private static boolean[] buttons = new boolean[MouseInfo.getNumberOfButtons()+1];

    public static boolean isButtonPressed(int button){
        if(button > buttons.length) return false;
        return buttons[button];
    }



    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) { }


}
