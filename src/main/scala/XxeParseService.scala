import javax.xml.parsers.SAXParserFactory
import scala.xml.XML

object XxeParseService {
  def parseXml(configXml: String): String = {
    val factory = SAXParserFactory.newInstance()
    factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
    factory.setFeature("http://xml.org/sax/features/external-general-entities", true)
    factory.setFeature("http://xml.org/sax/features/external-parameter-entities", true)
    val saxParser = factory.newSAXParser()
    //CWE-611
    //SINK
    val xml = XML.withSAXParser(saxParser).loadString(configXml)
    xml.text
  }
}
