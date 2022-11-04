package com.nevidimka655.lab3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.nevidimka655.lab3.databinding.ActivityMainBinding
import com.nevidimka655.lab3.views.StudentsGroupActivity
import com.nevidimka655.lab3.views.StudentsListActivity

class MainActivity : AppCompatActivity() {

    object Keys {
        const val GROUP_NAME = "grpnum"
    }

    private val vm by viewModels<MainVM>()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val selectedGroup get() = binding.spinner.selectedItem as String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm.secondsLive.observe(this) {
            binding.time.text = it
        }
        vm.runTimer()
        binding.button.setOnClickListener {
            Toast.makeText(
                this,
                getString(R.string.t_buttonPressed),
                Toast.LENGTH_SHORT
            ).show()
            binding.text.text = vm.searchStudentsByGroupName(selectedGroup)
                .joinToString(separator = "\n") { it.name }
        }
        binding.button2.setOnClickListener {
            startActivity(
                Intent(this, StudentsListActivity::class.java).apply {
                    putExtra(Keys.GROUP_NAME, selectedGroup)
                }
            )
        }
        binding.moreButton.setOnClickListener {
            startActivity(
                Intent(this, StudentsGroupActivity::class.java).apply {
                    putExtra(Keys.GROUP_NAME, selectedGroup)
                }
            )
        }
    }

    override fun onStart() {
        super.onStart()
        vm.runTimer()
    }

    override fun onStop() {
        super.onStop()
        vm.stopTimer()
    }
}