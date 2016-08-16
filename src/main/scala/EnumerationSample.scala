object Color extends Enumeration {
  val Red = Value
  val Green = Value
  val Blue = Value
}

object Direction extends Enumeration {
  val North = Value("North")
  val East = Value("East")
  val South = Value("South")
  val West = Value("West")
}

object EnumerationSample extends App {
  for (d <- Direction.values) print(d + " ")
}