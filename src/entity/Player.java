package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{
	
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	int standCounter = 0;

	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		this.keyH = keyH;
		
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		
		solidArea = new Rectangle();
		solidArea.x = 14;
		solidArea.y = 20;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 16;
		solidArea.height = 18;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
		
	}
	
	public void getPlayerImage() {
		
		up0 = setup("/player/tama_up_0");
		up1 = setup("/player/tama_up_1");
		up2 = setup("/player/tama_up_2");
		
		down0 = setup("/player/tama_down_0");
		down1 = setup("/player/tama_down_1");
		down2 = setup("/player/tama_down_2");
		
		left0 = setup("/player/tama_left_0");
		left1 = setup("/player/tama_left_1");
		left2 = setup("/player/tama_left_2");
		left3 = setup("/player/tama_left_3");
		
		right0 = setup("/player/tama_right_0");
		right1 = setup("/player/tama_right_1");
		right2 = setup("/player/tama_right_2");
		right3 = setup("/player/tama_right_3");
	}
	
	public void update() {
		if (keyH.upPressed == true || keyH.downPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if (!keyH.lastKeyPressed.equals("")) {
				direction = keyH.lastKeyPressed;
			} 
			
			// Reset walkFrameIndex when switching between vertical and horizontal directions
			if ((direction.equals("left") || direction.equals("right")) && (spriteNum > 3 || spriteNum < 0)) {
			    spriteNum = 0;
			    walkFrameIndex = 0;
			}
			else if ((direction.equals("up") || direction.equals("down")) && spriteNum > 2) {
			    spriteNum = 0;
			    walkFrameIndex = 0;
			}
			
			// CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			// CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			// IF COLLISION IS FALSE, PLAYER CAN MOVE 
			if (collisionOn == false) {
				
				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			spriteCounter++;
			
			if (direction.equals("left") || direction.equals("right")) {
				
				int[] walkSequence = {1, 3, 0, 3, 2, 3, 0};

				if (spriteCounter > 10 / 2) {
				    spriteNum = walkSequence[walkFrameIndex];
				    walkFrameIndex = (walkFrameIndex + 1) % walkSequence.length;
				    spriteCounter = 0;
				}
			}
			else {
				// Force spriteNum = 1 if it was idle
				if (spriteNum == 0) {
					spriteNum = 1;
				}
				
				// player image gets changed every 10 frames
				if (spriteCounter > 10) {
					if (spriteNum == 1) {
						spriteNum = 2;
					} 
					else if(spriteNum == 2) {
						spriteNum = 1;
					}
					spriteCounter = 0;
				}
			}

			
		} else {
			standCounter++;
			
			if (standCounter == 10) {
				spriteNum = 0;
				standCounter = 0;
				walkFrameIndex = 0;
			}
			
		}
		
	}
	
	public void pickUpObject(int i) {
		
		if (i != 999) {
			
		}
	}
	
	public void interactNPC(int i) {
		
		if (i != 999) {
			
			if (gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		
		gp.keyH.enterPressed = false;
	}
	
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if (spriteNum == 0) {
				image = up0;
			}
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 0) {
				image = down0;
			}
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 0) {
				image = left0;
			}
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			if (spriteNum == 3) {
				image = left3;
			}
			break;
		case "right":
			if (spriteNum == 0) {
				image = right0;
			}
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			if (spriteNum == 3) {
				image = right3;
			}
			break;
		}
		
		g2.drawImage(image, screenX, screenY, null);
		// check solidArea for player 
//		g2.setColor(Color.red);
//		g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
		
	}
}
