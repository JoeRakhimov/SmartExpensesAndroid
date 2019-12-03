package com.joerakhimov.smartexpenses.screen.main.profile

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import biz.kasual.materialnumberpicker.MaterialNumberPicker
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.ipakyulibank.mobile.util.permissions.MyPermissionChecker
import com.ipakyulibank.mobile.util.permissions.MyPermissionListener
import com.joerakhimov.smartexpenses.base.BaseFragment
import com.joerakhimov.smartexpenses.databinding.FragmentProfileBinding
import com.joerakhimov.smartexpenses.di.Injector
import com.joerakhimov.smartexpenses.extension.startActivityToOpenUrlInBrowser
import com.joerakhimov.smartexpenses.helper.transformation.CircleTransform
import com.joerakhimov.smartexpenses.screen.auth.AuthActivity
import com.joerakhimov.smartexpenses.screen.main.profile.model.ProfileInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

const val REQUEST_CODE_PICK_IMAGE = 1

class ProfileFragment : BaseFragment() {

    override fun getLayoutRes() = com.joerakhimov.smartexpenses.R.layout.fragment_profile

    private lateinit var viewModel: ProfileViewModel

    @Inject
    lateinit var permissionChecker: MyPermissionChecker

    init {
        Injector.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initProfileImage()
        observeToastMessage()
        observeIsOpen()
        observeProfileInfo()
    }

    private fun initProfileImage() {
        Picasso.get().load(com.joerakhimov.smartexpenses.R.drawable.profile)
            .transform(CircleTransform()).into(image_profile)
        image_profile.setOnClickListener {
            permissionChecker.checkWriteExternalStoragePermission(
                activity,
                object : MyPermissionListener {
                    override fun onAllow() {
                        dispatchImagePickIntent()
                    }

                    override fun onDeny() {}
                })
        }
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

    private fun observeToastMessage() {
        viewModel.toastMessage.observe(this, Observer { any ->
            var message = ""
            when (any) {
                is Int -> message = getString(any)
                is String -> message = any
            }
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        })
    }

    private fun observeProfileInfo() {
        viewModel.profileInfo.observe(this, Observer {
            if (it != null) initProfileSettings(it)
        })
    }

    private fun initProfileSettings(profileInfo: ProfileInfo) {
        recycler_profile_settings.layoutManager = LinearLayoutManager(context)

        val items = arrayListOf(
            ProfileScreenListItem(
                com.joerakhimov.smartexpenses.R.string.number_of_latest_spendings,
                View.OnClickListener {
                    showAmountPickerDialog(profileInfo.numLatestSpendings)
                }),
            ProfileScreenListItem(
                com.joerakhimov.smartexpenses.R.string.set_profile_color,
                View.OnClickListener {
                    if (profileInfo.color != null) showColorPickerDialog(profileInfo.color!!)
                }),
            ProfileScreenListItem(
                com.joerakhimov.smartexpenses.R.string.privacy,
                View.OnClickListener { context?.startActivityToOpenUrlInBrowser(profileInfo.privacyUrl) }),
            ProfileScreenListItem(
                com.joerakhimov.smartexpenses.R.string.terms_and_conditions,
                View.OnClickListener { context?.startActivityToOpenUrlInBrowser(profileInfo.termsAndConditionsUrl) }
            )
        )

        recycler_profile_settings.adapter = ProfileSettingsAdapter(items)
    }

    var dialog: AlertDialog? = null
    private fun showAmountPickerDialog(numLatestSpendings: Int?) {

        val numberPicker = MaterialNumberPicker.Builder(context!!)
            .minValue(5)
            .maxValue(10)
            .defaultValue(numLatestSpendings!!)
            .backgroundColor(Color.WHITE)
            .separatorColor(Color.TRANSPARENT)
            .textColor(Color.BLACK)
            .textSize(20f)
            .build()

        dialog = AlertDialog.Builder(context!!)
            .setTitle(com.joerakhimov.smartexpenses.R.string.choose_amount_for_recent_expenses)
            .setView(numberPicker)
            .setPositiveButton(getString(android.R.string.ok)) { _, _ ->
                viewModel.onAmountSelection(numberPicker.value)
            }
            .setNegativeButton(getString(android.R.string.cancel)) { _, _ ->
                dialog?.dismiss()
            }.create()
        dialog?.show()

    }

    private fun showColorPickerDialog(hex: String) {
        ColorPickerDialogBuilder
            .with(context)
            .setTitle(com.joerakhimov.smartexpenses.R.string.choose_profile_color)
            .initialColor(Color.parseColor(hex))
            .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
            .density(12)
            .setPositiveButton(com.joerakhimov.smartexpenses.R.string.save) { dialog, selectedColor, allColors ->
                viewModel.onColorSelection("#" + Integer.toHexString(selectedColor))
            }
            .setNegativeButton(com.joerakhimov.smartexpenses.R.string.cancel) { dialog, which -> }
            .build()
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(com.joerakhimov.smartexpenses.R.menu.profile_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            com.joerakhimov.smartexpenses.R.id.logout -> viewModel.onLogoutButtonClick()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeIsOpen() {
        viewModel.isOpen.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                startActivity(Intent(activity, AuthActivity::class.java))
            }
        })
    }

    private fun dispatchImagePickIntent() {
        //Create an Intent with action as ACTION_PICK
        val intent = Intent(Intent.ACTION_PICK)
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.type = "image/*"
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        // Launching the Intent
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (REQUEST_CODE_PICK_IMAGE == requestCode) {
                val selectedImage = data?.data
                Picasso.get().load(selectedImage).transform(CircleTransform()).into(image_profile)
            }
        }
    }

}
