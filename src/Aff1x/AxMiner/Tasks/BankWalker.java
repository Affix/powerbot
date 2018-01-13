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
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.Area;
import org.powerbot.script.Tile;



public class BankWalker extends Task<ClientContext> {

    Tile[] toMineTile = new Tile[] {
            new Tile(3272, 3165, 0),
            new Tile(3276, 3168, 0),
            new Tile(3277, 3174, 0),
            new Tile(3280, 3180, 0),
            new Tile(3280, 3188, 0),
            new Tile(3282, 3194, 0),
            new Tile(3282, 3201, 0),
            new Tile(3281, 3206, 0),
            new Tile(3282, 3211, 0),
            new Tile(3277, 3216, 0),
            new Tile(3278, 3223, 0),
            new Tile(3281, 3230, 0),
            new Tile(3285, 3234, 0),
            new Tile(3288, 3240, 0),
            new Tile(3292, 3243, 0),
            new Tile(3294, 3249, 0),
            new Tile(3295, 3255, 0),
            new Tile(3297, 3260, 0),
            new Tile(3298, 3266, 0),
            new Tile(3299, 3272, 0),
            new Tile(3299, 3278, 0),
            new Tile(3302, 3282, 0),
            new Tile(3303, 3288, 0),
            new Tile(3301, 3294, 0),
            new Tile(3301, 3300, 0),
            new Tile(3301, 3305, 0)
    };

    Area bankArea = new Area(
            new Tile(3268, 3171, 0),
            new Tile(3272, 3171, 0),
            new Tile(3273, 3166, 0),
            new Tile(3268, 3164, 0)
    );

    public BankWalker(ClientContext ctx){

        super(ctx);

    }

    @Override
    public boolean activate(){

        return ctx.backpack.select().count()==28
                &&!bankArea.contains(ctx.players.local())
                &&bankArea.getClosestTo(ctx.players.local()).distanceTo(ctx.players.local())>12;
    }

    @Override
    public void execute(){
        if(!ctx.players.local().inMotion())
            ctx.movement.newTilePath(toMineTile).reverse().randomize(1, 1).traverse();
    }
}
