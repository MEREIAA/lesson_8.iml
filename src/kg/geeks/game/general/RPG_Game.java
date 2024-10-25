package kg.geeks.game.general;

import kg.geeks.game.players.*;

import java.util.Random;

public class RPG_Game {
    public static Random random = new Random();
    private static int roundNumber;
    private static int heroesKilled;

    public static void startGame() {
        Boss boss = new Boss("Black", 1000, 50);
        Warrior warrior1 = new Warrior("Rick", 280, 10);
        Warrior warrior2 = new Warrior("Arthur", 270, 15);
        Magic magic = new Magic("Potter", 260, 10);
        Medic doc = new Medic("Haus", 250, 5, 15);
        Medic assistant = new Medic("Levin", 300, 5, 5);
        Berserk berserk = new Berserk("Valera", 240, 10);
        Spitfire spitfire = new Spitfire("Kratos", 250, 20);

        Hero[] heroes = {warrior1, doc, warrior2, magic, berserk, assistant, spitfire};

        printStatistics(boss, heroes);

        while (!isGameOver(boss, heroes)) {
            playRound(boss, heroes);
        }
    }

    public static void playRound(Boss boss, Hero[] heroes) {
        roundNumber++;
        boss.chooseDefence();
        boss.attack(heroes);

        heroesKilled = 0;

        for (Hero hero : heroes) {
            if (hero.getHealth() <= 0) {
                heroesKilled++;
            }
            if (hero.getHealth() > 0 && boss.getHealth() > 0 && hero.getAbility() != boss.getDefence()) {
                hero.attack(boss);
                hero.applySuperPower(boss, heroes);
            }
        }

        if (heroesKilled > 0) {
            for (Hero hero : heroes) {
                if (hero instanceof Spitfire) {
                    ((Spitfire) hero).dealAggressiveHit(boss);
                }
            }
        }
        printStatistics(boss, heroes);
    }

    private static boolean isGameOver(Boss boss, Hero[] heroes) {
        if (boss.getHealth() <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    private static void printStatistics(Boss boss, Hero[] heroes) {
        System.out.println("--------------------------");
        System.out.println("ROUND " + roundNumber);
        System.out.println(boss);
        for (Hero hero : heroes) {
            System.out.println(hero);
        }
    }
}
