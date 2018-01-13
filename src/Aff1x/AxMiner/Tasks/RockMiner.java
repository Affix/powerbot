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
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.ClientContext;

public class RockMiner extends Task<ClientContext> {

    Area mineArea = new Area(
            new Tile(3306, 3318, 0),
            new Tile(3295, 3318, 0),
            new Tile(3294, 3283, 0),
            new Tile(3305, 3286, 0)
    );

    public RockMiner(ClientContext ctx)
    {
        super (ctx);
    }

    @Override
    public boolean activate()
    {
        return ctx.backpack.select().count() < 28
                && !ctx.objects.select().name("Iron ore rocks").isEmpty()
                && ctx.players.local().animation() == -1;
    }

    @Override
    public void execute()
    {
        GameObject rock = ctx.objects.select().name("Iron ore rocks").nearest().poll();
        /* Add Animation Check to Prevent Spam Clicking */
        if(rock.inViewport() && ctx.players.local().animation() == -1 && !ctx.players.local().inMotion()
                && mineArea.contains(ctx.players.local())) {
            rock.interact("Mine");
        } else {
            ctx.camera.turnTo(rock);
            if(!ctx.players.local().inMotion())
                ctx.movement.step(rock);
        }
    }
}
