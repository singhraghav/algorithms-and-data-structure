package com.singhraghav.scala3.language

object Enums extends App {

  enum Permission {
    case READ, WRITE, EXECUTE, NONE

    def openDocument(): Unit = if (this == READ) println("opening document !!!!") else println("read not allowed !!!!")
  }

  val somePermissions: Permission = Permission.READ
  val someWritePermission: Permission = Permission.WRITE

  someWritePermission.openDocument()
  somePermissions.openDocument()

  enum PermissionWithBits(bits: Int) {
    case Read extends PermissionWithBits(4)
    case Write extends PermissionWithBits(2)
    case Execute extends PermissionWithBits(1)
  }

  object PermissionWithBits {
    def fromBits(bits: Int): PermissionWithBits =
      PermissionWithBits.Read
  }


}
