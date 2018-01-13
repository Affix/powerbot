/*
 * Title : AxMiner
 * Author : Keiran "Affix" Smith
 * License : GNU/GPLv3
 * Copyright : Keiran Smith
 *
 * Description : AxMiner Mines Iron Ore at Al Kharid
 */


package Aff1x.AxMiner;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;

import Aff1x.AxMiner.Tasks.*;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Script.Manifest(name="AxMiner",
        properties = "author=Aff1x; topic=1322609; client=6;",
        description="Mines and Banks Iron Ore at Al Kharid")

public class AxMiner extends PollingScript<ClientContext> implements MessageListener, PaintListener {

    private List<Task> taskList = new ArrayList<Task>();
    private int numOre = 0;
    public static long startTime;

    @Override
    public void start() {

        taskList.addAll(Arrays.asList(
                new BankWalker(ctx),
                new BankOpener(ctx),
                new OreDepositor(ctx),
                new BankExiter(ctx),
                new MineWalker(ctx),
                new RockMiner(ctx)
        ));

        startTime = System.currentTimeMillis();
    }

    @Override
    public void poll() {
        for (Task task: taskList)
        {
            if(task.activate())
            {
                task.execute();
            }
        }
    }

    @Override
    public void messaged(MessageEvent e) {
        String msg = e.text().toLowerCase();
        if (msg.contains("you manage to mine some iron")) {
            numOre++;
        }
    }

    @Override
    public void repaint(Graphics g) {

        g.setColor(new Color(90, 12, 8,180));
        g.fillRect(9, 26, 150, 20);
        g.fillRect(9, 27, 150, 93);
        g.setColor(Color.BLACK);
        g.drawRect(8, 25, 151, 21);
        g.drawRect(8, 25, 151, 93);

        g.setColor(Color.WHITE);
        g.drawString("AxMiner!", 12, 41);

        g.setColor(Color.WHITE);

        g.drawString("Ore Mined: " + numOre, 12, 66);
        g.drawString("Ore/Hour: " + perHour(numOre), 12, 82);
        g.drawString("Runtime: " + runTime(startTime), 12, 98);

        int mX = ctx.input.getLocation().x;
        int mY = ctx.input.getLocation().y;

        int pX[] = {mX, mX + 10, mX + 5, mX + 9, mX + 7, mX + 3, mX, mX};
        int pY[] = {mY, mY + 8, mY + 8, mY + 14, mY + 16, mY + 9, mY + 13, mY};

        g.setColor(Color.WHITE);
        g.fillPolygon(pX,pY,8);
        g.setColor(Color.BLACK);
        g.drawPolygon(pX,pY,8);


    }

    public String runTime(long i) {
        DecimalFormat nf = new DecimalFormat("00");
        long millis = System.currentTimeMillis() - i;
        long hours = millis / (1000 * 60 * 60);
        millis -= hours * (1000 * 60 * 60);
        long minutes = millis / (1000 * 60);
        millis -= minutes * (1000 * 60);
        long seconds = millis / 1000;
        return nf.format(hours) + ":" + nf.format(minutes) + ":" + nf.format(seconds);
    }

    public String perHour(int gained) {
        return formatNumber((int) ((gained) * 3600000D / (System.currentTimeMillis() - startTime)));
    }

    public String formatNumber(int start) {
        DecimalFormat nf = new DecimalFormat("0.0");
        double i = start;
        if(i >= 1000000) {
            return nf.format((i / 1000000)) + "M";
        }
        if(i >=  1000) {
            return nf.format((i / 1000)) + "K";
        }
        return ""+start;
    }


}
