import java.sql.DriverManager
import org.squeryl.adapters.H2Adapter
import org.squeryl.{Session, SessionFactory}

object SquerylInit {
  def init(): Unit = {
    Class.forName("org.h2.Driver")
    SessionFactory.concreteFactory = Some(() => {
      val c = DriverManager.getConnection("jdbc:h2:mem:remora;DB_CLOSE_DELAY=-1")
      Session.create(c, new H2Adapter)
    })
  }
}
