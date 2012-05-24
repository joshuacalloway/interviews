package getco.model

import scala.annotation.target.getter
import scala.reflect.BeanProperty
import org.slf4j.{Logger, LoggerFactory}

protected trait EquityModelUtils {
  def log = LoggerFactory.getLogger(getClass)

  def getValueOrNull(headers: Map[String, Int], checksum: Map[String, (Int, Int)], values: Array[String], item: String): String = {
    
    headers.contains(item) match {
      case true => {
        if (values.size <= headers(item)) {
          log.error("Was not able to parse value '" + item + "' from record '" + values.mkString(",") + "'")
          null
        }
        else getValueWithoutChecksum(checksum, item, values(headers(item)))
      }
      case false => null
    }
  }

  def getValueWithoutChecksum(checksum: Map[String, (Int, Int)], item: String, value: String) : String = {
    if (checksum.contains(item)) value.substring(0, checksum(item)._1)  else value
  }
}
