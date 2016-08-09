abstract class Element { val id: Long }
case class Head(id: Long, text: String) extends Element
case class Body(id: Long, text: String) extends Element

trait Tag[A] {
  def toHtml(i: A): String
}

object Tag {
  implicit object HeadTag extends Tag[Head] {
    def toHtml(h: Head): String = {
      s"<head>${h.text}</head>"
    }
  }

  implicit object BodyTag extends Tag[Body] {
    def toHtml(b: Body): String = {
      s"<body>${b.text}</body>"
    }
  }

  implicit object ElementTag extends Tag[Element] {
    def toHtml(e: Element): String = {
      ""
    }
  }
}

object TypeParameterTest extends App {
  // Elementに汎化された状態で渡ってくる
  val e: Element = Head(42, "<title>test</title>")

  // error: could not find implicit value for parameter t: this.Tag[this.Element]
  // HeadTag.toHtmlに流したい
  val output = toHtml(e)
  println(output)

  private def toHtml[A](e: A)(implicit t: Tag[A]): String = {
    t.toHtml(e)
  }
}
