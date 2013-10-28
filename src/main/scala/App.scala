import model.User
import HibernateUtils._
import PimpedCriteria._
import PimpedSession._

object App {

  def main(args: Array[String]) {
    val s = openSession

    s.save(new User("Ralph", 4))
    s.save(new User("Altair"))
    s.save(new User("Ezio", 8))

    val u = s.from[User].get[User]

    println(u)
  }
}
