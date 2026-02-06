import akka.stream.Materializer
import com.typesafe.config.ConfigFactory
import play.api.libs.ws.ahc.{AhcWSClient, AhcWSClientConfigFactory}

object InsecureWsClientFactory {
  def createWsClient()(implicit materializer: Materializer): play.api.libs.ws.WSClient = {
    val looseSsl = """
      play.ws.ssl.loose.acceptAnyCertificate = true
      play.ws.ssl.loose.disableHostnameVerification = true
    """
    val config = ConfigFactory.parseString(looseSsl).withFallback(ConfigFactory.load())
    val ahcConfig = AhcWSClientConfigFactory.forConfig(config, this.getClass.getClassLoader)
    //CWE-295
    //SINK
    AhcWSClient(ahcConfig)
  }
}
