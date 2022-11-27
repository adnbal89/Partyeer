package com.partyeer.presentation.ui.main.activity

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.ActivityPartyInviteeListBinding
import com.partyeer.presentation.ui.main.base.BaseActivity
import com.partyeer.presentation.ui.main.features.party.InviteeListRecyclerViewAdapter
import com.partyeer.presentation.ui.main.util.navigation.Navigator
import com.partyeer.presentation.ui.main.util.setDivider
import com.partyeer.presentation.ui.main.view.extension.onQueryTextSubmit
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InviteeListActivity : BaseActivity() {
    private lateinit var partyInviteeList: HashMap<String, Boolean>
    private lateinit var binding: ActivityPartyInviteeListBinding

    @Inject
    lateinit var navigator: Navigator

    private val inviteeListRecyclerViewAdapter by lazy {
        InviteeListRecyclerViewAdapter { userName ->
            Toast.makeText(this, "User item clicked", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPartyInviteeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        partyInviteeList = intent.getSerializableExtra("partyInviteeList").let {
            it as HashMap<String, Boolean>
        }
        inviteeListRecyclerViewAdapter.setItems(partyInviteeList.keys)

        with(binding.recyclerViewInviteeList) {
            adapter = inviteeListRecyclerViewAdapter
            layoutManager = LinearLayoutManager(this@InviteeListActivity)
            setDivider(drawableRes = R.drawable.bg_divider, showLastDivider = false)
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        menu.setGroupVisible(0, false)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_user)
        searchItem.isVisible = true

        searchView.onQueryTextSubmit { query ->
            //viewModel.onSearchQuerySubmit(query)
            searchView.clearFocus()
        }

        return super.onCreateOptionsMenu(menu)
    }
}