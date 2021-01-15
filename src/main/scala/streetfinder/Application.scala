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
    streets = streetFinderImpl.findStreets(streets,Set("azizler","BURGAZ ÇAYI","inönü"))

    println("Count of streets: " + streets.length)
    for(street <- streets) println(street)
  }



}


class CsvLoaderImpl extends CsvLoader{

  def loadCsv(fileName: String): List[String] = {

    val bufferedSource = io.Source.fromFile(fileName)
    val streets: List[String] = (for (line <- bufferedSource.getLines )  yield (line.split(",").map(_.trim).toList)(1)).toList
    bufferedSource.close
    streets

  }
}

class StreetFinderImpl extends StreetFinder{

  override def findStreets(streets: List[String], names: Set[String]): List[String] = {

    names.flatMap(name => {streets.filter(_.toLowerCase().contains(name.toLowerCase()))}).toList

 }
}


