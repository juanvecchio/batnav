package batnav.ui.screens;

import batnav.instance.Game;
import batnav.ui.components.GameButton;
import batnav.ui.components.GamePanel;
import batnav.utils.Colour;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsScreen extends JFrame implements ActionListener
{
   private CardLayout cl;
   private GameButton logoutButton;
   private JPanel mainPanel, settingsPanel;

   public SettingsScreen()
   {
      this.cl = new CardLayout();
      this.setSize(500, 500);
      this.setLocationRelativeTo(null);

      this.settingsPanel = new JPanel();
      this.mainPanel = new GamePanel();
      this.mainPanel.setLayout(cl);
      this.add(mainPanel);
      mainPanel.add(settingsPanel, "1");

      cl.show(mainPanel, "1");
      settingsPanel.setLayout(null);
      this.settingsPanel.setOpaque(false);
      this.settingsPanel.setBackground(Colour.Transparent);
      this.logoutButton = new GameButton("Logout");
      logoutButton.setCancel(true);
      logoutButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Colour.Black,Colour.Black));
      logoutButton.setBounds(100, 30, 300, 50);
      logoutButton.setFont(new Font("Display Medium", Font.PLAIN, 18));
      logoutButton.addActionListener(e -> {
         Game.getInstance().getSessionManager().setAndSaveSessionId(null);
         Game.getInstance().getConnection().disconnect();
      });

      this.settingsPanel.add(this.logoutButton);

      this.setResizable(false);
      this.setAlwaysOnTop(true);
      this.setVisible(true);
   }

   public static void main(String[] args)
   {
      new SettingsScreen();
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {

   }
}
