import scala.collection.convert.WrapAsScala._
import org.hibernate.criterion._
import org.hibernate.{Session, Criteria}

object PimpedCriteria {

  implicit def pimpedCriteria(c: Criteria) = new {
    def get[G]: Option[G] = c.uniqueResult match {
      case x: G => Option(x)
      case _ => throw new Error("Unexpected result type")
    }

    def asList[G]: List[G] = c.list.asInstanceOf[java.util.List[G]].toList
    def where(crit: Criterion) = c.add(crit)
    def orderBy(field: String, order: String = "asc") = order match {
      case "asc" => c.addOrder(Order.asc(field))
      case "desc" => c.addOrder(Order.desc(field))
      case _ => throw new Error("Unexpected order")
    }
  }

  implicit def criterions(field: String) = new {
    def /==(value: Any) = Restrictions.eq(field, value)

    def />(value: Any) = Restrictions.gt(field, value)

    def />=(value: Any) = Restrictions.ge(field, value)

    def /<(value: Any) = Restrictions.lt(field, value)

    def /<=(value: Any) = Restrictions.le(field, value)
  }

  implicit def criterions(crit: Criterion) = new {
    def /&&(other: Criterion) = Restrictions.and(crit, other)

    def /||(other: Criterion) = Restrictions.or(crit, other)
  }
}

object PimpedSession {

  implicit def pimpedSession(s: Session) = new {
    def from[T](implicit m: Manifest[T]) = s.createCriteria(m.runtimeClass)
  }
}
