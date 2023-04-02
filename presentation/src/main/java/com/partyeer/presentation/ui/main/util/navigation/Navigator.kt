package com.partyeer.presentation.ui.main.util.navigation

import android.app.Activity
import android.content.Intent
import com.google.firebase.auth.FirebaseUser
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.presentation.ui.main.activity.*
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class Navigator @Inject constructor(private val activity: Activity) {
    fun toLoginActivity(): NavigationCreator =
        NavigationCreator(activity).intent(Intent(activity, LoginActivity::class.java))

    fun toMainActivity(firebaseUser: FirebaseUser?): NavigationCreator {
        val intent = Intent(activity, MainActivity::class.java)
        intent.putExtra("firebaseUser", firebaseUser?.email?.substringBefore("@"))

        return NavigationCreator(activity).intent(intent)
    }

    fun toCratePartyActivity(): NavigationCreator =
        NavigationCreator(activity).intent(Intent(activity, CreatePartyActivity::class.java))

    fun toInviteeListActivity(): NavigationCreator =
        NavigationCreator(activity).intent(Intent(activity, InviteeListActivity::class.java))

    fun toPartyMapsActivity(): NavigationCreator =
        NavigationCreator(activity).intent(Intent(activity, PartyMapsActivity::class.java))

    fun toPartyDetailsActivity(party: Party?): NavigationCreator {
        val intent = Intent(activity, PartyDetailActivity::class.java)
        intent.putExtra("party", party)

        return NavigationCreator(activity).intent(intent)
    }

    fun toSameTaggedPartiesActivity(tag: String): NavigationCreator {
        val intent = Intent(activity, SameTaggedPartiesActivity::class.java)
        intent.putExtra("tag", tag)

        return NavigationCreator(activity).intent(intent)
    }
}
