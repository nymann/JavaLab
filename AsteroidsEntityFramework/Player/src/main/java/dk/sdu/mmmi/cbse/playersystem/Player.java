package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;

public class Player extends Entity {
    private float cooldown = 0;

    public Player() {
        this.hexColor = "#8FEB34";
    }

    public boolean canShoot(float gameTime) {
        cooldown -= gameTime;
        return cooldown <= 0;
    }

    public void resetCooldown() {
        this.cooldown = 0.5f;
    }


    @Override
    public EntityType getEntityType() {
        return EntityType.SHIP;
    }
}
