import play.api.http.SecretConfiguration
import play.api.libs.crypto.DefaultCookieSigner

object CookieSignerService {
  private val signer = new DefaultCookieSigner(SecretConfiguration("cookie-secret"))

  def signMessage(message: String, key: Array[Byte]): String = {
    //CWE-327
    //SINK
    signer.sign(message, key)
  }
}
