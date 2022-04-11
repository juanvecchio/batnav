package batnav.instance;

import batnav.config.ConfigManager;
import batnav.notifications.NotificationManager;
import batnav.online.session.SessionManager;
import batnav.online.socket.Connection;
import batnav.utils.Logger;
import batnav.utils.RestUtils;
import batnav.utils.Sambayon;

public class Game
{
   private final Sambayon sambayon;
   private final ConfigManager configManager;
   private final NotificationManager notificationManager;
   private RestUtils restUtils;
   private SessionManager sessionManager;
   private Connection connection;

   /**
    * Damas, a checkers game.
    * Made as a final project for Programming class.
    *
    * @author Gerardo Wacker
    * @author Juan Ignacio Vecchio
    */
   public Game()
   {
      this.notificationManager = new NotificationManager();
      this.sambayon = new Sambayon(this.notificationManager);
      this.configManager = new ConfigManager();
   }

   /**
    * Launches the game.
    */
   public void launch() throws Exception
   {
      // Verify if server is accesible
      if (this.sambayon.isAccesible())
      {
         Logger.log("Se pudo establecer una conexión con Sambayón.");

         // Create handlers.
         this.restUtils = new RestUtils(this.sambayon, this.notificationManager);
         this.sessionManager = new SessionManager(this.restUtils, this.configManager, this.notificationManager);
         this.connection = new Connection(this.sambayon, this.sessionManager, notificationManager);

         // Load session.
         this.sessionManager.loadSession();

         // Connect to real-time server.
         this.connection.connect(this.sessionManager.getSessionId());
      }
   }
}