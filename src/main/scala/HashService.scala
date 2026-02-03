import com.roundeights.hasher.Algo

object HashService {
  def hashToken(token: String): String = {
    //CWE-328
    //SINK
    Algo.md5(token).hex
  }
}
