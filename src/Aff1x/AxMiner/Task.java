/*
 * Title : AxMiner
 * Author : Keiran "Affix" Smith
 * License : GNU/GPLv3
 * Copyright : Keiran Smith
 *
 * Description : AxMiner Mines Iron Ore at Varrock West
 */


package Aff1x.AxMiner;

import org.powerbot.script.ClientAccessor;
import org.powerbot.script.ClientContext;


public abstract class Task<C extends ClientContext> extends ClientAccessor<C>
{

    public Task(C ctx) {
        super(ctx);
    }

    public abstract boolean activate();

    public abstract void execute();
}