package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
		public boolean upPressed, downPressed, leftPressed, rightPressed;
		public String lastKeyPressed = "";
		// DEBUG
		public boolean checkDrawTime = false; 

	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = true;
			lastKeyPressed = "up";
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
			lastKeyPressed = "down";
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
			lastKeyPressed = "left";
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
			lastKeyPressed = "right";
		}
		
		// DEBUG
		if(code == KeyEvent.VK_T) {
			if (checkDrawTime == false) {
				checkDrawTime = true;
			} 
			else if (checkDrawTime == true){
				checkDrawTime = false;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if(code == KeyEvent.VK_W) {
			upPressed = false;
			if(lastKeyPressed.equals("up")) {
				updateLastKeyHeld();
			}
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
			if(lastKeyPressed.equals("down")) {
				updateLastKeyHeld();
			}
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
			if(lastKeyPressed.equals("left")) {
				updateLastKeyHeld();
			}
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
			if(lastKeyPressed.equals("right")) {
				updateLastKeyHeld();
			}
		}
	}

	public void updateLastKeyHeld() {
		// Priority fallback order (reverse of typical key press priority)
		if (upPressed) {
			lastKeyPressed = "up";
		}
		else if (downPressed) {
			lastKeyPressed = "down";
		}
		else if (leftPressed) {
			lastKeyPressed = "left";
		}
		else if (rightPressed) {
			lastKeyPressed = "right";
		}
		else {
			lastKeyPressed = "";
		}
	}

	
}
