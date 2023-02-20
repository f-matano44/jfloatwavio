package jp.f_matano44.jfloatwavio;

import java.awt.*;
import javax.swing.*;

public class DrawSignal extends JFrame
{
    public DrawSignal(String windowTitle, double[]... signal)
    {
        final int 
            defaultWidth = 600,
            defaultHeight = 40 + 160 * signal.length;
        // window setting
        setTitle(windowTitle);
        setBackground(Color.WHITE);
        setSize(defaultWidth, defaultHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add graph panel
        getContentPane().add(this.new PanelClass(signal));
        // display panel
        setVisible(true);
    }


    private class PanelClass extends JPanel
    {
        private double[][] x;
        public PanelClass(final double[][] signal){ this.x = signal; }

        @Override
        public void paint(final Graphics g)
        {
            // canvas size
            final int frameWidth = 10;
            final Dimension d = getSize();
            final int 
                windowWidth = (int)d.getWidth(),
                windowHeight = (int)d.getHeight(),
                minWindowSize = frameWidth + frameWidth*x.length,
                canvasWidth = (int)d.getWidth() - frameWidth * 2,
                canvasHeight = (((int)d.getHeight() - frameWidth) / x.length) - frameWidth;

            // clear frame
            g.clearRect(0, 0, windowWidth, windowHeight);

            // if window is too narrow, display nothing.
            if(windowWidth < minWindowSize || windowHeight < minWindowSize)
                return;

            // draw panel
            for(int i=0; i<x.length; i++)
            {
                final int xAxis = 0, yAxis=1, startPoint=0, endPoint=1;
                final int
                    xOffset = frameWidth,
                    yOffset = frameWidth + canvasHeight/2 + ((frameWidth+canvasHeight)*i);
                int[][] pos = new int[2][2];
                    pos[xAxis][endPoint] = xOffset;
                    pos[yAxis][endPoint] = yOffset;

                // draw background
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(
                    frameWidth, frameWidth + (canvasHeight + frameWidth) * i,
                    canvasWidth, canvasHeight
                );

                // draw signal
                g.setColor(Color.BLACK);
                for(int j=0; j<x[i].length; j++)
                {
                    final double AMP_MAX = (canvasHeight - (frameWidth*2)) / 2;

                    pos[xAxis][startPoint] = pos[xAxis][endPoint];
                    pos[yAxis][startPoint] = pos[yAxis][endPoint];
                    pos[xAxis][endPoint] =
                        xOffset + (int)(canvasWidth * ((double)j / (x[i].length-1)));
                    pos[yAxis][endPoint] =
                        yOffset + (int)((-1) * x[i][j] * AMP_MAX);

                    if(0 < AMP_MAX)
                    {
                        g.drawLine(
                            pos[xAxis][startPoint], pos[yAxis][startPoint],
                            pos[xAxis][endPoint], pos[yAxis][endPoint]
                        );
                    }
                }
            }
        }
    }
}
