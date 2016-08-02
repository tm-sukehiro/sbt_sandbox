import scala.collection.mutable
import scala.reflect.ClassTag

object MutableCollection extends App {
  // mutable.Queue
  val queue = new mutable.Queue[String]

  queue += "a"
  queue ++= List("b", "c")

  queue.dequeue()

  // mutable.HashMap
  val map = mutable.HashMap.empty[Int, String]

  map += (1 -> "make a web site")
  map += (3 -> "profit!")

  map(1)
  map.contains(2)

  // mutable.BitSet
  val bits = mutable.BitSet.empty

  bits += 1
  bits += 3

  // 配列
  val a1 = Array(1, 2, 3)
  val a2 = a1.map(_ * 3)
  val a3 = a2.filter(_ % 2 != 0)
  a3.reverse

  val seq: Seq[Int] = a1
  val a4: Array[Int] = seq.toArray
  a1 eq a4

  // def evenElems[T](xs: Vector[T])(implicit t: ClassTag[T]): Array[T] = {
  def evenElems[T: ClassTag](xs: Vector[T]): Array[T] = {
    val arr = new Array[T]((xs.length + 1) / 2)
    for (i <- 0 until xs.length by 2)
      arr(i / 2) = xs(i)
    arr
  }

  evenElems(Vector(1, 2, 3, 4, 5))
  evenElems(Vector("this", "is", "a", "test", "run"))

  // 文字列
  val str = "hello"
  str.reverse
  str.map(_.toUpper)
  str.drop(3)
  str.slice(1, 4)

  val s: Seq[Char] = str

  // 等価性
  val buf = mutable.ArrayBuffer(1, 2, 3)
  val hashMap = mutable.HashMap(buf -> 3)

  buf(0) += 1

  hashMap(buf)
}
