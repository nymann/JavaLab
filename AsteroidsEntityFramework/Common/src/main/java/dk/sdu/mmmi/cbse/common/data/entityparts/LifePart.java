/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class LifePart implements EntityPart {

    private int life;
    private int health;
    private int maxHealth;
    private boolean lostLife = false;
    private Entity attacker;

    public LifePart(int life, int health) {
        this.life = life;
        this.health = health;
        this.maxHealth = health;
        this.attacker = null;
    }

    public int getHealth() {
        return health;
    }

    public int getLife() {
        return life;
    }


    public void setAttacker(Entity attacker) {
        this.attacker = attacker;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        if (this.attacker == null) {
            return;
        }
        this.health -= calculateDamageTaken(entity, this.attacker);
        this.attacker = null;
        if (this.health <= 0) {
            this.life--;
            this.lostLife = true;
            this.health = this.maxHealth;
        }
    }

    private int calculateDamageTaken(Entity entityThatLifePartBelongsTo, Entity attacker) {
        if (entityThatLifePartBelongsTo.getEntityType() == EntityType.ASTEROID) {
            return 100;
        }
        switch (attacker.getEntityType()) {
            case SHIP:
            case ASTEROID:
                return 100;
            case BULLET:
                return 5;
            case UNKNOWN:
            default:
                return 0;
        }
    }

    public boolean lostLifeLastCycle() {
        return lostLife;
    }

    public void lostLifeProcessed() {
        this.lostLife = false;
    }
}
