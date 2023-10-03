public class Hero extends Creature {
    private int gold;
    private int experience; // opit

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        if ((experience % 5) == 0)
            this.agility += 5;
        this.experience = experience;
    }

    public Hero(String name) {
        this.name = name;
        this.forse = 10;
        this.agility = 15;
        this.health = 40;
        this.gold = 10;
        this.experience = 1;
        System.out.println(this.toString());
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public String toString() {
        return name +
                " - здоровье=" + health +
                ", сила=" + forse +
                ", ловкость=" + agility +
                ", золото=" + gold +
                ", опыт=" + experience;
    }
}
