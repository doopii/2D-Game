package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Pantsu extends Entity {

	
	public NPC_Pantsu (GamePanel gp) {
		super(gp);
		
		direction = "left";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		
		up1 = setup("/npc/pantsu_up_1");
		up2 = setup("/npc/pantsu_up_2");
		
		down1 = setup("/npc/pantsu_down_1");
		down2 = setup("/npc/pantsu_down_2");
		
		left0 = setup("/npc/pantsu_left_0");
		left1 = setup("/npc/pantsu_left_1");
		left2 = setup("/npc/pantsu_left_2");
		//left3 = setup("/npc/pantsu_left_3");
		
		right0 = setup("/npc/pantsu_right_0");
		right1 = setup("/npc/pantsu_right_1");
		right2 = setup("/npc/pantsu_right_2");

	}
	
	public void setDialogue() {
		
		dialogues[0] = "Aye... another new face.";
		dialogues[1] = "Treasure, huh?\nHope you brought a shovel. \nNot for gold, but for your grave.";
		dialogues[2] = "I chased it too.\nNow I'm part of the island's decor.";
		dialogues[3] = "Still going? Bold, or just stupid. \nEither way, good luck.";

	}
	
	public void setAction() {
		
		actionLockCounter++;
		
		if (actionLockCounter > 120) {
			
			Random random = new Random();
			int i = random.nextInt(100) + 1; // pick up a number from 1 to 100
			
			if (i <= 25) {
				direction = "up";
			}
			if (i > 25 && i <= 50) {
				direction = "down";
			}
			if (i > 50 && i <= 75) {
				direction = "left";
			}
			if (i > 75 && i <= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
		
	}
	
	public void speak() {
		
		// Do this character specific stuffs
		super.speak();
	}
	

	
}
