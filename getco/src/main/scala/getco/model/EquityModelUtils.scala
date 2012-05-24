package getco.model

import scala.reflect.BeanProperty
import org.slf4j.{Logger, LoggerFactory}


trait EquityModelUtils {
  def log = LoggerFactory.getLogger(getClass)

  def parseLong(value: String) : java.lang.Long = {
    val regex = """(\d+)[\.*][0]+""".r
    val longregex = """(\d+)""".r
    value match {
      case longregex(value) => value.toLong
      case regex(whole) => whole.toLong
      case _ => null
    }
  }

  def parseDouble(value: String) : java.lang.Double = {
    val doubleregex = """([\d]+[\.][\d]+)""".r
    val double2regex = """([\d]+[\.])""".r
    val double3regex = """([\.][\d]+)""".r

    val longregex = """(\d+)""".r
    value match {
      case doubleregex(value) => value.toDouble
      case double2regex(value) => value.toDouble
      case double3regex(value) => value.toDouble
      case longregex(value) => value.toDouble
      case _ => null
    }
  }

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
