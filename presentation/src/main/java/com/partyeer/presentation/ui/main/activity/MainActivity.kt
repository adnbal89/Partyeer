package com.partyeer.presentation.ui.main.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.ActivityMainBinding
import com.partyeer.presentation.ui.main.extension.inTransaction
import com.partyeer.presentation.ui.main.features.home.HomeFragment
import com.partyeer.presentation.ui.main.features.party.CreatePartyFragment
import com.partyeer.presentation.ui.main.features.party.SearchPartyFragment
import com.partyeer.presentation.ui.main.features.profile.ProfileFragment
import com.partyeer.presentation.ui.main.util.DialogManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //Fragments bound to bottom nav.
    private lateinit var homeFragment: HomeFragment
    private lateinit var searchPartyFragment: SearchPartyFragment
    private lateinit var createPartyFragment: CreatePartyFragment
    private lateinit var profileFragment: ProfileFragment

    //fragments array to arrange better navigation switch experience.
    private val fragments: Array<Fragment>
        get() = arrayOf(
            homeFragment,
            searchPartyFragment,
            createPartyFragment,
            profileFragment
        )

    private var selectedIndex = 0
    private val selectedFragment
        get() = fragments[selectedIndex]


    private fun selectFragment(selectedFragment: Fragment) {
        var transaction = supportFragmentManager.beginTransaction()
        fragments.forEachIndexed { index, fragment ->
            if (selectedFragment == fragment) {
                transaction = transaction.attach(fragment)
                selectedIndex = index
            } else
                transaction = transaction.detach(fragment)
        }
        transaction.commit()

        title = when (selectedFragment) {
            is HomeFragment -> getString(R.string.title_home)
            is SearchPartyFragment -> getString(R.string.title_search_party)
            is CreatePartyFragment -> getString(R.string.title_create_party)
            is ProfileFragment -> getString(R.string.title_profile)
            else -> ""
        }
    }


    @Inject
    lateinit var dialogManager: DialogManager

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        dialogManager.init(binding.composeView)

        //if fresh start
        if (savedInstanceState == null) {
            homeFragment = HomeFragment()
            searchPartyFragment = SearchPartyFragment()
            createPartyFragment = CreatePartyFragment()
            profileFragment = ProfileFragment()

            supportFragmentManager.inTransaction {
                add(
                    R.id.nav_host_fragment_content_main, homeFragment,
                    TAG_HOME_FRAGMENT
                )
                add(
                    R.id.nav_host_fragment_content_main, searchPartyFragment,
                    TAG_SEARCH_PARTY_FRAGMENT
                )
                add(
                    R.id.nav_host_fragment_content_main, createPartyFragment,
                    TAG_CREATE_PARTY_FRAGMENT
                )
                add(
                    R.id.nav_host_fragment_content_main, profileFragment,
                    TAG_PROFILE_FRAGMENT
                )
            }
        } else {
            homeFragment =
                supportFragmentManager.findFragmentByTag(TAG_HOME_FRAGMENT) as HomeFragment
            searchPartyFragment =
                supportFragmentManager.findFragmentByTag(TAG_SEARCH_PARTY_FRAGMENT) as SearchPartyFragment
            createPartyFragment =
                supportFragmentManager.findFragmentByTag(TAG_CREATE_PARTY_FRAGMENT) as CreatePartyFragment
            profileFragment =
                supportFragmentManager.findFragmentByTag(TAG_PROFILE_FRAGMENT) as ProfileFragment

            selectedIndex = savedInstanceState.getInt(KEY_SELECTED_INDEX, 0)

        }
        selectFragment(selectedFragment)

        binding.contentMain.bottomNav.setOnNavigationItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.nav_home -> homeFragment
                R.id.nav_search -> searchPartyFragment
                R.id.nav_add_party -> createPartyFragment
                R.id.nav_profile -> profileFragment
                else -> throw IllegalArgumentException("Unexpected itemId")
            }

            if (selectedFragment === fragment) {
                if (fragment is OnBottomNavigationFragmentReselectedListener) {
                    fragment.onBottomNavigationFragmentReselected()
                }
            } else {
                selectFragment(fragment)
            }
            true
        }


        /* binding.fab.setOnClickListener { view ->
             Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 .setAnchorView(R.id.fab)
                 .setAction("Action", null).show()
         }*/
    }

    override fun onBackPressed() {
        if (selectedIndex != 0) {
            binding.contentMain.bottomNav.selectedItemId = R.id.nav_home
        } else {
            super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_SELECTED_INDEX, selectedIndex)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    interface OnBottomNavigationFragmentReselectedListener {
        fun onBottomNavigationFragmentReselected()
    }
}

private const val TAG_HOME_FRAGMENT = "TAG_HOME_FRAGMENT"
private const val TAG_SEARCH_PARTY_FRAGMENT = "TAG_SEARCH_PARTY_FRAGMENT"
private const val TAG_CREATE_PARTY_FRAGMENT = "TAG_CREATE_PARTY_FRAGMENT"
private const val TAG_PROFILE_FRAGMENT = "TAG_PROFILE_FRAGMENT"
private const val KEY_SELECTED_INDEX = "KEY_SELECTED_INDEX"