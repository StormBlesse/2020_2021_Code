package whatexe.dungeoncrawler.entities.behavior.transformations;

import whatexe.dungeoncrawler.entities.Entity;
import whatexe.dungeoncrawler.entities.items.Currency;
import whatexe.dungeoncrawler.entities.motionsupport.Vector;

import java.util.List;

public class DropCurrencyOnDeathTransformation extends BehaviorTransformation<Entity> {

    private final double dropChance;

    public DropCurrencyOnDeathTransformation(Entity owningEntity) {
        this(owningEntity, 0.5);
    }

    public DropCurrencyOnDeathTransformation(Entity owningEntity, double dropChance) {
        super(owningEntity, -1);
        this.dropChance = dropChance;
    }

    @Override
    public void onAdd() {

    }

    @Override
    public void onRemove() {

    }

    @Override
    public List<? extends Entity> transformAttack(List<? extends Entity> inputEntities) {
        return inputEntities;
    }

    @Override
    public Vector transformMovement(Vector inputMovement) {
        return inputMovement;
    }

    @Override
    public void postDeath() {
        if (Math.random() < dropChance) {
            Currency newCurrency = new Currency(owningEntity.getOwningRoom());
            owningEntity.getOwningRoom().addEntity(newCurrency);

            newCurrency.setEntityPosition(owningEntity.getDisplayNode().getTranslateX(),
                                          owningEntity.getDisplayNode().getTranslateY());
        }
    }

    @Override
    public void postHandleEntityOverlap(Entity otherEntity) {

    }

    @Override
    public void postHandleEntityCollision(Entity otherEntity) {

    }

    @Override
    public void postHandleBoundaryCollision() {

    }
}
