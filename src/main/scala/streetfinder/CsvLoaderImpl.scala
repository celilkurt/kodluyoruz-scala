import java.io.File

import dev.akif.kodluyoruz.streetfinder.CsvLoader

class CsvLoaderImpl extends CsvLoader{

  def loadCsv(fileName: String): List[String] = {


    val bufferedSource = io.Source.fromFile(fileName)
    var key = 0
    val streets: List[String] = (for (line <- bufferedSource.getLines )  yield (line.split(",").map(_.trim).toList)(1)).toList

    print("Size of list: " + streets.length)
    bufferedSource.close
    streets
  }




}