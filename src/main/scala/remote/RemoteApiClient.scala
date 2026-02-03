package remote

import RemoteApiCredentials._
import sttp.client3._
import scala.concurrent.Future

object RemoteApiClient {
  def sendHeartbeat(baseUrl: String)(implicit backend: SttpBackend[Future, Any]): Future[Response[Either[String, String]]] = {
    val request = basicRequest
      .get(uri"$baseUrl/health")
      //CWE-798
      //SINK
      .auth.basic(username, password)
    request.send(backend)
  }
}
