package com.partyeer.presentation.ui.main.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.partyeer.domain.repository.party.model.Concept
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.domain.repository.party.model.Picture
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.ActivityCreatePartyBinding
import com.partyeer.presentation.ui.main.base.BaseActivity
import com.partyeer.presentation.ui.main.features.party.createparty.CreatePartyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePartyActivity : BaseActivity() {
    private val viewModel: CreatePartyViewModel by viewModels()

    private lateinit var binding: ActivityCreatePartyBinding
    private var partyPictureList: MutableList<Picture> = arrayListOf()
    private lateinit var party: Party
    private lateinit var logoUri: String


    /*private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            binding.imageViewPartyLogo.setImageURI(uri)
            logoUri = uri.toString() ?: ""

            val picture = Picture(uri.toString(), uri.toString())
            if (uri != null) {
                partyPictureList.add(picture)
            }
        }*/

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents())
        { uriList ->
            binding.imageViewPartyLogo.setImageURI(uriList[0])
            logoUri = uriList[0].toString()

            uriList.forEach {
                val picture = Picture(it.toString(), it.toString())
                println("adnan uriList : " + it.toString())
                partyPictureList.add(picture)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreatePartyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.imageViewPartyLogo.setOnClickListener {
            getContent.launch("image/*")
        }

        val partyConceptList = resources.getStringArray(R.array.party_concepts)
        val arrayAdapter =
            ArrayAdapter(this, R.layout.item_loyout_concept_dropdown, partyConceptList)
        binding.autoCompleteTextViewConcept.setAdapter(arrayAdapter)
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
                    1665254150,
                    1665264150,
                    "Example Description",
                    partyPictureList,
                    likeCount = 51,
                    mutableListOf<String>("1"),
                    mutableListOf<String>("1"),
                    mutableMapOf<String,Boolean>(),
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

}