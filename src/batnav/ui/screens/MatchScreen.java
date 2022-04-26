package batnav.ui.screens;

import batnav.instance.Game;
import batnav.online.match.Bomb;
import batnav.online.match.Match;
import batnav.online.session.User;
import batnav.ui.boards.OpponentBoard;
import batnav.ui.boards.PlayerBoard;
import batnav.utils.Colour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MatchScreen extends JFrame implements ActionListener
{
   private final Match match;

   private ShipSelectionScreen shipSelectionScreen;

   private OpponentBoard opponentBoard;
   private PlayerBoard playerBoard;

   public MatchScreen(final Match match)
   {
      this.match = match;

      this.opponentBoard = new OpponentBoard(this.match);
      this.playerBoard = new PlayerBoard(this.match);

      this.opponentBoard.setPreferredSize(new Dimension(380, 390));
      this.playerBoard.setPreferredSize(new Dimension(380, 390));

      this.setSize(400, 820);
      this.setLayout(new BorderLayout());
      this.setResizable(false);

      JPanel divisionPanel = new JPanel();
      divisionPanel.setSize(new Dimension(400, 20));
      divisionPanel.setBackground(Colour.Gray);

      this.add(opponentBoard, BorderLayout.SOUTH);
      this.add(divisionPanel, BorderLayout.CENTER);
      this.add(playerBoard, BorderLayout.NORTH);

      try
      {
         if (this.match.getPlayerShips().size() <= 0)
            this.shipSelectionScreen = new ShipSelectionScreen();
      } catch (IOException e)
      {
         e.printStackTrace();
      }

      this.setVisible(true);
   }

   public static void main(String[] args)
   {
      new MatchScreen(new Match("asdfghjkl", new User("tu vieja", "AR", 0, 0, false)));
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {

   }
}
