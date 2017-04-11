package com.ziyasal.peksimet.app

import akka.actor.{Actor, ActorRef, Props}

class UsersActor extends Actor {

  val ctx = this.context
  var users = Map[String, ActorRef]()

  def findOrSpawn(user: String): ActorRef = {
    if (users.exists(kv => kv._1 == user)) {
      users(user)
    } else {
      val userActorProps = Props.create(classOf[UserActor], user)
      users = users + (user -> ctx.actorOf(userActorProps))
      findOrSpawn(user)
    }
  }

  def updateUser(user: String, asset: String): Unit = {
    findOrSpawn(user) ! asset
  }

  def receive = {
    case evt: PlayEvent => {
      updateUser(evt.user, evt.asset)
      println("Unique users: %d".format(users.size))
    }
    case _ => println("Unknown event")
  }
}
