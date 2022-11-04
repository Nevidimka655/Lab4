package com.nevidimka655.lab3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nevidimka655.lab3.entities.Student
import com.nevidimka655.lab3.entities.StudentGroup
import java.util.*
import kotlin.concurrent.schedule

class MainVM : ViewModel() {

    private val students by lazy { fetchStudentsArray() }
    private val studentGroups by lazy { fetchStudentGroupsArray() }
    private var seconds = 0
        private set(value) {
            val h = value / 3600
            val m = (value % 3600) / 60
            val s = value % 60
            val str = "%d:%02d:%02d".format(h, m, s)
            _secondsLive.postValue(str)
            field = value
        }
    private var timer: Timer? = null
    private val _secondsLive = MutableLiveData<String>()
    val secondsLive: LiveData<String> = _secondsLive

    private fun fetchStudentsArray() = arrayOf(
        Student("Іванов Роман", "К20-3"),
        Student("Петров Федір", "К20-2"),
        Student("Осадча Оксана", "К20-1"),
        Student("Максимов Руслан", "К20-3"),
        Student("Смірнов Василь", "К20-2"),
        Student("Потапова Марія", "К20-3"),
        Student("Гонський Іван", "К20-1"),
        Student("Васильєв Максим", "К20-2")
    )

    private fun fetchStudentGroupsArray() = arrayOf(
        StudentGroup(
            "К20-1", "Комп'ютерних наук", 1,
            flagContractExists = false,
            flagPrivilageExists = true
        ),
        StudentGroup(
            "К20-2", "Комп'ютерних наук", 0,
            flagContractExists = false,
            flagPrivilageExists = false
        ),
        StudentGroup(
            "К20-3", "Комп'ютерних наук", 0,
            flagContractExists = false,
            flagPrivilageExists = true
        ),
        StudentGroup(
            "К20-4", "Комп'ютерних наук", 0,
            flagContractExists = true,
            flagPrivilageExists = true
        )
    )

    fun searchStudentsByGroupName(groupName: String) = students.filter {
        it.groupName.contentEquals(
            other = groupName,
            ignoreCase = true
        )
    }

    fun getGroupByName(name: String) = studentGroups.find {
        it.name.contentEquals(
            other = name,
            ignoreCase = true
        )
    }

    fun runTimer() {
        if (timer == null) {
            timer = Timer().apply {
                schedule(0, 1000) {
                    seconds++
                }
            }
        }
    }

    fun stopTimer() {
        timer?.cancel()
        timer = null
    }

}