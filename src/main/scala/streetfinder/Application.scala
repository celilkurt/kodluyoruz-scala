package dev.akif.kodluyoruz.streetfinder

import java.io.File


/**
 * See CSV file at: https://github.com/makiftutuncu/kodluyoruz-scala/blob/main/data/streets.csv
 *
 * Original data: https://github.com/life/il-ilce-mahalle-sokak-cadde-sql
 */
object Application {
  def main(args: Array[String]): Unit = {

    val streetFinderImpl = new StreetFinderImpl()
    val csvLoaderImpl = new CsvLoaderImpl()
    var streets = csvLoaderImpl.loadCsv("./data/streets.csv")
    streets = streetFinderImpl.findStreets(streets,Set("AZİZLER SK.","BURGAZ ÇAYIRI SK.","inönü"))

    println("Count of streets: " + streets.length)
    for(street <- streets) println(street)
  }
}


class CsvLoaderImpl extends CsvLoader{

  def loadCsv(fileName: String): List[String] = {


    val bufferedSource = io.Source.fromFile(fileName)
    val streets: List[String] = (for (line <- bufferedSource.getLines )  yield (line.split(",").map(_.trim).toList)(1)).toList

    print("Size of list: " + streets.length)
    bufferedSource.close
    streets
  }

}

class StreetFinderImpl extends StreetFinder{

  override def findStreets(streets: List[String], names: Set[String]): List[String] = {
    var ansList: List[String] = List()
    for(name <- names) {
      ansList = ansList ++ streets.filter(_.toLowerCase.contains(name.toLowerCase()))
    }

    ansList
  }
}


