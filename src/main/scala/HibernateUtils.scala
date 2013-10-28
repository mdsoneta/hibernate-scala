import model.User
import org.hibernate.cfg.Configuration

object HibernateUtils {
  val sessionFactory = new Configuration().addAnnotatedClass(classOf[User]).buildSessionFactory()
  def openSession = sessionFactory.openSession()
}
