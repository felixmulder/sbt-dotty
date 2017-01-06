trait Base {
  def message: String
}
class A extends Base {
  def message: String = "Hello"
}
class B extends Base {
  def message: String = "Dotty!"
}

object Main {
  // Union types only exist in Dotty, so there's no chance that this will
  // accidentally be compiled with Scala 2
  def printMessages(msgs: (A | B)*): Unit =
    println(msgs.map(_.message).mkString(" "))
  
  def main(args: Array[String]): Unit =
    printMessages(new A, new B)
}
