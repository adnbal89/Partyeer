package com.partyeer.presentation.ui.main.activity

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
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
    private var partyUri: Uri = Uri.EMPTY


    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            binding.imageViewPartyLogo.setImageURI(uri)
            if (uri != null) {
                partyUri = uri
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cancel -> {
                finish()
            }
            R.id.action_publish -> {
                val pictureList = arrayListOf<Uri>(partyUri)
                val party = Party(
                    binding.textViewPartyTitle.editText?.text.toString(),
                    "https://images.unsplash.com/photo-1661612117616-84b7fcf639d6?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY2MzY5MjQ5Nw&ixlib=rb-1.2.1&q=80&w=200",
                    binding.textViewPartyTitle.editText?.text.toString(),
                    binding.textViewPartyConcept.editText?.text.toString(),
                    0.0,
                    pictureList,
                )
                viewModel.createParty(party)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_create_party, menu)
        return true
    }

}