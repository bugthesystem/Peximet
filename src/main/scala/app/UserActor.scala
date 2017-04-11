package com.ziyasal.peksimet.app

import akka.actor.Actor

class UserActor(val user: String) extends Actor {

  var watching: String = _

  def receive = {
    case asset: String => {
      watching = asset
      println("%s is reading %s".format(user, watching))
    }
    case _ => println("Unknown event")
  }
}
