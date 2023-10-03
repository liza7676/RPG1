import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;
/*
* Проект: Role Playing Game
* Игра героя против монстров и скилетов.
* Создайте своего героя и вступайте в бой с нечестью.
* За каждую победу вы поличите 10 золотых и один опыт,
* за каждые 5 единиц опыта ваша ловкость будет увеличена на 5 пунктов
* За золото можно купить зелье востанавливающее здоровье у торговца,
* но со временем зелье дорожает!
* Удачи в бою!!!
*/

public class Main {
    private static Creature hero;
    private static Dealer dealer;

    public static void main(String[] args) {
        System.out.println("Введите имя совего героя");
        Scanner in = new Scanner(System.in);
        String name = in.next();
        hero = new Hero(name);
        dealer = new Dealer();
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1 - К торговцу    2 - В тёмный лес    3 - На выход");
        String action;
        while (true) {
            action = in.next();
            switch (action) {
                case "1":
                    purchase();
                    break;
                case "2":
                    try {
                        Attack();
                    } catch (InterruptedException e) {
                        System.out.println("Программа зависла и будет остановлена!");
                        exit(0);
                    }
                    break;
                case "3":
                    System.out.println("Пока!");
                    return;
            }
            System.out.println("Куда вы хотите пойти?");
            System.out.println("1 - К торговцу    2 - В тёмный лес    3 - На выход");
        }
    }

    static void purchase() {
        while (true) {
            dealer.hello();
            System.out.println("Укажи сколько покупаешь зелья?");
            Scanner in = new Scanner(System.in);
            String health = in.next();
            dealer.purchase((Hero) hero, health);
            System.out.println(hero.toString());
            System.out.println("1 - Продолжить торговлю    2 - Вернуться в город");

            while (true) {
                String action = in.next();
                if (action.equals("1")) break;
                else if (action.equals("2")) return;
                System.out.println("1 - Продолжить торговлю    2 - Вернуться в город");
            }
        }
    }

    static void Attack() throws InterruptedException {
        Hero curhero = (Hero) hero;
        while (true) {
            Random rand = new Random();
            int randInt = rand.nextInt(2) + 1;
            Creature monstr;
            if (randInt == 1) monstr = new Goblin();
            else monstr = new Skeleton();
            System.out.println("Бой!!!");

            Thread thread = new Thread() {
                public void run() {
                    while (!isInterrupted()) {
                        if (monstr.Attack(curhero)) {
                            System.out.println(curhero.name + " ПОВЕРЖЕН!!!");
                            exit(0);
                        }
                        System.out.println(hero.toString());
                        if (curhero.Attack(monstr)) {
                            System.out.println(monstr.name + " ПОВЕРЖЕН!!!");
                            curhero.setGold(curhero.getGold() + 10);
                            curhero.setExperience(curhero.getExperience() + 1);
                            System.out.println(curhero.toString());
                            break;
                        }
                        System.out.println(monstr.toString());
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println("Программа зависла и будет остановлена!");
                        exit(0);
                    }
                }
            };
            thread.start();
            thread.join(20000);
            thread.interrupt();
            thread.join();

            System.out.println("1 - Искать еще монстров    2 - Вернуться в город");
            while (true) {
                Scanner in = new Scanner(System.in);
                String action = in.next();
                if (action.equals("1")) break;
                else if (action.equals("2")) return;
                System.out.println("1 - Искать еще монстров    2 - Вернуться в город");
            }
        }
    }
}