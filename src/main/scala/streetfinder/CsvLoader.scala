package dev.akif.kodluyoruz.streetfinder


trait CsvLoader {
  def loadCsv(fileName: String): List[String]
}


