package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Door extends SuperObject{
	GamePanel gp;

	public OBJ_Door(GamePanel gp) {
		
		this.gp = gp;
		
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			uTool.scaledImage(image, gp.tileSize, gp.tileSize);	
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;
	}
}


