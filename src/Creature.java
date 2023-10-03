import java.util.Random;

public class Creature implements Attack {
    String name;
    int health; // zdorovye
    int agility; // lovkost
    int forse; // sila

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    static int luck() {
        Random rand = new Random();
        int randInt = rand.nextInt(100) + 1;
        if (randInt > 90)
            return 2;
        else
            return 1;
    }

    @Override
    public String toString() {
        return name +
                " - здоровье=" + health +
                ", сила=" + forse +
                ", ловкость=" + agility;
    }

    @Override
    public boolean Attack(Creature creature) {
        Random rand = new Random();
        int curForse = forse;
        int randInt = rand.nextInt(100) + 1;
        if (agility * 3 > randInt) {
            curForse = 0;
        }
        curForse *= luck();
        System.out.println(name + " атакует " + creature.name + " силой " + curForse);
        creature.setHealth(creature.getHealth() - curForse);
        if (creature.getHealth() <= 0)
            return true;//pobeda
        return false;
    }
}
