/*
 * Title : AxMiner
 * Author : Keiran "Affix" Smith
 * License : GNU/GPLv3
 * Copyright : Keiran Smith
 *
 * Description : AxMiner Mines Iron Ore at Al Kharid
 */

package Aff1x.AxMiner.Tasks;

import Aff1x.AxMiner.Task;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

public class BankExiter extends Task<ClientContext> {

    Area bankArea = new Area(
            new Tile(3274, 3174, 0),
            new Tile(3268, 3174, 0),
            new Tile(3268, 3157, 0),
            new Tile(3274, 3158, 0)
    );

    public BankExiter(ClientContext ctx){

        super(ctx);

    }

    @Override
    public boolean activate(){

        return ctx.backpack.select().count()==0
                &&bankArea.contains(ctx.players.local())
                &&ctx.bank.opened()==true;

    }

    @Override
    public void execute(){

        int closeMethod = (Random.nextInt(1, 8));

        if (closeMethod > 1) {
            Condition.sleep(300);
            ctx.bank.close();
        } else {
            Condition.sleep(300);
            ctx.movement.step(bankArea.getRandomTile());
        }
    }
}