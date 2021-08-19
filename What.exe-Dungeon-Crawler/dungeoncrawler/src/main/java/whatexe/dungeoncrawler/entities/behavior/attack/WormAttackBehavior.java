package whatexe.dungeoncrawler.entities.behavior.attack;

import javafx.scene.paint.Color;
import whatexe.dungeoncrawler.entities.Sprite;
import whatexe.dungeoncrawler.entities.Entity;
import whatexe.dungeoncrawler.entities.behavior.EntityStatistics;
import whatexe.dungeoncrawler.entities.player.Player;
import whatexe.dungeoncrawler.entities.projectiles.PlayerProjectile;
import whatexe.dungeoncrawler.entities.projectiles.Projectile;

import java.util.List;

public class WormAttackBehavior extends PlayerAttackBehavior {
    public WormAttackBehavior(Player owningEntity) {
        super(owningEntity);
    }

    public static EntityStatistics getDefaultProjectileStatistics(Entity owningEntity) {
        return new EntityStatistics(0,
                                    owningEntity.getEntityStatistics().getModifiedAttackDamage(),
                                    1,
                                    2,
                                    1,
                                    1,
                                    1);
    }

    public static Sprite getDefaultDisplayNode() {
        return Sprite.asCircle(7, Color.BLACK);
    }

    public static int getDefaultLifetime() {
        return 300;
    }

    @Override
    public List<? extends Entity> getDefaultAttackEntities() {
        Projectile bullet =
                new PlayerProjectile(getDefaultDisplayNode(),
                                     owningEntity.getOwningRoom(),
                                     attackDirection,
                                     getDefaultLifetime(),
                                     getDefaultProjectileStatistics(owningEntity));
        bullet.setEntityPosition(owningEntity.getDisplayNode().getBoundsInParent().getCenterX()
                                         - bullet.getDisplayNode().getWidth() / 2,
                                 owningEntity.getDisplayNode().getBoundsInParent().getCenterY()
                                         - bullet.getDisplayNode().getHeight() / 2);
        return List.of(bullet);
    }
}
