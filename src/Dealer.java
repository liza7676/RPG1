public class Dealer {
    private int gold;
    private int price;

    public Dealer() {
        this.gold = 0;
        this.price = 5;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void purchase(Hero hero, String health) {
        int healthInt;
        try {
            healthInt = Integer.valueOf(health);
        } catch (NumberFormatException e) {
            System.out.println("Моя твоя не понимать!");
            return;
        }
        int cahs = healthInt * price;
        if (hero.getGold() < cahs) {
            System.out.println("В долг не даю!");
        } else {
            hero.setGold(hero.getGold() - cahs);
            hero.setHealth(hero.getHealth() + (healthInt * 10));
            System.out.println("Вот вам наздоровьечки!");
            gold += cahs;
            if (gold > 50)
                price += 1;
        }
    }

    public void hello() {
        System.out.println("Не проходим мимо!");
        System.out.println("1 зелье (10 здоровье) по " + price + " золотых.");
    }
}
