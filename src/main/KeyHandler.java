package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
		GamePanel gp;
		public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
		public String lastKeyPressed = "";
		// DEBUG
		public boolean checkDrawTime = false; 
		
		public KeyHandler(GamePanel gp) {
			this.gp = gp;
		}

		@Override
		public void keyTyped(KeyEvent e) {		
		}
	
		@Override
		public void keyPressed(KeyEvent e) {
			
			int code = e.getKeyCode();
			
			// TITLE STATE
			if (gp.gameState == gp.titleState) {
				
				if (gp.ui.titleScreenState == 0) {
					// MAIN MENU SELECTION 
					if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
						gp.playSE(5);
						gp.ui.commandNum--;
						if (gp.ui.commandNum < 0) {
							gp.ui.commandNum = 2;
						}
					}
					if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
						gp.playSE(5);
						gp.ui.commandNum++;
						if (gp.ui.commandNum > 2) {
							gp.ui.commandNum = 0;
						}
					}
					if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
						gp.playSE(6);
						if (gp.ui.commandNum == 0) {
							gp.ui.titleScreenState = 1;
						}
						if (gp.ui.commandNum == 1) {
							// add later
						}
						if (gp.ui.commandNum == 2) {
							System.exit(0);
						}
					}
				} 
				
				else if (gp.ui.titleScreenState == 1) {
					// CHARACTER CLASS SELECTION 
					if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
						gp.playSE(5);
						gp.ui.commandNum--;
						if (gp.ui.commandNum < 0) {
							gp.ui.commandNum = 3;
						}
					}
					if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
						gp.playSE(5);
						gp.ui.commandNum++;
						if (gp.ui.commandNum > 3) {
							gp.ui.commandNum = 0;
						}
					}
					if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
						gp.playSE(6);
						if (gp.ui.commandNum == 0) {
							System.out.println("play like a FIGHTER");
							gp.gameState = gp.playState;
							gp.playMusic(0);
						}
						if (gp.ui.commandNum == 1) {
							System.out.println("play like a THIEF");
							gp.gameState = gp.playState;
							gp.playMusic(0);
							
						}
						if (gp.ui.commandNum == 2) {
							System.out.println("play like a SORCERER");
							gp.gameState = gp.playState;
							gp.playMusic(0);
							
						}
						if (gp.ui.commandNum == 3) {
							gp.ui.titleScreenState = 0;
						}
					}
					if (code == KeyEvent.VK_ESCAPE) {
						gp.playSE(5);
						gp.ui.titleScreenState = 0;
					}
				} 
				
				

			}
			
			// PLAY STATE
			if (gp.gameState == gp.playState) {
				
				// player movement WSAD
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
				
				// pause game P
				if(code == KeyEvent.VK_P) {
					gp.playSE(5);
					gp.gameState = gp.pauseState;
				}
				if(code == KeyEvent.VK_ENTER) {
					enterPressed = true;
				}
				
				// DEBUG T
				if(code == KeyEvent.VK_T) {
					if (checkDrawTime == false) {
						checkDrawTime = true;
					} 
					else if (checkDrawTime == true){
						checkDrawTime = false;
					}
				}
			}
			
			// PAUSE STATE
			else if (gp.gameState == gp.pauseState) {
				if(code == KeyEvent.VK_P) {
					gp.gameState = gp.playState;
				}
			}
			
			// DIALOGUE STATE 
			else if (gp.gameState == gp.dialogueState) {
				// TRIGGER DIALOGUE
				if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
					gp.gameState = gp.playState;
				}
				// DEBUG T
				if(code == KeyEvent.VK_T) {
					if (checkDrawTime == false) {
						checkDrawTime = true;
					} 
					else if (checkDrawTime == true){
						checkDrawTime = false;
					}
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
