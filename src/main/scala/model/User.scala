package model

import javax.persistence.{Id, GeneratedValue, Entity}

@Entity class User(var name: String = null,
                    var num: Int = 0) {
  def this() = this(null, 0)

  @Id @GeneratedValue var id: Int = _
}
