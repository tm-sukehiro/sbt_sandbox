trait Abstract {
  type T
  def transform(x: T): T
  val initial: T
  var current: T
}

class Concrete extends Abstract {
  type T = String
  def transform(x: String) = x + x
  val initial = "hi"
  var current = initial
}

abstract class Fruit {
  val v: String
  def m: String
}

abstract class Apple extends Fruit {
  val v: String
  val m: String
}

abstract class BadApple extends Fruit {
  def v: String
  def m: String
}

trait AbstractTime {
  var hour: Int
  var minute: Int
}

trait RationalTrait {
  val numerArg: Int
  val denomArg: Int
  require(denomArg != 0)
  private val g = gcd(numerArg, denomArg)
  val numer = numerArg / g
  val denom = denomArg / g
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
  override def toString = numer + "/" + denom
}

new {
  val x = 2
  val numerArg = 1 * x
  val denomArg = 2 * x
} with RationalTrait

new RationalTrait {
  val numerArg = 1
  val denomArg = 2
}

object twoThirds extends {
  val numerArg = 2
  val denomArg = 3
} with RationalTrait

class RationalClass(n: Int, d: Int) extends {
  val numerArg = n
  val denomArg = d
} with RationalTrait {
  def + (that: RationalClass) = new RationalClass(
    numer * that.denom + that.numer * denom,
    denom * that.denom
  )
}

trait LazyRationalTrait {
  val numerArg: Int
  val denomArg: Int
  lazy val numer = numerArg / g
  lazy val denom = denomArg / g

  override def toString: String = numer + "/" + denom
  private lazy val g = {
    require(denomArg != 0)
    gcd(numerArg, denomArg)
  }
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}

new LazyRationalTrait {
  val numerArg = 1 * 2
  val denomArg = 2 * 2
}

class Food
abstract class Animal {
  type SuitableFood <: Food
  def eat(food: SuitableFood)
}

class Grass extends Food
class Cow extends Animal {
  override type SuitableFood = Grass
  override def eat(food: Grass): Unit = {}
}

class DogFood extends Food
class Dog extends Animal {
  override type SuitableFood = DogFood
  override def eat(food: DogFood): Unit = {}
}

class Pasture {
  var animals: List[Animal { type SuitableFood = Grass }] = Nil
}