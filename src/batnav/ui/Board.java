package batnav.ui;

import batnav.online.match.Match;
import batnav.online.match.Ship;
import batnav.utils.Colour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JButton
{

   private final Match match;

   public Board(final Match match)
   {
      this.match = match;

      // As the Board is a button, disabling the default values for styling is necessary.
      super.setBorderPainted(false);
      super.setFocusPainted(false);
      super.setContentAreaFilled(false);

      // Also, we set the same background as the board's.
      super.setBackground(Colour.AliceBlue);
   }

   @Override
   public void paint(Graphics g)
   {
      super.paint(g);

      // Set board position and size.
      final int tileSize = 50;
      final int boardSize = tileSize * 10;
      final int paddingX = (this.getWidth() - boardSize) / 2;
      final int paddingY = (this.getHeight() - boardSize) / 2;

      // Draw the main board background.
      g.setColor(Colour.AliceBlue);
      g.fillRect(paddingX, paddingY, boardSize, boardSize);

      // Draw each line.
      g.setColor(Colour.Black);
      for (int i = 0; i < 11; i++)
      {
         g.drawLine(paddingX + i * tileSize, paddingY, paddingX + i * tileSize, paddingY + boardSize);
         g.drawLine(paddingX, paddingY + i * tileSize, paddingX + boardSize, paddingY + i * tileSize);
      }

      for (Ship ship : this.match.getPlayerShips())
      {
         this.drawShip(g, ship);
      }
   }

   public void update()
   {
      this.repaint();
   }

   public void drawShip(Graphics g, final Ship ship)
   {
      final int tileSize = 50;
      final int boardSize = tileSize * 10;
      final int paddingX = (this.getWidth() - boardSize) / 2;
      final int paddingY = (this.getHeight() - boardSize) / 2;

      final int x = paddingX + ship.getX() * tileSize;
      final int y = paddingY + ship.getY() * tileSize;
      final int width = ship.getSize() * tileSize;

      g.setColor(Colour.Tomato);
      if(ship.isVertical())
         g.fillRect(x, y, tileSize, width);
      else
         g.fillRect(x, y, width, tileSize);
   }

   public int[] handleClick(final Point point)
   {
      // Set board position and size.
      final int tileSize = 50;
      final int boardSize = tileSize * 10;
      final int paddingX = (this.getWidth() - boardSize) / 2;
      final int paddingY = (this.getHeight() - boardSize) / 2;

      // Check if the click was executed inside the board.
      if (point.x > paddingX && point.y > paddingY && point.x < paddingX + boardSize && point.y < paddingY + boardSize)
      {
         // Perform calculations to get coordinates and return the values in an array.
         return new int[]{(point.x - paddingX) / 50, (point.y - paddingY) / 50};
      }
      else
         return null;
   }
}