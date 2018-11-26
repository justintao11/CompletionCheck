package com.completionCheck.project;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class PictogramPanel extends JPanel{
    private String filename = "test0.java";
    JPanel dPanel;
    private static String infile;
    private static final int BLOCK_HEIGHT = 10;
    private static final int BLOCK_WIDTH = 9;
    private static final int XOFFSET = 30;
    private Color textColor = Color.BLACK;
    private Color todoColor = Color.RED;
    private Color codeColor = new Color(44, 102, 230, 255);
    private Color indentColor = Color.LIGHT_GRAY;


    public PictogramPanel() {
        setPreferredSize(new Dimension(800,1500));
        try {
            infile = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Didn't find file");
            System.exit(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        int row = 0;
        int col = 0;
        int index = 0;
        String [] todocheck = {"t","o","d","o"};
        // blocked tells if there has been a non-space character in this line already
        // if blocked = true, then any space encountered must not be a new indent
        boolean blocked = false;

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(new Font("TimesRoman", Font.TRUETYPE_FONT, 12));

        String infilecopy = infile;
        // splits string into array of single character strings
        String[] charArray = infilecopy.split("(?!^)");
        for (String s: charArray) {
            // print line number
            if (col == 0){
                String rowNumber = Integer.toString(row);
                drawText(g2, textColor, rowNumber, (BLOCK_WIDTH / 2) * col,BLOCK_HEIGHT * row);
                col++;
            }

            // new line resets
            if (s.equals("\n")) {
                // System.out.println("new line");
                row++;
                col = 0;
                blocked = false;
            }

            // ignore carriage returns
            else if (s.equals("\r")){
                // do nothing
            }

            // only prints leading spaces / indents (white blocks)
            else if ((s.contains(" ")) && !blocked){
                drawBlock(g2, indentColor, (BLOCK_WIDTH / 2) * col, BLOCK_HEIGHT * row);
                col++;
            }

            // checks for todos (green blocks)
            else if (s.equalsIgnoreCase("t")){
                boolean matched = true;
                for (int i = 1; i < 4; i++){
                    if (!charArray[index+i].toLowerCase().contains(todocheck[i])) {
                        matched = false;
                    }
                }
                if (matched) {
                    // prints green blocks for todo
                    for (int j = 0; j < 4; j++) {
                        drawBlock(g2, todoColor, (BLOCK_WIDTH / 2) * col, BLOCK_HEIGHT * row);
                        col++;
                    }
                }
            }

            // everything else is code (red blocks)
            else {
                // System.out.println(s);
                drawBlock(g2, codeColor, (BLOCK_WIDTH / 2) * col, BLOCK_HEIGHT  * row);
                blocked = true;
                col++;
            }
            index++;
        }

    }

    // TODO: this actually creates an extra box on end of each line, no idea why
    public void drawBlock (Graphics2D g2, Color color, int x, int y) {
        g2.setColor(color);
        g2.fillRect(x + XOFFSET, y, BLOCK_WIDTH, BLOCK_HEIGHT);
    }

    // TODO: not sure why last line number isnt drawn
    public void drawText (Graphics2D g2, Color color, String s, int x, int y) {
        g2.setColor(color);
        g2.drawString(s, x, y);
    }

    // TODO: save not working yet
    public void save(String filename)
    {
        BufferedImage bImg = new BufferedImage(dPanel.getWidth(), dPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        dPanel.paintAll(cg);
        try {
            if (ImageIO.write(bImg, "png", new File("./" + filename + ".png")))
            {
                System.out.println("-- saved");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
