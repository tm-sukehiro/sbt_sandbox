import collection.JavaConversions._
import scala.collection._

object LazyCollection extends App {
  val v = Vector(1 to 10: _*)

  v map (_ + 1) map (_ * 2)
  // for view
  (v.view map (_ + 1) map (_ * 2)).force

  def isPalindrome(x: String) = x == x.reverse
  def findPalindrome(s: Seq[String]) = s find isPalindrome

  val words = (1 to 1000000).map(_.toString)

  findPalindrome(words.view take 1000000)

  val arr = (0 to 9).toArray
  val subarr = arr.view.slice(3, 6)

  def negate(xs: mutable.Seq[Int]) =
    for (i <- xs.indices) xs(i) = -xs(i)

  negate(subarr)

  def skipEmptyWords(it: BufferedIterator[String]) =
    while (it.head.isEmpty) { it.next() }

  val it = Iterator(1, 2, 3, 4)
  val bit = it.buffered
  bit.head
  bit.next()

  val jul: java.util.List[Int] = mutable.ArrayBuffer(1, 2, 3)
  val buf: Seq[Int] = mutable.ArrayBuffer(1, 2, 3)
  val m: java.util.Map[String, Int] = mutable.HashMap("abc" -> 1, "hello" -> 2)

  val im: java.util.List[Int] = List(1, 2, 3)
  im.add(7)

}
