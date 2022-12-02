package com.partyeer.presentation.ui.main.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.partyeer.presentation.databinding.ActivityUserProfileBinding
import com.partyeer.presentation.ui.main.base.BaseActivity
import com.partyeer.presentation.ui.main.features.user.UserProfileViewModel
import com.partyeer.presentation.ui.main.util.navigation.Navigator
import javax.inject.Inject

class UserProfileActivity : BaseActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    private val viewModel: UserProfileViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra("userName") ?: ""


        viewModel.userData.observe(this) { user ->
            Glide.with(this).load(user.profilePicture.preview)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(binding.imageViewProfile)

            binding.textViewProfileName.text = user.fullName
            binding.textViewPartyCountValue.text = user.invitedPartyIdMap.size.toString()
            binding.textViewFollowerCountValue.text = user.followerUserIdMap.size.toString()
            binding.textViewFollowingCountValue.text = user.followingUserIdMap.size.toString()
        }

        viewModel.getUserDetailByUserName(userName)

    }
}