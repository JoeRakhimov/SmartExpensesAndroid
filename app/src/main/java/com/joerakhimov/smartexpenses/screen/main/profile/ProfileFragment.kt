package com.joerakhimov.smartexpenses.screen.main.profile


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseFragment
import com.joerakhimov.smartexpenses.databinding.FragmentProfileBinding
import com.joerakhimov.smartexpenses.helper.transformation.CircleTransform
import com.joerakhimov.smartexpenses.screen.auth.AuthActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment() {

    override fun getLayoutRes() = com.joerakhimov.smartexpenses.R.layout.fragment_profile

    private lateinit var viewModel: ProfileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Picasso.get().load(com.joerakhimov.smartexpenses.R.drawable.profile)
            .transform(CircleTransform()).into(image_profile)

        recycler_profile_settings.layoutManager = LinearLayoutManager(context)

        val settingsItems = arrayListOf(
            "Notifications",
            "Select profile photo",
            "Number of latest spendings",
            "Privacy",
            "Terms and Conditions"
        )

        recycler_profile_settings.adapter = ProfileSettingsAdapter(settingsItems)

//        bind()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.getRoot()
    }

//    private fun bind() {
//        binding = DataBindingUtil.setContentView(activity, R.layout.fragment_profile)
//        binding.vm = viewModel
//        binding.lifecycleOwner = this
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                viewModel.onLogoutButtonClick()
                startActivity(Intent(activity, AuthActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
