package com.partyeer.presentation.ui.main.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.partyeer.domain.repository.party.model.Concept
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.domain.repository.party.model.Picture
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.ActivityCreatePartyBinding
import com.partyeer.presentation.ui.main.features.party.createparty.CreatePartyViewModel
import com.partyeer.presentation.ui.main.base.BaseActivity
import com.partyeer.presentation.ui.main.util.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class CreatePartyActivity : BaseActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private val viewModel: CreatePartyViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator

    private lateinit var binding: ActivityCreatePartyBinding
    private var partyPictureList: MutableList<Picture> = arrayListOf()
    private lateinit var party: Party
    private lateinit var logoUri: String
    private lateinit var dateTime: DateTime
    private lateinit var timeView: View
    private var startTime: Long = 0
    private var endTime: Long = 0

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents())
        { uriList ->
            //if not null
            uriList?.let { it ->
                //TODO: refactor
                if (it.size > 0) {
                    binding.imageViewPartyLogo.setImageURI(it[0])
                    logoUri = it[0].toString()

                    uriList.forEach { uri ->
                        val picture = Picture(uri.toString(), uri.toString())
                        partyPictureList.add(picture)
                    }
                }
            }

        }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreatePartyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.imageViewPartyLogo.setOnClickListener {
            getContent.launch("image/*")
        }

        timeView = View(this)

        binding.apply {
            textViewStartTime.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        pickDate()
                        timeView = textViewStartTime
                        //textViewStartTime.setText("${dateTime.day}/${dateTime.month}/${dateTime.year} @ ${dateTime.hour}:${dateTime.minute}")
                        true
                    }
                    else -> {
                        false
                    }
                }
            }

            textViewEndsTime.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        pickDate()
                        timeView = textViewEndsTime
                        //textViewEndsTime.setText("${dateTime.day}/${dateTime.month}/${dateTime.year} @ ${dateTime.hour}:${dateTime.minute}")
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }

        val partyConceptList = resources.getStringArray(R.array.party_concepts)
        val arrayAdapter =
            ArrayAdapter(this, R.layout.item_loyout_concept_dropdown, partyConceptList)
        binding.autoCompleteTextViewConcept.setAdapter(arrayAdapter)


        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collect { event ->
                    when (event) {
                        is CreatePartyViewModel.Event.FinishActivity -> {
                            Toast.makeText(baseContext, "Party Created", Toast.LENGTH_LONG).show()
                            finish()
                        }
                    }
                }
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cancel -> {
                finish()
            }
            R.id.action_publish -> {
                party = Party(
                    binding.textViewPartyTitle.editText?.text.toString(),
                    logoUri,
                    binding.textViewPartyTitle.editText?.text.toString(),
                    Concept(binding.textViewPartyConcept.editText?.text.toString()),
                    28.987,
                    40.254,
                    timeStart = startTime,
                    timeEnd = endTime,
                    "Example Description",
                    partyPictureList,
                    likeCount = 51,
                    entranceFee = "Free",
                    hashMapOf<String, Boolean>(),
                    hashMapOf<String, Boolean>(),
                    hashMapOf<String, Boolean>(),
                    "1"
                )
                viewModel.createParty(party)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_create_party, menu)

        val actionNewParty = menu.findItem(R.id.action_new_party)
        val actionOpenMapActivity = menu.findItem(R.id.action_open_map_activity)
        actionNewParty.isVisible = false
        actionOpenMapActivity.isVisible = false

        return true
    }

    private fun getDateTimeCalendar() {
        val calendar = Calendar.getInstance()
        dateTime = DateTime(0, 0, 0, 0, 0)
        dateTime.day = calendar.get(Calendar.DAY_OF_MONTH)
        dateTime.month = calendar.get(Calendar.MONTH)
        dateTime.year = calendar.get(Calendar.YEAR)
        dateTime.hour = calendar.get(Calendar.HOUR)
        dateTime.minute = calendar.get(Calendar.MINUTE)
    }

    private fun pickDate() {
        //initialize DatePicker to current date
        getDateTimeCalendar()

        DatePickerDialog(this, this, dateTime.year, dateTime.month, dateTime.month).show()
    }

    override fun onDateSet(datePicker: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        dateTime.year = year
        dateTime.month = month
        dateTime.day = dayOfMonth

        TimePickerDialog(this, this, dateTime.hour, dateTime.minute, true).show()
    }

    override fun onTimeSet(timePicker: TimePicker?, hour: Int, minute: Int) {
        dateTime.hour = hour
        dateTime.minute = minute
        val gregCalendar = GregorianCalendar(
            dateTime.year,
            dateTime.month,
            dateTime.day,
            dateTime.hour,
            dateTime.minute
        )

        if (timeView == binding.textViewStartTime) {
            binding.textViewStartTime.setText("${dateTime.day}/${dateTime.month}/${dateTime.year} ${dateTime.hour}:${dateTime.minute}")
            startTime = gregCalendar.timeInMillis
        } else if (timeView == binding.textViewEndsTime) {
            binding.textViewEndsTime.setText("${dateTime.day}/${dateTime.month}/${dateTime.year} ${dateTime.hour}:${dateTime.minute}")
            endTime = gregCalendar.timeInMillis
        }
    }

    data class DateTime(
        var day: Int = 0,
        var month: Int = 0,
        var year: Int = 0,
        var hour: Int = 0,
        var minute: Int = 0
    )


}