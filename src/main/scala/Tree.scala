sealed abstract class Tree extends Traversable[Int] {
  def foreach[U](f: Int => U) = this match {
    case Node(elem) => f(elem)
    case Branch(l, r) =>
      l foreach f
      r foreach f
  }
}
case class Branch(left: Tree, right: Tree) extends Tree
case class Node(elem: Int) extends Tree

