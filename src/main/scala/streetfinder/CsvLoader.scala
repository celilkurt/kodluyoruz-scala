package dev.akif.kodluyoruz.streetfinder

import java.io.File

trait CsvLoader {
  def loadCsv(fileName: String): List[String]
}


