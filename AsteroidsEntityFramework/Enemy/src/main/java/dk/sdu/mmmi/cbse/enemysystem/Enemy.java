package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;

public class Enemy extends Entity {

    public Enemy() {
        this.hexColor = "#eb4034";
    }
    private float cooldown = 0;

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
