package com.behnamuix.appointment.data.local.fake

import com.behnamuix.appointment.data.local.model.FakeAppointment
import com.behnamuix.appointment.data.local.model.FakePeople

val fakeRemovedAppointment = listOf(
    FakeAppointment(
        id = 1002,
        title = "Project Kickoff Meeting",
        startTime = "2026-05-18T11:00:00",
        endTime = "2026-05-18T12:30:00",
        personId = 2,
        isDeleted = false,
        deleteTime = null,
        description = "Initial meeting to define project scope and milestones",
        deleteReason = null,
        phoneNumber = "09351234567",
        personName = "Olivia Johnson"
    ),
    FakeAppointment(
        id = 1001,
        title = "Dental Checkup",
        startTime = "2026-05-18T09:00:00",
        endTime = "2026-05-18T09:45:00",
        personId = 1,
        isDeleted = false,
        deleteTime = null,
        description = "Routine dental cleaning and oral examination",
        deleteReason = null,
        phoneNumber = "09123456789",
        personName = "Daniel Smith"
    ),
    FakeAppointment(
        id = 1003,
        title = "Haircut Appointment",
        startTime = "2026-05-19T16:00:00",
        endTime = "2026-05-19T16:45:00",
        personId = 3,
        isDeleted = false,
        deleteTime = null,
        description = "Regular haircut and beard trim",
        deleteReason = null,
        phoneNumber = "09134567890",
        personName = "Michael Brown"
    ),
)
val fakeAppointments = listOf(

    FakeAppointment(
        id = 1001,
        title = "Dental Checkup",
        startTime = "2026-05-18T09:00:00",
        endTime = "2026-05-18T09:45:00",
        personId = 1,
        isDeleted = false,
        deleteTime = null,
        description = "Routine dental cleaning and oral examination",
        deleteReason = null,
        phoneNumber = "09123456789",
        personName = "Daniel Smith"
    ),

    FakeAppointment(
        id = 1002,
        title = "Project Kickoff Meeting",
        startTime = "2026-05-18T11:00:00",
        endTime = "2026-05-18T12:30:00",
        personId = 2,
        isDeleted = false,
        deleteTime = null,
        description = "Initial meeting to define project scope and milestones",
        deleteReason = null,
        phoneNumber = "09351234567",
        personName = "Olivia Johnson"
    ),

    FakeAppointment(
        id = 1003,
        title = "Haircut Appointment",
        startTime = "2026-05-19T16:00:00",
        endTime = "2026-05-19T16:45:00",
        personId = 3,
        isDeleted = false,
        deleteTime = null,
        description = "Regular haircut and beard trim",
        deleteReason = null,
        phoneNumber = "09134567890",
        personName = "Michael Brown"
    ),

    FakeAppointment(
        id = 1004,
        title = "Annual Health Checkup",
        startTime = "2026-05-20T08:30:00",
        endTime = "2026-05-20T10:00:00",
        personId = 4,
        isDeleted = false,
        deleteTime = null,
        description = "Blood test and general medical examination",
        deleteReason = null,
        phoneNumber = "09221234567",
        personName = "Sophia Wilson"
    ),

    FakeAppointment(
        id = 1005,
        title = "Car Service",
        startTime = "2026-05-21T13:00:00",
        endTime = "2026-05-21T15:00:00",
        personId = 5,
        isDeleted = false,
        deleteTime = null,
        description = "Oil change and brake inspection",
        deleteReason = null,
        phoneNumber = "09021234567",
        personName = "James Taylor"
    ),

    FakeAppointment(
        id = 1006,
        title = "Photography Session",
        startTime = "2026-05-22T17:30:00",
        endTime = "2026-05-22T19:00:00",
        personId = 6,
        isDeleted = false,
        deleteTime = null,
        description = "Outdoor portrait photography",
        deleteReason = null,
        phoneNumber = "09187654321",
        personName = "Emma Anderson"
    ),

    FakeAppointment(
        id = 1007,
        title = "Client Consultation",
        startTime = "2026-05-23T10:00:00",
        endTime = "2026-05-23T11:00:00",
        personId = 7,
        isDeleted = false,
        deleteTime = null,
        description = "Consultation about financial planning",
        deleteReason = null,
        phoneNumber = "09367891234",
        personName = "William Martinez"
    ),

    FakeAppointment(
        id = 1008,
        title = "Online Interview",
        startTime = "2026-05-24T14:00:00",
        endTime = "2026-05-24T15:00:00",
        personId = 8,
        isDeleted = false,
        deleteTime = null,
        description = "Technical interview via Zoom",
        deleteReason = null,
        phoneNumber = "09155667788",
        personName = "Isabella Garcia"
    ),

    FakeAppointment(
        id = 1009,
        title = "Yoga Class",
        startTime = "2026-05-25T18:00:00",
        endTime = "2026-05-25T19:00:00",
        personId = 9,
        isDeleted = false,
        deleteTime = null,
        description = "Evening Vinyasa yoga session",
        deleteReason = null,
        phoneNumber = "09099887766",
        personName = "Ethan Clark"
    ),

    FakeAppointment(
        id = 1010,
        title = "Cancelled Meeting",
        startTime = "2026-05-17T15:00:00",
        endTime = "2026-05-17T16:00:00",
        personId = 10,
        isDeleted = true,
        deleteTime = "2026-05-16T22:43:02",
        description = "Marketing strategy discussion",
        deleteReason = "Rescheduled to next week",
        phoneNumber = "09122334455",
        personName = "Ava Thompson"
    )
)

val fakePeoples = listOf(
    FakePeople(40, "Smith", "John", false, null, "09120000001", "0012345678", null, 1),
    FakePeople(41, "Johnson", "Emma", false, null, "09120000002", "0012345679", null, 3),
    FakePeople(42, "Brown", "Michael", false, null, "09120000003", "0012345680", null, 0),
    FakePeople(
        43,
        "Davis",
        "Sophia",
        true,
        "2023-10-26T10:00:00Z",
        "09120000004",
        "0012345681",
        "Patient request",
        2
    ),
    FakePeople(44, "Wilson", "James", false, null, "09120000005", "0012345682", null, 5),
    FakePeople(45, "Miller", "Olivia", false, null, "09120000006", "0012345683", null, 1),
    FakePeople(46, "Taylor", "Daniel", false, null, "09120000007", "0012345684", null, 2),
    FakePeople(
        47,
        "Anderson",
        "Ava",
        true,
        "2023-09-15T14:30:00Z",
        "09120000008",
        "0012345685",
        "No show",
        0
    ),
    FakePeople(48, "Thomas", "William", false, null, "09120000009", "0012345686", null, 4),
    FakePeople(49, "Moore", "Isabella", false, null, "09120000010", "0012345687", null, 1)
)