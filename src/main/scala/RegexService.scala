import scala.util.matching.Regex

object RegexService {
  def findMatches(pattern: String, input: String): String = {
    val regex = new Regex(pattern)
    //CWE-1333
    //SINK
    regex.findAllIn(input).mkString(",")
  }
}
