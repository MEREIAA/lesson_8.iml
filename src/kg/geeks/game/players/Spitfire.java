package kg.geeks.game.players;

import static kg.geeks.game.players.SuperAbility.FURY_STRIKE;

public class Spitfire extends Hero {

    public Spitfire(String name, int health, int damage) {
        super(name, health, damage, FURY_STRIKE);
    }

    public void dealAggressiveHit(Boss boss) {
        boss.setHealth(boss.getHealth() - 80);
        System.out.println(this.getName()+ "cтал агрессивным и наносит" + FURY_STRIKE);
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {}
}
