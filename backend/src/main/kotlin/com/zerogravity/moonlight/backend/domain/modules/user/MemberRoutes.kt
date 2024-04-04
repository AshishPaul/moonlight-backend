//package com.zerogravity.moonlight.backend.routes
//
//import com.zerogravity.moonlight.backend.domain.model.NewMember
//import com.zerogravity.moonlight.backend.controllers.MemberController
//import io.ktor.http.HttpStatusCode
//import io.ktor.server.application.call
//import io.ktor.server.request.receive
//import io.ktor.server.response.respond
//import io.ktor.server.response.respondText
//import io.ktor.server.routing.Route
//import io.ktor.server.routing.delete
//import io.ktor.server.routing.get
//import io.ktor.server.routing.post
//import io.ktor.server.routing.route
//
//fun Route.memberRouting(memberController: MemberController) {
//
////    route("/users/{userId}/members") {
////        get {
////            val userId = call.parameters["userId"] ?: return@get call.respondText(
////                "Missing userId",
////                status = HttpStatusCode.BadRequest
////            )
////
////            val memberList = memberController.getByUserId(userId)
////            if (memberList.isNotEmpty()) {
////                call.respond(memberList)
////            } else {
////                call.respondText(
////                    "No member found for this userId $userId",
////                    status = HttpStatusCode.OK
////                )
////            }
////        }
////
////        post {
////            val newMember = call.receive<NewMember>()
////            val createdUser = memberController.create(newMember)
////            call.respond(HttpStatusCode.Created, createdUser)
////        }
////
////        delete {
////            val userId = call.parameters["userId"] ?: return@delete call.respondText(
////                "Missing userId",
////                status = HttpStatusCode.BadRequest
////            )
////            val deleted = memberController.deleteAllByUser(userId)
////            if (deleted) call.respond(HttpStatusCode.OK)
////            else call.respond(HttpStatusCode.NotFound)
////        }
////    }
////
////    route("/members") {
////        get {
////            val memberList = memberController.getAll()
////            if (memberList.isNotEmpty()) {
////                call.respond(memberList)
////            } else {
////                call.respondText("No member found.", status = HttpStatusCode.OK)
////            }
////        }
////
////        get("/{memberId}") {
////            val id = call.parameters["id"] ?: return@get call.respondText(
////                "Missing id",
////                status = HttpStatusCode.BadRequest
////            )
////
////            val member = memberController.get(id) ?: return@get call.respondText(
////                "No member with id $id",
////                status = HttpStatusCode.NotFound
////            )
////            call.respond(member)
////        }
////
////
////        post {
////            val newMember = call.receive<NewMember>()
////            val createdUser = memberController.create(newMember)
////            call.respond(HttpStatusCode.Created, createdUser)
////        }
////
////        delete("/{memberId}") {
////            val memberId = call.parameters["memberId"] ?: return@delete call.respondText(
////                "Missing memberId",
////                status = HttpStatusCode.BadRequest
////            )
////            val deleted = memberController.delete(memberId)
////            if (deleted) call.respond(HttpStatusCode.OK)
////            else call.respond(HttpStatusCode.NotFound)
////        }
////    }
//}