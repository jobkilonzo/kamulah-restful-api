package com.example.kamulahtv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kamulahtv.viewmodel.ProgramApiViewModel
import com.example.kamulahtv.viewmodel.ProgramApiViewModelFactory
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private val apiViewModel: ProgramApiViewModel by viewModels {
        ProgramApiViewModelFactory((application as ProgramApplication).programApiRepository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        //val progressBar = findViewById<ProgressBar>(R.id.progress)


        val adapter = ProgramAdapter()

        val program: TextView = findViewById(R.id.txtProgram)
        val startTime: TextView = findViewById(R.id.txtFrom)
        val endTime: TextView = findViewById(R.id.txtTo)

        val cal = Calendar.getInstance()
        val hour = cal.get(Calendar.HOUR)



        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //fetchPrograms()
        fetchPrograms()

        apiViewModel.programs.observe(this) {

                programs -> run {
            Log.d("Room programs", "${programs[1]}")
                adapter.submitList(programs)
            for (i in programs) {
                if ((hour >= i.startTime?.toInt()!!) || (hour <= startTime?.toInt()!!)) {
                    program.text = i.title
                    startTime.text = i.startTime
                    endTime.text = i.endTime
                }
            }
        }
//            programs.forEach { _ ->
//                Log.d("Room programs", "${programs[1]}")
//                adapter.submitList(programs)
//                if (hour == (programs[programs.size].startTime)?.toInt()){
//                    program.text = programs[programs.size].title
//                    startTime.text = programs[programs.size].startTime
//                    endTime.text = programs[programs.size].endTime
//                }
//
//            }
        }
    }
    private fun fetchPrograms(){
        //if (NETWORK_STATS_SERVICE)

        apiViewModel.fetchPrograms()

    }
}