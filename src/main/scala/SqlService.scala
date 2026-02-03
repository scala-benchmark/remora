import org.squeryl.{Session, SessionFactory}

object SqlService {
  def executeQuery(taintedSql: String): String = {
    val session = SessionFactory.newSession
    session.bindToCurrentThread
    try {
      val stmt = Session.currentSession.connection.createStatement()
      try {
        //CWE-89
        //SINK
        val rs = stmt.executeQuery(taintedSql)
        try {
          val md = rs.getMetaData
          val n = md.getColumnCount
          if (rs.next()) {
            (1 to n).map(j => Option(rs.getObject(j)).fold("")(_.toString)).mkString(",")
          } else ""
        } finally {
          rs.close()
        }
      } finally {
        stmt.close()
      }
    } finally {
      session.unbindFromCurrentThread
      session.cleanup
    }
  }
}
