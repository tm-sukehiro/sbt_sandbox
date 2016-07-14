import scalax.file.Path

object ScalaIO {
  def main(args: Array[String]): Unit = {
    println(Path.fromString(".").toRealPath())
  }
}
