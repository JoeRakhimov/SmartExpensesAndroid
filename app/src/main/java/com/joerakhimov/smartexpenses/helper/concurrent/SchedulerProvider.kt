package com.joerakhimov.smartexpenses.helper.concurrent

import io.reactivex.Scheduler

interface SchedulerProvider {
    val io: Scheduler
    val ui: Scheduler
    val computation: Scheduler
}