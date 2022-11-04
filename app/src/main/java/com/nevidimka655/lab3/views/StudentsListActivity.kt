package com.nevidimka655.lab3.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import androidx.activity.viewModels
import com.nevidimka655.lab3.MainActivity
import com.nevidimka655.lab3.MainVM
import com.nevidimka655.lab3.R
import com.nevidimka655.lab3.databinding.ActivityStudentsListBinding

class StudentsListActivity : AppCompatActivity() {
    private val vm by viewModels<MainVM>()
    private val binding by lazy { ActivityStudentsListBinding.inflate(layoutInflater) }
    private var textSize
        get() = binding.text.textSize
        set(value) {
            binding.text.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                textSize * value
            )
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        intent.extras?.getString(MainActivity.Keys.GROUP_NAME)?.let { groupName ->
            binding.text.text = vm.searchStudentsByGroupName(groupName)
                .joinToString(separator = "\n") { it.name }
        }
        binding.sendMsg.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, "fff@gmail.com")
                putExtra(Intent.EXTRA_TEXT, binding.text.toString())
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.studentsList))
            }
            startActivity(intent)
        }
        binding.sizeIn.setOnClickListener { textSize = 1.1f }
        binding.sizeOut.setOnClickListener { textSize = 0.9f }
    }
}