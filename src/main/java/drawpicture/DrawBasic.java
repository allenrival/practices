package drawpicture;

import java.applet.Applet;
import java.awt.*;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年05月16日 14:52
 */
public class DrawBasic extends Applet {
    public void paint(Graphics g){
        g.drawString("用Graphics写字和画图的基本方法", 20,40);
        g.drawOval(100, 100, 30, 30);
        g.drawOval(200, 100, 40, 25);
        g.drawLine(20, 140, 200,140);
        g.drawRect(20, 160, 50, 80);
        g.drawRoundRect(110, 160, 100, 100, 25, 18);
    }

    public static void main(String[] args) {
        DrawBasic drawBasic = new DrawBasic();
        drawBasic.init();
        drawBasic.start();
        drawBasic.paint(drawBasic.getGraphics());
    }
}
